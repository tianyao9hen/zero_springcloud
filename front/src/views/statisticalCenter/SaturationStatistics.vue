<template>
    <div>
        <web-template self>
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
                <body-card card-name="流量趋势" class="self-card money-account">
                    <template slot="body">
                        <div id="trafficTrend"></div>
                    </template>
                </body-card>
            </div>
        </web-template>
    </div>
</template>

<script>
    import BodyCard from "../../components/webTemplate/cardTemplate/BodyCard";
    import echarts from "echarts";
    import Bus from "components/common/bus";

    export default {
        name: "SaturationStatistics",
        components: {BodyCard},
        data() {
            return {
                webName: '流量趋势',
                //搜搜
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
                //在场车辆数
                onSiteVehiclesNum: [],
                //饱和度
                saturationData: [],
            }
        },
        mounted() {
            this.queryData();
            window.addEventListener('resize', function () {
                const trafficTrendEchart = echarts.init(document.getElementById('trafficTrend'));
                trafficTrendEchart.resize()
            });
            Bus.$on('collapse',()=>{
                setTimeout(()=>{
                    const trafficTrendEchart = echarts.init(document.getElementById('trafficTrend'));
                    trafficTrendEchart.resize()
                },500)
            })
        },
        methods: {
            queryData(){
                this.queryOnSiteVehiclesNum();
                this.querySaturation();
            },
            //在场车辆数查询
            queryOnSiteVehiclesNum() {
                this.onSiteVehiclesNum = [ 38, 25,1, 4, 8, 10, 15, 20, 40,  20, 31, 38, 40, 42,30, 25, 24, 20, 15, 10, 8, 2, 1, 8, 19,]
            },
            //饱和度
            querySaturation() {
                this.saturationData = [ 10, 1, 4, 8, 10, 15, 20, 8, 2, 1, 8,  25, 24, 20, 15,8, 2, 1, 8, 19, 20, 31, 38, 40, 42]
                this.queryTrafficTrendEchart();
            },
            //折线图
            queryTrafficTrendEchart() {
                var time = [];
                for (let i = 0; i <= 24; i++) {
                    if (i < 10) {
                        time.push('0' + i + ':00');
                    } else if (i >= 10) {
                        time.push(i + ':00');
                    }
                }
                const trafficTrendEchart = echarts.init(document.getElementById('trafficTrend'));
                trafficTrendEchart.setOption({
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
                        data: ['在场车辆数', '饱和度']
                    },
                    grid: {
                        top: 70,
                        bottom: 50,
                        width:"auto", //图例宽度
                        height:"60%", //图例高度
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
                            name: '在场车辆数',
                            type: 'line',

                            smooth: true,
                            data: this.onSiteVehiclesNum,
                        },
                        {
                            name: '饱和度',
                            type: 'line',
                            smooth: true,
                            data: this.saturationData
                        }
                    ]
                });
            },
        }
    }
</script>

<style scoped>
    .self-card {
        margin-top: 20px;
    }
    #trafficTrend {
        width: 100%;
        height: 300px;
        margin: 0 auto;
    }
</style>