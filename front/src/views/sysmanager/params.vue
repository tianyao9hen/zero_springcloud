<template>
    <!--  参数设置-->
    <div>
        <el-container>
            <el-main>
                <!--    列表详情-->
                <el-card class="common-card">
                    <div slot="header" style="padding: 24px 20px 0px 0px;">
                        <div class="common-card-box">
                        </div>
                        <span class="common-card-span">参数列表</span>
                        <div class="common-card-btn">
                            <el-button class="el-icon-plus" type="primary" @click="add()"
                                       style="margin-right:8px">新增
                            </el-button>
                            <el-input placeholder="请输入参数名称搜索" suffix-icon="el-icon-search" v-model="queryParam"
                                      style="width:190px">
                            </el-input>
                        </div>
                    </div>
                    <el-table :data="tableData" style="margin-bottom: 20px;" border>
                        <el-table-column prop="paramName" label="参数名称" align="center"/>
                        <el-table-column prop="paramValue" label="参数值" align="center" min-width="35%"/>
                        <el-table-column prop="describe" label="参数描述" align="center"/>
                        <el-table-column align="center" label="参数状态">
                            <template slot-scope="scope">
                                <el-switch v-model="scope.row.status" style="display: block" active-color="#13ce66"
                                           inactive-color="#ff4949" active-text="可用" inactive-text="不可用"
                                           @change="Swatch(scope.row)"/>
                            </template>
                        </el-table-column>
                        <el-table-column prop="groupNum" label="分组编号" align="center" min-width="30%"/>
                        <el-table-column prop="createTime" label="创建时间" align="center"/>
                        <el-table-column label="操作" align="center" min-width="20%">
                            <template slot-scope="scope">
                                <el-tooltip content="编辑" placement="top">
                                    <el-button size="mini" type="text" @click="edit(scope.row)"><i
                                            class="el-icon-edit"></i></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination" style="text-align:center">
                        <pagination></pagination>
                    </div>
                </el-card>
                <!--        编辑弹窗-->
                <el-dialog title="新增/修改参数" :visible.sync="dialogFormVisible" center>
                    <el-form :model="form" label-width="80px" size="medium">
                        <el-form-item label="分组编号">
                            <el-select v-model="form.groupNum" placeholder="请选择" style="width: 100%">
                                <el-option v-for="item in options" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="参数名称">
                            <el-input v-model="form.paramName" placeholder="请填写参数名称" style="width: 100%"></el-input>
                        </el-form-item>
                        <el-form-item label="参数值">
                            <el-input v-model="form.paramValue" placeholder="请填写参数名称" style="width: 100%"></el-input>
                        </el-form-item>
                        <el-form-item label="描述">
                            <el-input type="textarea" v-model="form.describe" placeholder="请填写备注信息"></el-input>
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
        name: "machineRecordManage",
        data() {
            return {
                formChange: false,
                queryParam: '',
                dialogFormVisible: false,
                input1: '',
                formData: {
                    name: ''
                },
                tableData: [{
                    'paramName': 'redisVersionInfo',
                    'paramValue': '1.2.10',
                    'describe': 'redis版本信息',
                    'status': true,
                    'groupNum': '1',
                    'createTime': '2020-04-14 10:12:38'
                }, {
                    'paramName': 'quartzVersionInfo',
                    'paramValue': '1.2.10',
                    'describe': 'quartz版本信息',
                    'status': true,
                    'groupNum': '2',
                    'createTime': '2020-04-08 12:57:27'
                }, {
                    'paramName': '弹框时间间隔',
                    'paramValue': '60000',
                    'describe': '监控表的提示同弹框的时间间隔',
                    'status': true,
                    'groupNum': '3',
                    'createTime': '2020-04-10 14:15:54'
                }, {
                    'paramName': '弹框持续时间',
                    'paramValue': '100000',
                    'describe': '弹框持续时间',
                    'status': true,
                    'groupNum': '4',
                    'createTime': '2020-04-13 18:16:10'
                }],
                form: {
                    groupNum: '',
                    paramName: '',
                    paramValue: '',
                    describe: ''
                },
                options: [{
                    value: '选项1',
                    label: '编号1'
                }, {
                    value: '选项2',
                    label: '编号2'
                }, {
                    value: '选项3',
                    label: '编号3',
                }],
            }
        },
        created() {
        },
        // 页面初始化
        activated() {
        },
        mounted() {
            //this.showEcharts()
        },
        methods: {
            edit(rowData) {
                console.log(rowData);
                this.form = rowData;
                this.dialogFormVisible = true
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
                    groupNum: '',
                    type: '',
                    code: '',
                    num: '',
                    bz: '',
                }
                this.dialogFormVisible = true;
            },
            Swatch(item) {
                this.formChange = true
            },
        }
    }
</script>
<style rel="stylesheet/scss" lang="scss">
    .el-switch__core:after {
        content: "";
        position: absolute;
        top: 1px;
        left: 1px;
        border-radius: 100%;
        transition: all .3s;
        width: 16px;
        height: 16px;
        background-color: #fff;
    }

    .el-switch.is-checked .el-switch__core:after {
        left: 100%;
        margin-left: -17px;
    }
</style>