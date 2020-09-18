<template>
    <div id="dict">
        <web-template web-name="字典管理" :table-data="tableData" @change-page="queryPage" :table-loading="tableLoading">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData" label-width="100px">
                    <el-row>
                        <el-col :span="5">
                            <el-form-item label="字典组编号" prop="groupCode">
                                <el-input placeholder="请输入字典组编号" clearable v-model="formData.groupCode"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="字典组名称" prop="groupName">
                                <el-input placeholder="请输入字典组名称" clearable v-model="formData.groupName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="字典项编号" prop="itemCode">
                                <el-input placeholder="请输入字典项编号" clearable v-model="formData.itemCode"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="字典项名称" prop="itemName">
                                <el-input placeholder="请输入字典项名称" clearable v-model="formData.itemName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="4" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage()">查 询</el-button>
                            <el-button @click="rest">重 置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="insertDict">新 增</el-button>
            </div>
            <div slot="webTable">
                <div class="loadDiv">
                    <el-table :data="tableData.list" style="margin-bottom:20px" border>
                        <el-table-column prop="groupCode" label="字典组编号"  align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="groupName" label="字典组名称"  align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="itemCode" label="字典项编号" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="itemName" label="字典项名称" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="statusStr" label="字典项状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="remarks" label="备注" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="createBy" label="创建人" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="createDate" label="创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="updateBy" label="修改人" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column prop="updateDate" label="修改时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                        <el-table-column label="操作" align="center" width="120px">
                            <template slot-scope="scope">
                                <el-button size="mini" type="text" @click="updateDict(scope.row)">
                                    <i class="el-icon-edit">修改</i>
                                </el-button>
                                <el-button size="mini" type="text" @click="delDict(scope.row)">
                                    <i class="el-icon-delete">删除</i>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </web-template>

        <dict-item-dialog ref="dictItemDialog" @refreshQueryPage="queryPage"/>
    </div>
</template>

<script>
    import {queryPageDict,delDict} from 'network/sysManager'
    import DictItemDialog from "./dict/DictItemDialog";
    export default {
        name: 'Dict',
        components: {DictItemDialog},
        data() {
            return {
                tableLoading: false,
                formData: {
                    groupCode: '',
                    groupName: '',
                    itemCode: '',
                    itemName: '',
                },
                tableData: {
                    total: 0,
                    pageNum: 1,
                    pageSize: 10,
                    list: [],
                }
            }
        },
        mounted() {
            this.queryPage();
        },
        computed: {},
        methods: {
            queryPage(pageNum = 1,pageSize = this.tableData.pageSize){
                //分页查询
                this.tableLoading = true;
                this.formData.pageNum = pageNum;
                this.formData.pageSize = pageSize;
                queryPageDict(this.formData).then(res => {
                    this.tableLoading = false
                    if(res.success){
                        this.tableData = res.result
                    }else{
                        this.$message.error(res.error);
                    }
                }).catch(err => {
                    this.tableLoading = false
                    this.$message.error(err.error);
                })
            },
            rest(){
                //重置
                this.$refs['queryForm'].resetFields();
            },
            insertDict(){
                //新增字典项
                this.$refs['dictItemDialog'].initData();
            },
            updateDict(rowData){
                //修改字典项
                this.$refs['dictItemDialog'].initData(rowData);
            },
            delDict(rowData) {
                //删除字典项
                this.$confirm('此操作将删除该  数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.tableLoading = true;
                    delDict(rowData).then(res => {
                        this.tableLoading = false
                        if(res.success){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }else{
                            this.$message.error(res.error);
                        }
                        this.queryPage();
                    }).catch(err => {
                        this.tableLoading = false
                        this.$message.error(err.error);
                    })
                })
            },
        }
    }

</script>

<style scoped>
    .el-row {
        margin-bottom: 20px;

    }
    :last-child {
        margin-bottom: 0;
    }
    .el-col {
        border-radius: 4px;
    }
</style>
