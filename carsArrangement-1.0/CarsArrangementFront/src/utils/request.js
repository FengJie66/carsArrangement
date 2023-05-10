//对axios进行二次封装
import axios from "axios";
import {getCookies} from "@/utils/auth";
import {Message} from "element-ui";
import nprogress from 'nprogress';
import "nprogress/nprogress.css"
// console.log(nprogress)
//start:进度条开始 done：进度条结束
//利用axios对象的方法create，去创建一个axios示例
const requests = axios.create({
    //配置对象
    //基础路径，发送请求的时候，路径当中会出现api
    baseURL:"http://localhost:8001/",
    //代表请求超时的时间5s
    timeout:5000,
});
//请求拦截器
requests.interceptors.request.use((config)=>{
    //进度条开始
    nprogress.start();
    //config：配置对象，对象里面有一个属性很重要，headers请求头
    const token = getCookies('token');
    if(token){
        config.headers['token'] = token
    }
    return config;
})
//响应拦截器
requests.interceptors.response.use(
    (res)=>{
        //进度条结束
        nprogress.done();
        if(res.data.code === 1){
            Message({
                type:'error',
                message:res.data.msg,
                time:3000
            })
        }
        return res.data;
    },
    (error)=>{
        Promise.reject(error);
    });


export default requests;