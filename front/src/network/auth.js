/**
 * 鉴权模块请求
 * Created by admin on 2020/9/2.
 */

import {request} from './request'

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