<template>
    <div class="customer-list">
        <el-container>
            <el-main>
                <el-row>
                    <el-col :span="24">
                        <el-card class="box-card">
                            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                                <el-tab-pane label="用户列表" name="userList">
                                    <el-row>
                                        <el-col :span="24">
                                            <el-card class="box-card">
                                                <div slot="header">
                                                    <div class="common-card-box">
                                                    </div>
                                                    <span>用户管理</span>
                                                    <div class="common-card-btn" style="margin-right: 0px">
                                                        <el-button class="el-icon-plus" type="primary"
                                                                   @click.prevent="dialogUser = true"
                                                                   style="margin-right:8px">新增
                                                        </el-button>
                                                        <el-input placeholder="请填写姓名" suffix-icon="el-icon-search"
                                                                  v-model="username"
                                                                  style="width:190px">
                                                        </el-input>
                                                    </div>
                                                </div>
                                                <el-table :data="tableData" style="width: 100%;margin-bottom: 20px;"
                                                          border>
                                                    <el-table-column align="center" prop="username"
                                                                     :show-overflow-tooltip="true"
                                                                     label="姓名"></el-table-column>
                                                    <el-table-column align="center" prop="xb"
                                                                     :show-overflow-tooltip="true"
                                                                     label="性别"></el-table-column>
                                                    <el-table-column align="center" prop="ssbm"
                                                                     :show-overflow-tooltip="true"
                                                                     label="所属部门"></el-table-column>
                                                    <el-table-column align="center" prop="zt"
                                                                     :show-overflow-tooltip="true"
                                                                     label="状态"></el-table-column>
                                                    <el-table-column align="center" prop="sfzh"
                                                                     :show-overflow-tooltip="true"
                                                                     label="身份证号码"></el-table-column>
                                                    <el-table-column align="center" prop="dh"
                                                                     :show-overflow-tooltip="true"
                                                                     label="电话"></el-table-column>
                                                    <el-table-column align="center" prop="zz"
                                                                     :show-overflow-tooltip="true"
                                                                     label="住址"></el-table-column>
                                                    <el-table-column align="center" prop="sr"
                                                                     :show-overflow-tooltip="true"
                                                                     label="出生日期"></el-table-column>
                                                    <el-table-column align="center" prop="rzrq"
                                                                     :show-overflow-tooltip="true"
                                                                     label="入职日期"></el-table-column>
                                                    <el-table-column align="center" prop="lzrq"
                                                                     :show-overflow-tooltip="true"
                                                                     label="离职日期"></el-table-column>
                                                    <el-table-column align="center" prop="bz"
                                                                     :show-overflow-tooltip="true"
                                                                     label="备注"></el-table-column>
                                                    <el-table-column label="操作" align="center" width="100px">
                                                        <template slot-scope="scope">
<!--                                                            <el-tooltip content="关联角色" placement="top">-->
<!--                                                                <el-button size="mini" type="text"-->
<!--                                                                           @click="associateRoles=true"><i-->
<!--                                                                        class="el-icon-sort"></i></el-button>-->
<!--                                                            </el-tooltip>-->
                                                            <el-tooltip content="编辑" placement="top">
                                                                <el-button size="mini" type="text"
                                                                           @click="edit(scope.row)"><i
                                                                        class="el-icon-edit"></i></el-button>
                                                            </el-tooltip>
                                                            <el-tooltip content="删除" placement="top">
                                                                <el-button size="mini" type="text" @click="del"><i
                                                                        class="el-icon-delete"></i></el-button>
                                                            </el-tooltip>
                                                        </template>
                                                    </el-table-column>
                                                </el-table>
                                                <div class="pagination" style="text-align:center">
                                                    <pagination></pagination>
                                                </div>
                                            </el-card>
                                        </el-col>
                                    </el-row>
                                </el-tab-pane>
                                <el-tab-pane label="用户组" name="userGroup">
                                    <el-container>
                                        <el-aside width="200px">
                                            <el-input
                                                    placeholder="输入关键字进行过滤"
                                                    v-model="filterText">
                                            </el-input>
                                            <el-tree
                                                    class="filter-tree"
                                                    :data="data"
                                                    :props="defaultProps"
                                                    default-expand-all
                                                    :filter-node-method="filterNode"
                                                    ref="tree">
                                            </el-tree>
                                        </el-aside>
                                        <el-main>
                                            <el-row>
                                                <el-col :span="24">
                                                    <el-card class="box-card">
                                                        <!--<div style="height: 70px">
                                                          <el-form :inline="true"  class="demo-form-inline" size="medium" style="float: right">
                                                            <el-form-item >
                                                              <el-input v-model="username" placeholder="请填写用户名称" ></el-input>
                                                            </el-form-item>
                                                            <el-button type="primary" ><i class="el-icon-search"></i>查询</el-button>
                                                            <el-button type="primary"  @click="selUserDialog = true"><i class="el-icon-lx-add"></i>选择用户</el-button>
                                                          </el-form>
                                                        </div>-->
                                                        <div class="common-card-btn" style="height: 50px;margin-right: 10px">
                                                            <el-button class="el-icon-plus" type="primary"
                                                                       @click.prevent="selUserDialog = true"
                                                                       style="margin-right:8px">选择用户
                                                            </el-button>
                                                            <el-input placeholder="请填写用户名称" suffix-icon="el-icon-search"
                                                                      v-model="username"
                                                                      style="width:190px">
                                                            </el-input>
                                                        </div>
                                                        <el-table :data="tableData" class="common-table"
                                                                  style="margin-bottom: 20px;" border>
                                                            <el-table-column align="center" prop="username"
                                                                             :show-overflow-tooltip="true"
                                                                             label="姓名"></el-table-column>
                                                            <el-table-column align="center" prop="xb"
                                                                             label="性别" ></el-table-column>
                                                            <el-table-column align="center" prop="ssbm"
                                                                             :show-overflow-tooltip="true"
                                                                             label="所属部门"></el-table-column>
                                                            <el-table-column align="center" prop="zt"
                                                                             :show-overflow-tooltip="true"
                                                                             label="状态"></el-table-column>
                                                            <el-table-column align="center" prop="sfzh"
                                                                             :show-overflow-tooltip="true"
                                                                             label="身份证号码"></el-table-column>
                                                            <el-table-column align="center" prop="dh"
                                                                             :show-overflow-tooltip="true"
                                                                             label="电话"></el-table-column>
                                                            <el-table-column align="center" prop="zz"
                                                                             :show-overflow-tooltip="true"
                                                                             label="住址"></el-table-column>
                                                            <el-table-column align="center" prop="rzrq"
                                                                             :show-overflow-tooltip="true"
                                                                             label="入职日期"></el-table-column>
                                                            <el-table-column align="center" prop="lzrq"
                                                                             :show-overflow-tooltip="true"
                                                                             label="离职日期"></el-table-column>
                                                            <el-table-column align="center" prop="bz"
                                                                             :show-overflow-tooltip="true"
                                                                             label="备注"></el-table-column>
                                                            <el-table-column label="操作" align="center" >
                                                                <template slot-scope="scope">
                                                                    <el-tooltip content="删除" placement="top">
                                                                        <el-button size="mini" type="text" @click="del">
                                                                            <i class="el-icon-delete"></i></el-button>
                                                                    </el-tooltip>
                                                                </template>
                                                            </el-table-column>
                                                        </el-table>
                                                        <div class="pagination" style="text-align:center">
                                                            <pagination></pagination>
                                                        </div>
                                                    </el-card>
                                                </el-col>
                                            </el-row>
                                        </el-main>
                                    </el-container>
                                </el-tab-pane>
                            </el-tabs>
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>


        <!---->
        <el-dialog title="新增/修改用户信息" :visible.sync="dialogUser" center>
            <el-form :model="userForm" label-width="80px">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="头像">
                            <el-upload
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    list-type="picture-card"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <el-dialog :visible.sync="dialogVisible">
                                <img width="100%" :src="userForm.dialogImageUrl" alt="">
                            </el-dialog>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="姓名">
                            <el-input v-model="userForm.username" placeholder="请填写用户名称"></el-input>
                        </el-form-item>
                        <el-form-item label="工号">
                            <el-input v-model="userForm.gh" placeholder="请填写工号"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号">
                            <el-input v-model="userForm.dh" placeholder="请填写手机号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="所属部门">
                            <el-input v-model="userForm.ssbm" placeholder="请填写用户组名称"></el-input>
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-radio v-model="userForm.xb" label="男">男</el-radio>
                            <el-radio v-model="userForm.xb" label="女">女</el-radio>
                        </el-form-item>
                        <el-form-item label="QQ">
                            <el-input v-model="userForm.qq" placeholder="请填写QQ号"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="身份证">
                            <el-input v-model="userForm.sfz" placeholder="请填写身份证号码"></el-input>
                        </el-form-item>
                        <el-form-item label="入职日期">
                            <el-date-picker style="width: 100%" v-model="userForm.rzrq" type="date"
                                            placeholder="选择入职日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="住址">
                            <el-input v-model="userForm.zz" placeholder="请填写身份证号码"></el-input>
                        </el-form-item>
                        <el-form-item label="离职日期">
                            <el-date-picker style="width: 100%" v-model="userForm.lzrq" type="date"
                                            placeholder="选择离职日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="出生日期">
                            <el-date-picker style="width: 100%" v-model="userForm.sr" type="date"
                                            placeholder="选择出生日期"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="userForm.bz" placeholder="请填写备注"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <h4>登陆和权限</h4>
                    </el-col>
                </el-row>
                <el-row style="margin-bottom: 15px">
                    <el-col :span="24">
                        <el-checkbox v-model="checked0">允许登录</el-checkbox>
                    </el-col>
                </el-row>
                <el-row v-if="checked0">
                    <el-col :span="8">
                        <el-form-item label="用户名">
                            <el-input v-model="userForm.username" placeholder="请填写用户名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="16">
                        <el-form-item label="用户角色">
                            <el-checkbox v-model="checked1">角色1</el-checkbox>
                            <el-checkbox v-model="checked2">角色2</el-checkbox>
                            <el-checkbox v-model="checked3">角色3</el-checkbox>
                        </el-form-item>

                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showMessage(1,false)">取 消</el-button>
                <el-button type="primary" @click="showMessage(1,true)">确 定</el-button>
            </div>
        </el-dialog>
        <!---->
        <el-dialog title="设置用户" :visible.sync="selUserDialog" center>
            <el-row>
                <el-col :span="24">
                    <el-card class="box-card">
                        <div style="height: 50px;float: right">
                            <el-input placeholder="请填写用户名称" suffix-icon="el-icon-search" v-model="username"
                                      style="width:190px">
                            </el-input>
                            <!--<el-form :inline="true"  class="demo-form-inline" size="medium" style="float: right">
                              <el-form-item >
                                <el-input v-model="username" placeholder="请填写用户名称" ></el-input>
                              </el-form-item>
                              <el-button  type="primary" ><i class="el-icon-search"></i>查询</el-button>
                            </el-form>-->
                        </div>
                        <el-table :data="tableData" style="width: 100%;margin-bottom: 20px;" border>
                            <el-table-column type="selection"></el-table-column>
                            <el-table-column align="center" prop="username" :show-overflow-tooltip="true"
                                             label="姓名"></el-table-column>
                            <el-table-column align="center" prop="ssbm" :show-overflow-tooltip="true"
                                             label="所属部门"></el-table-column>
                            <el-table-column align="center" prop="zt" :show-overflow-tooltip="true"
                                             label="状态"></el-table-column>
                            <el-table-column align="center" prop="dh" :show-overflow-tooltip="true"
                                             label="电话"></el-table-column>
                            <el-table-column align="center" prop="rzrq" :show-overflow-tooltip="true"
                                             label="入职日期"></el-table-column>
                            <el-table-column align="center" prop="bz" :show-overflow-tooltip="true"
                                             label="备注"></el-table-column>
                        </el-table>
                        <div class="pagination" style="text-align:center">
                            <pagination></pagination>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showMessage(2,false)">取 消</el-button>
                <el-button type="primary" @click="showMessage(2,true)">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="关联角色" :visible.sync="associateRoles" center>
            <el-row>
                <el-col :span="24">
                    <el-card class="box-card">
                        <template >
                            <el-transfer v-model="value" :data="data1" style="margin-left: 8%;" :titles="['角色数据', '已选角色']"></el-transfer>
                        </template>
                    </el-card>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showRelation(false)">取 消</el-button>
                <el-button type="primary" @click="showRelation(true)">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>
<script>
    export default {
        data() {
            const generateData = _ => {
                const data = [];
                data.push({
                    key: 1,
                    label: `超级管理员`,
                },{
                    key: 2,
                    label: `业主`,
                },{
                    key: 3,
                    label: `维修人员`,
                },{
                    key: 4,
                    label: `测试1`,
                },{
                    key: 5,
                    label: `物业人员`,
                });
                return data;
            };
            return {
                data1: generateData(),
                value: [],
                checked0: true,
                checked1: false,
                checked2: false,
                checked3: false,
                username: '',
                dialogVisible: false,
                selUserDialog: false,
                associateRoles: false,
                dialogUser: false,
                activeName: 'userList',
                filterText: '',
                data: [{
                    id: 1,
                    label: '总公司',
                    children: [{
                        id: 4,
                        label: '系统管理员',
                    }, {
                        id: 2,
                        label: '综合服务组',
                    }, {
                        id: 3,
                        label: '工程组',
                    }, {
                        id: 4,
                        label: '安全组',
                    }, {
                        id: 5,
                        label: '绿化环卫组',
                    }, {
                        id: 6,
                        label: '租户',
                    }]
                },],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                userForm: {
                    username: '',
                    dialogImageUrl: '',
                    sfz: '',
                    rzrq: '',
                    gh: '',
                    dh: '',
                    zz: '',
                    lzrq: '',
                    ssbm: '',
                    xb: '男',
                    qq: '',
                    sr: '',
                    bz: '',

                },
                tableData: [{
                    id: 1,
                    username: '测试账户',
                    xb: '男',
                    ssbm: '工程部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '济南高新区',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 2,
                    username: '周大福',
                    xb: '男',
                    ssbm: '安全部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '济南高新区',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 3,
                    username: '唐三',
                    xb: '男',
                    ssbm: '唐门',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '斗罗大陆',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 4,
                    username: '悟空',
                    xb: '男',
                    ssbm: '西行部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '花果山',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 5,
                    username: '悟能',
                    xb: '男',
                    ssbm: '净坛部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '高老庄',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 6,
                    username: '悟净',
                    xb: '男',
                    ssbm: '西沙部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '流沙河',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }, {
                    id: 7,
                    username: '李白',
                    xb: '男',
                    ssbm: '诗人部',
                    zt: '启用',
                    sfzh: '370001198110016001',
                    dh: '138666660000',
                    zz: '大唐',
                    sr: '1990/05/31',
                    rzrq: '2020/03/26',
                    lzrq: '',
                    bz: '138666660000',
                }],
            }
        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        },
        methods: {
            relation() {

            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.userForm.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            edit(row) {
                this.dialogUser = true
                this.userForm = row;
            },
            handleClick(tab, event) {
                console.log(tab, event);
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
                })
            },
            showMessage(num, flag) {
                if (num == 1) {
                    this.dialogUser = false
                } else {
                    this.selUserDialog = false;
                }
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                }
            },
            showRelation(flag) {
                this.associateRoles=false;
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                }
            }
        },
        mounted() {

        }
    }
</script>
<style scoped>
    .common-card-box {
        position: absolute;
        left: 0;
    }
</style>

