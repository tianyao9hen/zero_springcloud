<template>
    <div>
        <dialog-template ref="userRoleManage" dialog-name="用户绑定角色" width="50%"
                         @primaryClick="saveUserRole" @closeClick="closeDialog"
                         primary-button-title="保 存" :dialog-loading="dialogLoading">
            <template slot="body">
                <el-row>
                    <el-col>
                        <el-transfer
                                filterable
                                filter-placeholder="请输入角色名称"
                                v-model="userRoleInfo"
                                :props="{
                                  key: 'id',
                                  label: 'name'
                                }"
                                :titles="['角色', '已绑定角色']"
                                :button-texts="['解绑', '绑定']"
                                :data="roleList">
                        </el-transfer>
                    </el-col>
                </el-row>
            </template>
        </dialog-template>
    </div>
</template>

<script>
    import {queryListRole,queryListUserRole,insertUserRole} from 'network/sysManager'
    export default {
        name: "UserRoleManage",
        data(){
            return {
                //弹窗是否加载中
                dialogLoading: false,
                //用户信息
                userInfo: {},
                //用户绑定角色信息
                userRoleInfo: [],
                //用户可以绑定的角色
                roleList: [],
            }
        },
        methods: {
            //初始化弹窗
            initData(rowData){
                if(rowData){
                    this.$nextTick(() => {
                        this.userInfo = Object.assign({},rowData);
                        this.queryRoleList();
                        this.queryUserRoleList();
                    })
                    this.$refs['userRoleManage'].show();
                }
            },
            //保存
            saveUserRole(){
                this.dialogLoading = false;
                insertUserRole({
                    id: this.userInfo.id,
                    roleIdList: this.userRoleInfo,
                }).then(res => {
                    this.dialogLoading = false;
                    if(res.success){
                        this.closeDialog();
                        this.$emit('refreshQueryPage');
                        this.$message({
                            message: '保存成功！',
                            type: 'success'
                        })
                    }else{
                        this.$message.error(res.error);
                    }
                }).catch(err => {
                    this.dialogLoading = false;
                    this.$message.error(err.error);
                })
            },
            //查询该用户的角色
            queryUserRoleList(){
                queryListUserRole({id: this.userInfo.id}).then(res => {
                    if(res.success){
                        let userRoleList = res.result;
                        for (const userRole of userRoleList) {
                            this.userRoleInfo.push(userRole.id)
                        }
                    }else{
                        this.$message.error(res.error)
                    }
                }).catch(err => {
                    this.$message.error(err.error);
                })
            },
            //查询全部角色列表
            queryRoleList(){
                queryListRole({}).then(res => {
                    if(res.success){
                        this.roleList = Object.assign([],res.result);
                    }else{
                        this.$message.error(res.error)
                    }
                }).catch(err => {
                    this.$message.error(err.error);
                })
            },
            //关闭弹窗
            closeDialog(){
                this.roleList = [];
                this.userRoleInfo = [];
                this.$refs['userRoleManage'].close();
            }
        }
    }
</script>

<style scoped>

</style>