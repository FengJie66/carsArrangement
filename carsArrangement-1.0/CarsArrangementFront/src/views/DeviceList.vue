<template>
  <div>
    <el-form :inline="true" :model="queryVo" class="demo-form-inline">
      <el-form-item label="关键字">
        <el-input v-model="queryVo.search" placeholder="设备名称|位置"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getPage">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="addDevice">添加</el-button>
      </el-form-item>

    </el-form>
    <el-table
        :data="deviceList"
        tooltip-effect="dark"
        style="width: 100%">
      <el-table-column label="设备图片" width="80">
        <template slot-scope="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.coverimagefilename"
              :preview-src-list="[scope.row.coverimagefilename]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column property="temperture" label="温度" ></el-table-column>
      <el-table-column property="humidity" label="湿度" ></el-table-column>
      <el-table-column property="devicename" label="设备名称" ></el-table-column>
      <el-table-column property="deviceworkplace" label="设备位置" ></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="editDialog(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
          <el-button @click="deleteById(scope.row)"  type="danger" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          layout="prev, pager, next,total,sizes"
          :page-size="queryVo.size"
          :total="total"
          :page-count="pages"
          :page-sizes="[5,10,20]"
      >
      </el-pagination>
    </div>
    <!--    添加或编辑弹窗-->
    <el-dialog :title="title" :visible.sync="dialogEditOrAdd" @close="closeDialog">
      <el-form ref="form" :rules="rules" :model="deviceInfo" label-width="80px">
        <el-form-item label="设备图片">
          <el-upload
              class="avatar-uploader"
              :action="$store.state.home.baseUrl+'file/upload/'"
              :show-file-list="false"
              name="file"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="deviceInfo.coverimagefilename" :src="deviceInfo.coverimagefilename" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="温度" prop="temperture">
          <el-input type="text" v-model="deviceInfo.temperture">
            <template slot="append">℃</template>
          </el-input>
        </el-form-item>
        <el-form-item label="湿度" prop="humidity">
          <el-input type="text" v-model="deviceInfo.humidity">
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <el-form-item label="设备名称" prop="devicename">
          <el-input type="text" v-model="deviceInfo.devicename"></el-input>
        </el-form-item>
        <el-form-item label="设备位置" prop="deviceworkplace">
          <el-input type="text" v-model="deviceInfo.deviceworkplace"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="saveBtn">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {getPage,update,del,add} from "@/api/device";

export default {
  name: "DeviceList",
  data(){
    return{
      deviceList:[],
      total:0,
      pages:0,
      queryVo:{
        current:1,
        size:10,
        search:''
      },
      deviceInfo:{},
      rules:{
        temperture: [
          { required: true, message: '设备温度不能为空', trigger: 'blur' }
        ],
        humidity: [
          { required: true, message: '设备湿度不能为空', trigger: 'blur' }
        ],
        devicename: [
          { required: true, message: '设备名称不能为空', trigger: 'blur' }
        ],
        deviceworkplace: [
          { required: true, message: '设备位置不能为空', trigger: 'blur' }
        ],
      },
      title:'',
      dialogEditOrAdd:false
    }
  },
  methods:{
    handleSizeChange(val) {
      this.queryVo.size = val
      this.getPage();
    },
    handleCurrentChange(val) {
      this.queryVo.current = val
      this.getPage();
    },
    getPage() {
      getPage(this.queryVo).then(res=>{
        this.deviceList = res.data.records;
        this.total = res.data.total;
        this.pages = res.data.pages;
        //处理页码超出范围的情况
        if(this.deviceList.length === 0 &&  //返回集合长度为0
            this.queryVo.current > this.pages &&  //当前页超过了总页数
            this.queryVo.current !== 1){  //当前页为已经为1时除外
          this.queryVo.current = 1;
          this.getPage()
        }

      })
    },
    //*************************编辑*********************************
    //编辑弹窗
    editDialog(row){
      this.deviceInfo = JSON.parse(JSON.stringify(row))
      this.title = '编辑设备信息'
      this.dialogEditOrAdd = true;
    },
    addDevice(){
      this.deviceInfo = {
        deviceid:'',
        coverimagefilename:'http://localhost:8001/images/noImage.jpg'
      }
      this.title = '添加设备信息'
      this.dialogEditOrAdd = true;

    },
    //关闭编辑弹窗
    closeDialog(){
      this.deviceInfo = {}
      this.dialogEditOrAdd = false
    },
    saveBtn(){
      this.$refs.form.validate((valid) =>{
        if(valid){
          if(this.deviceInfo.deviceid === ''){
            add(this.deviceInfo).then(res=>{
              if(res.code === 0){
                this.$message.success(res.data);
                this.getPage()
                this.closeDialog();
              }
            })
          }else{
            update(this.deviceInfo).then(res=>{
              if(res.code === 0){
                this.$message.success(res.data);
                this.getPage()
                this.closeDialog();
              }
            })
          }
        }
      })
    },
    handleAvatarSuccess(res, file) {
      console.log(res)
      this.deviceInfo.coverimagefilename = res.data;
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    },
    //****************************************************************
    //删除
    deleteById(row){
      this.$confirm( `确定删除嘛？`, '提示', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确认',
        cancelButtonText: '再想想'
      }).then(() => {
        //确认按钮  批量删除
        del(row.deviceid).then(res=>{
          if(res.code === 0){
            this.$message.success(res.data)
            this.getPage()
          }
        })

      })
    }
  },
  created() {
    this.getPage()
  }
}
</script>

<style scoped>
.avatar {
  width: 150px;
  height: 150px;
  display: block;
}
</style>