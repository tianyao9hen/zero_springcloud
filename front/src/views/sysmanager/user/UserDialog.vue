<template>
    <div>
        <dialog-template ref="userDialog" :dialog-name="dialogName" width="50%"
                         @primaryClick="saveUser" @closeClick="closeDialog"
                         primary-button-title="保 存" :dialog-loading="dialogLoading">
            <template slot="body">
                <el-form :model="userInfo" ref="userForm" label-width="100px" size="medium" :rules="rules">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="用户账户" prop="username">
                                <el-input v-model="userInfo.username" placeholder="请填写用户账户"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户名称" prop="name">
                                <el-input v-model="userInfo.name" placeholder="请填写用户名称"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="用户电话" prop="phone">
                                <el-input v-model="userInfo.phone" placeholder="请填写用户电话"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户邮箱" prop="email">
                                <el-input v-model="userInfo.email" placeholder="请填写用户邮箱"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="状态" prop="status">
                                <el-select v-model="userInfo.status" filterable clearable
                                           placeholder="请选择状态" style="width: 100%;">
                                    <el-option v-for="item in statusList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item label="备注">
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="userInfo.remarks" placeholder="请填写备注"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
        </dialog-template>
    </div>
</template>

<script>
    import {getOptions} from 'network/commons';
    import {insertUser,updateUser} from 'network/sysManager';
    export default {
        name: "UserDialog",
        data(){
            return {
                //是否编辑
                isEdit: false,
                //弹窗是否加载中
                dialogLoading: false,
                //用户信息
                userInfo: {
                    username: '',
                    name: '',
                    phone: '',
                    email: '',
                    status: '1',
                    remarks: '',
                },
                //状态字典项
                statusList: [],
                //form表单规则
                rules: {
                    username: {required: true,message: '用户账户不能为空',trigger: 'blur'},
                    name: {required: true,message: '用户名称不能为空',trigger: 'blur'},
                    status: {required: true,message: '状态不能为空',trigger: 'blur'},
                }
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return "编辑用户"
                }
                return "新增用户"
            },
        },
        mounted() {
            this.queryUserStatus();
        },
        methods: {
            //初始化弹窗
            initData(rowData){
                if(rowData){
                    //修改
                    this.$nextTick(() => {
                        this.userInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    //新增
                    this.isEdit = false;
                }
                this.$refs['userDialog'].show();
            },
            //查询用户状态
            queryUserStatus(){
                getOptions({
                    groupCode: 'user_status'
                }).then(res => {
                    if(res.success){
                        this.statusList = Object.assign([],res.result);
                    }
                })
            },
            //保存用户
            saveUser(){
                this.$refs['userForm'].validate(valid => {
                    if(valid){
                        if(this.isEdit){
                            //编辑
                            this.dialogLoading = true;
                            updateUser(this.userInfo).then(res => {
                                if(res.success){
                                    this.closeDialog();
                                    this.$emit('refreshQueryPage');
                                    this.$message({
                                        message: '修改成功！',
                                        type: 'success'
                                    })
                                }else{
                                    this.$message.error(res.error);
                                }
                                this.dialogLoading = false;
                            }).catch(err => {
                                this.$message.error(err.error);
                                this.dialogLoading = false;
                            })
                        }else{
                            //新增
                            this.dialogLoading = true;
                            insertUser(this.userInfo).then(res => {
                                if(res.success){
                                    this.closeDialog();
                                    this.$emit('refreshQueryPage');
                                    this.$message({
                                        message: '新增成功！',
                                        type: 'success'
                                    })
                                }else {
                                    this.$message.error(res.error);
                                }
                                this.dialogLoading = false;
                            }).catch(err => {
                                this.$message.error(err.error);
                                this.dialogLoading = false;
                            })
                        }
                    }
                })
            },
            //关闭弹窗
            closeDialog(){
                this.$refs['userForm'].resetFields();
                this.$refs['userDialog'].close();
            }
        }
    }
</script>

<style scoped>

</style>