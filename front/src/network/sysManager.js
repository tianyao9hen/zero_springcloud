import {request} from './request'

export function queryPageDict(formData){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/queryPage',
            method: 'get',
            params : formData
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

export function selectDictGroup(){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/selectGroup',
            method: 'get',
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

export function insertDict(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/insertDict',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

export function updateDict(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/updateDict',
            method: 'put',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

export function delDict(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/dict/delDict',
            method: 'delete',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}