<template>
    <dialog-template ref="dialogTemplate" :dialog-name="dialogName"
                     @primaryClick="saveMachine"
                     @closeClick="cancelAdjust" width="60%">
        <div slot="body">
            <el-form :model="ballMachine" ref="ballMachineForm" label-width="90px" size="medium" :rules="rules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="球机编号" prop="ballNo">
                            <el-input v-model="ballMachine.ballNo" placeholder="请填写电脑编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="球机名称" prop="ballName">
                            <el-input v-model="ballMachine.ballName" placeholder="请填写服务器名称名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="进出口" prop="doorwayId">
                            <el-select v-model="ballMachine.doorwayId" filterable clearable placeholder="请选择进出口"
                                       style="width: 100%">
                                <el-option v-for="item in doorwayList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="视频IP" prop="videoIP" required>
                            <el-input v-model="ballMachine.videoIP" placeholder="请填写视频IP"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="视频端口" prop="videoPort">
                            <el-input v-model="ballMachine.videoPort" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="通道" prop="passageway">
                            <el-input v-model="ballMachine.passageway" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="码流" prop="bitstream">
                            <el-input v-model="ballMachine.bitstream" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="生产厂家" prop="manufacturer">
                            <el-select v-model="ballMachine.manufacturer" filterable clearable placeholder="请选择生产厂家"
                                       style="width: 100%">
                                <el-option v-for="item in manufacturer" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="产品编号" prop=productNo>
                            <el-input v-model="ballMachine.productNo" placeholder="请填写产品编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="安装时间" prop="installTime">
                            <el-date-picker
                                    v-model="ballMachine.installTime"
                                    clearable
                                    type="datetime"
                                    style="width:100%"
                                    placeholder="请选择安装时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="描述">
                            <el-input type="textarea" v-model="ballMachine.describe" placeholder="请填写"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustBallMachineEquipment",
        data() {
            return {
                doorwayList: [
                    {
                        value: 1,
                        label: '入口1',
                    }, {
                        value: 0,
                        label: '出口1',
                    }
                ],
                //生产厂家
                manufacturer: [
                    {
                        value: '01',
                        label: '志宇',
                    }, {
                        value: '02',
                        label: '港通',
                    }
                ],
                ballMachine: {},
                isEdit: false,
                areaId: '',
                areaNo: '',
                rules: {
                    ballNo: {required: true,message: '球机编号不能为空！',trigger: 'blur'},
                    ballName: {required: true,message: '球机名称不能为空！',trigger: 'blur'},
                    doorwayId: {required: true,message: '进出口不能为空！',trigger: ['blur','change']},
                    videoIP: {validator: this.isIP,message: '视频IP地址不合法！',trigger: ['blur','change']},
                    videoPort: {required: true,message: '进出口不能为空！',trigger: ['blur','change']},
                    passageway: {required: true,message: '视频通道不能为空！',trigger: 'blur'},
                }
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑球形设备'
                }
                return '新增球形设备'
            }
        },
        methods: {
            isIP(rule,value,callback){
                const ipReg = this.BaseConstants.IS_IP_REG
                if(!ipReg.test(value)){
                    callback(new Error('视频IP地址不合法！'))
                    return;
                }
                callback()
            },
            initData(areaId,areaNo,rowData) {
                if (rowData) {
                    //编辑
                    this.ballMachine = Object.assign({},rowData);
                    this.isEdit = true;
                } else {
                    //新增
                    this.isEdit = false;
                }
                this.areaId = areaId;
                this.areaNo = areaNo;
                this.$refs.dialogTemplate.show();
            },
            saveMachine(){
                this.$refs.ballMachineForm.validate(valid => {
                    if(valid){
                        this.$emit('refreshMachineTable');
                        this.$message({
                            type: 'success',
                            message: this.isEdit? '球形设备编辑成功！': '球形设备新增成功！'
                        })
                        this.ballMachine = {}
                        this.$refs.ballMachineForm.clearValidate();
                        this.$refs.dialogTemplate.close();
                    }
                })
            },
            cancelAdjust(){
                //取消
                this.ballMachine = {}
                this.$refs.ballMachineForm.clearValidate();
                this.$refs.dialogTemplate.close();
            },
        },
    }
</script>

<style scoped>

</style>