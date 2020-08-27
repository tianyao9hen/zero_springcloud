<template>
    <div class="pay-record">
        <web-template :webName="webName" :tableData="tableData" @change-page="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="支付渠道" prop="payChannel" labelWidth="80px">
                                <el-select v-model="formData.payChannel" filterable clearable placeholder="请选择支付渠道" style="width: 100%;">
                                    <el-option v-for="item in payChannelList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="支付类型" prop="payType" labelWidth="80px">
                                <el-select v-model="formData.payType" filterable clearable placeholder="请选择支付类型" style="width: 100%;">
                                    <el-option v-for="item in payTypeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="支付方式" prop="payMode" labelWidth="80px">
                                <el-select v-model="formData.payMode" filterable clearable placeholder="请选择支付方式" style="width: 100%;">
                                    <el-option v-for="item in payModeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
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
                    </el-row>
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
                                <el-date-picker v-model="formData.exitDate"
                                                type="datetime"
                                                placeholder="请选择时间"
                                                style="width: 100%">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage()">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="webTable">
                <record-item-flex :recordGroup="recordGroup" />
                <div class="table-tab">
                    <h2>支付详情</h2>
                    <el-divider></el-divider>
                    <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                        <el-table-column prop="payDate" label="支付时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="orderNo" label="订单编号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="carNo" label="车牌号" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="authorTypeName" label="授权类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="parkingDuration" label="停车时长" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="receivableCost" label="应收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="officialCost" label="实收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="creditCost" label="减免金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="couponCost" label="优惠卷金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="payChannelName" label="支付渠道" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="payTypeName" label="支付类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="payModeName" label="支付形式" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column label="操作" align="center" width="120px">
                            <template slot-scope="scope">
                                <el-button size="mini" type="text" @click="showInfoDialog(scope.row)">
                                    <i class="el-icon-share">详情</i>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </web-template>

        <pay-record-dialog ref="payRecordDialog"/>

    </div>
</template>

<script>
    import RecordItemFlex from "./components/payRecord/RecordItemFlex";
    import PayRecordDialog from "./components/payRecord/PayRecordDialog";
    import {getFirstDayOfMonth,getLastTimeForDate} from "components/common/utils/datetimeUtils";
    export default {
        components: {PayRecordDialog, RecordItemFlex},
        name: 'PayRecord',
        data(){
            return {
                webName: '支付记录',
                formData: {
                    payChannel: '',
                    payType: '',
                    payMode: '',
                    authorType: '',
                    carNo: '',
                    entryDate: getFirstDayOfMonth(new Date()),
                    exitDate: getLastTimeForDate(new Date()),
                },
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                    {
                        "id": "1",
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
                },
                recordGroup: [
                    {
                        title: '应收总金额',
                        value: '200.01元'
                    },{
                        title: '实收总金额',
                        value: '200.01元'
                    },{
                        title: '减免总金额',
                        value: '200.01元'
                    },{
                        title: '优惠卷总金额',
                        value: '200.01元'
                    },{
                        title: '支付宝总金额',
                        value: '200.01元'
                    },{
                        title: '微信总金额',
                        value: '200.01元'
                    },{
                        title: 'ETC总金额',
                        value: '200.01元'
                    }
                ],
                payChannelList: [
                    {
                        label: '自助支付',
                        value: '1',
                    }
                ],
                payTypeList: [
                    {
                        label: '出口支付',
                        value: '1'
                    }
                ],
                payModeList: [
                    {
                        label: '微信支付',
                        value: '0'
                    },{
                        label: '支付宝支付',
                        value: '1'
                    },{
                        label: 'ETC支付',
                        value: '2'
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
            showInfoDialog(row){
                this.$refs.payRecordDialog.initDialog(row);

            }
        },
    }

</script>

<style scoped>
    .table-tab {
        margin-top: 40px;
    }
    .el-divider {
        margin: 10px 0;
    }
</style>