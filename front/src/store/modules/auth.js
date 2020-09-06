import {login,getUserEntityByToken,logout} from 'network/auth'
import baseComstants from 'components/common/constants/Base.constants.js'

const state = {
    userEntity: null,
    propertyMenu: null,
}

const getters = {
    getUserEntity(state){
        return state.userEntity;
    },
    getToken(state){
        return localStorage.getItem(baseComstants.TOKEN_STORAGE_NAME);
    },
    getPropertyMenu(state) {
        return state.propertyMenu;
    }
}

const mutations = {
    setUserEntity(state,model){
        state.userEntity = model;
    },
    setToken(state,model){
        state.token = model;
    },
    setPropertyMenu(state,model){
        state.propertyMenu = model;
    }
}

const actions = {
    /*
    * 登陆
    * */
    login(context,data){
        return new Promise((resolve,reject) => {
            login(data.username,data.password).then(res => {
                if(res.success){
                    context.commit('setUserEntity',res.result);
                    context.commit('setPropertyMenu',res.result.authorities);
                    window.localStorage.setItem(baseComstants.TOKEN_STORAGE_NAME,res.result.token);
                }
                resolve(res);
            }).catch(err => {
                reject(err);
            })
        })
    },
    /*
    * 退出
    * */
    logout(context){
        return new Promise((resolve,reject) => {
            const token = window.localStorage.getItem(baseComstants.TOKEN_STORAGE_NAME);
            if(token){
                logout().then(res => {
                    resolve(res);
                }).catch(err => {
                    resolve(err);
                })
            }else{
                resolve({})
            }
        })
    },
    /*
    * 通过token得到userEntity
    * */
    getUserEntityByToken(context){
        return new Promise((resolve,reject) => {
            if(!context.getters.getUserEntity || !context.getters.getToken){
                getUserEntityByToken().then(res => {
                    console.log("net");
                    if(res.success){
                        context.commit('setUserEntity',res.result);
                        context.commit('setPropertyMenu',res.result.authorities);
                        resolve(res)
                    }else{
                        reject(res)
                    }
                })
            }else {
                resolve({
                    success: true,
                    result: context.getters.getUserEntity,
                })
            }
        })
    },
}

export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions,
}