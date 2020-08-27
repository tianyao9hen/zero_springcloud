<template>
    <dialog-template ref="payRecordDialog"
                     :is-show-title="false"
                     :is-show-footer="false"
                     :is-show-bottom-empty="false"
                     width="800px">
        <template slot="body">
            <el-row class="dialog-row">
                <el-col :span="6">
                    <span>订单号： </span>
                    <span>{{recordData.orderNo}}</span>
                </el-col>
                <el-col :span="6">
                    <span>停放区域： </span>
                    <span>{{recordData.areaName}}</span>
                </el-col>
                <el-col :span="6">
                    <span>停放车位： </span>
                    <span>{{recordData.parkingSpotNo}}</span>
                </el-col>
                <el-col :span="6">
                    <span>车牌号： </span>
                    <span>{{recordData.carNo}}</span>
                </el-col>
            </el-row>
            <el-row class="dialog-row"  style="margin-bottom: 30px">
                <el-col :span="12" style="padding-right: 10px">
                    <div class="carImage">
                        <fill-image :src="recordData.entryUrl" :src-list="[recordData.entryUrl]">
                            <div slot="imageTextTL">
                                进场照片
                            </div>
                            <div slot="imageTextBR">
                                进场时间:{{recordData.entryAccessDate}}
                            </div>
                        </fill-image>
                    </div>
                </el-col>
                <el-col :span="12" style="padding-left: 10px" class="info-col">
                    <el-row style="margin-bottom: 0px">
                        <el-col>
                            <span><h2>车辆进场详情</h2></span>
                            <el-divider></el-divider>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-table :data="entryData" style="width: 100%" :show-header="false" stripe>
                            <el-table-column prop="title" label="title" align="left"></el-table-column>
                            <el-table-column prop="value" label="value" align="left"></el-table-column>
                        </el-table>
                    </el-row>
                </el-col>
            </el-row>
            <el-row class="dialog-row">
                <el-col :span="12" style="padding-right: 10px">
                    <div class="carImage">
                        <fill-image :src="recordData.exitUrl" :src-list="[recordData.exitUrl]">
                            <div slot="imageTextTL">
                                出场照片
                            </div>
                            <div slot="imageTextBR">
                                出场时间:{{recordData.exitAccessDate}}
                            </div>
                        </fill-image>
                    </div>
                </el-col>
                <el-col :span="12" style="padding-left: 10px" class="info-col">
                    <el-row style="margin-bottom: 0px">
                        <el-col>
                            <span><h2>车辆出场详情</h2></span>
                            <el-divider></el-divider>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-table :data="exitData" style="width: 100%" :show-header="false" stripe>
                            <el-table-column prop="title" label="title" align="left"></el-table-column>
                            <el-table-column prop="value" label="value" align="left"></el-table-column>
                        </el-table>
                    </el-row>
                </el-col>
            </el-row>
            <el-row>
                <el-row style="margin-bottom: 0px">
                    <el-col>
                        <span><h2>支付详情</h2></span>
                        <el-divider></el-divider>
                    </el-col>
                </el-row>
                <el-table :data="tableData" style="margin-bottom: 20px;" border>
                    <el-table-column prop="payDate" label="支付时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="parkingDuration" label="停车时长"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="receivableCost" label="应收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="officialCost" label="实收金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="creditCost" label="减免金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="creditReason" label="减免原因" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="couponCost" label="优惠卷金额" align="center" width="100%" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="payChannelName" label="支付渠道" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="payTypeName" label="支付类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="payModeName" label="支付方式" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="isInvoiceName" label="是否开具发票" width="100%" align="center" :show-overflow-tooltip="true"></el-table-column>
                </el-table>
            </el-row>
        </template>
    </dialog-template>
</template>

<script>
    import FillImage from "components/webTemplate/imageTemplate/FillImage";

    export default {
        name: 'PayRecordDialog',
        components: {FillImage},
        data(){
            return {
                dialogVisible: false,
                entryData: [],
                exitData: [],
                recordData: {
                    area: 'csqy001',
                    areaName: '测试区域',
                    parkingSpotNo: 'CW0001',
                    entryUrl: "",
                    entryAccessDate: '2020-01-01 01:01:01',
                    entryAccessRoad: "rk101",
                    entryAccessRoadName: "入口101",
                    entryAccessGate: "rkm101",
                    entryAccessGateName: "门101",
                    entryOrderNo: "jc001",
                    exitUrl: "",
                    exitAccessDate: '2020-01-01 01:01:01',
                    exitAccessRoad: "rk101",
                    exitAccessRoadName: "入口101",
                    exitAccessGate: "rkm101",
                    exitAccessGateName: "门101",
                    exitOrderNo: "jc001",
                    isInvoice: 1,
                    isInvoiceName: "否",
                    creditReason: "减免原因"
                },
                tableData: []
            }
        },
        methods: {
            initDialog(data){
                Object.assign(this.recordData,data);
                this.getTableData();
                this.getEntryData();
                this.getExitData();
                this.$nextTick(() => {
                    //this.dialogVisible = true;
                    this.$refs.payRecordDialog.show();
                })
            },
            getTableData(){
                this.tableData = [{
                    payDate: this.recordData.payDate,
                    parkingDuration: this.recordData.parkingDuration,
                    receivableCost: this.recordData.receivableCost,
                    officialCost: this.recordData.officialCost,
                    creditCost: this.recordData.creditCost,
                    creditReason: this.recordData.creditReason,
                    couponCost: this.recordData.couponCost,
                    payChannelName: this.recordData.payChannelName,
                    payTypeName: this.recordData.payTypeName,
                    payModeName: this.recordData.payModeName,
                    isInvoiceName: this.recordData.isInvoiceName,
                }]
            },
            getEntryData(){
                this.entryData = [
                    {
                        title: '进场时间：',
                        value: this.recordData.entryAccessDate,
                    },{
                        title: '进场车道编号：',
                        value: this.recordData.entryAccessRoadName,
                    },{
                        title: '进场口编号：',
                        value: this.recordData.entryAccessGateName,
                    },{
                        title: '进场票号：',
                        value: this.recordData.entryOrderNo,
                    },
                ]
            },
            getExitData(){
                this.exitData = [
                    {
                        title: '出场时间：',
                        value: this.recordData.exitAccessDate,
                    },
                    {
                        title: '出场车道编号：',
                        value: this.recordData.entryAccessRoadName,
                    },
                    {
                        title: '出场口编号：',
                        value: this.recordData.entryAccessGateName,
                    },
                    {
                        title: '停车时长：',
                        value: this.recordData.parkingDuration,
                    },
                    {
                        title: '缴费方式：',
                        value: this.recordData.payModeName,
                    },
                ]
            }
        },
    }

</script>

<style scoped>
    .dialog-row {
        margin-bottom: 20px;
    }
    .carImage {
        width: 100%;
        height: 250px;
    }
    .info-col .el-row {
        margin-bottom: 20px;
    }
    .el-divider {
        margin: 10px 0;
    }
    .image-text {
        position:absolute;
        bottom: 0;
        right: 0;
    }
</style>