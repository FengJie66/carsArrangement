import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from '@/router'
import store from "@/store";
import jsCookie from "js-cookie";

Vue.use(ElementUI);

//添加全局前置导航守卫
router.beforeEach((to, from, next) => {

  const token = jsCookie.get('token');
  if(!token && to.name!== 'login'){
    next({name:'login'})
  }else if(token && to.name=== 'login'){
    next({name:'home'})
  } else{ //token存在
    next();
  }

})


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
