import Vue from 'vue'
import Vuex from 'vuex'
import dailog from './modules/dailog'
import auth from './modules/auth'

Vue.use(Vuex);
export default () => {
    const store = new Vuex.Store({
        state: {
            userType: undefined,
            propertyMenu: [
                {"icon": "el-icon-lx-home", "href": "/homePage1", "name": "系统首页", "id": "800201904004", "label": ""},
                //车辆记录
                {
                    "icon": "el-icon-notebook-2",
                    "name": "车辆记录",
                    "href": "/carRecord",
                    "id": "802019091604450001",
                    "label": "",
                    "childs":
                        [
                            {"name": "进出记录", "href": "/carRecord/accessRecord", "seq": "5", "isShow": "Y"},
                            {"name": "支付记录", "href": "/carRecord/payRecord", "seq": "6", "isShow": "Y"},
                            {"name": "异常记录", "href": "/carRecord/abnormalRecord", "seq": "6", "isShow": "Y"},
                        ],
                    "seq": "7001"
                },
                //车辆授权
                {
                    "icon": "el-icon-mouse",
                    "name": "车辆授权",
                    "href": "/vehicleAuthorization",
                    "id": "802019011604450001",
                    "label": "",
                    "childs":
                        [
                            {"name": "车辆列表", "href": "/vehicleAuthorization/vehicleList", "seq": "1", "isShow": "Y"},
                            {"name": "包月车", "href": "/vehicleAuthorization/monthlyCar", "seq": "1", "isShow": "Y"},
                            {"name": "免费车", "href": "/vehicleAuthorization/freeCar", "seq": "1", "isShow": "Y"},
                            {"name": "访客车辆", "href": "/vehicleAuthorization/visitingBus", "seq": "1", "isShow": "Y"},
                            {
                                "name": "异常车辆",
                                "href": "/vehicleAuthorization/abnormalVehicle",
                                "seq": "1",
                                "isShow": "Y"
                            },

                        ],
                    "seq": "1103"
                },
                //财务对账
                {
                    "icon": "el-icon-document",
                    "name": "财务对账",
                    "href": "/financialAccount",
                    "id": "802019011604450002",
                    "label": "",
                    "childs":
                        [
                            {"name": "财务报表", "href": "/financialAccount/reportForm", "seq": "1", "isShow": "Y"},
                            {"name": "电子发票", "href": "/financialAccount/invoiceInfo", "seq": "1", "isShow": "Y"},

                        ],
                    "seq": "1105"
                },
                //优惠减免
                {
                    "icon": "el-icon-s-ticket",
                    "name": "优惠减免",
                    "href": "/couponManagement",
                    "id": "802019011604450002",
                    "label": "",
                    "childs":
                        [
                            {"name": "授权管理", "href": "/couponManagement/authManagement", "seq": "1", "isShow": "Y"},
                            {"name": "业务办理", "href": "/couponManagement/businessHandler", "seq": "1", "isShow": "Y"},

                        ],
                    "seq": "1106"
                },
                //车场配置
                {
                    "icon": "el-icon-help",
                    "name": "车场配置",
                    "href": "/parkManagement",
                    "id": "802019011604450002",
                    "label": "",
                    "childs":
                        [
                            {"name": "基础信息", "href": "/parkManagement/basicConfig", "seq": "1", "isShow": "Y"},
                            {"name": "区域管理", "href": "/parkManagement/areaManagement", "seq": "1", "isShow": "Y"},

                        ],
                    "seq": "1104"
                },
                //统计中心
                {
                    "icon": "el-icon-s-data",
                    "name": "统计中心",
                    "href": "/parkManagement",
                    "id": "802019011604450002",
                    "label": "",
                    "childs":
                        [
                            {"name": "流量统计", "href": "/statisticalCenter/trafficStatistics", "seq": "1", "isShow": "Y"},
                            {
                                "name": "饱和度统计",
                                "href": "/statisticalCenter/saturationStatistics",
                                "seq": "1",
                                "isShow": "Y"
                            },

                        ],
                    "seq": "1107"
                },
                //监控中心
                {
                    "icon": "el-icon-video-camera",
                    "name": "监控中心",
                    "href": "/parkManagement",
                    "id": "802019011604450002",
                    "label": "",
                    "childs":
                        [
                            {"name": "收费监控", "href": "/monitoringCenter/chargeMonitoring", "seq": "1", "isShow": "Y"},
                            {
                                "name": "监控中心",
                                "href": "/monitoringCenter/monitoringCenterPage",
                                "seq": "1",
                                "isShow": "Y"
                            },
                        ],
                    "seq": "1108"
                },
                //系统管理
                {
                    "icon": "el-icon-lx-settings", "name": "系统管理", "id": "802019091604450001", "label": "",
                    "childs":
                        [
                            {"name": "用户管理", "href": "/user", "seq": "5", "isShow": "Y"},
                            {"name": "字典管理", "href": "/dict", "seq": "6", "isShow": "Y"},
                            {"name": "参数设置", "href": "/params", "seq": "6", "isShow": "Y"},
                            {"name": "数据库备份", "href": "/databaseBackup", "seq": "6", "isShow": "Y"},
                            {"name": "系统日志", "href": "/systemLog", "seq": "6", "isShow": "Y"},
                        ],
                    "seq": "1"
                },
            ],
        },
        mutations: { // 处理状态
            Set_User_Type(state, model) {
                state.userType = model;
            }
        },
        actions: { // 提交改变后的状态
            setUserType({commit}, model) {
                commit('Set_User_Type', model);
            }
        },
        getters: {
            getUserType(state) {
                return state.userType;
            },
            getPropertyMenu(state) {
                return state.propertyMenu
            },

        },
        modules: {
            auth,
            dailog,
        },
    });
    return store;
}
