<template>
    <dialog-template ref="recordDialog"
                     :is-show-title="false"
                     :is-show-bottom-empty="false"
                     :is-show-footer="false"
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
            <el-row class="dialog-row">
                <el-col :span="12" style="padding-right: 10px">
                    <div class="carImage">
                        <fill-image :src="recordData.url" :src-list="[recordData.url]">
                            <div slot="imageTextTL">
                                {{imageTitle}}
                            </div>
                            <div slot="imageTextBR">
                                {{imageDateTitle}}:{{recordData.accessDate}}
                            </div>
                        </fill-image>
                    </div>
                </el-col>
                <el-col :span="12" style="padding-left: 10px" class="info-col">
                    <el-row style="margin-bottom: 0px">
                        <el-col>
                            <span><h2>{{dialogTitle}}</h2></span>
                            <el-divider></el-divider>
                        </el-col>
                    </el-row>
                    <template v-if="dialogType == 'entry'">
                        <el-row>
                            <el-table :data="entryData" style="width: 100%" :show-header="false" stripe>
                                <el-table-column prop="title" label="title" align="left"></el-table-column>
                                <el-table-column prop="value" label="value" align="left"></el-table-column>
                            </el-table>
                        </el-row>
                    </template>
                    <template v-if="dialogType == 'exit'">
                        <el-row>
                            <el-table :data="exitData" style="width: 100%" :show-header="false" stripe>
                                <el-table-column prop="title" label="title" align="left"></el-table-column>
                                <el-table-column prop="value" label="value" align="left"></el-table-column>
                            </el-table>
                        </el-row>
                    </template>
                </el-col>
            </el-row>
            <el-row v-if="dialogType == 'exit'">
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
        components: {FillImage},
        name: 'RecordDialog',
        data(){
            return {
                dialogVisible: false,
                dialogType: "",
                dialogTitle: "",
                imageTitle: "",
                imageDateTitle: "",
                entryData: [],
                exitData: [],
                recordData: {
                    payDate: '2020-01-01 01:01:01',
                    creditCost: '10.00',
                    creditReason: '减免原因',
                    couponCost: '10.00',
                    payChannel: 1,
                    payChannelName: '自助支付',
                    payType: 1,
                    payTypeName: '出口支付',
                    payMode: 1,
                    payModeName: '微信支付',
                    isInvoice: 1,
                    isInvoiceName: '否'
                },
            }
        },
        computed: {
            tableData(){
                return [{
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
        },
        methods: {
            initDialog(data){
                if(data && data.type=="entry"){
                    Object.assign(this.recordData,data);
                    this.dialogType = data.type;
                    this.dialogTitle = "车辆进场详情";
                    this.imageTitle = "进场照片";
                    this.imageDateTitle = "进场时间";
                    this.getEntryData();
                }else if(data && data.type=="exit"){
                    Object.assign(this.recordData,data);
                    this.dialogType = data.type;
                    this.dialogTitle = "车辆出场详情";
                    this.imageTitle = "出场照片";
                    this.imageDateTitle = "出场时间";
                    this.getExitData();
                }
                this.$nextTick(() => {
                    this.$refs.recordDialog.show();
                })
            },
            getEntryData(){
                this.entryData = [
                    {
                        title: '进场时间：',
                        value: this.recordData.accessDate,
                    },
                    {
                        title: '进场车道编号：',
                        value: this.recordData.accessRoadName,
                    },
                    {
                        title: '进场口编号：',
                        value: this.recordData.accessGateName,
                    },
                    {
                        title: '进场票号：',
                        value: this.recordData.entryOrderNo
                    },
                ]
            },
            getExitData(){
                this.exitData = [
                    {
                        title: '出场时间：',
                        value: this.recordData.accessDate,
                    },
                    {
                        title: '出场车道编号：',
                        value: this.recordData.accessRoadName,
                    },
                    {
                        title: '出场口编号：',
                        value: this.recordData.accessGateName,
                    },
                    {
                        title: '出场票号：',
                        value: this.recordData.exitOrderNo,
                    },
                ]
            }
        }
    }

</script>

<style scoped>
    .empty {
        height: 20px;
    }
    .dialog-row {
        margin-bottom: 20px;
    }
    .carImage {
        width: 100%;
        height: 200px;
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