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
                                          style="width: 180px"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车位类型" labelWidth="80px">
                                <el-select v-model="formData.parkingSpaceType" filterable clearable placeholder="请选择类型"
                                           style="width: 100%;">
                                    <el-option v-for="item in parkingSpaceType" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
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
                <el-button class="el-icon-edit" type="primary" @click="add()">新增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                        <el-table-column prop="area" label="区域" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="licensePlateNumber" label="车牌号" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="company" label="公司" align="center" :show-overflow-tooltip="true"/>
                        <el-table-column prop="parkingLotNumber" label="车位编号" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="parkingSpaceType" label="车位类型" align="center"
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
                                    <el-button size="mini" type="text" @click="edit(scope.row)"><i
                                            class="el-icon-edit"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="延期" placement="top">
                                    <el-button size="mini" type="text" @click="share()"><i
                                            class="el-icon-share"></i></el-button>
                                </el-tooltip>
                                <el-tooltip content="删除" placement="top">
                                    <el-button size="mini" type="text" @click="del()"><i
                                            class="el-icon-delete"></i></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
            </div>
        </web-template>

        <!--                新增车辆-->
        <el-dialog title="新增车辆类型" :visible.sync="dialogFormAdd" width="30%" center @close="closeAdd()">
            <center>
                <el-radio v-model="radio" label="1" @change="dialogFormVisible=true">包月车</el-radio>
                <el-radio v-model="radio" label="2" @change="dialogFormFreeAdmission=true">免费车</el-radio>
                <el-radio v-model="radio" label="3" @change="dialogFormVisitor=true">访客车</el-radio>
            </center>
        </el-dialog>

        <!--        新增包月车/编辑弹窗-->
        <el-dialog title="新增/修改车辆" :visible.sync="dialogFormVisible" center>
            <el-form :model="popup" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="停车区域">
                            <el-select v-model="popup.area" placeholder="请选择区域" style="width: 100%">
                                <el-option v-for="item in depotArea" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定车辆">
                            <el-input v-model="popup.licensePlateNumber" placeholder="请填写绑定车辆"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="公司">
                            <el-input v-model="popup.company" placeholder="请填写公司"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="姓名">
                            <el-input v-model="popup.name" placeholder="请填写姓名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电话">
                            <el-input v-model="popup.phone" placeholder="请填写手机号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车辆类型">
                            <el-select v-model="popup.parkingSpaceType" placeholder="请选择类型" style="width: 100%">
                                <el-option v-for="item in parkingSpaceType" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="开始时间">
                            <el-date-picker style="width: 103%"
                                            v-model="popup.startTime"
                                            align="right"
                                            type="date"
                                            placeholder="选择日期"
                                            :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="结束时间">
                            <el-date-picker
                                    v-model="popup.endTime"
                                    align="right"
                                    type="date"
                                    placeholder="选择日期"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车位编号">
                            <el-input v-model="popup.parkingLotNumber" placeholder="请填写编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车位数">
                            <el-input v-model="popup.numberOfCars" placeholder="请填写车位数"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="margin-top: -20px">
                <el-button @click="showMessage(false)">取 消</el-button>
                <el-button type="primary" @click="showMessage(true)">确 定</el-button>
            </div>
        </el-dialog>

        <!--        免费车辆弹窗-->
        <el-dialog title="新增免费车辆" :visible.sync="dialogFormFreeAdmission" center>
            <el-form :model="popup" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="停车区域">
                            <el-select v-model="popup.area" placeholder="请选择区域" style="width: 100%">
                                <el-option v-for="item in depotArea" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定车辆">
                            <el-input v-model="popup.licensePlateNumber" placeholder="请填写绑定车辆"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="公司">
                            <el-input v-model="popup.company" placeholder="请填写公司"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车辆类型">
                            <el-select v-model="popup.parkingSpaceType" placeholder="请选择类型" style="width: 100%">
                                <el-option v-for="item in parkingSpaceType" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="开始时间">
                            <el-date-picker style="width: 103%"
                                            v-model="popup.startTime"
                                            align="right"
                                            type="date"
                                            placeholder="选择日期"
                                            :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="结束时间">
                            <el-date-picker
                                    v-model="popup.endTime"
                                    align="right"
                                    type="date"
                                    placeholder="选择日期"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车位编号">
                            <el-input v-model="popup.parkingLotNumber" placeholder="请填写编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车位数">
                            <el-input v-model="popup.numberOfCars" placeholder="请填写车位数"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="margin-top: -20px">
                <el-button @click="showMessageFreeAdmission(false)">取 消</el-button>
                <el-button type="primary" @click="showMessageFreeAdmission(true)">确 定</el-button>
            </div>
        </el-dialog>

        <!--        访客车辆-->
        <el-dialog title="新增访客车辆" :visible.sync="dialogFormVisitor" center>
            <el-form :model="popup" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="停车区域">
                            <el-select v-model="popup.area" placeholder="请选择区域" style="width: 100%">
                                <el-option v-for="item in depotArea" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定车辆">
                            <el-input v-model="popup.licensePlateNumber" placeholder="请填写绑定车辆"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="公司">
                            <el-input v-model="popup.company" placeholder="请填写公司"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="姓名">
                            <el-input v-model="popup.name" placeholder="请填写姓名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电话">
                            <el-input v-model="popup.phone" placeholder="请填写手机号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车辆类型">
                            <el-select v-model="popup.parkingSpaceType" placeholder="请选择类型" style="width: 100%">
                                <el-option v-for="item in parkingSpaceType" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="开始时间">
                            <el-date-picker style="width: 103%"
                                            v-model="popup.startTime"
                                            align="right"
                                            type="date"
                                            placeholder="选择日期"
                                            :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="结束时间">
                            <el-date-picker
                                    v-model="popup.endTime"
                                    align="right"
                                    type="date"
                                    placeholder="选择日期"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车位编号">
                            <el-input v-model="popup.parkingLotNumber" placeholder="请填写编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车位数">
                            <el-input v-model="popup.numberOfCars" placeholder="请填写车位数"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="margin-top: -20px">
                <el-button @click="showMessageVisitor(false)">取 消</el-button>
                <el-button type="primary" @click="showMessageVisitor(true)">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import addMonth from './component/AddMonth'
    export default {
        name: "vehicleList",
        data() {
            return {
                webName:'车辆列表',
                dialogFormAdd: false,//新增车辆
                dialogFormFreeAdmission: false,//新增免费车两
                dialogFormVisitor: false,//新增访客车辆
                radio: '',
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
                //车位类型
                parkingSpaceType: [
                    {
                        value: '选项1',
                        label: '包月车'
                    }, {
                        value: '选项2',
                        label: '免费车'
                    }, {
                        value: '选项3',
                        label: '访客车',
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
                    parkingSpaceType: '',//车位类型
                    effectiveStatus: '',//有效状态
                    updateTime: [new Date(), new Date()],//更新时间
                    effectiveTime: '',//有效时间
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
                            parkingSpaceType: '免费车',
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
                            parkingSpaceType: '包月车',
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
                            parkingSpaceType: '访客车',
                            numberOfCars: '',
                            name: '王五',
                            phone: '18154274010',
                            startTime: '2020-08-03',
                            endTime: '2020-08-03',
                            effectiveStatus: '使用中',
                        },
                    ]
                },
                //弹窗
                dialogFormVisible: false,
                //弹窗form
                popup: {
                    depotArea: '',
                    licensePlateNumber: "",
                    company: '',
                    name: '',
                    phone: '',
                    parkingSpaceType: '',
                    parkingLotNumber: '',
                },
            };
        },
        methods: {

            add() {
                this.popup = [];
                this.dialogFormAdd = true
            },
            edit(rowData) {
                console.log(rowData);
                this.popup = rowData;
                this.dialogFormVisible = true
            },
            closeAdd() {
                this.radio = "";
            },
            showMessage(flag) {
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!',
                    });
                    this.popup = {};
                    this.dialogFormAdd = false;
                    this.dialogFormVisible = false;
                    this.popup = {};
                } else {
                    this.dialogFormVisible = false;
                    this.popup = {};
                }
            },
            showMessageFreeAdmission(flag) {
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!',
                    });
                    this.dialogFormAdd = false;
                    this.dialogFormFreeAdmission = false;
                    this.popup = {};
                } else {
                    this.dialogFormFreeAdmission = false;
                    this.popup = {};
                }
            },
            showMessageVisitor(flag) {
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!',
                    });
                    this.dialogFormAdd = false;
                    this.dialogFormVisitor = false;
                    this.popup = {};
                } else {
                    this.dialogFormVisitor = false;
                    this.popup = {};
                }
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