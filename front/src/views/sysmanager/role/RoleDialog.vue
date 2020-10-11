<template>
    <div>
        <dialog-template ref="roleDialog" :dialog-name="dialogName" width="50%"
                         @primaryClick="saveRole" @closeClick="closeDialog"
                         primary-button-title="保 存" :dialog-loading="dialogLoading">
            <template slot="body">
                <el-form :model="roleInfo" ref="roleForm" label-width="100px" size="medium" :rules="rules">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="角色编码" prop="code">
                                <el-input v-model="roleInfo.code" placeholder="请填写角色编码"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="角色名称" prop="name">
                                <el-input v-model="roleInfo.name" placeholder="请填写角色名称"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="状态" prop="status">
                                <el-select v-model="roleInfo.status" filterable clearable
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
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="roleInfo.remarks" placeholder="请填写备注"></el-input>
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
    import {insertRole,updateRole} from 'network/sysManager';
    export default {
        name: "RoleDialog",
        data(){
            return {
                //是否编辑
                isEdit: false,
                //弹窗是否加载中
                dialogLoading: false,
                //角色信息
                roleInfo: {
                    code: '',
                    name: '',
                    status: '1',
                    remarks: '',
                },
                //状态字典项
                statusList: [],
                //form表单规则
                rules: {
                    code: {required: true,message: '角色编号不能为空',trigger: 'blur'},
                    name: {required: true,message: '角色名称不能为空',trigger: 'blur'},
                    status: {required: true,message: '状态不能为空',trigger: 'blur'},
                }
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return "编辑角色"
                }
                return "新增角色"
            },
        },
        mounted() {
            this.queryRoleStatus();
        },
        methods: {
            //初始化弹窗
            initData(rowData){
                if(rowData){
                    //修改
                    this.$nextTick(() => {
                        this.roleInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    //新增
                    this.isEdit = false;
                }
                this.$refs['roleDialog'].show();
            },
            //保存
            saveRole(){
                this.$refs['roleForm'].validate(valid => {
                    if(valid){
                        if(this.isEdit){
                            //编辑
                            this.dialogLoading = true;
                            updateRole(this.roleInfo).then(res => {
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
                            insertRole(this.roleInfo).then(res => {
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
            //查询角色状态
            queryRoleStatus(){
                getOptions({
                    groupCode: 'role_status'
                }).then(res => {
                    if(res.success){
                        this.statusList = Object.assign([],res.result);
                    }
                })
            },
            //关闭弹窗
            closeDialog(){
                this.$refs['roleForm'].resetFields();
                this.$refs['roleDialog'].close();
            }
        }
    }
</script>

<style scoped>

</style>