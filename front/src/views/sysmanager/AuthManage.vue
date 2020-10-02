<template>
    <div id="authManage">
        <web-template web-name="权限管理"
                      :tableLoading="tableLoading"
                      simple
                      :is-page="false">
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="insertAuth">新 增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData"
                          :tree-props="{children: 'childs',hasChildren: 'hasChild'}"
                          row-key="id"queryAuthStatus
                          style="margin-bottom: 20px" border>
                    <el-table-column prop="name" label="权限名称" align="left" header-align="center" width="150px"></el-table-column>
                    <el-table-column prop="url" label="权限标识或url" width="150" align="left" header-align="center" :show-overflow-tooltip="true">
                        <template slot-scope="scope">
                            <template v-if="scope.row.type == '0'">--</template>
                            <template v-else>{{scope.row.url}}</template>
                        </template>
                    </el-table-column>
                    <el-table-column prop="remarks" label="备注" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="typeStr" label="权限类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="sort" label="权限排序" width="70px" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="icon" label="权限图标" width="150px" align="left" header-align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="statusStr" label="权限状态" width="70px" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createByName" label="创建人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createDate" label="创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateByName" label="最后修改人" width="100px" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="updateDate" label="修改时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="120px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="updateAuth(scope.row)">
                                <i class="el-icon-edit">修改</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delAuth(scope.row)">
                                <i class="el-icon-delete">删除</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>
        <auth-item-dialog ref="authItemDialog" @refreshQueryPage="queryList"/>
    </div>
</template>

<script>
    import {queryListAuth,delAuth} from 'network/sysManager'
    import {getOptions} from 'network/commons';
    import AuthItemDialog from "./auth/AuthItemDialog";
    export default {
        name: "AuthManage",
        components: {AuthItemDialog},
        data(){
            return {
                //表格加载标识
                tableLoading: false,
                //查询form,已废弃
                formData: {},
                //表数据
                tableData: [],
            }
        },
        mounted() {
            this.queryList();
        },
        methods: {
            //查询（这里不用分页）
            queryList(){
                this.tableLoading = true;
                queryListAuth(this.formData).then(res => {
                    this.tableLoading = false;
                    if(res.success){
                        this.tableData = res.result;
                    }else{
                        this.$message.error(res.error);
                    }
                }).catch(err => {
                    this.tableLoading = false;
                    this.$message.error(err.error);
                })
            },
            //重置表单
            rest(){
                this.$refs['queryForm'].resetFields();
            },
            //新增权限
            insertAuth(){
                this.$refs['authItemDialog'].initData();
            },
            //修改权限
            updateAuth(rowData){
                this.$refs['authItemDialog'].initData(rowData);
            },
            //删除权限
            delAuth(rowData){
                this.$confirm('此操作将删除该  数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.tableLoading = true;
                    delAuth(rowData).then(res => {
                        this.tableLoading = false
                        if(res.success){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.queryList()
                        }else{
                            this.$message.error(res.error);
                        }
                    }).catch(err => {
                        this.tableLoading = false
                        this.$message.error(err.error);
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>