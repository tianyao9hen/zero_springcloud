<template>
    <el-tabs v-model="trendType" type="card" @tab-click="trendTypeSwitch">
        <el-tab-pane label="合计" name="total">
            <div id="totalGraph" v-show="isShow"></div>
        </el-tab-pane>
        <el-tab-pane label="访客车" name="visitingBus">
            <div id="visitingBus" v-show="isShow"></div>
        </el-tab-pane>
        <el-tab-pane label="包月车" name="monthlyCar">
            <div id="monthlyCar" v-show="isShow"></div>
        </el-tab-pane>
        <el-tab-pane label="免费车" name="freeCar">
            <div id="freeCar" v-show="isShow"></div>
        </el-tab-pane>
        <el-tab-pane label="商务合作车" name="cooperativeCar">
            <div id="cooperativeCar" v-show="isShow"></div>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import echarts from "echarts";
    import Bus from "components/common/bus";

    export default {
        name: "TrafficTrend",
        data() {
            return {
                trendType: 'total',
                //合计出场流量
                exitFlowData: [],
                //合计入场流量
                admissionFlowData: [],
                isShow: false,
            }
        },
        mounted() {
            //echarts宽度自适应
            window.addEventListener('resize', this.chartResize);
            Bus.$on('collapse', () => {
                this.chartResize();
            })
            //出场流量 入场流量
            this.queryData('totalGraph');
        },
        methods: {
            //echarts宽度自适应
            chartResize() {
                let timer = null;
                if (timer) {
                    clearTimeout(timer);
                }
                timer = setTimeout(() => {
                    let chart = echarts.init(document.getElementById("totalGraph"));
                    chart.resize();
                    let chart2 = echarts.init(document.getElementById("visitingBus"));
                    chart2.resize();
                    let chart3 = echarts.init(document.getElementById("monthlyCar"));
                    chart3.resize();
                    let chart4 = echarts.init(document.getElementById("freeCar"));
                    chart4.resize();
                    let chart5 = echarts.init(document.getElementById("cooperativeCar"));
                    chart5.resize();
                    this.isShow = true;
                }, 600)
            },
            //查询数据
            queryData(carType) {
                //出场流量
                this.queryExitFlow(carType);
            },
            //查询出场流量
            queryExitFlow(carType) {
                this.exitFlowData = [
                    1, 4, 8, 10, 15, 20, 38, 25, 40, 30, 25, 24, 20, 15, 10, 8, 2, 1, 8, 19, 20, 31, 38, 40, 42
                ];
                // //随机生成两位数
                // for(var i=0;i<=24;i++){
                //     this.exitFlowData.push(parseInt(Math.random()*40+10));
                // }
                //查询入场流量
                this.queryAdmissionFlow(carType);
            },
            //查询入场流量
            queryAdmissionFlow(carType) {
                this.admissionFlowData = [
                    8, 10, 15, 20, 38, 25, 40, 30, 25, 24, 20, 15, 10, 8, 2, 1, 8, 19, 20, 31, 38, 40, 42, 30, 10,
                ];
                this.chartResize();
                this.queryTotalGraph(carType);
                // //随机生成两位数
                // for(var a=0;a<=24;a++){
                //     this.admissionFlowData.push(parseInt(Math.random()*40+10));
                // }
            },

            //根据类型查询折线图数据
            queryTotalGraph(carType) {
                if(carType=='totalGraph'){
                    this.isShow=true
                }
                var time = [];
                for (let i = 0; i <= 24; i++) {
                    if (i < 10) {
                        time.push('0' + i + ':00');
                    } else if (i >= 10) {
                        time.push(i + ':00');
                    }

                }
                let totalGraphEchart = echarts.init(document.getElementById(carType));
                totalGraphEchart.clear();
                totalGraphEchart.setOption({
                    tooltip: {
                        trigger: 'axis',
                        //指示器
                        axisPointer: {
                            type: 'line',
                            lineStyle: {
                                color: '#7171C6'
                            }
                        }
                    },
                    legend: {
                        data: ['入场流量', '出场流量']
                    },
                    grid: {
                        top: 70,
                        bottom: 50,
                    },
                    xAxis: [
                        {
                            type: 'category',
                            //x轴坐标点开始与结束点位置都不在最边缘
                            boundaryGap: false,
                            axisLine: {
                                show: true,
                            },
                            axisTick: {
                                alignWithLabel: true
                            },
                            data: time,
                        },
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '入场流量',
                            type: 'line',

                            smooth: true,
                            data: this.exitFlowData,
                        },
                        {
                            name: '出场流量',
                            type: 'line',
                            smooth: true,
                            data: this.admissionFlowData
                        }
                    ]
                });
            },
            // //访客车折线图
            // queryVisitingBus() {
            //     let time = [];
            //     for (let i = 0; i <= 24; i++) {
            //         if (i < 10) {
            //             time.push('0' + i + ':00');
            //         } else if (i >= 10) {
            //             time.push(i + ':00');
            //         }
            //     }
            //     let visitingBusEchart = echarts.init(document.getElementById('visitingBus'));
            //     visitingBusEchart.clear();
            //     visitingBusEchart.setOption({
            //         tooltip: {
            //             trigger: 'axis',
            //             //指示器
            //             axisPointer: {
            //                 type: 'line',
            //                 lineStyle: {
            //                     color: '#7171C6'
            //                 }
            //             }
            //         },
            //         legend: {
            //             data: ['入场流量', '出场流量']
            //         },
            //         grid: {
            //             top: 70,
            //             bottom: 50
            //         },
            //         xAxis: [
            //             {
            //                 type: 'category',
            //                 //x轴坐标点开始与结束点位置都不在最边缘
            //                 boundaryGap: false,
            //                 axisLine: {
            //                     show: true,
            //                 },
            //                 axisTick: {
            //                     alignWithLabel: true
            //                 },
            //                 data: time,
            //             },
            //         ],
            //         yAxis: [
            //             {
            //                 type: 'value'
            //             }
            //         ],
            //         series: [
            //             {
            //                 name: '入场流量',
            //                 type: 'line',
            //
            //                 smooth: true,
            //                 data: this.exitFlowData,
            //             },
            //             {
            //                 name: '出场流量',
            //                 type: 'line',
            //                 smooth: true,
            //                 data: this.admissionFlowData
            //             }
            //         ]
            //     });
            // },
            // //包月车折线图
            // queryMonthlyCar() {
            //     let time = [];
            //     for (let i = 0; i <= 24; i++) {
            //         if (i < 10) {
            //             time.push('0' + i + ':00');
            //         } else if (i >= 10) {
            //             time.push(i + ':00');
            //         }
            //
            //     }
            //     let monthlyCarEchart = echarts.init(document.getElementById('monthlyCar'));
            //     monthlyCarEchart.clear();
            //     monthlyCarEchart.setOption({
            //         tooltip: {
            //             trigger: 'axis',
            //             //指示器
            //             axisPointer: {
            //                 type: 'line',
            //                 lineStyle: {
            //                     color: '#7171C6'
            //                 }
            //             }
            //         },
            //         legend: {
            //             data: ['入场流量', '出场流量']
            //         },
            //         grid: {
            //             top: 70,
            //             bottom: 50
            //         },
            //         xAxis: [
            //             {
            //                 type: 'category',
            //                 //x轴坐标点开始与结束点位置都不在最边缘
            //                 boundaryGap: false,
            //                 axisLine: {
            //                     show: true,
            //                 },
            //                 axisTick: {
            //                     alignWithLabel: true
            //                 },
            //                 data: time,
            //             },
            //         ],
            //         yAxis: [
            //             {
            //                 type: 'value'
            //             }
            //         ],
            //         series: [
            //             {
            //                 name: '入场流量',
            //                 type: 'line',
            //
            //                 smooth: true,
            //                 data: this.exitFlowData,
            //             },
            //             {
            //                 name: '出场流量',
            //                 type: 'line',
            //                 smooth: true,
            //                 data: this.admissionFlowData
            //             }
            //         ]
            //     });
            // },
            // //免费车折线图
            // queryFreeCar() {
            //     let time = [];
            //     for (let i = 0; i <= 24; i++) {
            //         if (i < 10) {
            //             time.push('0' + i + ':00');
            //         } else if (i >= 10) {
            //             time.push(i + ':00');
            //         }
            //
            //     }
            //     let freeCarEchart = echarts.init(document.getElementById('freeCar'));
            //     freeCarEchart.clear();
            //     freeCarEchart.setOption({
            //         tooltip: {
            //             trigger: 'axis',
            //             //指示器
            //             axisPointer: {
            //                 type: 'line',
            //                 lineStyle: {
            //                     color: '#7171C6'
            //                 }
            //             }
            //         },
            //         legend: {
            //             data: ['入场流量', '出场流量']
            //         },
            //         grid: {
            //             top: 70,
            //             bottom: 50
            //         },
            //         xAxis: [
            //             {
            //                 type: 'category',
            //                 //x轴坐标点开始与结束点位置都不在最边缘
            //                 boundaryGap: false,
            //                 axisLine: {
            //                     show: true,
            //                 },
            //                 axisTick: {
            //                     alignWithLabel: true
            //                 },
            //                 data: time,
            //             },
            //         ],
            //         yAxis: [
            //             {
            //                 type: 'value'
            //             }
            //         ],
            //         series: [
            //             {
            //                 name: '入场流量',
            //                 type: 'line',
            //
            //                 smooth: true,
            //                 data: this.exitFlowData,
            //             },
            //             {
            //                 name: '出场流量',
            //                 type: 'line',
            //                 smooth: true,
            //                 data: this.admissionFlowData
            //             }
            //         ]
            //     });
            // },
            // //商务合作车折线图
            // queryCooperativeCar() {
            //     let time = [];
            //     for (let i = 0; i <= 24; i++) {
            //         if (i < 10) {
            //             time.push('0' + i + ':00');
            //         } else if (i >= 10) {
            //             time.push(i + ':00');
            //         }
            //
            //     }
            //     let cooperativeCarEchart = echarts.init(document.getElementById('cooperativeCar'));
            //     cooperativeCarEchart.clear();
            //     cooperativeCarEchart.setOption({
            //         tooltip: {
            //             trigger: 'axis',
            //             //指示器
            //             axisPointer: {
            //                 type: 'line',
            //                 lineStyle: {
            //                     color: '#7171C6'
            //                 }
            //             }
            //         },
            //         legend: {
            //             data: ['入场流量', '出场流量']
            //         },
            //         grid: {
            //             top: 70,
            //             bottom: 50
            //         },
            //         xAxis: [
            //             {
            //                 type: 'category',
            //                 //x轴坐标点开始与结束点位置都不在最边缘
            //                 boundaryGap: false,
            //                 axisLine: {
            //                     show: true,
            //                 },
            //                 axisTick: {
            //                     alignWithLabel: true
            //                 },
            //                 data: time,
            //             },
            //         ],
            //         yAxis: [
            //             {
            //                 type: 'value'
            //             }
            //         ],
            //         series: [
            //             {
            //                 name: '入场流量',
            //                 type: 'line',
            //
            //                 smooth: true,
            //                 data: this.exitFlowData,
            //             },
            //             {
            //                 name: '出场流量',
            //                 type: 'line',
            //                 smooth: true,
            //                 data: this.admissionFlowData
            //             }
            //         ]
            //     });
            // },

            //切换折线图得tag 切换echarts
            trendTypeSwitch(tab, event) {
                this.chartResize();
                console.log(tab, event);
                if (this.trendType === 'visitingBus') {
                    this.queryData('visitingBus');
                    this.isShow=false;
                } else if (this.trendType === 'monthlyCar') {
                    this.queryData('monthlyCar');
                    this.isShow=false;
                } else if (this.trendType === 'freeCar') {
                    this.queryData('freeCar');
                    this.isShow=false;
                } else if (this.trendType === 'cooperativeCar') {
                    this.queryData('cooperativeCar');
                    this.isShow=false;
                }else if(this.trendType === 'totalGraph') {
                    this.queryData('totalGraph');
                }

            },

        },
    }
</script>

<style scoped>
    #totalGraph {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }

    #visitingBus {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }

    #monthlyCar {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }

    #freeCar {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }

    #cooperativeCar {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }
</style>