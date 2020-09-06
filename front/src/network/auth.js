/**
 * 鉴权模块请求
 * Created by admin on 2020/9/2.
 */

import {request} from './request'

/*
* 登陆
* */
export function login(username,password){
    return request({
        url: '/auth/login',
        method: 'post',
        data: {
            username,
            password
        }
    })
}

/*
* 根据token获取用户信息
* */
export function getUserEntityByToken(){
    return request({
        url: '/auth/getUserEntityByToken',
        method: 'get',
    })
}

/*
* 退出登陆
* */
export function logout(){
    return request({
        url: '/auth/logout',
        method: 'get',
    })
}