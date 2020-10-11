<template>
    <div>
        <dialog-template ref="roleAuthDialog" dialog-name="角色赋权" widht="50%"
                         @primaryClick="saveRoleAuth" @closeClick="closeDialog"
                         primary-button-title="保 存" :dialog-loading="dialogLoading">
            <template slot="body">
                <el-tree ref="tree"
                         :check-strictly="true"
                         :data="authTree"
                         show-checkbox
                         node-key="id"
                         :default-checked-keys="optionList"
                         :props="defaultProps"
                >
                </el-tree>
            </template>
        </dialog-template>
    </div>
</template>

<script>
    import {queryListAuth,queryListRoleAuth,insertRoleAuth} from 'network/sysManager';
    export default {
        name: "RoleAuthManage",
        data(){
            return {
                dialogLoading: false,
                authTree: [],
                optionList: [],
                defaultProps: {
                    children: 'childs',
                    label: "name",
                },
                roleInfo: {
                    id: '',
                },
            }
        },
        methods: {
            //初始化弹窗
            initData(rowData){
                this.roleInfo.id = rowData.id;
                this.queryAuthTree();
                this.queryRoleAuthList();
                this.$refs['roleAuthDialog'].show();
            },
            //查询全部权限树
            queryAuthTree(){
                queryListAuth({}).then(res => {
                    if(res.success){
                        this.authTree = res.result;
                    }else{
                        this.$message.error(res.error);
                    }
                }).catch(err => {
                    this.tableLoading = false;
                    this.$message.error(err.error);
                })
            },
            //查询目前已绑定的权限集合
            queryRoleAuthList(){
                if(this.roleInfo){
                    queryListRoleAuth(this.roleInfo).then(res => {
                        if(res.success){
                            let authList = res.result;
                            for(let auth of authList){
                                this.optionList.push(auth.id);
                            }
                            this.$nextTick(() => {
                                this.$refs.tree.setCheckedKeys(this.optionList);
                            });
                        }else{
                            this.$message.error(res.error);
                        }
                    }).catch(err => {
                        this.$message.error(err.error);
                    })
                }
            },
            //保存赋权结果
            saveRoleAuth(){
                this.dialogLoading = true;
                let chooseAuth = this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys())
                insertRoleAuth({
                    id: this.roleInfo.id,
                    authIdList: chooseAuth,
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
            //关闭弹窗
            closeDialog(){
                this.optionList = [];
                this.authTree = [];
                this.$refs['roleAuthDialog'].close();
            },
        }
    }
</script>

<style scoped>

</style>