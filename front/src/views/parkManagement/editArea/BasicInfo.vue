<template>
    <el-row>
        <el-col :span="22" :offset="1">
            <el-form layout="inline" ref="basicForm" :model="basicData" labelWidth="110px" :rules="basicFormRules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="区域编号：" prop="areaNo">
                            <el-input placeholder="请输入区域编号" disabled clearable v-model="basicData.areaNo"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="区域名称：" prop="areaName">
                            <el-input placeholder="请输入区域名称" :disabled="!isEdit" clearable
                                      v-model="basicData.areaName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车位数：" prop="parkingSpotCount" required>
                            <el-input placeholder="请输入车位数" :disabled="!isEdit" clearable
                                      v-model="basicData.parkingSpotCount"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="运行类型：" prop="areaType">
                            <el-select v-model="basicData.areaType" :disabled="!isEdit" filterable clearable
                                       placeholder="请选择运行类型" style="width: 100%;">
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
                        <el-form-item label="运行状态：" prop="areaStatus">
                            <el-select v-model="basicData.areaStatus" :disabled="!isEdit" filterable clearable
                                       placeholder="请选择运行状态" style="width: 100%;">
                                <el-option v-for="item in areaStatusList" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="入场限制：" prop="areaType">
                            <el-select v-model="basicData.areaAstrict" :disabled="!isEdit" filterable clearable
                                       placeholder="请选择入场限制" style="width: 100%;">
                                <el-option v-for="item in areaAstrictList" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="是否允许黑名单车辆进入：" prop="isPermitBlackList" label-width="209px">
                            <el-radio v-model="basicData.isPermitBlackList" :disabled="!isEdit" :label="1">允许</el-radio>
                            <el-radio v-model="basicData.isPermitBlackList" :disabled="!isEdit" :label="0">不允许
                            </el-radio>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否允许一位多车进入：" prop="isPermitMoveCar" label-width="193px">
                            <el-radio v-model="basicData.isPermitMoveCar" :disabled="!isEdit" :label="1">允许</el-radio>
                            <el-radio v-model="basicData.isPermitMoveCar" :disabled="!isEdit" :label="0">不允许</el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="所属父级区域：" prop="parentArea">
                            <el-select v-model="basicData.parentArea" :disabled="!isEdit" filterable clearable
                                       placeholder="请选择所属父级区域" style="width: 100%;">
                                <el-option v-for="item in areaList" :key="item.value"
                                           :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="备注：" prop="remarks">
                            <el-input type="textarea" :disabled="!isEdit" :autosize="{ minRows: 4, maxRows: 10}"
                                      placeholder="请输入备注"
                                      v-model="basicData.remarks">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div class="footer-button" v-if="isEdit">
                    <el-button @click="cancelBasic" v-if="!isAdd">取 消</el-button>
                    <el-button class="el-icon-edit-outline" type="primary" @click="saveBasic" v-if="!isAdd"> 保 存
                    </el-button>
                    <el-button class="el-icon-edit-outline" type="primary" @click="saveAndNextBasic" v-if="isAdd">保存并下一步
                    </el-button>
                </div>
                <div class="footer-button" v-if="!isEdit">
                    <el-button class="el-icon-edit" type="primary" @click="editBasic" v-if="!isAdd"> 编 辑</el-button>
                </div>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
    import bus from "components/common/bus";

    export default {
        name: "BasicInfo",
        data() {
            return {
                basicFormRules: {
                    areaNo: {required: true, message: '区域编号不能为空！', trigger: 'blur'},
                    areaName: {required: true, message: '区域名称不能为空！', trigger: 'blur'},
                    parkingSpotCount: {validator: this.isNum, trigger: ['blur', 'change']},
                    areaType: {required: true, message: '请选择运行类型！', trigger: ['blur', 'change']},
                    areaStatus: {required: true, message: '请选择运行状态！', trigger: ['blur', 'change']},
                    areaAstrict: {required: true, message: '请选择入场限制！', trigger: ['blur', 'change']},
                    isPermitBlackList: {required: true, message: '请选择是否允许黑名单车辆进入！', trigger: 'blur'},
                    isPermitMoveCar: {required: true, message: '请选择是否允许一位多车进入！', trigger: 'blur'},
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
                    }, {
                        label: '停用',
                        value: '0',
                    }
                ],
                areaAstrictList: [],
                areaList: [
                    {
                        label: '固定区域1',
                        value: 'gd001',
                    }, {
                        label: '固定区域2',
                        value: 'gd002',
                    }, {
                        label: '临时区域1',
                        value: 'ls001',
                    }, {
                        label: '临时区域2',
                        value: 'ls002',
                    }
                ],
                isEdit: false,
                basicData: {
                    areaId: '',
                    areaNo: '',
                    areaName: '',
                    parkingSpotCount: 1,
                    areaType: '',
                    areaStatus: '',
                    areaAstrict: '',
                    parentArea: '',
                    isPermitBlackList: 1,
                    isPermitMoveCar: 1,
                    remarks: '',
                },
                oldBasicData: {
                    areaId: '',
                    areaNo: '',
                    areaName: '',
                    parkingSpotCount: 0,
                    areaType: '',
                    areaStatus: '',
                    areaAstrict: '',
                    parentArea: '',
                    isPermitBlackList: 1,
                    isPermitMoveCar: 1,
                    remarks: '',
                },
            }
        },
        props: {
            areaId: {
                type: String,
            },
            areaNo: {
                type: String,
            },
            isAdd: {
                type: Boolean,
                default: true,
            }
        },
        mounted() {
            //判断是新增还是编辑
            this.addOrEdit();
            //新增：字段为空不调用方法  编辑：调用方法
            if (this.areaId && this.areaNo) {
                this.queryBasicInfo()
            }
        },
        methods: {
            //判断是新增还是编辑
            addOrEdit(){
                if(this.isAdd){
                    this.isEdit=true
                }
            },

            //查询区域基本信息
            queryBasicInfo() {
                this.basicData = Object.assign({}, {
                    areaId: this.areaId,
                    areaNo: this.areaNo,
                    areaName: '测试名称1',
                    parkingSpotCount: '50',
                    areaType: '0',
                    areaStatus: '1',
                    areaAstrict: '',
                    parentArea: 'gd002',
                    isPermitBlackList: 1,
                    isPermitMoveCar: 1,
                    remarks: '备注内容测试',
                });
                this.oldBasicData = Object.assign({}, {
                    areaId: this.areaId,
                    areaNo: this.areaNo,
                    areaName: '测试名称1',
                    parkingSpotCount: '50',
                    areaType: '0',
                    areaStatus: '1',
                    areaAstrict: '',
                    parentArea: 'gd002',
                    isPermitBlackList: 1,
                    isPermitMoveCar: 1,
                    remarks: '备注内容测试',
                });
            },
            //编辑 / 保存 / 取消 / 保存并下一步
            editBasic() {
                this.isEdit = true;
            },
            saveBasic() {
                this.$refs.basicForm.validate(valid => {
                    if (valid) {
                        this.$message({
                            type: 'success',
                            message: '保存成功!'
                        });
                        this.isEdit = false;
                    }
                })
            },
            cancelBasic() {
                Object.assign(this.basicData, this.oldBasicData);
                this.isEdit = false;
            },
            saveAndNextBasic() {
                this.basicData.areaId = '5';
                this.basicData.areaNo = 'LS001';
                this.basicData.areaAstrict = '无限制';
                this.$refs.basicForm.validate(valid => {
                    if (valid) {
                        this.$message({
                            type: 'success',
                            message: '保存成功!'
                        });
                        this.$emit("saveAndNext-basic", this.basicData);
                    }
                })

            },

            isNum(rule, value, callback) {
                const age = /^[1-9][0-9]*$/
                if (!age.test(value)) {
                    callback(new Error('车位数量必须为大于0的数字！'))
                } else {
                    callback()
                }
            },
        }
    }
</script>

<style scoped>
    .footer-button {
        text-align: center;
        position: relative;
    }
</style>