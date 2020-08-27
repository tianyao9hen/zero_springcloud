<template>
    <dialog-template ref="adjustDoorway" :dialog-name="dialogName"
                     @primaryClick="saveDoorway" @closeClick="closeDialog"
                     primaryButtonTitle="保 存"
                     width="50%" top="5vh">
        <template slot="body">
            <el-form layout="inline" ref="doorwayForm" :model="doorwayData" labelWidth="110px"
                     :rules="doorwayFormRules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="进出口编号：" prop="doorwayNo">
                            <el-input placeholder="请输入进出口编号" disabled clearable
                                      v-model="doorwayData.doorwayNo"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="进出口名称：" prop="doorwayName">
                            <el-input placeholder="请输入进出口名称" clearable v-model="doorwayData.doorwayName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="进出口类型：" prop="doorwayType">
                            <el-select v-model="doorwayData.doorwayType" placeholder="请选择进出口类型" style="width: 100%">
                                <el-option v-for="item in doorwayTypeList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="!isEdit">
                    <el-col :span="12">
                        <el-form-item label="车道编号：" prop="roadNo">
                            <el-input placeholder="请输入车道编号" disabled clearable v-model="doorwayData.roadNo"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车道名称：" prop="roadName">
                            <el-input placeholder="请输入车道名称" clearable v-model="doorwayData.roadName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="!isEdit">
                    <el-col :span="12">
                        <el-form-item label="主车道：" prop="isMainRoad">
                            <el-radio v-model="doorwayData.isMainRoad" :label=1>是</el-radio>
                            <el-radio v-model="doorwayData.isMainRoad" :label=0>否</el-radio>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="远程呼叫号码：" prop="telephone">
                            <el-input placeholder="请输入远程呼叫号码" clearable v-model="doorwayData.telephone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="!isEdit">
                    <el-col :span="12">
                        <el-form-item label="设备类型：" prop="deviceType">
                            <el-select v-model="doorwayData.deviceType" placeholder="请选择设备类型" style="width: 100%">
                                <el-option v-for="item in deviceTypeList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="摄像头IP：" prop="cameraIP">
                            <el-input placeholder="请输入摄像头IP" clearable v-model="doorwayData.cameraIP"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="!isEdit">
                    <el-col :span="12">
                        <el-form-item label="设备厂家：" prop="deviceProducer">
                            <el-select v-model="doorwayData.deviceProducer" placeholder="请选择设备厂家" style="width: 100%">
                                <el-option v-for="item in deviceProducerList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="备注：" prop="remarks">
                            <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}"
                                      placeholder="请输入备注"
                                      v-model="doorwayData.remarks">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustDoorway",
        data() {
            return {
                doorwayTypeList: [
                    {
                        label: '入口',
                        value: 1,
                    }, {
                        label: '出口',
                        value: 0,
                    }
                ],
                deviceTypeList: [
                    {
                        label: '车牌识别仪',
                        value: 1,
                    }
                ],
                deviceProducerList: [
                    {
                        label: '测试1',
                        value: 1,
                    }, {
                        label: '测试2',
                        value: 2,
                    }
                ],
                isEdit: false,
                doorwayData: {
                    isMainRoad: 1,
                    doorwayType: 1,
                },
                addDoorwayFormRules: {
                    doorwayName: {required: true, message: '进出口名称不能为空！', trigger: 'blur'},
                    doorwayType: {required: true, message: '进出口类型不能为空！', trigger: 'blur'},
                    roadName: {required: true, message: '车道名称不能为空！', trigger: 'blur'},
                    isMainRoad: {required: true, message: '是否主车道必须选择！', trigger: 'blur'},
                },
                editDoorwayFormRules: {
                    doorwayName: {required: true, message: '进出口名称不能为空！', trigger: 'blur'},
                    doorwayType: {required: true, message: '进出口类型不能为空！', trigger: 'blur'},
                }
            }
        },
        computed: {
            doorwayFormRules() {
                if (this.isEdit) {
                    return this.editDoorwayFormRules;
                } else {
                    return this.addDoorwayFormRules;
                }
            },
            dialogName() {
                if (this.isEdit) {
                    return '编辑进出口';
                } else {
                    return '新增进出口';
                }
            }
        },
        methods: {
            initData(rowData) {
                if (rowData != null) {
                    this.isEdit = true;
                    this.$nextTick(() => {
                        this.doorwayData = Object.assign({}, rowData);
                    })
                } else {
                    this.isEdit = false;
                }
                this.$refs.adjustDoorway.show();
            },

            saveDoorway() {
                //保存进出口
                if (this.isEdit) {
                    //编辑
                    this.$refs.doorwayForm.validate((valid) => {
                        if (valid) {
                            this.doorwayData = {
                                isMainRoad: 1,
                                doorwayType: 1,
                            }
                            this.$refs.adjustDoorway.close();
                            this.$emit('refreshDoorwayTable');
                            this.$message({
                                message: '编辑进出口成功！',
                                type: "success"
                            })
                        } else {
                            //校验不通过
                        }
                    })
                } else {
                    //新增
                    this.$refs.doorwayForm.validate((valid) => {
                        if (valid) {
                            //校验通过
                            this.$refs.doorwayForm.resetFields();
                            this.$refs.doorwayForm.clearValidate();
                            this.$refs.adjustDoorway.close();
                            this.$emit('refreshDoorwayTable');
                            this.$message({
                                message: '新增进出口成功！',
                                type: "success"
                            });
                        } else {
                            //校验不通过
                        }
                    })
                }
            },
            closeDialog() {
                //关闭弹窗
                this.$refs.doorwayForm.resetFields();
                this.$refs.doorwayForm.clearValidate();
                this.doorwayData = {
                    isMainRoad: 1,
                    doorwayType: 1,
                }
                this.$refs.adjustDoorway.close();
            }
        }
    }
</script>

<style scoped>

</style>