import Vue from 'vue';
import Router from 'vue-router';
// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'

Vue.use(dataV)
Vue.use(Router);
// 解决功能菜单重复点击报错
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location)
}
export default new Router({
    routes:
        [
            {
                path: '/',
                redirect: 'homePage1'
            },
            {
                path: '/',
                component: resolve => require(['../components/common/Home.vue'], resolve),
                meta: {title: '自述文件'},
                children:
                    [
                        {
                            path: '/homePage1',
                            //component: resolve => require(['../views/Dashboard.vue'], resolve),
                            component: resolve => require(['../views/homePage/homePage1.vue'], resolve),
                            meta: {title: '系统首页'}
                        },
                        // 系统管理
                        {
                            path: '/dict',
                            component: resolve => require(['../views/sysmanager/Dict.vue'], resolve),
                            meta: {title: '字典管理'}
                        },
                        {
                            path: '/authority',
                            component: resolve => require(['../views/sysmanager/AuthManage.vue'], resolve),
                            meta: {title: '权限管理'}
                        },

                        /*{
                            path: '/user',
                            component: resolve => require(['../views/sysmanager/user.vue'], resolve),
                            meta: {title: '用户管理'}
                        },*/
                        /*{
                            path: '/systemLog',
                            component: resolve => require(['../views/sysmanager/systemLog.vue'], resolve),
                            meta: {title: '系统日志'}
                        },
                        {
                            path: '/params',
                            component: resolve => require(['../views/sysmanager/params.vue'], resolve),
                            meta: {title: '参数设置'}
                        },
                        {
                            path: '/databaseBackup',
                            component: resolve => require(['../views/sysmanager/databaseBackup.vue'], resolve),
                            meta: {title: '数据库备份'}
                        },*/

                        //房产管理
                        /*{
                            path: '/customerInfoManage',
                            component: resolve => require(['../views/propertyManagement/customerInfoManage/CustomerInfoList.vue'], resolve),
                            meta: {title: '业主信息'}
                        },
                        {
                            path: '/realEstateInformation',
                            component: resolve => require(['../views/propertyManagement/realEstateInformation.vue'], resolve),
                            meta: {title: '房产信息'}
                        },
                        {
                            path: '/instrumentMeter',
                            component: resolve => require(['../views/payManagement/instrumentMeter.vue'], resolve),
                            meta: {title: '仪表抄表'}
                        },*/

                        //车辆记录
                        /*{
                            path: '/carRecord/accessRecord',
                            component: resolve => require(['../views/carRecord/AccessRecord'], resolve),
                            meta: {title: '进出记录'}
                        },
                        {
                            path: '/carRecord/payRecord',
                            component: resolve => require(['../views/carRecord/PayRecord'], resolve),
                            meta: {title: '支付记录'}
                        },
                        {
                            path: '/carRecord/abnormalRecord',
                            component: resolve => require(['../views/carRecord/AbnormalRecord'], resolve),
                            meta: {title: '异常记录'}
                        },*/

                        //车辆授权
                        /*{
                            path: '/vehicleAuthorization/vehicleList',
                            component: resolve => require(['../views/vehicleAuthorization/VehicleList'], resolve),
                            meta: {title: '车辆列表'}
                        },
                        {
                            path: '/vehicleAuthorization/MonthlyCar',
                            component: resolve => require(['../views/vehicleAuthorization/MonthlyCar'], resolve),
                            meta: {title: '包月车'}
                        },
                        {
                            path: '/vehicleAuthorization/FreeCar',
                            component: resolve => require(['../views/vehicleAuthorization/FreeCar'], resolve),
                            meta: {title: '免费车'}
                        },
                        {
                            path: '/vehicleAuthorization/VisitingBus',
                            component: resolve => require(['../views/vehicleAuthorization/VisitingBus'], resolve),
                            meta: {title: '访客车辆'}
                        },
                        {
                            path: '/vehicleAuthorization/abnormalVehicle',
                            component: resolve => require(['../views/vehicleAuthorization/AbnormalVehicle'], resolve),
                            meta: {title: '异常车辆'}
                        },*/

                        //车场配置
                        /*{
                            path: '/parkManagement/basicConfig',
                            component: resolve => require(['../views/parkManagement/BasicConfig'], resolve),
                            meta: {title: '基础信息'}
                        },
                        {
                            path: '/parkManagement/areaManagement',
                            component: resolve => require(['../views/parkManagement/AreaManagement'], resolve),
                            meta: {title: '区域管理'}
                        },
                        {
                            path: '/parkManagement/addArea',
                            component: resolve => require(['../views/parkManagement/AddArea'], resolve),
                            meta: {title: '新增区域'}
                        },
                        {
                            path: '/parkManagement/editArea',
                            name: 'editArea',
                            component: resolve => require(['../views/parkManagement/EditArea'], resolve),
                            meta: {title: '编辑区域'}
                        },*/

                        //财务对账
                        /*{
                            path: '/financialAccount/reportForm',
                            name: 'reportForm',
                            component: resolve => require(['../views/financialAccount/ReportForm'], resolve),
                            meta: {title: '财务报表'}
                        },
                        {
                            path: '/financialAccount/invoiceInfo',
                            name: 'reportForm',
                            component: resolve => require(['../views/financialAccount/InvoiceInfo'], resolve),
                            meta: {title: '电子发票'}
                        },*/

                        //优惠减免
                        /*{
                            path: '/couponManagement/authManagement',
                            name: 'authManagement',
                            component: resolve => require(['../views/couponManagement/AuthManagement'], resolve),
                            meta: {title: '授权管理'}
                        },
                        {
                            path: '/couponManagement/businessHandler',
                            name: 'businessHandler',
                            component: resolve => require(['../views/couponManagement/BusinessHandler'], resolve),
                            meta: {title: '业务办理'}
                        },*/

                        //统计中心
                        /*{
                            path: '/statisticalCenter/trafficStatistics',
                            component: resolve => require(['../views/statisticalCenter/TrafficStatistics'], resolve),
                            meta: {title: '流量统计'}
                        },
                        {
                            path: '/statisticalCenter/saturationStatistics',
                            component: resolve => require(['../views/statisticalCenter/SaturationStatistics'], resolve),
                            meta: {title: '饱和度统计'}
                        },*/

                        //监控中心
                        /*{
                            path: '/monitoringCenter/chargeMonitoring',
                            component: resolve => require(['../views/monitoringCenter/ChargeMonitoring'], resolve),
                            meta: {title: '收费监控'}
                        },*/
                    ]
            },
            /*{
                path: '/monitoringCenter/monitoringCenterPage',
                component: resolve => require(['../views/monitoringCenter/MonitoringCenterPage'], resolve),
                meta: {title: '监控中心'}
            },*/
            {
                path: '/login',
                component: resolve => require(['../views/common/Login.vue'], resolve)
            },
            {
                path: '/desktop',
                component: resolve => require(['../views/homePage/HomeDeskTop.vue'], resolve)
            },
            {
                path: '/admin',
                component: resolve => require(['../views/common/admin.vue'], resolve)
            },
            {
                path: '/tabs',
                component: resolve => require(['../views/common/Tabs.vue'], resolve)
            },
            {
                path: '/noPrivilege',
                component: resolve => require(['../views/common/403.vue'], resolve)
            },
            {
                path: '/Permission',
                component: resolve => require(['../views/common/Permission.vue'], resolve)
            },
            {
                path: '*',
                component: resolve => require(['../views/common/404.vue'], resolve)
            }
        ]
})
