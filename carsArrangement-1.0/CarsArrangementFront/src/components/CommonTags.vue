<template>
  <div class="tags">
    <el-tag
        v-for="(item,index) in tags"
        :key="item.name"
        :closable="item.name !== 'home'"
        :effect="$route.name === item.name ? 'dark' : 'plain'"
        @click="changeMenu(item)"
        @close="handleClose(item,index)"
        size="small"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<script>
export default {
  name: "CommonTags",
  computed:{
    tags(){
      return this.$store.state.home.tabList;
    }
  },
  methods:{
    changeMenu(item){
      if(this.$route.path !== item.path && !(this.$route.path === '/home' && item.path === '/')){
        // this.$router.push({name:item.name})
        this.$router.push(item.path)
      }
    },
    handleClose(item,index){
      console.log(item,index)
      this.$store.commit('home/closeMenu',item);
      if(item.name !== this.$route.name){
        return
      }
      //删除的是最后一项
      if(index === this.tags.length){
        this.$router.push(this.tags[index-1].path)
      }else{ //删除的是中间的一项 路由向后移动一个
        this.$router.push(this.tags[index].path)
      }
    }
  }
}
</script>

<style scoped lang="less">
  .tags{
    padding:20px;
    .el-tag{
      margin-right: 15px;
      cursor:pointer;
    }
  }

</style>