import requests from "@/utils/request";
export const login = (data) =>{
    return requests({
        url:'/admin/login',
        method:'post',
        data:data
    })
}
export const updatePwd = (pwd) =>{
    return requests({
        url:'/admin/'+pwd,
        method:'put'
    })
}

