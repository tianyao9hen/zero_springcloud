<template>

    <el-row :gutter="20">
        <el-col :span="12">
            <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
                <el-tab-pane label="柱状" name="columnar">
                    <center>
                        <div id="columnarEcharts" style="width: 400px;height:320px"></div>
                    </center>
                </el-tab-pane>
                <el-tab-pane label="饼状" name="pancake">
                    <center>
                        <div id="pancakeEcharts" style="width: 400px;height:300px"></div>
                    </center>
                </el-tab-pane>
            </el-tabs>
        </el-col>
        <el-col :span="12">
            <el-table :data="flowNumberData" style="margin-top: 20px;"
                      class="common-table"
                      border>
                <el-table-column prop="vehicleType" label="车辆类型" align="center"/>
                <el-table-column prop="exitFlow" label="出场流量" align="center"/>
                <el-table-column prop="exitFlowPer" label="出场流量占比" align="center"/>
                <el-table-column prop="admissionFlow" label="入场流量" align="center"/>
                <el-table-column prop="admissionFlowPer" label="入场流量占比" align="center"/>
            </el-table>
        </el-col>
    </el-row>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "TrafficProportion",
        data() {
            return {
                //切換tag名
                activeName: 'columnar',
                //流量列表
                flowNumberData: [],
            }
        },
        mounted() {
            //查询柱状图
            this.queryColumnarEcharts();
            //查詢流量占比列表
            this.queryFlowNumberData();
        },
        methods: {
            //查询柱状图
            queryColumnarEcharts() {
                this.chart = echarts.init(document.getElementById('columnarEcharts'));
                this.chart.setOption({
                    legend: {},
                    tooltip: {},
                    dataset: {
                        dimensions: ['product', '入场流量', '出场流量'],
                        source: [
                            ['访客车辆', 625, 620],
                            ['包月车', 263, 262],
                            ['免费车', 179, 178],
                            ['商户优惠车', 111, 108]
                        ]
                    },
                    xAxis: {type: 'category'},
                    yAxis: {},
                    // Declare several bar series, each will be mapped
                    // to a column of dataset.source by default.
                    series: [
                        {type: 'bar'},
                        {type: 'bar'},
                    ]
                });
            },
            //tag点击切换  饼 柱状
            handleClick(tab, event) {
                console.log(tab, event);
                if (this.activeName === 'pancake') {
                    this.queryData();
                }
            },
            //查询echarts2中data数据
            queryData() {
                this.echarts2Data = [
                    {
                        name: '访客车',
                        value: '625'
                    }, {
                        name: '包月车',
                        value: '264'
                    }, {
                        name: '免费车',
                        value: '179'
                    }, {
                        name: '商务合作车',
                        value: '111'
                    }
                ],
                    this.queryPancakeEcharts();
            },
            //饼图
            queryPancakeEcharts() {
                this.chart2 = echarts.init(document.getElementById('pancakeEcharts'));
                this.chart2.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'right',
                        data: ['访客车', '包月车', '免费车', '商务合作车',]
                    },
                    series: [
                        {
                            name: '车辆类型',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '45%'],//移动图表  1.左右  2.上下
                            data: this.echarts2Data,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            //查询出入流量占比列表
            queryFlowNumberData() {
                this.flowNumberData = [
                    {
                        vehicleType: '访客车',
                        exitFlow: '625',
                        exitFlowPer: '53.01%',
                        admissionFlow: '625',
                        admissionFlowPer: '53.01%',
                    }, {
                        vehicleType: '包月车',
                        exitFlow: '264',
                        exitFlowPer: '22.39%',
                        admissionFlow: '264',
                        admissionFlowPer: '22.39%',
                    }, {
                        vehicleType: '免费车',
                        exitFlow: '179',
                        exitFlowPer: '15.18%',
                        admissionFlow: '178',
                        admissionFlowPer: '15.17%',
                    }, {
                        vehicleType: '商务合作车',
                        exitFlow: '111',
                        exitFlowPer: '9.42%',
                        admissionFlow: '108',
                        admissionFlowPer: '9.4%',
                    }
                ];
            },
        }
    }
</script>

<style scoped>

</style>