<template>
    <div>
        <dialog-template ref="authDialog" :dialog-name="dialogName"
                    @primaryClick="saveAuth" @closeClick="closeDialog" primary-button-title="保 存" width="50%">
            <template slot="body">
                <el-form :model="authInfo" ref="authForm" label-width="70px" size="medium">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="商户名称" prop="businessName">
                                <el-input v-model="authInfo.businessName" placeholder="请填写商户名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户名" prop="userName">
                                <el-input v-model="authInfo.userName" placeholder="请填写用户名"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row v-if="!isEdit">
                        <el-col :span="12">
                            <el-form-item label="密码" prop="password">
                                <el-input type="password" v-model="authInfo.password" placeholder="请填写密码"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="确认密码" prop="password2">
                                <el-input type="password" v-model="authInfo.password2" placeholder="请确认密码"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="联系人" prop="linkName">
                                <el-input v-model="authInfo.linkName" placeholder="请填写联系人"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="手机号" prop="telephone">
                                <el-input v-model="authInfo.telephone" placeholder="请填写手机号"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item label="地址" prop="address">
                                <el-input v-model="authInfo.address" placeholder="请填写地址"></el-input>
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
        name: "BusinessAuthDialog",
        data(){
            return {
                isEdit: false,
                authInfo: {}
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑商家信息'
                }
                return '新增授权商家'
            }
        },
        methods: {
            initData(rowData){
                if(rowData){
                    this.$nextTick(() => {
                        this.authInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    this.isEdit = false;
                }
                this.$refs.authDialog.show();
            },
            saveAuth(){
                this.$message({
                    type: 'success',
                    message: '保存成功!'
                });
            },
            closeDialog(){
                this.$refs.authForm.clearValidate()
                this.authInfo = {}
                this.$refs.authDialog.close()
            }
        }
    }
</script>

<style scoped>

</style>