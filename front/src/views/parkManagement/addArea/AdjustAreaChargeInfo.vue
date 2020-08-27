<template>
    <dialog-template ref="dialogTemplate" :dialog-name="dialogName"
                     primary-button-title="保 存"
                     @primaryClick="saveAreaChargeInfo"
                     @closeClick="cancelAdjust" width="60%">
        <div slot="body">
            <el-form :model="chargeInformation" ref="chargeInfoForm" label-width="120px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="计费方案名称" prop="billingSchemeName">
                            <el-input v-model="chargeInformation.billingSchemeName" clearable placeholder="计费方案名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用户类型" prop="customerType">
                            <el-select v-model="chargeInformation.customerType" filterable clearable
                                       style="width: 100%;"
                                       placeholder="请选择用户类型">
                                <el-option v-for="item in customerTypeList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="计费开始时间" prop="billingStartTime">
                            <el-date-picker
                                    v-model="chargeInformation.billingStartTime"
                                    clearable
                                    type="datetime"
                                    placeholder="选择日期" style="width:100%">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="计费结束时间" prop='billingEndTime'>
                            <el-date-picker
                                    v-model="chargeInformation.billingEndTime"
                                    clearable
                                    type="datetime"
                                    placeholder="选择日期" style="width:100%">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="规则类型" prop="ruleType">
                            <el-select v-model="chargeInformation.ruleType" filterable clearable
                                       style="width: 100%;"
                                       placeholder="请选择规则类型">
                                <el-option v-for="item in ruleTypeList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="收费金额" prop="chargeAmount">
                            <el-input v-model="chargeInformation.chargeAmount" clearable placeholder="请填写收费金额"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustAreaChargeInfo",
        data() {
            return {
                chargeInformation: {},
                //计费规则
                ruleTypeList: [
                    {
                        value: 1,
                        label: '分时'
                    }, {
                        value: 2,
                        label: '分次'
                    }
                ],
                //用户类型
                customerTypeList: [
                    {
                        value: 1,
                        label: '访客'
                    },
                    {
                        value: 2,
                        label: '员工'
                    },
                ],
                isEdit: false,
                areaId: '',
                areaNo: '',
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑区域计费信息'
                }
                return '新增区域计费信息'
            }
        },
        methods: {

            initData(areaId,areaNo,rowData) {
                if (rowData) {
                    //编辑
                    this.chargeInformation = Object.assign({},rowData);
                    this.isEdit = true;
                } else {
                    //新增
                    this.isEdit = false;
                }
                this.areaId = areaId;
                this.areaNo = areaNo;
                this.$refs.dialogTemplate.show();
            },
            //区域计费信息编辑/新增保存按钮
            saveAreaChargeInfo(){
                this.$refs.chargeInfoForm.validate(valid => {
                    if(valid){
                        this.$emit('refreshAreaChargeTable');
                        this.$message({
                            type: 'success',
                            message: this.isEdit? '区域计费信息编辑成功！': '区域计费信息新增成功！'
                        })
                        this.chargeInformation = {}
                        this.$refs.chargeInfoForm.clearValidate()
                        this.$refs.dialogTemplate.close();
                    }
                })
            },
            //区域计费信息取消按钮
            cancelAdjust(){
                this.chargeInformation = {}
                this.$refs.chargeInfoForm.clearValidate()
                this.$refs.dialogTemplate.close();
            },
        },
    }
</script>

<style scoped>

</style>