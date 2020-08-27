import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
import ElementUI from 'element-ui';
// import TreeTable from '@weilan/el-tree-table'
//import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import './assets/css/theme-green/index.css';       // 浅绿色主题
import './assets/css/icon.css';
import './components/common/directives';
import message from './components/common/message';
import './styles/index.scss';
import "babel-polyfill";
import { quillEditor } from 'vue-quill-editor';
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
import store from './store';
import pagination from './components/pagination/Pagination'
import webTemplate from './components/webTemplate/WebTemplate'
import dialogTemplate from './components/webTemplate/DialogTemplate'
import baseConstants from './components/common/constants/Base.constants'
import BaiduMap from 'vue-baidu-map';

////import Mock from './mock/mock'
Vue.config.productionTip = false
Vue.use(message);
Vue.use(ElementUI, {
    size: 'small'
});
Vue.prototype.BaseConstants = baseConstants;
Vue.use(BaiduMap, {
    // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
    ak: baseConstants.BAIDU_MAP_KEY
})
Vue.component('pagination', pagination);
Vue.component('webTemplate',webTemplate);
Vue.component('dialogTemplate',dialogTemplate);
// Vue.use(TreeTable)
Vue.prototype.$axios = axios;
//Mock.bootstrap();
//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const role = sessionStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin' ? next() : next('/403');
    } else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
})


new Vue({
    router,
    store,//注册store(这可以把 store 的实例注入所有的子组件)
    render: h => h(App)
}).$mount('#app')



