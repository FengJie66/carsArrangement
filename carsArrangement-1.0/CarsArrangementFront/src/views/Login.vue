<template>
  <div class="login-container">
    <el-form :model="form" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
      <h3 class="login-title">系统登录</h3>
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" show-password v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">登录</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {setCookies} from '@/utils/auth'
import {login} from '@/api/user'
export default {
  name: "Login",
  data(){
    return{
      form:{
        username:'admin',
        password:'abc123'
      },
      rules:{
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请选择密码', trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    submitForm() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          login(this.form).then((res) =>{
            console.log(res)
            if(res.code === 0 ){
                 //成功
                            const token = res.data;
                            //保存  20分钟
                            this.$message.success('登录成功，正在跳转');
                            setCookies('token',token,20)
                            this.resetForm();
                            this.$router.push({name: 'home'})
            }
          })
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.loginForm.resetFields();
    }
  }
}
</script>

<style scoped lang="less">
body{
  margin:0;
  padding:0;
  background-color: #fff;
}
.login-container{
  width: 350px;
  border: 1px solid #eaeaea;
  margin:180px auto;
  padding: 35px 35px 15px 15px;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  h3{
    margin-left: 140px;
    margin-bottom: 30px;
    color: #505458;

  }
}
.login-title{

}

</style>