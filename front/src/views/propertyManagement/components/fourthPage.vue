<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <label style="font-size: 20px;">{{treeName}}</label>
            </div>
            <el-tabs v-model="activeName" type="card">
                <el-tab-pane label="房间" name="fj">
                    <el-card class="box-card" >
                        <div class="cond" >
                            <el-row >
                                <el-col :span="8"  >
                                    <el-button type="primary" @click="editRoom"><i class="el-icon-lx-add"></i>新增</el-button>
                                </el-col>
                                <el-col :span="15" >
                                    <el-input placeholder="请填写房间名称" suffix-icon="el-icon-search" v-model="lyName"
                                              class="patrol-search">
                                    </el-input>
                                </el-col>

                            </el-row>
                            <!--
                            <el-form-item >
                                <el-input v-model="lyName" icon="el-icon-search" placeholder="请填写房间名称" ></el-input>
                            </el-form-item>
                            <el-button type="primary" ><i class="el-icon-search"></i>查询</el-button>
                            -->
                        </div>
                        <el-table :data="RoomTableData" style="width: 100%;margin-bottom: 20px;" border>
                            <el-table-column align="center"  type="selection"></el-table-column>
                            <el-table-column align="center" prop="fjdm" :show-overflow-tooltip="true" label="房间代码"></el-table-column>
                            <el-table-column align="center" prop="fjzt" :show-overflow-tooltip="true" label="房间状态"></el-table-column>
                            <el-table-column align="center" prop="ly" :show-overflow-tooltip="true" label="楼宇"></el-table-column>
                            <el-table-column align="center" prop="fjlx" :show-overflow-tooltip="true" label="房间类型"></el-table-column>
                            <el-table-column align="center" prop="kh" :show-overflow-tooltip="true" label="客户"></el-table-column>
                            <el-table-column align="center" prop="lxdh" :show-overflow-tooltip="true" label="联系电话"></el-table-column>
                            <el-table-column align="center" prop="zjhm" :show-overflow-tooltip="true" label="证件号码"></el-table-column>
                            <el-table-column align="center" prop="symj" :show-overflow-tooltip="true" label="使用面积"></el-table-column>

                            <!--
                                                        <el-table-column align="center" prop="jzmj" :show-overflow-tooltip="true" label="建筑面积"></el-table-column>
                                                        <el-table-column align="center" prop="sm" :show-overflow-tooltip="true" label="说明"></el-table-column>
                            -->
                            <el-table-column label="操作"  align="center">
                                <template slot-scope="scope">
                                    <el-tooltip content="编辑" placement="top">
                                        <el-button size="mini" type="text" @click="editRoom(scope.row)"><i class="el-icon-edit"></i></el-button>
                                    </el-tooltip>
                                    <el-tooltip content="删除" placement="top">
                                        <el-button size="mini" type="text" @click="del"><i class="el-icon-delete"></i></el-button>
                                    </el-tooltip>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="pagination"  style="text-align:center">
                            <pagination></pagination>
                        </div>
                    </el-card>
                </el-tab-pane>
            </el-tabs>
        </el-card>
        <!--编辑房间弹窗-->
        <el-dialog
                title="编辑房间"
                :visible.sync="editRoomInfo"
                center>
            <el-form :model="formRoom" label-width="100px" size="medium" >
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="上级机构">
                            <el-select v-model="formRoom.sjjg" placeholder="请选择" multiple collapse-tags style="width: 100%">
                                <el-option :value="formRoom.sjjg" style="height: auto">
                                    <el-tree :data="treedata" default-expand-all show-checkbox check-strictly node-key="id"
                                             ref="tree" highlight-current :props="defaultProps"
                                             @check-change="handleCheckChange"></el-tree>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="楼层">
                            <el-input v-model="formRoom.lc" placeholder="请填写楼层"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="房间序号">
                            <el-input type="number" v-model="formRoom.fjxh" placeholder="请填写房间序号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="房间代码">
                            <el-input type="number" v-model="formRoom.fjdm" placeholder="请填写房间代码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="房间类型">
                            <el-select v-model="formRoom.lx" placeholder="请选择房间类型" style="width: 100%">
                                <el-option label="办公" value="0"></el-option>
                                <el-option label="商铺" value="1"></el-option>
                                <el-option label="住宅" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="建筑面积">
                            <el-input v-model="formRoom.jzmj" placeholder="请填写建筑面积"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="使用面积">
                            <el-input v-model="formRoom.symj" placeholder="请填写使用面积"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="初次验收日期">
                            <el-date-picker v-model="formRoom.ccys" type="date" placeholder="选择初次验收日期" style="width: 100%"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="二次验收日期">
                            <el-date-picker v-model="formRoom.ecys" type="date" placeholder="选择初次验收日期" style="width: 100%"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="交房状态">
                            <el-select v-model="formRoom.jfzt" :rules="[ { required: true, message: '不能为空'}]" style="width: 100%">
                                <el-option value="0" label="未出售"></el-option>
                                <el-option value="0" label="交房"></el-option>
                                <el-option value="0" label="接房"></el-option>
                                <el-option value="0" label="控制"></el-option>
                                <el-option value="0" label="装修"></el-option>
                                <el-option value="0" label="入驻"></el-option>
                                <el-option value="0" label="出租"></el-option>
                                <el-option value="0" label="已售"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="能否出租">
                            <el-checkbox v-model="formRoom.nfcz" ></el-checkbox>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="违规整改">
                            <el-input type="textarea" v-model="formRoom.wgzg" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="formRoom.bz" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showMessage(false)">取 消</el-button>
                <el-button type="primary" @click="showMessage(true)">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        props:["treeId","treeName"],
        data() {
            return {
                // mineStatus: "",
                editRoomInfo:false,
                formRoom:{
                    sjjg:'',
                    lc:'',
                    fjxh:'',
                    fjdm:'',
                    fjlx:'',
                    jzmj:'',
                    symj:'',
                    ccys:'',
                    ecys:'',
                    jfzt:'',
                    fjzt:'',
                    nfcz:true,
                    jtcy:'',
                    wgzg:'',
                    bz:'',
                },
                RoomTableData:[{
                    fjdm:'201',
                    fjzt:'入驻',
                    ly:'B股东201',
                    glq:'明珠城(商业)',
                    fjlx:'办公',
                    kh:'白骨精',
                    lxdh:'18428375497',
                    zjhm:'12314',
                    symj:'80',
                    jzmj:'123',
                    sm:'',
                },{
                    fjdm:'201',
                    fjzt:'入驻',
                    ly:'B股东201',
                    glq:'明珠城(商业)',
                    fjlx:'办公',
                    kh:'白骨精',
                    lxdh:'18428375497',
                    zjhm:'12314',
                    symj:'80',
                    jzmj:'123',
                    sm:'',
                },{
                    fjdm:'201',
                    fjzt:'入驻',
                    ly:'B股东201',
                    glq:'明珠城(商业)',
                    fjlx:'办公',
                    kh:'白骨精',
                    lxdh:'18428375497',
                    zjhm:'12314',
                    symj:'80',
                    jzmj:'123',
                    sm:'',
                }],
                treedata: [{
                    id: 1,
                    label: '齐鲁交通集团',
                    level:1,
                    children: [{
                        id: 2,
                        label: 'A区',
                        level:2,
                        children: [{
                            id: 21,
                            label: '1号楼',
                            level:3,
                            children: [{
                                id: 211,
                                label: '1单元',
                                level:4,
                            },{
                                id: 212,
                                label: '2单元',
                                level:4,
                            },{
                                id: 213,
                                label: '3单元',
                                level:4,
                            }]
                        }, {
                            id: 22,
                            label: '2号楼',
                            level:3,
                            children: [{
                                id: 221,
                                label: '1单元',
                                level:4,
                            },{
                                id: 222,
                                label: '2单元',
                                level:4,
                            },{
                                id: 223,
                                label: '3单元',
                                level:4,
                            }]
                        }]
                    },{
                        id: 3,
                        label: 'B区',
                        level:2,
                        children: [{
                            id: 31,
                            label: '3号楼',
                            level:3,
                            children: [{
                                id: 311,
                                label: '1单元',
                                level:4,
                            },{
                                id: 312,
                                label: '2单元',
                                level:4,
                            },{
                                id: 313,
                                label: '3单元',
                                level:4,
                            }]
                        }, {
                            id: 32,
                            label: '4号楼',
                            level:3,
                            children: [{
                                id: 321,
                                label: '1单元',
                                level:4,
                            },{
                                id: 322,
                                label: '2单元',
                                level:4,
                            },{
                                id: 323,
                                label: '3单元',
                                level:4,
                            }]
                        }]
                    }]
                }],
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                activeName:'fj',
                lyName:'',
                editInfo:false,
                formLy: {
                    sjjg:'',
                    mc:'',
                    xh:'',
                    cs:'',
                    lx:'',
                    yt:'',
                    cx:'',
                    bz:'',
                },
            };
        },
        methods: {
            editRoom(rowData){
                this.editRoomInfo = true
            },
            handleCheckChange(data, checked, node) {
                let res = this.$refs.tree.getCheckedNodes(false, true); //true，1. 是否只是叶子节点 2.选择的时候不包含父节点）
                if (checked) {
                    this.$refs.tree.setCheckedNodes([data]);
                }
                let arrLabel = [];
                // console.log(res, 'res')
                res.forEach(item => {
                    if (arrLabel.length === 0) {
                        arrLabel.push(item.label);
                    } else {
                        arrLabel.length === 0;
                    }
                });
                this.form.sjjg = arrLabel;
                // console.log(arrLabel, 'arrLabel')
            },

            del(){
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
            showMessage(flag){
                this.editRoomInfo = false
                if(flag){
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                }
            }
        }
    };
</script>
<style scoped>
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
    .box-card{
        height: 550px;
    }
     .cond{
         height: 50px;
         float: right;
     }
</style>