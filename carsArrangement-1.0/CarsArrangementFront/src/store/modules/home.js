const state = {
    isCollapse: false, //控制菜单的展开和收起
    tabList:[{
        "path":'/home',
        "label": "汽车列表",
        "name":'home',
        "icon":"user-solid"
    }],//面包屑数据
    baseUrl:'http://localhost:8001/',
};

//
const actions = {
};
//
const mutations = {
    resetTabList(state){
        console.log('state reset')
        state.tabList = [{
            "path":'/home',
            "label": "汽车列表",
            "name":'home',
            "icon":"user-solid"
        }];
    },
    collapseMenu(state){
        state.isCollapse = !state.isCollapse;
    },
    //更新面包屑
    selectMenu(state,item){
        //判断添加的数据是否为首页
        if(item.name !== 'home'){
            //在集合中遍历看添加的数据是否存在
            const index = state.tabList.findIndex(item1 => item1.name === item.name);
            //如果不存在 加入集合中
            if(index === -1){
                state.tabList.push(item)
            }
        }
    },
    closeMenu(state,item){
        const index = state.tabList.findIndex(val => val.name === item.name);
        state.tabList.splice(index,1);
    },
};
//
const getters = {};
export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters
}