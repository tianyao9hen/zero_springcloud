<template>
    <div class="customer-list">
        <el-container>
            <el-main>
                <el-row :gutter="20">
                    <el-col :span="16">
                        <el-card class="cardStyle" style="height: 400px">
                            <el-row :gutter="20">
                                <el-col :span="8">
                                    <img src="../../assets/img/1.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/5.png">
                                    </div>
                                    <div class="two">
                                        <span>{{totalParkingSpace}}</span><br>
                                    </div>
                                    <div class="three">
                                        <span>{{totalParkingSpaceTitile}}</span>
                                    </div>
                                </el-col>
                                <el-col :span="8">
                                    <img src="../../assets/img/2.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/6.png">
                                    </div>
                                    <div class="two">
                                        <span>{{availableParkingNo}}</span><br>

                                    </div>
                                    <div class="three">
                                        <span>{{availableParkingNoTitile}}</span>
                                    </div>
                                </el-col>
                                <el-col :span="8">
                                    <img src="../../assets/img/3.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/7.png">
                                    </div>
                                    <div class="two">
                                        <span>{{chargingPileNo}}</span><br>

                                    </div>
                                    <div class="three">
                                        <span>{{chargingPileNoTitile}}</span>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="20" style="margin-top: 70px">
                                <el-col :span="8">
                                    <img src="../../assets/img/4.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/8.png">
                                    </div>
                                    <div class="two">
                                        <span>{{totalRevenue}}</span><br>

                                    </div>
                                    <div class="three">
                                        <span>{{totalRevenueTitile}}</span>
                                    </div>
                                </el-col>
                                <el-col :span="8">
                                    <img src="../../assets/img/1.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/9.png">
                                    </div>
                                    <div class="two">
                                        <span>{{monthlyIncome}}</span><br>

                                    </div>
                                    <div class="three">
                                        <span>{{monthlyIncomeTitile}}</span>
                                    </div>
                                </el-col>
                                <el-col :span="8">
                                    <img src="../../assets/img/2.png" style="width:100%;height: 170px">
                                    <div class="one">
                                        <img src="../../assets/img/10.png">
                                    </div>
                                    <div class="two">
                                        <span>{{dailyIncome}}</span><br>

                                    </div>
                                    <div class="three">
                                        <span>{{dailyIncomeTitile}}</span>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card style="height: 400px">
                            <div class="echartsAnnular">
                                <div class="common-card-box"></div>
                                <span class="common-card-span">在场车辆占比</span>
                                <div id="myChart" ></div>
                            </div>

                        </el-card>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :span="16">
                        <el-card class="cardStyle" style="height: 400px">
                            <div class="echartsAnnular">
                                <div class="common-card-box"></div>
                                <span class="common-card-span">线上收入汇总(当月)</span>
                            </div>
                            <el-table :data="paymentList"
                                      style="margin-bottom: 20px;" class="common-table"
                                      border>
                                <el-table-column prop="mode" label="支付方式" align="center"/>
                                <el-table-column prop="numberOfStrokes" label="支付笔数" align="center"/>
                                <el-table-column prop="paidInAmount" label="实收金额" align="center"/>
                                <el-table-column prop="proportion" label="支付占比" align="center"/>
                            </el-table>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card style="height: 400px">
                            <div class="echartsAnnular">
                                <div class="common-card-box"></div>
                                <span class="common-card-span">电子支付增长量柱状图</span>
                                <div id="myChart2"></div>
                            </div>

                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import Bus from "../../components/common/bus";
    export default {
        name: "homePage1",
        data() {
            return {

                totalParkingSpace: '590',
                totalParkingSpaceTitile: '总车位数',

                availableParkingNo:'150',
                availableParkingNoTitile:'可用停车位',

                chargingPileNo:'150',
                chargingPileNoTitile:'可用充电桩',

                totalRevenue:'3520',
                totalRevenueTitile:'总实际收入',

                monthlyIncome:'1200',
                monthlyIncomeTitile:'当月实收',

                dailyIncome:'890',
                dailyIncomeTitile:'当日实收',
                //支付列表
                paymentList: [
                    {
                        mode: 'ETC',
                        numberOfStrokes: 3450,
                        paidInAmount: 3450,
                        proportion: 31,
                    },
                    {
                        mode: '支付宝',
                        numberOfStrokes: 9450,
                        paidInAmount: 9450,
                        proportion: 31,
                    },
                    {
                        mode: '微信',
                        numberOfStrokes: 3450,
                        paidInAmount: 3450,
                        proportion: 31,
                    },
                    {
                        mode: '一卡通',
                        numberOfStrokes: 3450,
                        paidInAmount: 3450,
                        proportion: 31,
                    },
                ],
                //myecharts1
                legendData: ['包月车', '免费车', '访客车', '商务合作车'],
                seriesData1: [
                    {value: 335, name: '包月车'},
                    {value: 310, name: '免费车'},
                    {value: 234, name: '访客车'},
                    {value: 135, name: '商务合作车'},
                ],
                //myecharts2
                xAxisData:['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                seriesData2:[5, 8, 10, 15, 21, 7, 3, 8, 0, 0, 0, 0],
            }
        },
        mounted() {
            this.initChart();
            this.initChart2();
            //echarts宽度自适应
            window.addEventListener('resize', this.chartResize);
            Bus.$on('collapse', () => {
                this.chartResize();
            })
        },
        methods: {

            //echarts宽度自适应
            chartResize() {
                let timer = null;
                if (timer) {
                    clearTimeout(timer);
                }
                timer = setTimeout(() => {
                    let chart = echarts.init(document.getElementById("myChart"));
                    chart.resize();
                    let chart2 = echarts.init(document.getElementById("myChart2"));
                    chart2.resize();
                }, 500)
            },


            initChart() {
                const chart = echarts.init(document.getElementById('myChart'))
                chart.clear();
                chart.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',//垂直显示
                        top: -5,
                        right: 0, //右侧显示
                        data: this.legendData,
                    },
                    series: [
                        {
                            name: '车辆类型',
                            type: 'pie',//pie为饼状图
                            radius: ['30%', '50%'],//内圆径，外远内径
                            color: ['#91e5ff', '#00cbff', '#32aeff', '#407dec'],//颜色
                            center: ['50%', '45%'],//移动图表  1.左右  2.上下
                            avoidLabelOverlap: false,//是否启用防止标签重叠策略
                            /*label: {
                                show: true,
                                position: 'center'
                            },
                            labelLine: {
                                show: false
                            },
                            */
                            label: {
                                color: '#407dec',
                                formatter: '{b} \n {d}%'
                            },
                            labelLine: {
                                lineStyle: {
                                    color: '#91e5ff'
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '12',
                                    fontWeight: 'bold'
                                },
                                itemStyle: {}
                            },
                            data:this.seriesData1,
                        }
                    ]
                })
            },
            initChart2() {
                const chart2 = echarts.init(document.getElementById('myChart2'));
                chart2.clear();
                chart2.setOption({
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        data: this.xAxisData,
                        axisLabel: {
                            inside: false,
                            textStyle: {
                                color: '#focusNodeAdjacency'
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        z: 10
                    },
                    yAxis: {
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#999'
                            }
                        }
                    },
                    series: [
                        { // For shadow
                            type: 'bar',
                            itemStyle: {
                                color: 'rgba(0,0,0,0)'
                            },
                            barGap: '-50%',
                            animation: false

                        },
                        {
                            name: '增长率',
                            type: 'bar',
                            barWidth: '60%',
                            right: 10,
                            center: ['40%', '0%'],//移动图表  1.左右  2.上下
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#83bff6'},
                                        {offset: 0.5, color: '#188df0'},
                                        {offset: 1, color: '#188df0'}
                                    ]
                                )
                            },
                            emphasis: {
                                itemStyle: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                    )
                                }
                            },
                            data: this.seriesData2,
                        }
                    ]
                })
            }
        }
    }
</script>

<style scoped>
    label {
        font-size: 14px;
        color: #606266;

    }

    .echartsAnnular {
        height: 40px;
        margin-left: -20px;
    }

    strong {
        font-size: 16px;
        color: #636363;
    }


    .cardStyle {
        margin-bottom: 20px;
    }


    .one {
        margin-top: -120px;
        margin-left: 30px;
    }

    .two {
        margin-top: -70px;
        margin-left: 120px;
        font-size: 35px;
    }

    .three {
        font-size: 10px;
        margin-left: 130px;
        font-family: 'Microsoft YaHei';
    }

    #myChart{
        width: 100%;
        height: 400px;
        margin: 0 auto;
    }

    #myChart2{
        width: 100%;
        height: 350px;
        margin-left: 35px;
    }
</style>