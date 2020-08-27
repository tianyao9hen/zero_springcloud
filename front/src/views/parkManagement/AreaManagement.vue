<template>
    <div class="area-management">
        <web-template web-name="区域管理"
                      :table-data="tableData" @change-page="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区域编号" prop="areaNo" labelWidth="80px">
                                <el-input placeholder="请输入区域编号" clearable v-model="formData.areaNo" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="区域名称" prop="areaName" labelWidth="80px">
                                <el-input placeholder="请输入区域名称" clearable v-model="formData.areaName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="父级区域" prop="parentAreaName" labelWidth="80px">
                                <el-input placeholder="请输入所属父级区域名称" clearable v-model="formData.parentAreaName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运行类型" prop="areaType" labelWidth="80px">
                                <el-select v-model="formData.areaType" filterable clearable placeholder="请选择运行类型" style="width: 100%;">
                                    <el-option v-for="item in areaTypeList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="区域创建时间" prop="createDateList" labelWidth="108px">
                                <el-date-picker
                                        v-model="formData.createDateList"
                                        type="daterange"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        range-separator="至"
                                        style="width: 100%"
                                >
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运行状态" prop="areaStatus" labelWidth="80px">
                                <el-select v-model="formData.areaStatus" filterable clearable placeholder="请选择运行状态" style="width: 100%;">
                                    <el-option v-for="item in areaStatusList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align:right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage(1,tableData.pageSize)">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="addArea">新增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border stripe>
                    <el-table-column prop="areaNo" label="区域编号" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="areaName" label="区域名称"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="parentAreaName" label="所属父级区域" align="center" :show-overflow-tooltip="true">
                        <template slot-scope="scope">
                            <template v-if="!scope.row.parentArea || scope.row.parentArea === ''">
                                -
                            </template>
                            <template v-else>
                                {{scope.row.parentAreaName}}
                            </template>
                        </template>
                    </el-table-column>
                    <el-table-column prop="areaTypeName" label="运行类型" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="parkingSpotCount" label="车位数量" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="createDate" label="区域创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="areaStatusName" label="运行状态" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="200px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="editArea(scope.row)" v-if="scope.row.areaStatus == '1'">
                                <i class="el-icon-edit">编辑</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="openArea(scope.row)" v-if="scope.row.areaStatus == '0'">
                                <i class="el-icon-check">启用</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="closeArea(scope.row)" v-if="scope.row.areaStatus == '1'">
                                <i class="el-icon-close">停用</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delArea(scope.row)">
                                <i class="el-icon-delete">删除</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>
    </div>
</template>

<script>
    import bus from "../../components/common/bus";

    export default {
        name: "AreaManagement",
        data(){
            return {
                formData: {
                    areaNo: '',
                    areaName: '',
                    parentAreaName: '',
                    areaType: '',
                    createDateList: [],
                    areaStatus: '',
                },
                areaTypeList: [
                    {
                        label: '固定',
                        value: '0',
                    },
                    {
                        label: '临时',
                        value: '1',
                    }
                ],
                areaStatusList: [
                    {
                        label: '正常',
                        value: '1',
                    },{
                        label: '停用',
                        value: '0',
                    }
                ],
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            id: "1",
                            areaNo: 'GD001',
                            areaName: '测试区域-固定',
                            parentAreaName: '',
                            parentAreaNo: '',
                            parentArea: '',
                            areaType: '0',
                            areaTypeName: '固定',
                            parkingSpotCount: '50',
                            createDate: '2020-01-01',
                            areaStatus: '1',
                            areaStatusName: '正常'
                        }, {
                            id: "2",
                            areaNo: 'GD001',
                            areaName: '测试区域-固定',
                            parentAreaName: '',
                            parentAreaNo: '',
                            parentArea: '',
                            areaType: '0',
                            areaTypeName: '固定',
                            parkingSpotCount: '50',
                            createDate: '2020-01-01',
                            areaStatus: '0',
                            areaStatusName: '停用'
                        },  {
                            id: "3",
                            areaNo: 'LS001',
                            areaName: '测试区域-临时',
                            parentAreaName: '测试区域-固定',
                            parentAreaNo: 'GD001',
                            parentArea: '2',
                            areaType: '1',
                            areaTypeName: '临时',
                            parkingSpotCount: '50',
                            createDate: '2020-01-01',
                            areaStatus: '0',
                            areaStatusName: '停用'
                        }, {
                            id: "3",
                            areaNo: 'LS001',
                            areaName: '测试区域-临时',
                            parentAreaName: '测试区域-固定',
                            parentAreaNo: 'GD001',
                            parentArea: '2',
                            areaType: '1',
                            areaTypeName: '临时',
                            parkingSpotCount: '50',
                            createDate: '2020-01-01',
                            areaStatus: '1',
                            areaStatusName: '正常'
                        }, {
                            id: "3",
                            areaNo: 'LS001',
                            areaName: '测试区域-临时',
                            parentAreaName: '',
                            parentAreaNo: '',
                            parentArea: '',
                            areaType: '1',
                            areaTypeName: '临时',
                            parkingSpotCount: '50',
                            createDate: '2020-01-01',
                            areaStatus: '1',
                            areaStatusName: '正常'
                        },
                    ]
                }
            }
        },
        mounted() {
            bus.$on('refresh_area',()=> {
                this.queryPage(1,this.tableData.pageSize);
            })
        },
        methods: {
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
            rest(){
                //重置
                this.$refs.queryForm.resetFields()
            },
            addArea(){
                //新增区域
                bus.$emit('close_current_tag_to_url','/parkManagement/addArea')
            },
            editArea(row){
                //编辑区域
                bus.$emit('close_current_tag_to_name_params',{name: 'editArea',params: {id: row.id,areaNo: row.areaNo}})
            },
            delArea(row){
                //删除区域
                this.$confirm('是否删除区域？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.queryPage(1,this.tableData.pageSize);
                    this.$message({
                        message: '删除成功！',
                        type: 'success'
                    });
                })
            },
            openArea(row){
                //启用区域
                this.$confirm('是否启用区域？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.queryPage(1,this.tableData.pageSize);
                    this.$message({
                        message: '启用成功！',
                        type: 'success'
                    });
                })
            },
            closeArea(row){
                //停用区域
                this.$confirm('是否停用区域？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.queryPage(1,this.tableData.pageSize);
                    this.$message({
                        message: '停用成功！',
                        type: 'success'
                    });
                })
            }
        }
    }
</script>

<style scoped>

</style>