<template>
    <div>
        <web-template :web-name="webName" :table-data="tableData" @change-page="queryPage" self>
            <!-- 搜索-->
            <div slot="searchForm">
                <el-form layout="inline" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区域选择" labelWidth="80px">
                                <el-select v-model="formData.areaSelection" filterable clearable placeholder="请选择区域"
                                           style="width: 100%">
                                    <el-option v-for="item in areaSelection" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="时间粒度" labelWidth="80px">
                                <el-input v-model="formData.timeGranularity" clearable placeholder="请输入车牌号"
                                          style="width: 100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="公司" labelWidth="80px">
                                <el-date-picker
                                        v-model="formData.queryTime"
                                        type="daterange"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期" style="width: 100%">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5" style="text-align:right">
                            <el-button type="primary" @click="queryPage(1,tableData.pageSize)">查询</el-button>
                            <el-button>重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="selfBody">
                <!-- 出入场流量               -->
                <div>
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-card style="margin-top: 20px;width: 100%;">
                                <el-row>
                                    <el-col :span="4" :offset="7">
                                        <img src="../../assets/img/admission.png"
                                             style="float: right;width: 70%;height: 70%;">
                                    </el-col>
                                    <el-col :span="4" :offset="1" style="margin-top: 1%;">
                                        <el-row>
                                            <h2 style="float: left">入场流量</h2>
                                        </el-row>
                                        <el-row>
                                            <h2>{{admissionFlow}}</h2>
                                        </el-row>
                                    </el-col>
                                </el-row>
                            </el-card>
                        </el-col>
                        <el-col :span="12" style="float: right;">
                            <el-card style="margin-top: 20px;width: 100%;">
                                <el-row>
                                    <el-col :span="4" :offset="7">
                                        <img src="../../assets/img/showUp.png"
                                             style="float: right;width: 70%;height: 70%;">
                                    </el-col>
                                    <el-col :span="4" :offset="1" style="margin-top: 1%;">
                                        <el-row>
                                            <h2 style="float: left">出场流量</h2>
                                        </el-row>
                                        <el-row>
                                            <h2>{{showUpFlow}}</h2>
                                        </el-row>
                                    </el-col>
                                </el-row>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
                <body-card card-name="流量占比" style="margin-top: 20px;">
                    <div slot="body">
                        <traffic-proportion></traffic-proportion>
                    </div>
                </body-card>
                <body-card card-name="流量趋势" style="margin-top: 20px">
                    <div slot="body">
                        <traffic-trend></traffic-trend>
                    </div>
                </body-card>
                <body-card card-name="进出场流量详细数据" style="margin-top: 20px">
                    <div slot="body">
                        <inflow-and-outflow-data></inflow-and-outflow-data>
                    </div>
                </body-card>
            </div>
        </web-template>
    </div>
</template>

<script>

    import BodyCard from "../../components/webTemplate/cardTemplate/BodyCard";
    import TrafficProportion from "./components/TrafficProportion";
    import TrafficTrend from "./components/TrafficTrend";
    import InflowAndOutflowData from "./components/InflowAndOutflowData";

    export default {
        name: "TrafficStatistics",
        components: {InflowAndOutflowData, TrafficTrend, TrafficProportion, BodyCard,},
        data() {
            return {
                webName: '流量占比',
                admissionFlow: '',//入场流量
                showUpFlow: '',//出场流量
                echarts2Data: [],
                formData: {
                    areaSelection: '',//区域选择
                    timeGranularity: '', //时间粒度
                    queryTime: '',//查询时间
                },
                areaSelection: [
                    {
                        label: 'A区',
                        value: 'A',
                    }, {
                        label: 'B区',
                        value: 'B',
                    }, {
                        label: '访客区',
                        value: 'C',
                    }
                ],
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {}, {}, {}, {}, {},
                    ]
                },

            }
        },
        mounted() {
            //查询入场和出场流量
            this.queryTraffic();

        },
        methods: {
            //查询按钮
            queryPage(currentPage = 1, pageSize = this.tableData.pageSize) {
                console.log(currentPage + ":" + pageSize);
            },
            //查询入场和出场流量
            queryTraffic() {
                this.admissionFlow = 7191;
                this.showUpFlow = 6904;
            },


        }
    }
</script>

<style scoped>

</style>