<template>
    <div>
        <dialog-template ref="businessDialog" :dialog-name="dialogName"
                         @primaryClick="saveAuth" @closeClick="closeDialog" primary-button-title="保 存" width="50%">
            <template slot="body">
                <el-form :model="businessInfo" ref="authForm" label-width="110px" size="medium" style="margin-left: -5%">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="商家名称" >
                                <el-input v-model="businessInfo.businessName" placeholder="请填写商户名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="区域">
                                <el-input v-model="businessInfo.area" placeholder="请填写用户名"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="区域">
                                <el-input v-model="businessInfo.area" placeholder="请填写联系人"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="充值金额" >
                                <el-input v-model="businessInfo.rechargeAmount" placeholder="请填写手机号"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="最低可用时长" >
                                <el-input-number v-model="businessInfo.lowestAvailableTime" style="width: 100%" controls-position="right"  :min="1" :max="10"></el-input-number>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="单张金额" >
                                <el-input v-model="businessInfo.solaMoney"  placeholder="请填写单张金额"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item label="活动形式">
                                <el-input type="textarea" v-model="businessInfo.remarks"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                </el-form>
            </template>
        </dialog-template>
    </div>
</template>

<script>
    export default {
        name: "BusinessManagementDialog",
        data(){
            return {
                isEdit: false,
                businessInfo: {}
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑业务'
                }
                return '新增业务'
            }
        },
        methods: {
            initData(rowData){
                if(rowData){
                    this.$nextTick(() => {
                        this.businessInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    this.isEdit = false;
                }
                this.$refs.businessDialog.show();
            },
            saveAuth(){
                this.$message({
                    type: 'success',
                    message: '保存成功!'
                });
                this.$refs.businessDialog.close()
            },
            closeDialog(){
                this.$refs.authForm.clearValidate()
                this.businessInfo = {}
                this.$refs.businessDialog.close()
            }
        }
    }
</script>

<style scoped>

</style>