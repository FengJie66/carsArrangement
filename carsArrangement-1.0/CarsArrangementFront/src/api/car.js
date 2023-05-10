import requests from "@/utils/request";
export const add = (data) =>{
    return requests({
        url:'/car',
        method:'post',
        data:data
    })
}
export const update = (data) =>{
    return requests({
        url:'/car',
        method:'put',
        data:data
    })
}
export const del = (id) =>{
    return requests({
        url:'/car/'+id,
        method:'delete'
    })
}

export const getPage = (data) =>{
    return requests({
        url:'/car/getPage',
        method:'post',
        data:data
    })
}


