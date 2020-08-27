<template>
    <dialog-template ref="reportFormInfo" dialog-name="报表信息详情" width="40%"
                     @closeClick="closeDialog" :is-show-footer="false">
        <template slot="body">
            <el-form :model="reportInfo" label-width="100px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="订单编号:">
                            <el-input v-model="reportInfo.orderNo" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="停车区域:">
                            <el-input v-model="reportInfo.areaName" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车牌号:">
                            <el-input v-model="reportInfo.carNo" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="停车时长:">
                            <el-input v-model="reportInfo.parkingDuration" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="应付费用:">
                            <el-input v-model="reportInfo.receivableCost" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="实付费用:">
                            <el-input v-model="reportInfo.officialCost" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="减免金额:">
                            <el-input v-model="reportInfo.creditCost" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="优惠卷金额:">
                            <el-input v-model="reportInfo.officialCost" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="支付形式:">
                            <el-input v-model="reportInfo.payModeName" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </dialog-template>
</template>

<script>
    export default {
        name: "reportFormInfo",
        data(){
            return {
                reportInfo: {}
            }
        },
        methods: {
            init(data){
                if(data){
                    this.$nextTick(() => {
                        this.reportInfo = Object.assign({},data);
                    })
                    this.$refs.reportFormInfo.show();
                }
            },
            closeDialog(){
                this.reportInfo = {};
                this.$refs.reportFormInfo.close();
            }
        }
    }
</script>

<style scoped>

</style>