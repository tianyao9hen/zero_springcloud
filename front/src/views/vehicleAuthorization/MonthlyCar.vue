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
                            <el-form-item label="有效状态" labelWidth="80px">
                                <el-select v-model="formData.effectiveStatus" filterable clearable placeholder="请选择类型"
                                           style="width: 100%;">
                                    <el-option v-for="item in effectiveStatus" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="9">
                            <el-form-item label="更新时间" labelWidth="80px">
                                <div class="block">
                                    <el-date-picker style="width: 75%"
                                                    v-model="formData.updateTime"
                                                    type="datetimerange"
                                                    :picker-options="pickerOptions"
                                                    range-separator="至"
                                                    start-placeholder="开始日期"
                                                    end-placeholder="结束日期"
                                                    align="right">
                                    </el-date-picker>
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :span="9">
                            <el-form-item label="有效时间">
                                <div class="block">
                                    <el-date-picker style="width: 75%"
                                                    v-model="formData.effectiveTime"
                                                    type="datetimerange"
                                                    :picker-options="pickerOptions"
                                                    range-separator="至"
                                                    start-placeholder="开始日期"
                                                    end-placeholder="结束日期"
                                                    align="right">
                                    </el-date-picker>
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align:right">
                            <el-button style="margin-right:8px;" type="primary">查询</el-button>
                            <el-button>重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-edit" type="primary" @click="add()" style="margin-right:8px">新增</el-button>
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
                        <el-table-column prop="numberOfCars" label="车位数" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="name" label="姓名" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="phone" label="电话" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="startTime" label="开始时间" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="endTime" label="结束时间" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="effectiveStatus" label="有效状态" align="center"
                                         :show-overflow-tooltip="true" id="status">
                            <template scope="scope">
                                <span v-if="scope.row.effectiveStatus!== '已过期'">{{scope.row.effectiveStatus}}</span>
                                <span v-else style="color: red">{{scope.row.effectiveStatus}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
                            <template slot-scope="scope">
                                <el-tooltip content="编辑" placement="top">
                                    <el-button size="mini" type="text" @click="edit(scope.row)" ><i
                                            class="el-icon-edit"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="延期" placement="top">
                                    <el-button size="mini" type="text" @click="share()" ><i
                                            class="el-icon-share"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="删除" placement="top">
                                    <el-button size="mini" type="text" @click="del()" ><i
                                            class="el-icon-delete"></i></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </web-template>

<!--                     新增车辆-->
<!--        <el-dialog title="新增车辆类型" :visible.sync="dialogFormAdd" width="30%" center @close="closeAdd()">-->
<!--            <center>-->
<!--                <el-radio v-model="radio" label="1" @change="dialogFormVisible=true">包月车</el-radio>-->
<!--                <el-radio v-model="radio" label="2" @change="dialogFormFreeAdmission=true">免费车</el-radio>-->
<!--                <el-radio v-model="radio" label="3" @change="dialogFormVisitor=true">访客车</el-radio>-->
<!--            </center>-->
<!--        </el-dialog>-->
<!--        新增-->
        <AddMonth ref="addMonth"></AddMonth>
<!--        编辑-->
        <EditMonth ref="editMonth"></EditMonth>
    </div>

</template>

<script>

    import AddMonth from "./component/AddMonth";
    import EditMonth from "./component/EditMonth";
    export default {
        name: "monthlyCar",
        components: {AddMonth,EditMonth},
        data() {
            return {
                webName:'包月车辆列表',
                dialogFormAdd:false,
                dialogFormEdit: false,//新增包月车辆
                //车厂区域
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

                //日期选择器
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                //有效状态
                effectiveStatus: [
                    {
                        value: '选项1',
                        label: '使用中'
                    }, {
                        value: '选项2',
                        label: '已过期'
                    },
                ],
                //搜索框绑定值
                formData: {
                    depotArea: '',//车厂区域
                    licensePlateNumber: '',//车牌号
                    effectiveStatus: '',//有效状态
                    updateTime: [new Date(), new Date()],//更新时间
                    effectiveTime: '',//有效时间
                    parkingLotNumber:'',
                },
                //表格
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
                            numberOfCars: '1',
                            name: '张三',
                            phone: '18475487041',
                            startTime: '2020-01-01',
                            endTime: '2021-12-31',
                            effectiveStatus: '使用中',

                        },
                        {
                            area: "B区",
                            licensePlateNumber: "鲁ADL25C",
                            company: "信息",
                            parkingLotNumber: 'B117',
                            numberOfCars: '2',
                            name: '李四',
                            phone: '15125475104',
                            startTime: '2018-12-31',
                            endTime: '2020-01-01',
                            effectiveStatus: '已过期',
                        },
                        {
                            area: "访客区",
                            licensePlateNumber: "鲁BEKL75",
                            company: "运维",
                            parkingLotNumber: 'F108',
                            numberOfCars: '',
                            name: '王五',
                            phone: '18154274010',
                            startTime: '2020-08-03',
                            endTime: '2020-08-03',
                            effectiveStatus: '使用中',
                        },
                    ]
                },
            };
        },
        methods: {
           //新增
            add() {
                this.$refs.addMonth.initData();
            },
            edit(rowData) {
               this.$refs.editMonth.initData(rowData);
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
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
        },
    }
</script>

<style scoped>

</style>