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

//查询权限列表
export function queryListAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/queryList',
            method: 'get',
            params: data
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//根据角色查询权限列表
export function queryListRoleAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/queryRoleAuth',
            method: 'get',
            params: data
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//查询可以选择的父权限
export function queryListPAuth(){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/queryListPAuth',
            method: 'get',
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//新增权限
export function insertAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/insertAuth',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//修改权限
export function updateAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/updateAuth',
            method: 'put',
            data,
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

//删除权限
export function delAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/authority/delAuth',
            method: 'delete',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//查询角色
export function queryPageRole(formData){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/queryPage',
            method: 'get',
            params : formData
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//查询全部角色
export function queryListRole(formData){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/queryList',
            method: 'get',
            params : formData
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//查询指定用户的角色
export function queryListUserRole(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/queryListUserRole',
            method: 'get',
            params : data
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//新增角色
export function insertRole(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/insertRole',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//修改角色
export function updateRole(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/updateRole',
            method: 'put',
            data,
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

//删除角色
export function delRole(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/delRole',
            method: 'delete',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//新增角色权限对应关系
export function insertRoleAuth(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/role/insertRoleAuth',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//查询用户
export function queryPageUser(formData){
    return new Promise((resolve,reject) => {
        request({
            url: '/user/queryPage',
            method: 'get',
            params : formData
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//新增用户
export function insertUser(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/user/insertUser',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//新增用户角色
export function insertUserRole(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/user/insertUserRole',
            method: 'post',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}

//修改用户
export function updateUser(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/user/updateUser',
            method: 'put',
            data,
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

//删除用户
export function delUser(data){
    return new Promise((resolve,reject) => {
        request({
            url: '/user/delUser',
            method: 'delete',
            data,
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    })
}