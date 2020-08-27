<template>
    <!--  系统日志-->
    <div class="customer-list">
        <el-container>
            <el-main>
                <!--        查询-->
                <!-- 检索条件-->
                <el-card>
                    <el-form layout="inline"  :model="formData">
                        <el-row>
                            <el-col :span="8">
                                <el-form-item  label="模板">
                                    <el-select v-model="formData.value" placeholder="请选择模块" >
                                        <el-option v-for="item in options" :key="item.value" :label="item.label"
                                                   :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="操作类型" >
                                    <el-select v-model="formData.value1" placeholder="请选择操作类型" >
                                        <el-option v-for="item in options1" :key="item.value" :label="item.label"
                                                   :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="4" :offset="4" style="text-align:right">
                                <el-button style="margin-right:8px;" type="primary">查询</el-button>
                                <el-button>重置</el-button>
                            </el-col>
                        </el-row>

                    </el-form>
                </el-card>
                <!--    列表详情-->
                <el-card class="common-card" style="margin-top:20px">
                    <div slot="header" style="padding: 24px 20px 0px 0px;">
                        <div class="common-card-box">
                        </div>
                        <span class="common-card-span">列表详情</span>
                    </div>
                    <el-table :data="tableData" style="margin-bottom: 20px;" border>
                        <el-table-column prop="modular" label="模块"  align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="operType" label="操作类型"  align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="operator" label="操作人" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="operTime" label="操作时间" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="operCenter" label="操作内容" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
                            <template slot-scope="scope">
                                <el-tooltip content="详情" placement="top">
                                    <el-button size="mini" type="text" @click="share()"><i
                                            class="el-icon-share"></i></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination" style="text-align:center">
                        <pagination></pagination>
                    </div>
                </el-card>
                <!--    详情弹窗-->
                <el-dialog :visible.sync="dialogFormVisible" center>
                    <el-table :data="form" height="200px" :show-header="false">
                        <el-table-column prop="value" style="width: 100%" label="详情" align="center"></el-table-column>
                    </el-table>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="showMessage(false)">关闭</el-button>
                    </div>
                </el-dialog>
            </el-main>
        </el-container>
    </div>
</template>
<script>
    export default {
        data() {
            return {

                dialogFormVisible: false,
                formData:{
                  value:[],
                  value1:[],
                },
                form: [{
                    "value": '【FR-5#101】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00'
                }, {
                    "value": '【FR-5#102】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }, {
                    "value": '【FR-5#103】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }, {
                    "value": '【FR-5#104】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }, {
                    'value': '【FR-5#105】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00'
                }, {
                    "value": '【FR-5#102】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }, {
                    "value": '【FR-5#102】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }, {
                    "value": '【FR-5#102】收费标准：物业费，收费开始时间：2020/6/1 0:00:00，收费结束时间：2020/6/30 0:00:00',
                }],
                options: [{
                    value: '选项1',
                    label: '收费标准设置'
                }, {
                    value: '选项2',
                    label: '基础数据'
                }, {
                    value: '选项3',
                    label: '合同',
                }],
                options1: [{
                    value: '选项1',
                    label: '添加'
                }, {
                    value: '选项2',
                    label: '删除'
                }, {
                    value: '选项3',
                    label: '编辑',
                }],
                tableData: [{
                    "modular": "收费标准设置",
                    "operType": "添加",
                    "operator": "唐僧",
                    "operTime": "2020-04-14 10:12:38",
                    "operCenter": "批量绑定收费标准5条"
                }, {
                    "modular": "基础数据",
                    "operType": "修改房间状态",
                    "operator": "哪吒",
                    "operTime": "2020-04-08 12:57:27",
                    "operCenter": "修改房间状态"
                }, {
                    "modular": "合同",
                    "operType": "执行",
                    "operator": "白骨精",
                    "operTime": "2020-04-10 14:15:54",
                    "operCenter": "执行合同编号【ass12233】的合同"
                }, {
                    "modular": "基础数据",
                    "operType": "添加",
                    "operator": "猪八戒",
                    "operTime": "2020-04-13 18:16:10",
                    "operCenter": "新增【明珠城(商业)】【巴伯本】客户档案"
                }]
            };
        },
        methods: {
            share() {
                this.dialogFormVisible = true;
            },
            showMessage(flag) {
                this.dialogFormVisible = false
            },
        },
        mounted() {
            //this.getUsers();
            //this.testFn();
        }
    };
</script>
<style  rel="stylesheet/scss" lang="scss">
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center;
    }

    .table {
        margin-top: 2%;
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }
    .el-card__body {
        padding: 30px;
    }
</style>

