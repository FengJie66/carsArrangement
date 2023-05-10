<template>
  <div>
    <el-menu
        default-active="1-4-1"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
    >
      <h3>{{isCollapse ? '汽车' : '汽车信息管理系统'}}</h3>
      <el-menu-item v-for="menu in noChildren" @click="clickMenu(menu)" :index="menu.name" :key="menu.name">
        <i :class="`el-icon-${menu.icon}`"></i>
        <span slot="title">{{ menu.label }}</span>
      </el-menu-item>

      <el-submenu v-for="menu in hasChildren" :index="menu.name" :key="menu.name">
        <template slot="title">
          <i :class="`el-icon-${menu.icon}`"></i>
          <span slot="title">{{menu.label }}</span>
        </template>
        <template v-for="child in menu.children">
          <el-menu-item @click="clickMenu(child)" :key="child.name" :index="child.name">
            <span slot="title">{{child.label}}</span>
          </el-menu-item>
        </template>

      </el-submenu>

    </el-menu>
  </div>
</template>

<script>
export default {
  name: "CommonAside",
  data() {
    return {
      menuList:[
        {
          "path":'/home',
          "label": "汽车列表",
          "name":'home',
          "icon":"s-home"
        },
        {
          "path":'/deviceList',
          "label": "设备列表",
          "name":'deviceList',
          "icon":"s-unfold"
        },
      ]
    };
  },
  methods: {
    clickMenu(item){
      if(this.$route.path !== item.path && !(this.$route.path === '/home' && item.path === '/')){
        this.$router.push(item.path);
      }
      this.$store.commit('home/selectMenu',item)
    }
  },
  computed:{
    isCollapse(){
      // return this.$store.state.home.isCollapse;
      return this.$store.state.home.isCollapse
    },
    noChildren(){
      return this.menuList.filter(item => !item.children)
    },
    hasChildren(){
      return this.menuList.filter(item => item.children)
    }
  }
}
</script>

<style scoped lang="less">
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el-menu{
    height:100vh;
    border-right:none;

    h3{
      color: #fff;
      text-align: center;
      line-height: 48px;
      font-size: 16px;
      font-weight: 400;
    }
  }


</style>
<style>
  html,body{
    padding: 0;
    margin: 0;
  }
</style>