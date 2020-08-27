<template>
    <div class="invoice-info">
        <web-template simple no-table web-name="发票信息">
            <template slot="headerButton">
                <template v-if="!isEdit">
                    <el-button type="primary" @click="editInvoiceInfo">
                        <i class="el-icon-edit"> 编 辑</i>
                    </el-button>
                </template>
                <template v-if="isEdit">
                    <el-button @click="resetEdit">
                        重 置
                    </el-button>
                    <el-button @click="cancelEdit">
                        取消
                    </el-button>
                    <el-button type="primary" @click="saveEdit">
                        <i class="el-icon-edit-outline"> 保 存</i>
                    </el-button>
                </template>
            </template>
            <template slot="body">
                <el-form layout="inline" ref="invoiceInfoForm" :model="invoiceInfo" labelWidth="150px">
                    <div class="invoice-form-box">
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="服务名称：" prop="name">
                                    <el-input v-model="invoiceInfo.name" :readonly="!isEdit" clearable placeholder="请填写服务名称"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="是否可开票：" prop="canInvoice">
                                    <el-radio-group :disabled="!isEdit" v-model="invoiceInfo.canInvoice">
                                        <el-radio :label=0>不可开票</el-radio>
                                        <el-radio :label=1>可开票</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="税率：" prop=taxRate>
                                    <el-input v-model="invoiceInfo.taxRate" :readonly="!isEdit" clearable placeholder="请填写税率"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="第三方接口类型：" prop="thirdParty">
                                    <el-radio-group :disabled="!isEdit" v-model="invoiceInfo.thirdParty">
                                        <el-radio v-for="item in thirdPartyList" :label=item.value>{{item.label}}</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col>
                                <h2>销售方信息</h2>
                                <el-divider class="invoice-divider"></el-divider>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="纳税人识别号：" prop="taxpayerNumber">
                                    <el-input v-model="invoiceInfo.taxpayerNumber" :readonly="!isEdit" clearable placeholder="请填写纳税人识别号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="销售方名称：" prop="sellerName">
                                    <el-input v-model="invoiceInfo.sellerName" :readonly="!isEdit" clearable placeholder="请填写销售方名称"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="销售方地址：" prop="address">
                                    <el-input v-model="invoiceInfo.address" :readonly="!isEdit" clearable placeholder="请填写销售方地址"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="销售方电话：" prop="telephone">
                                    <el-input v-model="invoiceInfo.telephone" :readonly="!isEdit" clearable placeholder="请填写销售方电话"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="开户行：" prop="depositBank">
                                    <el-input v-model="invoiceInfo.depositBank" :readonly="!isEdit" clearable placeholder="请填写开户行"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="账号：" prop="bankAccount">
                                    <el-input v-model="invoiceInfo.bankAccount" :readonly="!isEdit" clearable placeholder="请填写账号"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col>
                                <el-form-item label="备注：" prop="remarks">
                                    <el-input type="textarea" :readonly="!isEdit" :autosize="{ minRows: 4, maxRows: 10}"
                                              placeholder="请输入备注"
                                              v-model="invoiceInfo.remarks">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </div>
                </el-form>
            </template>
        </web-template>
    </div>
</template>

<script>
    export default {
        name: "InvoiceInfo",
        data(){
            return {
                isEdit: false,
                invoiceInfo: {
                    name: '',
                    canInvoice: 1,
                    taxRate: '',
                    thirdParty: '',
                    taxpayerNumber: '',
                    sellerName: '',
                    address: '',
                    telephone: '',
                    depositBank: '',
                    bankAccount: '',
                    remarks: '',
                },
                //第三方接口
                thirdPartyList: [
                    {
                        label: '百旺',
                        value: 0
                    },
                    {
                        label: '爱信诺',
                        value: 1
                    },
                    {
                        label: '国信',
                        value: 2
                    },
                    {
                        label: '容津',
                        value: 3
                    },
                ]
            }
        },
        mounted(){
            this.queryData()
        },
        methods: {
            queryData(){
                this.invoiceInfo = {
                    name: '',
                    canInvoice: 1,
                    taxRate: '',
                    thirdParty: '',
                    taxpayerNumber: '',
                    sellerName: '',
                    address: '',
                    telephone: '',
                    depositBank: '',
                    bankAccount: '',
                    remarks: '',
                }
            },
            editInvoiceInfo(){
                //开启编辑
                this.isEdit = true;
            },
            resetEdit(){
                //重置
                this.$refs.invoiceInfoForm.resetFields();
                this.isEdit = false;
            },
            cancelEdit(){
                //取消
                this.resetEdit()
            },
            saveEdit(){
                //保存
                this.$confirm('是否保存发票信息?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                    this.queryData();
                    this.isEdit = false;
                })
            }
        }
    }
</script>

<style scoped>
    .invoice-form-box {
        margin: 0 50px 0 20px;
    }
    .invoice-divider {
        margin: 10px 0 20px;
    }
</style>