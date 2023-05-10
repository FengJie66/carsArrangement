<template>
  <div>
    <el-form :inline="true" :model="queryVo" class="demo-form-inline">
      <el-form-item label="关键字">
        <el-input v-model="queryVo.search" placeholder="品牌|车型"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getPage">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="addCar">添加</el-button>
      </el-form-item>

    </el-form>
    <el-table
        :data="carList"
        tooltip-effect="dark"
        style="width: 100%">
      <el-table-column label="汽车图片" width="80">
        <template slot-scope="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.coverimagefilename"
              :preview-src-list="[scope.row.coverimagefilename]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column property="carbrand" label="品牌" ></el-table-column>
      <el-table-column property="cartype" label="车型" ></el-table-column>
      <el-table-column property="speed" label="速度" ></el-table-column>
      <el-table-column property="runsnumber" label="运行编号" ></el-table-column>
      <el-table-column property="tyrepressure" label="轮胎气压" ></el-table-column>
      <el-table-column property="seat" label="座位描述" ></el-table-column>
      <el-table-column property="usetime" label="使用时长" ></el-table-column>
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
      <el-form ref="form" :rules="rules" :model="carInfo" label-width="80px">
        <el-form-item label="图片">
          <el-upload
              class="avatar-uploader"
              :action="$store.state.home.baseUrl+'file/upload/'"
              :show-file-list="false"
              name="file"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="carInfo.coverimagefilename" :src="carInfo.coverimagefilename" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="品牌" prop="carbrand">
          <el-input type="text" v-model="carInfo.carbrand"></el-input>
        </el-form-item>
        <el-form-item label="车型" prop="cartype">
          <el-input type="text" v-model="carInfo.cartype"></el-input>
        </el-form-item>
        <el-form-item label="速度" prop="speed">
          <el-input type="text" v-model="carInfo.speed"></el-input>
        </el-form-item>
        <el-form-item label="运行编号" prop="runsnumber">
          <el-input type="text" v-model="carInfo.runsnumber"></el-input>
        </el-form-item>
        <el-form-item label="轮胎气压" prop="tyrepressure">
          <el-input type="text" v-model="carInfo.tyrepressure"></el-input>
        </el-form-item>
        <el-form-item label="座位描述" prop="seat">
          <el-input type="text" v-model="carInfo.seat"></el-input>
        </el-form-item>
        <el-form-item label="使用时长" prop="usetime">
          <el-input type="text" v-model="carInfo.usetime">
            <template slot="append">hours</template>
          </el-input>
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
import {getPage,update,del,add} from "@/api/car";

export default {
  name: "CarList",
  data(){
    return{
      carList:[],
      total:0,
      pages:0,
      queryVo:{
        current:1,
        size:10,
        search:''
      },
      carInfo:{},
      rules:{
        carbrand: [
          { required: true, message: '品牌不能为空', trigger: 'blur' }
        ],
        cartype: [
          { required: true, message: '车型不能为空', trigger: 'blur' }
        ],
        speed: [
          { required: true, message: '速度不能为空', trigger: 'blur' }
        ],
        runsnumber: [
          { required: true, message: '运行编号不能为空', trigger: 'blur' }
        ],
        tyrepressure: [
          { required: true, message: '轮胎气压不能为空', trigger: 'blur' }
        ],
        seat: [
          { required: true, message: '座位描述不能为空', trigger: 'blur' }
        ],
        usetime: [
          { required: true, message: '使用时长不能为空', trigger: 'blur' }
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
        this.carList = res.data.records;
        this.total = res.data.total;
        this.pages = res.data.pages;
        //处理页码超出范围的情况
        if(this.carList.length === 0 &&  //返回集合长度为0
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
      this.carInfo = JSON.parse(JSON.stringify(row))
      this.title = '编辑汽车信息'
      this.dialogEditOrAdd = true;
    },
    addCar(){
      this.carInfo = {
        carid:'',
        coverimagefilename:'http://localhost:8001/images/noImage.jpg'
      }
      this.title = '添加汽车信息'
      this.dialogEditOrAdd = true;

    },
    //关闭编辑弹窗
    closeDialog(){
      this.carInfo = {}
      this.dialogEditOrAdd = false
    },
    saveBtn(){
      this.$refs.form.validate((valid) =>{
        if(valid){
            if(this.carInfo.carid === ''){
              add(this.carInfo).then(res=>{
                if(res.code === 0){
                  this.$message.success(res.data);
                  this.getPage()
                  this.closeDialog();
                }
              })
            }else{
              update(this.carInfo).then(res=>{
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
      this.carInfo.coverimagefilename = res.data;
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
        del(row.carid).then(res=>{
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