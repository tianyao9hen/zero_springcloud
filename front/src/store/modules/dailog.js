const state = {// 初始化状态
    showUser: false,
    userType: "",
};
const getters = {
    isShow(state) {
        return state.showUser;
    },
}
const mutations = { // 处理状态
    hide(state) {
        state.showUser = false;
    },
    show(state) {
        state.showUser = true;
    },
}
const actions = { // 提交改变后的状态
    showDailog({ commit }) {
        commit('show');//提交改变后的state.count值
    },
    hideDailog({ commit }) {
        commit('hide');//提交改变后的state.count值
    },
}
export default {
    namespaced: true,
    getters,
    mutations,
    actions,
    state
}