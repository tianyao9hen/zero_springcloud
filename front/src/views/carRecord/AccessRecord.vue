<template>
    <div class="access-record">
        <web-template :webName="webName" :tableData="tableData" @change-page="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="车牌号" prop="carNo" labelWidth="80px">
                                <el-input placeholder="请输入车牌号" clearable v-model="formData.carNo" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车位编号" prop="parkingSpotNo" labelWidth="80px">
                                <el-input placeholder="请输入车位编号" clearable v-model="formData.parkingSpotNo"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="授权类型" prop="authorType" labelWidth="80px">
                                <el-select v-model="formData.authorType" filterable clearable placeholder="请选择授权类型" style="width: 100%;">
                                    <el-option v-for="item in authorTypeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="区域" prop="areaNo" labelWidth="80px">
                                <el-select v-model="formData.areaNo" filterable clearable placeholder="请选择区域" style="width: 100%;">
                                    <el-option v-for="item in areaList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>

                        <el-col :span="12">
                            <el-form-item label="出入时间" prop="accessDateList" labelWidth="80px">
                                <el-date-picker
                                        v-model="formData.accessDateList"
                                        type="datetimerange"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        range-separator="至"
                                        style="width: 100%"
                                >
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" :offset="6" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage(1,tableData.pageSize)">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>

                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-download" @click="exportData">导出</el-button>
            </div>
            <div slot="webTable">
                <el-tabs v-model="activeName" @tab-click="clickTab()">
                    <el-tab-pane label="进场管理" name="entryManage" >
                        <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                            <el-table-column prop="carNo" label="车牌号" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="accessDate" label="入场时间"  align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="accessRoadName" label="入口车道" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="areaName" label="区域" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="parkingSpotNo" label="车位编号" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="authorTypeName" label="授权类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="statusName" label="状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column label="操作" align="center" width="120px">
                                <template slot-scope="scope">
                                    <el-button size="mini" type="text" @click="showInfoDialog(scope.row)">
                                        <i class="el-icon-share">详情</i>
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane label="出场管理" name="exitManage">
                        <el-table :data="tableData2.dataList" style="margin-bottom: 20px;" border>
                            <el-table-column prop="carNo" label="车牌号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="accessDate" label="出场时间"  align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="receivableCost" label="应收费用" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="officialCost" label="实收费用" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="parkingDuration" label="停车时长" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="accessRoadName" label="出口车道" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="areaName" label="区域" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="parkingSpotNo" label="车位编号" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="authorTypeName" label="授权类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column prop="statusName" label="状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                            <el-table-column label="操作" align="center" width="120px">
                                <template slot-scope="scope">
                                    <el-button size="mini" type="text" @click="showInfoDialog(scope.row)">
                                        <i class="el-icon-share">详情</i>
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </web-template>

        <record-dialog ref="recordDialog"/>
    </div>
</template>


<script>
    import RecordDialog from "./components/accessRecord/RecordDialog";
    import {getFirstDayOfWeek,getLastTimeForDate} from "components/common/utils/datetimeUtils";

    export default {
        components: {RecordDialog},
        name: 'AccessRecord',
        data() {
            return {
                webName: "进出记录",
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "rk101",
                            "accessRoadName": "入口101",
                            "accessGate": "rkd001",
                            "accessGateName": "门001",
                            "entryOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "authorTypeName": "免费车",
                            "status": "1",
                            "statusName": "进场",
                        }, {
                            "id": "1",
                            "orderNo": "PO002",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "rk101",
                            "accessRoadName": "入口101",
                            "accessGate": "rkd001",
                            "accessGateName": "门001",
                            "entryOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "authorTypeName": "免费车",
                            "status": "1",
                            "statusName": "进场",
                        },  {
                            "id": "1",
                            "orderNo": "PO003",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "rk101",
                            "accessRoadName": "入口101",
                            "accessGate": "rkd001",
                            "accessGateName": "门001",
                            "entryOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "authorTypeName": "免费车",
                            "status": "1",
                            "statusName": "进场",
                        }, {
                            "id": "1",
                            "orderNo": "PO004",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "rk101",
                            "accessRoadName": "入口101",
                            "accessGate": "rkd001",
                            "accessGateName": "门001",
                            "entryOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "authorTypeName": "免费车",
                            "status": "1",
                            "statusName": "进场",
                        }, {
                            "id": "1",
                            "orderNo": "PO005",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "rk101",
                            "accessRoadName": "入口101",
                            "accessGate": "rkd001",
                            "accessGateName": "门001",
                            "entryOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "authorTypeName": "免费车",
                            "status": "1",
                            "statusName": "进场",
                        },
                    ]
                },
                tableData2: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "ck101",
                            "accessRoadName": "出口101",
                            "accessGate": "ckd001",
                            "accessGateName": "门001",
                            "exitOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "receivableCost": "10.00",
                            "officialCost": "10.00",
                            "parkingDuration": "30分钟",
                            "authorTypeName": "免费车",
                            "status": "2",
                            "statusName": "出场",
                        }, {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "ck101",
                            "accessRoadName": "出口101",
                            "accessGate": "ckd001",
                            "accessGateName": "门001",
                            "exitOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "receivableCost": "10.00",
                            "officialCost": "10.00",
                            "parkingDuration": "30分钟",
                            "authorTypeName": "免费车",
                            "status": "2",
                            "statusName": "出场",
                        },  {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "ck101",
                            "accessRoadName": "出口101",
                            "accessGate": "ckd001",
                            "accessGateName": "门001",
                            "exitOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "receivableCost": "10.00",
                            "officialCost": "10.00",
                            "parkingDuration": "30分钟",
                            "authorTypeName": "免费车",
                            "status": "2",
                            "statusName": "出场",
                        }, {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "ck101",
                            "accessRoadName": "出口101",
                            "accessGate": "ckd001",
                            "accessGateName": "门001",
                            "exitOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "receivableCost": "10.00",
                            "officialCost": "10.00",
                            "parkingDuration": "30分钟",
                            "authorTypeName": "免费车",
                            "status": "2",
                            "statusName": "出场",
                        }, {
                            "id": "1",
                            "orderNo": "PO001",
                            "carNo": "鲁A00001",
                            "accessDate": "2020-01-01 01:01:01",
                            "accessRoad": "ck101",
                            "accessRoadName": "出口101",
                            "accessGate": "ckd001",
                            "accessGateName": "门001",
                            "exitOrderNo": "jc001",
                            "area": "qy001",
                            "areaName": "测试区域1",
                            "parkingSpotNo": "CW00101",
                            "authorType": "3",
                            "receivableCost": "10.00",
                            "officialCost": "10.00",
                            "parkingDuration": "30分钟",
                            "authorTypeName": "免费车",
                            "status": "2",
                            "statusName": "出场",
                        },
                    ]
                },
                formData: {
                    carNo: '',
                    authorType: '',
                    accessDateList: [getFirstDayOfWeek(new Date()),getLastTimeForDate(new Date())],
                    areaNo: '',
                    parkingSpotNo: '',
                },
                areaList: [
                    {
                        label: '测试区域1',
                        value: '001'
                    },{
                        label: '测试区域2',
                        value: '002'
                    }
                ],
                authorTypeList: [
                    {
                        label: '月租车',
                        value: '001',
                    },
                    {
                        label: '临时车',
                        value: '002',
                    },
                    {
                        label: '免费车',
                        value: '003',
                    },
                ],
                activeName: 'entryManage',
                dialogEntryRecord: false,
            }
        },
        methods: {
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
            rest(){
                //重置
                this.$refs.queryForm.resetFields()
            },
            exportData(){
                //导出
            },
            clickTab(){
                //点击标签页
                this.queryPage(1,10);
            },
            showInfoDialog(row){
                if(this.activeName === 'entryManage'){
                    row.type = 'entry';
                }else if(this.activeName === 'exitManage'){
                    row.type = 'exit'
                }
                this.$refs.recordDialog.initDialog(row);
            }
        }
    }

</script>

<style scoped>

</style>