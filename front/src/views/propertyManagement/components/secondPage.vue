<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <label style="font-size: 20px;">{{treeName}}</label>
            </div>
            <el-tabs v-model="activeName" type="card">
                <el-tab-pane label="楼宇" name="ly">
                    <el-card class="box-card" >
                        <div class="cond">
                            <el-row >
                                <el-col :span="8"  >
                                    <el-button type="primary" @click="edit"><i class="el-icon-lx-add"></i>新增</el-button>
                                </el-col>
                                <el-col :span="15" >
                                    <el-input placeholder="请填写楼宇名称" suffix-icon="el-icon-search" v-model="lyName"
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
                        <el-table :data="tableData" style="width: 100%;margin-bottom: 20px;" border>
                            <el-table-column align="center"  type="selection"></el-table-column>
                            <el-table-column align="center" prop="ly" :show-overflow-tooltip="true" label="楼宇"></el-table-column>
                            <el-table-column align="center" prop="xh" :show-overflow-tooltip="true" label="序号"></el-table-column>
                            <el-table-column align="center" prop="cs" :show-overflow-tooltip="true" label="层数"></el-table-column>
                            <el-table-column align="center" prop="lx" :show-overflow-tooltip="true" label="类型"></el-table-column>
                            <el-table-column align="center" prop="yt" :show-overflow-tooltip="true" label="用途"></el-table-column>
                            <el-table-column align="center" prop="cx" :show-overflow-tooltip="true" label="朝向"></el-table-column>
                            <el-table-column align="center" prop="sm" :show-overflow-tooltip="true" label="说明"></el-table-column>
                            <el-table-column label="操作"  align="center">
                                <template slot-scope="scope">
                                    <el-tooltip content="编辑" placement="top">
                                        <el-button size="mini" type="text" @click="edit(scope.row)"><i class="el-icon-edit"></i></el-button>
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
        <el-dialog
                title="编辑楼宇"
                :visible.sync="editInfo"
                center>
            <el-form :model="formLy" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="上级机构">
                            <el-select v-model="formLy.sjjg" placeholder="请选择" multiple collapse-tags style="width: 100%">
                                <el-option :value="formLy.sjjg" style="height: auto">
                                    <el-tree :data="treedata" default-expand-all show-checkbox check-strictly node-key="id"
                                             ref="tree" highlight-current :props="defaultProps"
                                             @check-change="handleCheckChange"></el-tree>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="名称">
                            <el-input v-model="formLy.mc" placeholder="请填写名称"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="序号">
                            <el-input type="number" v-model="formLy.xh" placeholder="请填写序号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="层数">
                            <el-input type="number" v-model="formLy.cs" placeholder="请填写层数"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="类型">
                            <el-select v-model="formLy.lx" placeholder="请选择类型" style="width: 100%">
                                <el-option label="高层" value="0"></el-option>
                                <el-option label="中高层" value="1"></el-option>
                                <el-option label="低层" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用途">
                            <el-select v-model="formLy.yt" placeholder="请选择用途" style="width: 100%">
                                <el-option label="住宅用途" value="1"></el-option>
                                <el-option label="商业用途" value="2"></el-option>
                                <el-option label="工业用途" value="3"></el-option>
                                <el-option label="文体娱乐" value="4"></el-option>
                                <el-option label="多功能建筑" value="5"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="朝向">
                            <el-select v-model="formLy.cx" placeholder="请选择朝向" style="width: 100%">
                                <el-option label="东方" value="1"></el-option>
                                <el-option label="西方" value="2"></el-option>
                                <el-option label="南方" value="3"></el-option>
                                <el-option label="北方" value="4"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="formLy.bz" placeholder="请填写容积率"></el-input>
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
                activeName:'ly',
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
                tableData:[{
                    glq:'火焰山',
                    ly:'1栋',
                    xh:'001',
                    cs:'20',
                    lx:'低层',
                    yt:'商业用途',
                    cx:'南方',
                    sm:'挺热的！',
                },{
                    glq:'火焰山',
                    ly:'1栋',
                    xh:'001',
                    cs:'20',
                    lx:'低层',
                    yt:'商业用途',
                    cx:'南方',
                    sm:'挺热的！',
                },{
                    glq:'火焰山',
                    ly:'1栋',
                    xh:'001',
                    cs:'20',
                    lx:'低层',
                    yt:'商业用途',
                    cx:'南方',
                    sm:'挺热的！',
                },{
                    glq:'火焰山',
                    ly:'1栋',
                    xh:'001',
                    cs:'20',
                    lx:'低层',
                    yt:'商业用途',
                    cx:'南方',
                    sm:'挺热的！',
                }],
            };
        },
        methods: {
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
            edit(rowData){
                this.form = rowData;
                this.editInfo = true
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
                this.editInfo = false
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