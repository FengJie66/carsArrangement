<template>
  <div class="common-header">
    <div class="left">
      <el-button style="margin-right: 20px;" icon="el-icon-menu" size="mini" @click="handleMenu"></el-button>

      <!--面包屑-->
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="item in tags" :key="item.name" :to="{ path: item.path }">{{item.label}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="right">
      <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link">
          {{userInfo.username}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
          <el-dropdown-item command="logout">注销</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="form" :rules="rules">
        <el-form-item label="新密码" label-width="120px"  prop="password">
          <el-input show-password v-model="form.password" placeholder="请输入新密码" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" label-width="120px" prop="password">
          <el-input show-password v-model="form.confirmPwd" placeholder="请输入确认密码" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import jsCookie from "js-cookie";
import {updatePwd} from "@/api/user";

export default {
  name: "CommonHeader",
  data(){
    return{
      dialogFormVisible : false,
      form:{},
      userInfo:{},
      rules:{
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  mounted(){
    this.getUserInfo();

  },
  methods:{
    getUserInfo(){
      // getInfo().then(res=>{
      //   this.userInfo = res.data
      // })
      this.userInfo = { username:'admin'}
    },
    handleMenu(){
      this.$store.commit('home/collapseMenu');
    },
    handleCommand(command){
      if(command === 'logout'){
        this.logout();
      }
      if(command === 'updatePassword'){
        //修改密码
        this.dialogFormVisible = true
      }
    },
    resetForm(){
      this.form = {};
      this.dialogFormVisible = false;
    },
    submit(){
      this.$refs.form.validate((valid)=>{
        if(valid){
          if(this.form.password !== this.form.confirmPwd){
            this.$message.error('两次密码不一致');
            return;
          }
          // var re =/^(?=.*[a-z])(?=.*\d)[^]{6,20}$/;
          // var result =  re.test(this.form.password);
          // if(!result) {
          //   this.$message.error('密码不符合规则');
          //   return;
          // }
          // 密码一致 发送请求
          updatePwd(this.form.password).then(res=>{
            if(res.code === 0){
              this.$message.success(res.data)
            }
          })

        }
      })
    },
    logout(){
      jsCookie.remove('token')
      this.$store.commit('home/resetTabList');
      this.$message.success('注销成功')
      this.$router.push({name:'login'})
    },
  },
  computed:{
    tags(){
      return this.$store.state.home.tabList;
    }
  },

}
</script>

<style scoped lang="less">
    .common-header{
      background-color:#333;
      height: 60px;
      display: flex;
      justify-content: space-between;
      align-items: center;

      .left{
        padding-left:20px;
        display: flex;
        align-items: center;
        /deep/ .el-breadcrumb__item{
          .el-breadcrumb__inner{
            font-weight: normal;
            &.is-link{
              color: #666;
            }
          }
          &:last-child{
            .el-breadcrumb__inner{
              color:#fff;
            }
          }
        }


        .nav{
          color: #fff;
          margin-left:10px;
          font-size: 14px;
        }
      }

      .right{
        margin-right: 20px;

        .el-dropdown-link {
          cursor: pointer;
          color: #fff;
        }
        .el-icon-arrow-down {
          font-size: 12px;
        }
      }
    }
</style>