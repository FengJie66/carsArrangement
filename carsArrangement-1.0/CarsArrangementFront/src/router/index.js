import Vue from 'vue'
import Router from 'vue-router'
import Layout from "@/components/Layout";

Vue.use(Router)


export const constantRoutes = [
    {
        path:'/',
        component:Layout,
        name:'main',
        redirect:'/home',
        children:[
            {
                path: '/home',
                name:'home',
                component: () => import('@/views/CarList'),
            },
            {
                path: '/deviceList',
                name:'deviceList',
                component: () => import('@/views/DeviceList'),
            },
        ]
    },
    {
        path: '/login',
        name:'login',
        component: () => import('@/views/Login'),
    }
]


const createRouter = () => new Router({
    //other operation
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router