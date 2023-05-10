import Vue from 'vue';
import Vuex from 'vuex';
//需要使用插件一次
Vue.use(Vuex);
//引入小仓库
import home from './modules/home';


//对外暴露一个store类的实例
export default new Vuex.Store({
    modules:{
        home,
    }
});