<template>
    <div class="access-record">
        <web-template :webName="webName" :tableData="tableData" @change-page="queryPage">
            <!--        查询-->
            <!-- 检索条件-->
            <div slot="searchForm">
                <el-form layout="inline" :model="formData">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="车厂区域" labelWidth="80px">
                                <el-select v-model="formData.depotArea" filterable clearable placeholder="请选择区域"
                                           style="width: 100%">
                                    <el-option v-for="item in depotArea" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车牌号" labelWidth="80px">
                                <el-input v-model="formData.licensePlateNumber" clearable placeholder="请输入车牌号"
                                         ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车位编号" labelWidth="80px">
                                <el-input v-model="formData.parkingLotNumber" clearable placeholder="请输入车牌号"
                                          ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="公司" labelWidth="80px">
                                <el-input v-model="formData.company" clearable placeholder="请输入车牌号"
                                         ></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6" :offset="18" style="text-align:right">
                            <el-button style="margin-right:8px;" type="primary">查询</el-button>
                            <el-button>重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-edit" type="primary" @click="addOrEdit()" style="margin-right:8px">新增</el-button>
            </div>
            <div slot="webTable">
                <div class="table-tab">
                    <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                        <el-table-column prop="area" label="区域" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="licensePlateNumber" label="车牌号" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="company" label="公司" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="parkingLotNumber" label="车位编号" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column label="操作" align="center" width="200px">
                            <template slot-scope="scope">
                                <el-tooltip content="编辑" placement="top">
                                    <el-button size="mini" type="text" @click="addOrEdit(scope.row)"><i
                                            class="el-icon-edit"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="删除" placement="top">
                                    <el-button size="mini" type="text" @click="del()"><i
                                            class="el-icon-delete"></i></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </web-template>
        <AddOrEditFree ref="addOrEditFree"></AddOrEditFree>
    </div>
</template>

<script>
    import AddOrEditFree from "./component/AddOrEditFree";
    export default {
        name: "FreeCar",
        components: {AddOrEditFree},
        data(){
            return{
                webName:'免费车辆列表',
                //搜索框绑定值
                formData: {
                    depotArea: '',//车厂区域
                    licensePlateNumber: '',//车牌号
                    parkingLotNumber:'',
                    company:'',//公司
                },
                depotArea: [
                    {
                        value: '选项1',
                        label: 'A区'
                    }, {
                        value: '选项2',
                        label: 'B区'
                    }, {
                        value: '选项3',
                        label: '访客区',
                    }
                ],
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            area: "A区",
                            licensePlateNumber: "鲁AEKL25",
                            company: "通维",
                            parkingLotNumber: 'A005',

                        },
                        {
                            area: "A区",
                            licensePlateNumber: "鲁ADL25C",
                            company: "信息",
                            parkingLotNumber: 'B117',
                        },
                        {
                            area: "A区",
                            licensePlateNumber: "鲁BEKL75",
                            company: "运维",
                            parkingLotNumber: 'F108',
                        },
                    ]
                },
            }
        },
        methods:{
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
            addOrEdit(rowData){
                this.$refs.addOrEditFree.initData(rowData);
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
        }
    }
</script>

<style scoped>

</style>