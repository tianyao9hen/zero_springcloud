/**
 * Created by admin on 2020/9/2.
 */

import axios from 'axios';
import baseConstants from 'components/common/constants/Base.constants';

export function request(config){
    const instance = axios.create({
        baseURL: "http://localhost:8001",
        timeout: 5000,
    })

    //拦截器
    //拦截请求
    instance.interceptors.request.use(
        config => {
            //请求成功的拦截方法
            //config为http请求信息
            /*
             * 可能做的事情：
             * 1. 处理config中的一些不符合服务器要求的信息
             * 2. 在发送网络请求时，希望在界面显示一个请求的图标
             * 3. 某些网络请求(比如登陆)，必须携带一些特殊的信息
             * */
            const token = window.localStorage.getItem(baseConstants.TOKEN_STORAGE_NAME);
            if(token){
                config.headers[baseConstants.TOKEN_HEADER_NAME] = token;
                config.headers['Access-Control-Expose-Headers'] = baseConstants.TOKEN_HEADER_NAME;
            }

            return config //必须将config进行返回，这样才能继续执行请求
        },
        err => {
            //请求失败的拦截方法
        }
    );
    //拦截响应
    instance.interceptors.response.use(
        res => {
            //响应成功的拦截方法
            //res为服务器返回的结果对象
            //res.data为服务器返回的数据信息
            const token = res.headers[baseConstants.TOKEN_HEADER_NAME];
            if(token){
                window.localStorage.setItem(baseConstants.TOKEN_STORAGE_NAME,token);
            }
            //必须将结果返回，才能让回调函数处理返回的结果
            return res.data;
        },
        err =>{
            //响应失败的拦截方法
        }
    );

    //axios实例本身就是一个Promise对象，所以直接返回对象，
    //axios在查询成功时会调用.then，在失败时会调用.catch
    return instance(config)
}
