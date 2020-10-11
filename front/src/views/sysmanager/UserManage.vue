<template>
    <div id="userManage">
        <web-template web-name="用户管理"
                      :table-data="tableData"
                      @change-page="queryPage"
                      :table-loading="tableLoading">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData" label-width="100px">
                    <el-row>
                        <el-col :span="5">
                            <el-form-item label="用户账户" prop="username">
                                <el-input placeholder="请输入用户账户" clearable v-model="formData.username"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="用户名称" prop="name">
                                <el-input placeholder="请输入用户名称" clearable v-model="formData.name"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="4" :offset="10" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage()">查 询</el-button>
                            <el-button @click="rest">重 置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="insertUser">新 增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.list" style="margin-bottom:20px" border>
                    <el-table-column prop="username" label="用户账户"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="name" label="用户名称"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="phone" label="用户电话"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="email" label="邮箱" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="remarks" label="备注" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="statusStr" label="账户状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createByName" label="创建人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createDate" label="创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateByName" label="修改人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateDate" label="修改时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="200px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="updateUser(scope.row)">
                                <i class="el-icon-edit">修改</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delUser(scope.row)">
                                <i class="el-icon-delete">删除</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="roleManage(scope.row)">
                                <i class="el-icon-setting">绑定角色</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>

        <user-dialog ref="userDialog" @refreshQueryPage="queryPage"/>
        <user-role-manage ref="userRoleManage" @refreshQueryPage="queryPage"/>
    </div>
</template>

<script>
    import {queryPageUser,delUser} from 'network/sysManager'
    import UserDialog from "./user/UserDialog";
    import UserRoleManage from "./user/UserRoleManage";
    export default {
        name: "UserManage",
        components: {UserRoleManage, UserDialog},
        data(){
            return {
                tableLoading: false,
                tableData: {
                    total: 0,
                    pageNum: 1,
                    pageSize: 10,
                    list: [],
                },
                formData: {
                    username: '',
                    name: '',
                }
            }
        },
        mounted() {
            this.queryPage();
        },
        methods: {
            //分页查询
            queryPage(pageNum = 1,pageSize = this.tableData.pageSize){
                this.tableLoading = true;
                this.formData.pageNum = pageNum;
                this.formData.pageSize = pageSize;
                queryPageUser(this.formData).then(res => {
                    this.tableLoading = false
                    if(res.success){
                        this.tableData = res.result
                    }else{
                        this.$message.error(res.error)
                    }
                }).catch(err => {
                    this.tableLoading = false
                    this.$message.error(err.error);
                })
            },
            //新增用户
            insertUser(){
                this.$refs['userDialog'].initData();
            },
            //修改用户
            updateUser(rowData){
                this.$refs['userDialog'].initData(rowData);
            },
            //删除用户
            delUser(rowData){
                this.$confirm('此操作将删除该  数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.tableLoading = true;
                    delUser(rowData).then(res => {
                        this.tableLoading = false
                        if(res.success){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.queryPage();
                        }else{
                            this.$message.error(res.error);
                        }
                    }).catch(err => {
                        this.tableLoading = false
                        this.$message.error(err.error);
                    })
                })
            },
            //为用户绑定角色
            roleManage(rowData){
                this.$refs['userRoleManage'].initData(rowData);
            },
            //重置
            rest(){
                this.$refs['queryForm'].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>