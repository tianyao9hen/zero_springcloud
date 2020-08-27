<template>
    <div id="all-content">
        <dv-full-screen-container>
            <dv-border-box-11 title="监控中心">
                <div class="home-page">
                    <i class="el-icon-house" @click="backHome"> 返回首页</i>
                </div>
                <div class="head-content">
                    <div class="main-content">
                        <digital-flop/>
                    </div>
                </div>
                <div class="primary-coverage">
                    <div class="left">
                        <div class="scroll-board-left">
                            <dv-scroll-board :config="configLeft"/>
                        </div>
                        <div class="scroll-board-right">
                            <dv-scroll-board :config="configRight"/>
                        </div>
                    </div>
                    <div class="right">
                        <dv-border-box-10>
                            <big-map/>
                        </dv-border-box-10>
                    </div>
                </div>
                <div class="picture-content">
                    <div class="one-pic">
                        <dv-border-box-10></dv-border-box-10>
                    </div>
                    <div class="two-pic">
                        <dv-border-box-10></dv-border-box-10>
                    </div>
                    <div class="three-pic">
                        <dv-border-box-10></dv-border-box-10>
                    </div>
                    <div class="four-pic">
                        <dv-border-box-10></dv-border-box-10>
                    </div>
                </div>
            </dv-border-box-11>
        </dv-full-screen-container>
    </div>

</template>

<script>
    import BigMap from "./components/BigMap";
    import DigitalFlop from "./components/DigitalFlop";

    export default {
        name: "MonitoringCenterPage",
        components: {DigitalFlop, BigMap,},
        data() {
            return {
                configLeft: {
                    header: ['区域', '时间', '入口', '出口', '车道', '车牌号', '车辆类型'],
                    data: [
                        ['A区', '2019-07-02 17:25:00', 1, 2, 1, '鲁AFE785', '免费车'],
                        ['B区', '2020-07-10 12:50:22', 3, 1, 2, '鲁ACG935', '包月车'],
                        ['C区', '2020-08-17 15:01:32', 4, 1, 2, '鲁ABL9E5', '访客车'],
                        ['A区', '2019-09-08 17:20:30', 2, 2, 1, '鲁AFG787', '免费车'],
                        ['B区', '2020-09-18 17:50:32', 2, 1, 2, '鲁ACL937', '包月车'],
                        ['C区', '2020-08-17 15:01:30', 4, 2, 2, '鲁ABE7E5', '访客车'],
                    ],
                    columnWidth: [67, 230, 67, 67, 67, 120, 120],
                    align: ['center', 'center', 'center', 'center', 'center', 'center', 'center',],
                    rowNum: 4,
                    headerBGC: '#0076BB',
                    headerHeight: 35,
                    oddRowBGC: 'rgba(0, 44, 81, 0.5)',
                    evenRowBGC: 'rgba(10, 29, 50, 0.5)',
                    waitTime: 2000,
                },
                configRight: {

                    header: ['入场时间', '出场时间', '车牌号', '车辆类型'],
                    data: [
                        ['2019-07-02 17:25:00', '2019-07-22 17:25:50', '鲁AFE785', '免费车'],
                        ['2020-07-10 12:50:22', '2020-07-20 12:50:22', '鲁ACG935', '包月车'],
                        ['2020-08-17 15:01:32', '2020-08-27 15:01:32', '鲁ABL9E5', '访客车'],
                        ['2019-09-08 17:20:30', '2019-09-28 17:20:30', '鲁AFG787', '免费车'],
                        ['2020-09-18 17:50:32', '2020-09-28 17:50:32', '鲁ACL937', '包月车'],
                        ['2020-08-17 15:01:30', '2020-08-27 15:01:30', '鲁ABE7E5', '访客车'],
                    ],
                    columnWidth: [230, 230, 120, 120],
                    align: ['center', 'center', 'center', 'center'],
                    rowNum: 3,
                    headerBGC: '#0076BB',
                    headerHeight: 35,
                    oddRowBGC: 'rgba(0, 44, 81, 0.5)',
                    evenRowBGC: 'rgba(10, 29, 50, 0.5)',
                    waitTime: 2000,
                },
            }
        },
        methods: {
            //返回首页
            backHome() {
                this.$router.push({path: '/homePage1'});
            }
        }

    }
</script>

<style lang="less">
    #all-content {
        width: 100%;
        height: 100%;
        background-color: #030409;
        color: #fff;

        #dv-full-screen-container {
            background-image: url('../../assets/img/bg.png');
            background-size: 100% 100%;
            box-shadow: 0 0 3px blue;
            display: flex;
            flex-direction: column;

        }

        .home-page {
            width: 100%;
            height: 5%;
            margin-left: 92%;
        }

        .head-content {
            width: 100%;
            height: 11%;
            padding-top: 35px;
        }

        .main-content {
            flex: 1;
            display: flex;
            flex-direction: column;
        }


        .primary-coverage {
            display: flex;
            flex-direction: row;
            width: 100%;
            height: 52%;
            margin-top: 10px;

            .left {
                width: 40%;
                height: 100%;
                margin-left: 20px;

                .scroll-board-left {
                    width: 100%;
                    height: 50%;
                    /*padding-left: 20px;*/
                    /*padding-top: 55px;*/
                }

                .scroll-board-right {
                    width: 100%;
                    height: 47%;
                    /*padding-left: 20px;*/
                    padding-top: 10px;

                }

            }

            .right {
                width: 60%;
                height: 100%;
                margin-left: 10px;
                margin-right: 20px;
            }

        }

        .picture-content {
            display: flex;
            flex-direction: row;
            width: 100%;
            height: 24%;
            margin-top: 10px;

            .one-pic {
                margin-left: 20px;
                width: 25%;
                height: 100%;
            }

            .two-pic {

                width: 25%;
                height: 100%;
            }

            .three-pic {

                width: 25%;
                height: 100%;
            }

            .four-pic {
                margin-right: 20px;
                width: 25%;
                height: 100%;
            }
        }
    }


</style>
