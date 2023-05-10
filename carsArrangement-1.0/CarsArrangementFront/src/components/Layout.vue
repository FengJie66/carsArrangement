<template>
  <div>
    <el-container>
      <el-aside width="auto" height="auto">
        <CommonAside />
      </el-aside>
      <el-container>
        <el-header>
          <CommonHeader/>
        </el-header>
        <CommonTags/>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CommonAside from "@/components/CommonAside";
import CommonHeader from "@/components/CommonHeader";
import CommonTags from "@/components/CommonTags";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Layout",
  components: {CommonTags, CommonHeader, CommonAside}
}
</script>

<style scoped>
.el-header{
  padding: 0;
}
.el-main {
  height: calc(100vh - 125px);
}
</style>