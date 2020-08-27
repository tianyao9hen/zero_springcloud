<template>
    <div class="report-form">
        <web-template self>
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="车辆类型" prop="carType" labelWidth="80px">
                                <el-select v-model="formData.carType" filterable clearable placeholder="请选择车辆类型" style="width: 100%;">
                                    <el-option v-for="item in carTypeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="开始时间" prop="startDate" labelWidth="80px">
                                <el-date-picker
                                        v-model="formData.startDate"
                                        type="datetime"
                                        style="width: 100%"
                                        placeholder="选择日期时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="结束时间" prop="endDate" labelWidth="80px">
                                <el-date-picker
                                        v-model="formData.endDate"
                                        type="datetime"
                                        style="width: 100%"
                                        placeholder="选择日期时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryData">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <template slot="selfBody">
                <body-card card-name="金额统计" class="self-card money-account">
                    <template slot="body">
                        <div id="money-account-echarts"></div>
                    </template>
                </body-card>
                <body-card card-name="总计"  class="self-card money-summary">
                    <template slot="headerButton">
                        <el-select v-model="showRange" @change="queryMoneySummary" filterable style="margin-right: 10px;width: 150px;">
                            <el-option v-for="item in showRangeList" :key="item.value"
                                       :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                        <el-button class="el-icon-download" @click="exportData">导出</el-button>
                    </template>
                    <template slot="body">
                        <div id="money-summary-echarts"></div>
                    </template>
                </body-card>
                <div class="card-coat">
                    <body-card card-name="车辆日报表" class="self-card">
                        <template slot="body">
                            <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                                <el-table-column prop="orderNo" label="订单编号" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="areaName" label="区域"  align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="carNo" label="车牌号" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="parkingDuration" label="停车时长" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="receivableCost" label="应收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="officialCost" label="实收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="creditCost" label="减免金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="couponCost" label="优惠卷金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column prop="payModeName" label="支付形式" align="center" :show-overflow-tooltip="true"></el-table-column>
                                <el-table-column label="操作" align="center" width="120px">
                                    <template slot-scope="scope">
                                        <el-button size="mini" type="text" @click="showInfoDialog(scope.row)">
                                            <i class="el-icon-share">详情</i>
                                        </el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <pagination :currentPage="tableData?tableData.currentPage:undefined"
                                        :pageSize="tableData?tableData.pageSize:undefined"
                                        :total="tableData?tableData.total:undefined"
                                        @change-page="queryDetail"/>
                        </template>
                    </body-card>
                </div>
            </template>
        </web-template>
        <report-form-info ref="reportFormInfo" />
    </div>
</template>

<script>
    import BodyCard from "components/webTemplate/cardTemplate/BodyCard";
    import echarts from 'echarts'
    import ReportFormInfo from "./reportForm/ReportFormInfo";
    import Bus from "components/common/bus";

    export default {
        name: "ReportForm",
        components: {ReportFormInfo, BodyCard},
        data(){
            return {
                formData: {
                    carType: '',
                    startDate: '',
                    endDate: '',
                },
                carTypeList: [
                    {
                        label: '访客',
                        value: 1,
                    },
                    {
                        label: '月租',
                        value: 0,
                    }
                ],
                //总计 收入展示区间选择
                showRange: 2,
                showRangeList: [
                    {
                        label: '按年收入展示',
                        value: 0
                    },
                    {
                        label: '按月收入展示',
                        value: 1
                    },
                    {
                        label: '按天收入展示',
                        value: 2
                    },
                ],
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: []
                }
            }
        },
        mounted() {
            this.queryData();
            //echarts宽度自适应
            window.addEventListener("resize",this.chartResize)
            Bus.$on('collapse',()=>{
                this.chartResize()
            })
        },
        methods: {
            //echarts重新加载
            chartResize(){
                let timer = null;
                if(timer){
                    clearTimeout(timer);
                }
                timer = setTimeout(()=>{
                    let chart = echarts.init(document.getElementById("money-summary-echarts"));
                    chart.resize();
                    let chart2 = echarts.init(document.getElementById("money-account-echarts"));
                    chart2.resize();
                },500)
            },
            //echarts设置
            initMoneyAccountChart(chartData){
                let chart = echarts.init(document.getElementById("money-account-echarts"));
                chart.clear();
                chart.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b} <br/>{a} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data: chartData.legendData,
                    },
                    series: [
                        {
                            name: '总计金额',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '50%'],
                            data: chartData.seriesData,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                })
            },
            initMoneySummaryChart(chartData){
                let chart = echarts.init(document.getElementById("money-summary-echarts"));
                chart.clear();
                chart.setOption({
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['应收金额','实收金额','减免金额','优惠卷总金额'],
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {show: true, type: ['line', 'bar']},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: chartData.xAxisData,
                        }
                    ],
                    yAxis: [
                        {type: 'value'}
                    ],
                    series: [
                        {
                            name: '应收金额',
                            type: 'bar',
                            data: chartData.receivableData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name: '实收金额',
                            type: 'bar',
                            data: chartData.receivedData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name: '减免金额',
                            type: 'bar',
                            data: chartData.mitigateData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name: '优惠卷总金额',
                            type: 'bar',
                            data: chartData.couponData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                    ]
                })
            },
            //查询数据
            queryData(){
                this.queryMoneyAccount();
                this.queryMoneySummary();
                this.queryDetail();
            },
            //查询金额统计数据
            queryMoneyAccount(){
                setTimeout(()=> {
                    const data = {
                        legendData: ['ETC', '支付宝', '微信', '其他'],
                        seriesData: [
                            {
                                name: 'ETC',
                                value: 500
                            }, {
                                name: '支付宝',
                                value: 600
                            }, {
                                name: '微信',
                                value: 700
                            }, {
                                name: '其他',
                                value: 800
                            }
                        ],
                    }
                    this.initMoneyAccountChart(data);
                },1000)
            },
            //查询金额总计数据
            queryMoneySummary(){
                setTimeout(()=>{
                    const data = {
                        xAxisData: ['2020-08-01','2020-08-02','2020-08-03','2020-08-04','2020-08-05','2020-08-06','2020-08-07','2020-08-08','2020-08-09','2020-08-10'],
                        receivableData: [100,200,300,250,150,50,500,200,300,400],
                        receivedData: [100,250,250,250,100,100,500,200,300,400],
                        mitigateData: [50,10,50,100,50,10,200,100,300,200],
                        couponData: [90,20,100,20,150,100,100,90,200,300]
                    }
                    this.initMoneySummaryChart(data);
                },1000)
            },
            //查询日报表数据
            queryDetail(currentPage = 1,pageSize = this.tableData.pageSize){
                console.log(currentPage,pageSize);
                let that = this;
                setTimeout(()=>{
                    that.tableData = {
                        total: 200,
                        currentPage: currentPage,
                        pageSize: pageSize,
                        dataList: [
                            {
                                "id": "1",
                                "areaId": 1,
                                "areaName": '区域1',
                                "payDate": "2020-01-01 01:01:01",
                                "orderNo": "PO001",
                                "carNo": "鲁A00001",
                                "authorType": "3",
                                "authorTypeName": "免费车",
                                "parkingDuration": "30分钟",
                                "receivableCost": "10.00",
                                "officialCost":  "10.00",
                                "creditCost":  "10.00",
                                "couponCost":  "10.00",
                                "payChannel": 1,
                                "payChannelName": '自助支付',
                                "payType": 1,
                                "payTypeName": '出口支付',
                                "payMode": 1,
                                "payModeName": '微信支付',
                            }, {
                                "id": "1",
                                "payDate": "2020-01-01 01:01:01",
                                "areaId": 1,
                                "areaName": '区域1',
                                "orderNo": "PO001",
                                "carNo": "鲁A00001",
                                "authorType": "3",
                                "authorTypeName": "免费车",
                                "parkingDuration": "30分钟",
                                "receivableCost": "10.00",
                                "officialCost":  "10.00",
                                "creditCost":  "10.00",
                                "couponCost":  "10.00",
                                "payChannel": 1,
                                "payChannelName": '自助支付',
                                "payType": 1,
                                "payTypeName": '出口支付',
                                "payMode": 1,
                                "payModeName": '微信支付',
                            },  {
                                "id": "1",
                                "payDate": "2020-01-01 01:01:01",
                                "areaId": 1,
                                "areaName": '区域1',
                                "orderNo": "PO001",
                                "carNo": "鲁A00001",
                                "authorType": "3",
                                "authorTypeName": "免费车",
                                "parkingDuration": "30分钟",
                                "receivableCost": "10.00",
                                "officialCost":  "10.00",
                                "creditCost":  "10.00",
                                "couponCost":  "10.00",
                                "payChannel": 1,
                                "payChannelName": '自助支付',
                                "payType": 1,
                                "payTypeName": '出口支付',
                                "payMode": 1,
                                "payModeName": '微信支付',
                            }, {
                                "id": "1",
                                "payDate": "2020-01-01 01:01:01",
                                "areaId": 1,
                                "areaName": '区域1',
                                "orderNo": "PO001",
                                "carNo": "鲁A00001",
                                "authorType": "3",
                                "authorTypeName": "免费车",
                                "parkingDuration": "30分钟",
                                "receivableCost": "10.00",
                                "officialCost":  "10.00",
                                "creditCost":  "10.00",
                                "couponCost":  "10.00",
                                "payChannel": 1,
                                "payChannelName": '自助支付',
                                "payType": 1,
                                "payTypeName": '出口支付',
                                "payMode": 1,
                                "payModeName": '微信支付',
                            }, {
                                "id": "1",
                                "payDate": "2020-01-01 01:01:01",
                                "areaId": 1,
                                "areaName": '区域1',
                                "orderNo": "PO001",
                                "carNo": "鲁A00001",
                                "authorType": "3",
                                "authorTypeName": "免费车",
                                "parkingDuration": "30分钟",
                                "receivableCost": "10.00",
                                "officialCost":  "10.00",
                                "creditCost":  "10.00",
                                "couponCost":  "10.00",
                                "payChannel": 1,
                                "payChannelName": '自助支付',
                                "payType": 1,
                                "payTypeName": '出口支付',
                                "payMode": 1,
                                "payModeName": '微信支付',
                            },
                        ]
                    }
                },1000)
            },
            //导出金额总计数据
            exportData(){

            },
            //重置
            rest(){
                this.$refs.queryForm.resetField();
            },
            //展示详情弹窗
            showInfoDialog(row){
                this.$refs.reportFormInfo.init(row);
            }
        }
    }
</script>

<style scoped>
    .card-coat {
        width: 100%;
        float: left;
    }
    .self-card {
        margin-top: 20px;
    }
    .money-account {
        width: 30%;
        float: left;
        margin-right: 20px;
    }
    .money-summary {
        width: calc(70% - 24px);
        float: left;
    }
    #money-account-echarts {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }
    #money-summary-echarts {
        height: 300px;
        width: 100%;
        margin: 0 auto;
    }
</style>