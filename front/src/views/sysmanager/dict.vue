<template>
    <div class="customer-list">
        <el-container>
            <el-main>
                <!--        查询-->
                <!--        列表详情-->
                <el-card class="common-card">
                    <div slot="header" style="padding: 24px 20px 0px 0px;">
                        <div class="common-card-box">
                        </div>
                        <span class="common-card-span">字典表</span>
                        <div class="common-card-btn">
                            <el-button class="el-icon-plus" type="primary" @click="add()"
                                       style="margin-right:8px">新增
                            </el-button>
                            <el-input placeholder="按字典名搜索" suffix-icon="el-icon-search" v-model="queryParam"
                                      style="width:190px">
                            </el-input>
                        </div>
                    </div>
                    <el-table :data="tableData"
                              style="margin-bottom: 20px;" class="common-table"
                              border>
                        <el-table-column prop="id" label="序号" align="center"/>
                        <el-table-column prop="dictName" label="字典名称" align="center"/>
                        <el-table-column prop="dictType" label="字典类型" align="center"/>
                        <el-table-column prop="dictCode" label="字典码" align="center"/>
                        <el-table-column prop="dictNum" label="字典值" align="center"/>
                        <el-table-column prop="remarks" label="备注" align="center"/>
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope">
                                <el-tooltip content="编辑" placement="top">
                                    <el-button size="mini" type="text" @click="edit(scope.row)"><i
                                            class="el-icon-edit"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="删除" placement="top">
                                    <el-button size="mini" type="text" @click="del"><i class="el-icon-delete"></i>
                                    </el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination" style="text-align:center">
                       <pagination></pagination>
                    </div>
                </el-card>
                <!--        编辑弹窗-->
                <el-dialog title="新增/修改字典表" :visible.sync="dialogFormVisible" center>
                    <el-form :model="form" label-width="80px" size="medium">
                        <el-form-item label="字典名称">
                            <el-input v-model="form.dictName" placeholder="请填写字典名称" style="width: 100%"></el-input>
                        </el-form-item>
                        <el-form-item label="字典类型">
                            <el-select v-model="form.dictType" placeholder="请选择" style="width: 100%">
                                <el-option v-for="item in options" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="字典码">
                            <el-input v-model="form.dictCode" placeholder="请填写字典名称" style="width: 100%"></el-input>
                        </el-form-item>
                        <el-form-item label="字典值">
                            <el-input v-model="form.dictNum" placeholder="请填写字典名称" style="width: 100%"></el-input>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="form.remarks" placeholder="请填写备注信息"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="showMessage(false)">取 消</el-button>
                        <el-button type="primary" @click="showMessage(true)">确 定</el-button>
                    </div>
                </el-dialog>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: 'parkingSpaceCreateFee',
        data() {
            return {
                queryParam: "",
                dialogFormVisible: false,
                input1: '',
                formData: {
                    name: ''
                },
                form: {
                    name: '',
                    type: '',
                    code: '',
                    num: '',
                    bz: '',

                },
                options: [{
                    value: '选项1',
                    label: '类型1'
                }, {
                    value: '选项2',
                    label: '类型2'
                }, {
                    value: '选项3',
                    label: '类型3',
                }],
                tableData: [{
                    "id": "1",
                    "dictName": "DeleteHisDataJob",
                    "dictType": "task",
                    "dictCode": "deleteGantryIpcNetHisTask",
                    "dictNum": "0",
                    "remarks": "",
                }, {
                    "id": "2",
                    "dictName": "DeleteInfoDataJob",
                    "dictType": "task",
                    "dictCode": "deleteGantryIpcNetInfoTask",
                    "dictNum": "0",
                    "remarks": "",
                }, {
                    "id": "3",
                    "dictName": "GantryErrorJob",
                    "dictType": "task",
                    "dictCode": "getGantryErrorInfo",
                    "dictNum": "0",
                    "remarks": "",
                }, {
                    "id": "4",
                    "dictName": "GantryStatusInfoJob",
                    "dictType": "task",
                    "dictCode": "gantryStatusInfoTask",
                    "dictNum": "0",
                    "remarks": "",
                }]
            }
        },
        created() {
        },
        computed: {},
        methods: {
            edit(rowData) {
                console.log(rowData);
                this.form = rowData;
                this.dialogFormVisible = true
            },
            del() {
                this.$confirm('此操作将删除该  数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {

                });
            },
            showMessage(flag) {
                this.dialogFormVisible = false
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                } else {
                }
            },
            add() {
                this.form = {
                    name: '',
                    type: '',
                    code: '',
                    num: '',
                    bz: '',
                }
                this.dialogFormVisible = true;
            }
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
