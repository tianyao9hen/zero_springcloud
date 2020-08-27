<template>
    <div>
        <body-card card-name="停车场基本收费信息" style="margin-top: 10px">
            <template slot="headerButton">
                <template v-if="isBasicEdit">
                    <el-button @click="cancelButton">取 消</el-button>
                    <el-button class="el-icon-edit-outline" type="primary" @click="saveBasicBill"> 保 存</el-button>
                </template>
                <template v-if="!isBasicEdit">
                    <el-button class="el-icon-edit" type="primary" @click="editBasicBill"> 编 辑</el-button>
                </template>
            </template>
            <template slot="body">
                <el-form :model="parkingChargeInfo" ref="parkingChargeInfoForm" :rules="formRules" label-width="220px">
                    <el-row>
                        <el-col :span="7">
                            <el-form-item label="首次计费周期(小时)" prop="firstBillingCycle" label-width="150px">
                                <el-input v-model="parkingChargeInfo.firstBillingCycle" clearable
                                          placeholder="请输入首次计费周期(小时)" :disabled="!isBasicEdit"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="不足计费周期是否入舍">
                                <el-select v-model="parkingChargeInfo.isRoundNotCycle" filterable clearable
                                           style="width: 100%"
                                           :disabled="!isBasicEdit" placeholder="请选择不足计费周期是否入舍">
                                    <el-option v-for="item in isRoundNotCycleList" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="最低消费">
                                <el-input v-model="parkingChargeInfo.minimumConsumption" clearable
                                          :disabled="!isBasicEdit" placeholder="请输入最低消费"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="7">
                            <el-form-item label="入场免费时长(分钟)" prop="freeMinutes" label-width="150px">
                                <el-input v-model="parkingChargeInfo.freeMinutes" clearable placeholder="请输入入场免费时长"
                                          :disabled="!isBasicEdit"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="免费时长是否算入首次计费" prop="isFreeToFirst">
                                <el-select v-model="parkingChargeInfo.isFreeToFirst" filterable clearable
                                           style="width: 100%"
                                           :disabled="!isBasicEdit" placeholder="请确定免费时长是否算入首次计费">
                                    <el-option v-for="item in isFreeToFirstList" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="场内支付免费离场时长(分钟)" prop="freeDepartureMinutes">
                                <el-input v-model="parkingChargeInfo.freeDepartureMinutes" clearable
                                          placeholder="请输入场内支付免费离场时长"
                                          :disabled="!isBasicEdit"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="7">
                            <el-form-item label="收费入舍" label-width="150px" prop="billRound">
                                <el-select v-model="parkingChargeInfo.billRound" filterable clearable
                                           style="width: 100%"
                                           :disabled="!isBasicEdit" placeholder="请选择收费入舍">
                                    <el-option v-for="item in billRoundList" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="计费类型" prop="billRound">
                                <el-select v-model="parkingChargeInfo.chargeType" filterable clearable
                                           style="width: 100%"
                                           :disabled="!isBasicEdit" placeholder="请选择计费类型">
                                    <el-option v-for="item in chargeTypeList" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="出口车辆托管异常处理" prop="exceptionHandling">
                                <el-select v-model="parkingChargeInfo.exceptionHandling" filterable clearable
                                           style="width: 100%"
                                           :disabled="!isBasicEdit" placeholder="请选择出口车辆托管异常处理">
                                    <el-option v-for="item in exceptionHandlingList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
        </body-card>
        <body-card card-name="区域收费信息" style="margin-top: 20px">
            <template slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="addOrEdit()">新增</el-button>
            </template>
            <template slot="body">
                <el-table :data="areaChargeInfoTableData.dataList" style="margin-bottom: 20px" stripe border>
                    <el-table-column prop="billingSchemeNo" label="计费方案编码" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="billingSchemeName" label="计费方案名称" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="customerTypeStr" label="用户类型" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="billingStartTime" label="计费开始时间" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="billingEndTime" label="计费结束时间" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="ruleTypeStr" label="规则类型" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="chargeAmount" label="收费金额" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column label="操作" align="center" width="120px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="addOrEdit(scope.row)">
                                <i class="el-icon-edit">编辑</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="del()">
                                <i class="el-icon-delete">删除</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <pagination :currentPage="areaChargeInfoTableData?areaChargeInfoTableData.currentPage:undefined"
                            :pageSize="areaChargeInfoTableData?areaChargeInfoTableData.pageSize:undefined"
                            :total="areaChargeInfoTableData?areaChargeInfoTableData.total:undefined"
                            @change-page="queryAreaChargeInfo"/>
            </template>
        </body-card>
        <div class="footer-button"  v-if="isAdd">
            <el-button style="margin-top: 12px;" @click="prevBilling">上一步</el-button>
            <el-button class="el-icon-edit-outline" type="primary" @click="submitBilling">提 交</el-button>
        </div>
        <adjust-area-charge-info ref="adjustAreaChargeInfo"/>
    </div>
</template>

<script>
    import AdjustAreaChargeInfo from "./AdjustAreaChargeInfo";
    import BodyCard from "components/webTemplate/cardTemplate/BodyCard";

    export default {
        name: "BillingBinding",
        components: {BodyCard, AdjustAreaChargeInfo},
        data() {
            return {
                parkingChargeInfo: {},
                areaChargeInfoTableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [],
                },
                //是否入舍
                isRoundNotCycleList: [
                    {
                        value: 1,
                        label: '入'
                    }, {
                        value: 0,
                        label: '不入'
                    }
                ],
                //免费时长是否算入首次计费
                isFreeToFirstList: [
                    {
                        value: 1,
                        label: '计费'
                    }, {
                        value: 0,
                        label: '不计费'
                    }
                ],
                //收费入舍
                billRoundList: [
                    {
                        value: '01',
                        label: '不处理'
                    }, {
                        value: '02',
                        label: '计入'
                    }, {
                        value: '03',
                        label: '不计入'
                    }
                ],
                //计费类型
                chargeTypeList: [
                    {
                        value: '01',
                        label: '分时分段计费'
                    }
                ],
                //出口车辆托管异常处理
                exceptionHandlingList: [
                    {
                        value: '01',
                        label: '最低收费'
                    }, {
                        value: '02',
                        label: '等待管理人员'
                    }
                ],
                //是否编辑
                isBasicEdit: false,
            }
        },
        props: {
            areaId: '',
            areaNo: '',
            isAdd:false,
        },
        mounted() {
            if (this.areaId && this.areaNo) {
                this.queryParkingChargeInfo();
                this.queryAreaChargeInfo();
            }
        },
        methods: {
            //编辑与保存取消得切换
            editBasicBill() {
                //开启编辑停车场支付配置
                this.isBasicEdit = true;
            },
            saveBasicBill() {
                //保存停车场支付配置
                this.$refs.parkingChargeInfoForm.validate(valid => {
                    if (valid) {
                        this.queryParkingChargeInfo();
                        this.$refs.parkingChargeInfoForm.clearValidate();
                        this.$message({
                            type: 'success',
                            message: '保存成功!'
                        });
                        this.isBasicEdit = false;
                    }
                })
            },
            cancelButton() {
                //取消编辑停车场支付配置
                this.$refs.parkingChargeInfoForm.resetFields();
                this.parkingChargeInfo = {}
                this.$refs.parkingChargeInfoForm.clearValidate();
                this.isBasicEdit = false;
            },

            queryParkingChargeInfo() {
                let that = this;
                setTimeout(function () {
                    that.parkingChargeInfo = {}
                }, 1000)
            },
            queryAreaChargeInfo() {
                let that = this;
                setTimeout(function () {
                    that.areaChargeInfoTableData = {
                        total: 200,
                        currentPage: 1,
                        pageSize: 10,
                        dataList: [
                            {
                                billingSchemeNo: '101',
                                billingSchemeName: '测试计费方案',
                                customerType: 1,
                                customerTypeStr: '访客',
                                billingStartTime: '2020-01-01',
                                billingEndTime: '2020-01-01',
                                ruleType: 1,
                                ruleTypeStr: '分时',
                                chargeAmount: '100',
                            },
                            {
                                billingSchemeNo: '101',
                                billingSchemeName: '测试计费方案',
                                customerType: 1,
                                customerTypeStr: '访客',
                                billingStartTime: '2020-01-01',
                                billingEndTime: '2020-01-01',
                                ruleType: 1,
                                ruleTypeStr: '分时',
                                chargeAmount: '100',
                            },
                            {
                                billingSchemeNo: '101',
                                billingSchemeName: '测试计费方案',
                                customerType: 1,
                                customerTypeStr: '访客',
                                billingStartTime: '2020-01-01',
                                billingEndTime: '2020-01-01',
                                ruleType: 1,
                                ruleTypeStr: '分时',
                                chargeAmount: '100',
                            },
                            {
                                billingSchemeNo: '101',
                                billingSchemeName: '测试计费方案',
                                customerType: 1,
                                customerTypeStr: '访客',
                                billingStartTime: '2020-01-01',
                                billingEndTime: '2020-01-01',
                                ruleType: 1,
                                ruleTypeStr: '分时',
                                chargeAmount: '100',
                            },
                            {
                                billingSchemeNo: '101',
                                billingSchemeName: '测试计费方案',
                                customerType: 1,
                                customerTypeStr: '访客',
                                billingStartTime: '2020-01-01',
                                billingEndTime: '2020-01-01',
                                ruleType: 1,
                                ruleTypeStr: '分时',
                                chargeAmount: '100',
                            },
                        ]
                    }
                }, 1000)
            },

            addOrEdit(rowData) {
                this.$refs.adjustAreaChargeInfo.initData(this.areaId, this.areaNo, rowData);
            },
            del() {
                this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {

                });
            },

            prevBilling() {
                this.$emit('prev-billing', [this.areaId, this.areaNo])
            },
            //点击下一步
            submitBilling(){
                this.$emit('submit-billing',[this.areaId, this.areaNo])
            },


        },
        computed: {
            formRules() {
                if (this.isEdit) {
                    return this.rules;
                } else {
                    return {};
                }
            },
        },
    }
</script>

<style scoped>
    .footer-button {
        text-align: center;
        position: relative;
    }
</style>