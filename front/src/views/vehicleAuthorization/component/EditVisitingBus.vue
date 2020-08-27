<template>
    <dialog-template ref="dialogTemplate" dialog-name="编辑包月车辆"
                     @primaryClick="showMessage(true)"
                     @closeClick="showMessage(false)" width="60%">
        <div slot="body">
            <el-form :model="popup" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="停车区域" :rules="[ { required: true, message: '不能为空'}]">
                            <el-select v-model="popup.area" placeholder="请选择区域" style="width: 100%">
                                <el-option v-for="item in depotArea" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定车辆" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.licensePlateNumber" placeholder="请填写绑定车辆"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="被访公司" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.companiesInterviewed" placeholder="请填写"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="访客姓名" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.name" placeholder="请填写姓名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="联系方式" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.phone" placeholder="请填写联系方式"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定车位" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.parkingLotNumber" placeholder="请填写费用"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="开始时间" :rules="[ { required: true, message: '不能为空'}]">
                            <el-date-picker style="width: 100%"
                                            v-model="popup.startTime"
                                            align="right"
                                            type="date"
                                            placeholder="选择日期"
                                            :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="结束时间" :rules="[ { required: true, message: '不能为空'}]">
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
                        <el-form-item label="车位数量" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.numberOfCars" placeholder="请填写购买数量"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="使用状态" :rules="[ { required: true, message: '不能为空'}]">
                            <el-select v-model="popup.effectiveStatus" placeholder="请选择区域" style="width: 100%">
                                <el-option v-for="item in effectiveStatus" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "EditVisitingBus",
        data(){
            return{
                //弹窗form
                popup: {
                    depotArea: '',
                    licensePlateNumber: "",
                    company: '',
                    name: '',
                    phone: '',
                    parkingLotNumber: '',
                    cost:'',
                },
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
                //使用状态
                effectiveStatus: [
                    {
                        value: '选项1',
                        label: '使用中'
                    }, {
                        value: '选项2',
                        label: '已过期'
                    },
                ],
            }
        },
        methods:{
            initData(rowData){
                console.log(rowData);
                this.popup=rowData;
                this.$refs.dialogTemplate.show()
            },
            showMessage(flag) {
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                } else {
                }
                this.$refs.dialogTemplate.close();
            },
        },

    }
</script>

<style scoped>

</style>