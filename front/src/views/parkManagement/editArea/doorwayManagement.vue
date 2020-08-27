<template>
    <div class="doorway-management">
        <el-row>
            <el-col :span="22" :offset="1">
                <el-button @click="addDoorway" type="primary" class="table-top-button">
                    <i class="el-icon-plus"> 新增进出口</i>
                </el-button>
                <el-table :data="doorwayTable.dataList" :span-method="objectSpanMethod"
                          style="margin-bottom: 20px;" border>
                    <el-table-column prop="doorwayNo" label="进出口编号" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="doorwayName" label="进出口名称" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="doorwayTypeStr" label="进出口类型" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="roadNo" label="车道编号" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="roadName" label="车道名称" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="isMainRoadStr" label="主车道" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="cameraIP" label="摄像头IP" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="deviceTypeStr" label="设备类型" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="deviceProducerStr" label="设备厂家" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="车道操作" align="center" width="220px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="createQRCode(scope.row)">
                                <span>生成二维码</span>
                            </el-button>
                            <el-button size="mini" type="text" @click="editRoad(scope.row)">
                                <span>编辑</span>
                            </el-button>
                            <el-button size="mini" type="text" @click="delRoad(scope.row)">
                                <span>删除</span>
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column label="进出口操作" align="center" width="150px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="addRoad(scope.row)">
                                <span>新增车道</span>
                            </el-button>
                            <el-button size="mini" type="text" @click="editDoorway(scope.row)">
                                <span>编辑</span>
                            </el-button>
                            <el-button size="mini" type="text" @click="delDoorway(scope.row)">
                                <span>删除</span>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <div class="footer-button" style="padding-top: 20px" v-if="isAdd">
            <el-button style="margin-top: 12px;" @click="prev">上一步</el-button>
            <el-button class="el-icon-edit-outline" type="primary" @click="nextDoorway">下一步</el-button>
        </div>
        <adjust-doorway ref="adjustDoorway" @refreshDoorwayTable="refreshTable"></adjust-doorway>
        <adjust-road ref="adjustRoad"></adjust-road>
    </div>
</template>

<script>
    import AdjustDoorway from "./AdjustDoorway";
    import AdjustRoad from "./AdjustRoad";

    export default {
        name: "doorwayManagement",
        components: {AdjustRoad, AdjustDoorway},
        props: {
            areaId: {
                type: String,
            },
            areaNo: {
                type: String,
            },
            isAdd: {
                type: Boolean,
                default: false,
            }
        },
        data() {
            return {
                doorwayTable: {
                    dataList: [],
                },
                position: 0,//合并单元格遍历当前行号
                spanArr: [],
            }
        },
        mounted() {
            this.doorwayQueryList();
        },
        methods: {
            //进出口查询,全量查询，不做分页
            doorwayQueryList() {
                let that = this;
                setTimeout(function () {
                   //新增：初始化两条车道  编辑: 查询车道
                    if(that.isAdd){
                        that.doorwayTable={
                            dataList:[
                                {
                                    doorId: "4",
                                    doorwayNo: 'doorway004',
                                    doorwayName: '测试口1',
                                    doorwayType: 1,
                                    doorwayTypeStr: '入口',
                                    roadId: '1',
                                    roadNo: 'road005',
                                    roadName: '车道005',
                                    isMainRoad: 1,
                                    isMainRoadStr: '是',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                },{
                                    doorId: "5",
                                    doorwayNo: 'doorway005',
                                    doorwayName: '测试口5',
                                    doorwayType: 0,
                                    doorwayTypeStr: '出口',
                                    roadId: '2',
                                    roadNo: 'road006',
                                    roadName: '车道006',
                                    isMainRoad: 0,
                                    isMainRoadStr: '否',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                }
                            ]
                        }
                    }else{
                        that.doorwayTable = {
                            dataList: [
                                {
                                    doorId: "1",
                                    doorwayNo: 'doorway001',
                                    doorwayName: '测试口1',
                                    doorwayType: 1,
                                    doorwayTypeStr: '入口',
                                    roadId: '1',
                                    roadNo: 'road001',
                                    roadName: '车道001',
                                    isMainRoad: 1,
                                    isMainRoadStr: '是',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                }, {
                                    doorId: "1",
                                    doorwayNo: 'doorway001',
                                    doorwayName: '测试口1',
                                    doorwayType: 1,
                                    doorwayTypeStr: '入口',
                                    roadId: '2',
                                    roadNo: 'road002',
                                    roadName: '车道002',
                                    isMainRoad: 0,
                                    isMainRoadStr: '否',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                }, {
                                    doorId: "2",
                                    doorwayNo: 'doorway002',
                                    doorwayName: '测试口2',
                                    doorwayType: 0,
                                    doorwayTypeStr: '出口',
                                    roadId: '2',
                                    roadNo: 'road003',
                                    roadName: '车道003',
                                    isMainRoad: 1,
                                    isMainRoadStr: '是',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                }, {
                                    doorId: "2",
                                    doorwayNo: 'doorway002',
                                    doorwayName: '测试口2',
                                    doorwayType: 0,
                                    doorwayTypeStr: '出口',
                                    roadId: '2',
                                    roadNo: 'road004',
                                    roadName: '车道004',
                                    isMainRoad: 0,
                                    isMainRoadStr: '否',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                }, {
                                    doorId: "3",
                                    doorwayNo: 'doorway003',
                                    doorwayName: '测试口3',
                                    doorwayType: 0,
                                    doorwayTypeStr: '出口',
                                    roadId: '2',
                                    roadNo: 'road005',
                                    roadName: '车道005',
                                    isMainRoad: 0,
                                    isMainRoadStr: '否',
                                    cameraIP: '192.168.0.1',
                                    deviceType: 1,
                                    deviceTypeStr: '车牌识别仪',
                                    deviceProducerStr: '设备厂家1',
                                    deviceProducer: 1,
                                },
                            ]
                        }
                    }
                    that.rowspan(that.doorwayTable.dataList);
                }, 1000)
            },
            //获取相同行
            rowspan(arrList) {
                this.spanArr = []
                arrList.forEach((item, index) => {
                    if (index === 0) {
                        this.spanArr.push(1);
                        this.position = 0;
                    } else {
                        if (item.doorId === arrList[index - 1].doorId && item.doorwayNo === arrList[index - 1].doorwayNo) {
                            this.spanArr.splice(this.position, 1, this.spanArr[this.position] + 1);
                            this.spanArr.push(0);
                        } else {
                            this.spanArr.push(1);
                            this.position = index;
                        }
                    }
                });
            },
            //合并行或列
            objectSpanMethod({row, column, rowIndex, columnIndex}) {
                if ([0, 1, 2, 10].indexOf(columnIndex) > -1) {
                    let rowspan = this.spanArr[rowIndex];
                    let colspan = 1;
                    return {
                        rowspan,
                        colspan
                    }
                }
            },

            addDoorway() {
                this.$refs.adjustDoorway.initData();
            },
            editDoorway(row) {
                this.$refs.adjustDoorway.initData(row);
            },
            delDoorway(row) {
                this.$confirm('一旦删除该进出口，关联车道也将删除，是否继续删除？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                })
            },

            createQRCode(row) {
                //生成二维码
            },
            editRoad(row) {
                this.$refs.adjustRoad.initData(row, row);
            },
            delRoad(row) {
                this.$confirm('是否删除该车道？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                })
            },
            addRoad(row) {
                this.$refs.adjustRoad.initData(row);
            },

            refreshTable() {
                this.doorwayQueryList();
            },
            //点击上一步
            prev() {
                this.$emit('prev-doorway', [this.areaId, this.areaNo])
            },
            //点击下一步
            nextDoorway(){
                this.$emit('next-doorway',[this.areaId, this.areaNo])
            },

        }
    }
</script>

<style scoped>
    .table-top-button {
        margin-bottom: 10px;
        float: right;
    }

    .footer-button {
        text-align: center;
        position: relative;
    }
</style>