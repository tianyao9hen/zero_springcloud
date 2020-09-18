import {request} from './request'

export function getOptions(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/getOptions',
            method: 'get',
            params: data
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}