<template>
    <dialog-template ref="dialogTemplate" :dialog-name="dialogName"
                     @primaryClick="saveParkingCamera"
                     @closeClick="cancelAdjust" width="60%">
        <div slot="body">
            <el-form :model="parkingCamera" ref="parkingCameraForm" label-width="90px" size="medium" :rules="rules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="相机编号" prop="cameraNo">
                            <el-input v-model="parkingCamera.cameraNo" placeholder="请填写电脑编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="相机名称" prop="cameraName">
                            <el-input v-model="parkingCamera.cameraName" placeholder="请填写服务器名称名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车位号" prop="parkingLotNumber">
                            <el-select v-model="parkingCamera.parkingLotNumber" filterable clearable
                                                     placeholder="请选择进出口"
                                                     style="width: 100%">
                            <el-option v-for="item in parkingLotNumber" :key="item.value" :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="视频IP" prop="videoIP">
                            <el-input v-model="parkingCamera.videoIP" placeholder="请填写视频IP"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="视频端口" prop="videoPort">
                            <el-input v-model="parkingCamera.videoPort" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="通道" prop="passageway">
                            <el-input v-model="parkingCamera.passageway" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="码流">
                            <el-input v-model="parkingCamera.bitstream" placeholder="请填写视频端口"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="生产厂家">
                            <el-select v-model="parkingCamera.manufacturer" filterable clearable placeholder="请选择生产厂家"
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
                        <el-form-item label="产品编号">
                            <el-input v-model="parkingCamera.productNo" placeholder="请填写产品编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="安装时间">
                            <el-date-picker
                                    v-model="parkingCamera.installTime"
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
                        <el-form-item label="备注">
                            <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="parkingCamera.remarks" placeholder="请填写备注"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustParkingCamera",
        data() {
            return {
                parkingCamera: {},
                //车位号
                parkingLotNumber: [
                    {
                        value: '001',
                        label: '701',
                    }, {
                        value: '002',
                        label: '702'
                    }
                ],
                //生产厂家
                manufacturer: [
                    {
                        value: '01',
                        label: '测试',
                    }, {
                        value: '02',
                        label: '港通',
                    }
                ],
                isEdit: false,
                rules: {
                    cameraNo: {required: true,message: '相机编号不能为空！',trigger: 'blur'},
                    cameraName: {required: true,message: '相机名称不能为空！',trigger: 'blur'},
                    parkingLotNumber: {required: true,message: '车位号不能为空！',trigger: ['blur','change']},
                    videoIP: {required: true,message: '视频IP不能为空！',trigger: 'blur'},
                    videoPort: {required: true,message: '视频端口不能为空！',trigger: 'blur'},
                    passageway: {required: true,message: '通道不能为空！',trigger: 'blur'},
                }
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑车位相机';
                }
                return '新增车位相机';
            }
        },
        methods: {
            initData(areaId,areaNo,rowData) {
                if (rowData) {
                    //编辑
                    this.parkingCamera = Object.assign({},rowData);
                    this.isEdit = true;
                } else {
                    //新增
                    this.isEdit = false;
                }
                this.areaId = areaId;
                this.areaNo = areaNo;
                this.$refs.dialogTemplate.show();
            },
            saveParkingCamera(){
                this.$refs.parkingCameraForm.validate(valid => {
                    if(valid){
                        this.$emit('refreshParkingCameraTable');
                        this.$message({
                            type: 'success',
                            message: this.isEdit? '车位相机编辑成功！': '车位相机新增成功！'
                        })
                        this.parkingCamera = {};
                        this.$refs.dialogTemplate.close();
                    }
                })
            },
            cancelAdjust(){
                //取消
                this.parkingCamera = {};
                this.$refs.parkingCameraForm.clearValidate();
                this.$refs.dialogTemplate.close();
            },
        },
    }
</script>

<style scoped>

</style>