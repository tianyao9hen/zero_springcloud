<template>
    <div>
        <dialog-template ref="authItemDialog" :dialog-name="dialogName" width="50%"
                         @primaryClick="saveAuthItem"
                         @closeClick="closeDialog"
                         primary-button-title="保 存"
                         :dialog-loading="dialogLoading">
            <template slot="body">
                <el-form :model="authItemInfo" ref="authItemForm" label-width="100px" size="medium" :rules="rules">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="权限名称" prop="name">
                                <el-input v-model="authItemInfo.name" placeholder="请填写权限名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="权限类型" prop="type">
                                <el-select v-model="authItemInfo.type" filterable clearable
                                           placeholder="请选择权限类型" style="width: 100%;">
                                    <el-option v-for="item in typeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row v-if="authItemInfo.type !== '0'">
                        <el-col :span="12">
                            <el-form-item label="权限标识或url" prop="url">
                                <el-input v-model="authItemInfo.url" placeholder="请填写权限标识或url"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="父权限" prop="pid">
                                <el-select v-model="authItemInfo.pid" filterable clearable
                                           placeholder="请选择父权限" style="width: 100%;">
                                    <el-option v-for="item in pAuthList" :key="item.id"
                                               :label="item.name"
                                               :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="权限次序" prop="sort">
                                <el-input v-model="authItemInfo.sort" placeholder="请填写权限次序"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="权限图标" prop="icon">
                                <el-input v-model="authItemInfo.icon" placeholder="请填写权限图标"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="状态" prop="status">
                                <el-select v-model="authItemInfo.status" filterable clearable
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
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="authItemInfo.remarks" placeholder="请填写备注"></el-input>
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
    import {queryListPAuth,insertAuth,updateAuth} from 'network/sysManager';

    export default {
        name: "AuthItemDialog",
        data(){
            return {
                //是否编辑
                isEdit: false,
                //弹窗是否加载中
                dialogLoading: false,
                //权限对象信息
                authItemInfo: {
                    name: '',
                    type: '',
                    url: '',
                    pid: '',
                    sort: '',
                    icon: '',
                    status: '1',
                    remarks: '',
                },
                //权限状态字典项
                statusList: [],
                //权限类型字典项
                typeList: [],
                //可以选择的父权限
                pAuthList: [],
                //form表单规则
                rules: {
                    name: {required: true,message: '权限名称不能为空',trigger: 'blur'},
                    type: {required: true,message: '权限类型不能为空',trigger: ['blur','change']},
                    url: {required: false},
                    pid: {required: false},
                    sort: {required: true,message: '排序不能为空',trigger: 'blur'},
                    status: {required: true,message: '状态不能为空',trigger: ['blur','change']},
                },
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return "编辑权限"
                }
                return "新增权限"
            }
        },
        mounted() {
            this.queryAuthStatus();
            this.queryAuthType();
            this.queryPAuthList();
        },
        methods: {
            //初始化弹窗
            initData(rowData){
                if(rowData){
                    //修改
                    this.$nextTick(() => {
                        this.authItemInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    //新增
                    this.isEdit = false;
                }
                this.$refs['authItemDialog'].show();
            },
            //查询权限状态字典项
            queryAuthStatus(){
                getOptions({
                    groupCode: 'auth_status'
                }).then(res => {
                    if(res.success){
                        this.statusList = Object.assign([],res.result);
                    }
                })
            },
            //查询权限类型字典项
            queryAuthType(){
                getOptions({
                    groupCode: 'auth_type'
                }).then(res => {
                    if(res.success){
                        this.typeList = Object.assign([],res.result);
                    }
                })
            },
            //查询可以选择的父权限
            queryPAuthList(){
                queryListPAuth().then(res => {
                    if(res.success){
                        this.pAuthList = Object.assign([],res.result);
                    }else{
                        this.$message.error(res.error);
                    }
                    this.dialogLoading = false;
                }).catch(err => {
                    this.$message.error(err.error);
                    this.dialogLoading = false;
                })
            },
            //保存权限
            saveAuthItem() {
                this.$refs['authItemForm'].validate(valid => {
                    if(valid){
                        //数据校验
                        if(this.authItemInfo.type === '0'){
                            this.authItemInfo.pid = '';
                            this.authItemInfo.url = '';
                        }
                        if(this.isEdit){
                            //编辑
                            this.dialogLoading = true;
                            updateAuth(this.authItemInfo).then(res => {
                                if(res.success){
                                    this.queryPAuthList();
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
                            insertAuth(this.authItemInfo).then(res => {
                                if(res.success){
                                    this.queryPAuthList();
                                    this.closeDialog();
                                    this.$emit('refreshQueryPage');
                                    this.$message({
                                        message: '新增成功！',
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
                        }
                    }
                })
            },
            //关闭弹窗
            closeDialog(){
                this.$refs['authItemForm'].resetFields();
                this.authItemInfo = {
                    name: '',
                    type: '',
                    url: '',
                    pid: '',
                    sort: '',
                    icon: '',
                    status: '1',
                    remarks: '',
                }
                this.$refs['authItemDialog'].close()
            }
        }
    }
</script>

<style scoped>

</style>