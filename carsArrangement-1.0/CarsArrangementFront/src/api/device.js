import requests from "@/utils/request";
export const add = (data) =>{
    return requests({
        url:'/device',
        method:'post',
        data:data
    })
}
export const update = (data) =>{
    return requests({
        url:'/device',
        method:'put',
        data:data
    })
}
export const del = (id) =>{
    return requests({
        url:'/device/'+id,
        method:'delete'
    })
}

export const getPage = (data) =>{
    return requests({
        url:'/device/getPage',
        method:'post',
        data:data
    })
}


