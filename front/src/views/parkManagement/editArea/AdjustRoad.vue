<template>
    <dialog-template ref="adjustRoad" :dialog-name="dialogName"
                     @primaryClick="saveRoad" @closeClick="closeDialog"
                     primary-button-title="保 存"
                     width="50%"
                     top="5vh">
        <template slot="body">
            <el-form layout="inline" ref="roadForm" :model="roadData" labelWidth="110px" :rules="roadFormRules">
                <el-row>
                    <el-col>
                        <el-form-item label="车道编号：" prop="roadNo">
                            <el-input placeholder="请输入车道编号" disabled clearable v-model="roadData.roadNo"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="车道名称：" prop="roadName">
                            <el-input placeholder="请输入车道名称" clearable v-model="roadData.roadName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="主车道：" prop="isMainRoad">
                            <el-radio v-model="roadData.isMainRoad" :label=1>是</el-radio>
                            <el-radio v-model="roadData.isMainRoad" :label=0>否</el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="远程呼叫号码：" prop="telephone">
                            <el-input placeholder="请输入远程呼叫号码" clearable v-model="roadData.telephone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="设备类型：" prop="deviceType">
                            <el-select v-model="roadData.deviceType" placeholder="请选择设备类型" style="width: 100%">
                                <el-option v-for="item in deviceTypeList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="摄像头IP：" prop="cameraIP">
                            <el-input placeholder="请输入摄像头IP" clearable v-model="roadData.cameraIP"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="设备厂家：" prop="deviceProducer">
                            <el-select v-model="roadData.deviceProducer" placeholder="请选择设备厂家" style="width: 100%">
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
                                      v-model="roadData.remarks">
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
        name: "AdjustRoad",
        data(){
            return {
                isEdit: false,
                roadData: {
                    isMainRoad: 1,
                },
                roadFormRules: {
                    roadName: {required: true,message: '车道名称不能为空！',trigger: 'blur'},
                    isMainRoad: {required: true,message: '是否主车道必须选择！',trigger: 'blur'},
                },
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
                    },{
                        label: '测试2',
                        value: 2,
                    }
                ],
                doorway: {},
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑车道';
                }else{
                    return '新增车道';
                }
            }
        },
        methods: {
            initData(doorway,road){
                if(road){
                    this.isEdit = true;
                    this.$nextTick(() => {
                        this.roadData = Object.assign({}, road);
                    })
                }else{
                    this.isEdit = false;
                }
                this.doorway = doorway;
                this.$refs.adjustRoad.show();
            },
            saveRoad(){
                if(this.isEdit){
                    //编辑
                    this.$refs.roadForm.validate(valid => {
                        if(valid){
                            this.doorway = {}
                            this.$refs.roadForm.resetFields();
                            this.$refs.roadForm.clearValidate();
                            this.$refs.adjustRoad.close();
                            this.$emit('refreshDoorwayTable');
                            this.$message({
                                message: '编辑车道成功！',
                                type: "success"
                            })
                        }
                    })
                }else{
                    //新增
                    this.$refs.roadForm.validate(valid => {
                        if(valid){
                            this.doorway = {}
                            this.$refs.roadForm.resetFields();
                            this.$refs.roadForm.clearValidate();
                            this.$refs.adjustRoad.close();
                            this.$emit('refreshDoorwayTable');
                            this.$message({
                                message: '新增车道成功！',
                                type: "success"
                            })
                        }
                    })
                }
            },
            closeDialog(){
                this.$refs.roadForm.resetFields();
                this.$refs.roadForm.clearValidate();
                this.doorway = {}
                this.roadData = {isMainRoad: 1}
                this.$refs.adjustRoad.close();
            },
        }
    }
</script>

<style scoped>

</style>