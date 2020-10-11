<template>
    <div id="ruleMange">
        <web-template web-name="角色管理" :table-data="tableData" @change-page="queryPage"
                      :table-loading="tableLoading">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData" label-width="100px">
                    <el-row>
                        <el-col :span="5">
                            <el-form-item label="角色编号" prop="groupCode">
                                <el-input placeholder="请输入角色编号" clearable v-model="formData.code"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="角色名称" prop="groupName">
                                <el-input placeholder="请输入角色名称" clearable v-model="formData.name"></el-input>
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
                <el-button class="el-icon-plus" type="primary" @click="insertRole">新 增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.list" style="margin-bottom:20px" border>
                    <el-table-column prop="code" label="角色编号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="name" label="角色名称"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="remarks" label="角色描述"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="status" label="角色状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createByName" label="创建人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createDate" label="创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateByName" label="修改人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateDate" label="修改时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="200px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="updateRule(scope.row)">
                                <i class="el-icon-edit">修改</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delRule(scope.row)">
                                <i class="el-icon-delete">删除</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="authManage(scope.row)">
                                <i class="el-icon-setting">赋权</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>

        <role-dialog ref="roleDialog" @refreshQueryPage="queryPage"/>
        <role-auth-manage ref="roleAuthDialog" @refreshQueryPage="queryPage"/>
    </div>
</template>

<script>
    import {queryPageRole,delRole} from 'network/sysManager'
    import RoleDialog from "./role/RoleDialog";
    import RoleAuthManage from "./role/RoleAuthManage";
    export default {
        name: "RuleManage",
        components: {RoleAuthManage, RoleDialog},
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
                    code: '',
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
                queryPageRole(this.formData).then(res => {
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
            //新增
            insertRole(){
                this.$refs['roleDialog'].initData();
            },
            //修改
            updateRule(rowData){
                this.$refs['roleDialog'].initData(rowData);
            },
            //删除
            delRule(rowData){
                this.$confirm('此操作将删除该  数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.tableLoading = true;
                    delRole(rowData).then(res => {
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
            //角色赋权
            authManage(rowData){
                this.$refs['roleAuthDialog'].initData(rowData);
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