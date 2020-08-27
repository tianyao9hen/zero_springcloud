<template>
    <div class="abnormal-record">
        <web-template :webName="webName" :tableData="tableData" @change-page="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="车牌号" prop="carNo" labelWidth="80px">
                                <el-input placeholder="请输入车牌号" clearable v-model="formData.carNo"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="入场时间" prop="entryDate" labelWidth="80px">
                                <el-date-picker v-model="formData.entryDate"
                                                type="datetime"
                                                placeholder="请选择时间"
                                                style="width: 100%">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="出场时间" prop="exitDate" labelWidth="80px">
                                <el-date-picker v-model="formData.exitDate" type="datetime" placeholder="请选择时间" style="width: 100%">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="停放区域" prop="areaNo" labelWidth="80px">
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
                        <el-col :span="6">
                            <el-form-item label="授权类型" prop="authorType" labelWidth="80px">
                                <el-select v-model="formData.authorType" filterable clearable placeholder="请选择授权类型" style="width: 100%;">
                                    <el-option v-for="item in authorTypeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" :offset="12" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage()">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-download" @click="exportData">导出</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                    <el-table-column prop="orderNo" label="订单编号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="areaNo" label="区域编号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="areaName" label="区域名称" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="carNo" label="车牌号码" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="authorTypeName" label="授权类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="entryAccessGateName" label="入口" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="entryAccessDate" label="入场时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="exitAccessGateName" label="出口" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="exitAccessDate" label="出场时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="parkingDuration" label="停车时长" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="officialCost" label="实收费用" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="abnormalReasonName" label="异常原因" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="120px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="showInfoDialog(scope.row)">
                                <i class="el-icon-share">详情</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>

        <abnormal-record-dialog ref="abnormalRecordDialog"/>
    </div>
</template>

<script>
    import AbnormalRecordDialog from "./components/abnormalRecord/AbnormalRecordDialog";
    export default {
        components: {AbnormalRecordDialog},
        name: 'AbnormalRecord',
        data(){
            return {
                webName: "异常记录",
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
                formData: {
                    carNo: '',
                    entryDate: '',
                    exitDate: '',
                    areaNo: '',
                    authorType: '',
                },
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            id: "1",
                            orderNo: "PO001",
                            areaNo:"qy001",
                            areaName: "测试区域1",
                            carNo: "鲁A00001",
                            authorType: "3",
                            authorTypeName: "免费车",
                            entryAccessGate: "rkd001",
                            entryAccessGateName: "门001",
                            entryAccessDate:"2020-01-01 01:01:01",
                            exitAccessGate:"ckd001",
                            exitAccessGateName:"门002",
                            exitAccessDate: "2020-01-01 01:01:01",
                            parkingDuration:"30分钟",
                            officialCost:"10.00",
                            abnormalReasonName:"无入场数据",
                        }, {
                            id: "1",
                            orderNo: "PO001",
                            areaNo:"qy001",
                            areaName: "测试区域1",
                            carNo: "鲁A00001",
                            authorType: "3",
                            authorTypeName: "免费车",
                            entryAccessGate: "rkd001",
                            entryAccessGateName: "门001",
                            entryAccessDate:"2020-01-01 01:01:01",
                            exitAccessGate:"ckd001",
                            exitAccessGateName:"门002",
                            exitAccessDate: "2020-01-01 01:01:01",
                            parkingDuration:"30分钟",
                            officialCost:"10.00",
                            abnormalReasonName:"无入场数据",
                        },  {
                            id: "1",
                            orderNo: "PO001",
                            areaNo:"qy001",
                            areaName: "测试区域1",
                            carNo: "鲁A00001",
                            authorType: "3",
                            authorTypeName: "免费车",
                            entryAccessGate: "rkd001",
                            entryAccessGateName: "门001",
                            entryAccessDate:"2020-01-01 01:01:01",
                            exitAccessGate:"ckd001",
                            exitAccessGateName:"门002",
                            exitAccessDate: "2020-01-01 01:01:01",
                            parkingDuration:"30分钟",
                            officialCost:"10.00",
                            abnormalReasonName:"无入场数据",
                        }, {
                            id: "1",
                            orderNo: "PO001",
                            areaNo:"qy001",
                            areaName: "测试区域1",
                            carNo: "鲁A00001",
                            authorType: "3",
                            authorTypeName: "免费车",
                            entryAccessGate: "rkd001",
                            entryAccessGateName: "门001",
                            entryAccessDate:"2020-01-01 01:01:01",
                            exitAccessGate:"ckd001",
                            exitAccessGateName:"门002",
                            exitAccessDate: "2020-01-01 01:01:01",
                            parkingDuration:"30分钟",
                            officialCost:"10.00",
                            abnormalReasonName:"无入场数据",
                        }, {
                            id: "1",
                            orderNo: "PO001",
                            areaNo:"qy001",
                            areaName: "测试区域1",
                            carNo: "鲁A00001",
                            authorType: "3",
                            authorTypeName: "免费车",
                            entryAccessGate: "rkd001",
                            entryAccessGateName: "门001",
                            entryAccessDate:"2020-01-01 01:01:01",
                            exitAccessGate:"ckd001",
                            exitAccessGateName:"门002",
                            exitAccessDate: "2020-01-01 01:01:01",
                            parkingDuration:"30分钟",
                            officialCost:"10.00",
                            abnormalReasonName:"无入场数据",
                        },
                    ]
                },
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
            showInfoDialog(row){
                this.$refs.abnormalRecordDialog.initDialog(row);
            }
        }
    }

</script>

<style scoped>

</style>