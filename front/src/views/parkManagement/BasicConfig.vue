<template>
    <div class="basic-config">
        <el-container>
            <el-main>
                <body-card card-name="基础信息">
                    <template slot="headerButton" v-if="infoCanEdit">
                        <el-button class="el-icon-edit" v-if="!isEdit" type="primary" @click="editBasic">编辑</el-button>
                        <el-button v-if="isEdit" @click="cancelEdit">取消</el-button>
                        <el-button class="el-icon-edit-outline" v-if="isEdit" type="primary" @click="saveEdit">保存</el-button>
                    </template>
                    <template slot="body">
                        <el-row>
                            <el-col :offset="1" :span="8">
                                <el-form layout="inline" ref="queryForm" :model="basicData" labelWidth="110px" :rules="formRules">
                                    <el-form-item label="停车场名称：" prop="parkName">
                                        <el-input placeholder="请输入停车场名称" :readonly="!isEdit" clearable v-model="basicData.parkName"></el-input>
                                    </el-form-item>
                                    <el-form-item label="所有者名称：" prop="ownerName">
                                        <el-input placeholder="请输入所有者名称" :readonly="!isEdit" clearable v-model="basicData.ownerName"></el-input>
                                    </el-form-item>
                                    <el-form-item label="联系电话1：" prop="telephone1">
                                        <el-input placeholder="请输入联系电话1" :readonly="!isEdit" clearable v-model="basicData.telephone1"></el-input>
                                    </el-form-item>
                                    <el-form-item label="联系电话2：" prop="telephone2">
                                        <el-input placeholder="请输入联系电话2" :readonly="!isEdit" clearable v-model="basicData.telephone2"></el-input>
                                    </el-form-item>
                                    <el-form-item label="详细地址：" prop="address">
                                        <el-input placeholder="请输入详细地址" :readonly="!isEdit" clearable v-model="basicData.address"></el-input>
                                    </el-form-item>
                                    <el-form-item label="经纬度坐标：" prop="coordinateStr">
                                        <el-input placeholder="请选择坐标" readonly v-model="basicData.coordinateStr"></el-input>
                                    </el-form-item>
                                    <el-form-item label="访客访问时间：">
                                        <el-row>
                                            <el-col :span="11">
                                                <el-form-item prop="startTime">
                                                    <el-time-select style="width: 100%;margin-right: 4%"
                                                                    placeholder="起始时间" :readonly="!isEdit"
                                                                    v-model="basicData.startTime"
                                                                    @change="startTimeChange"
                                                                    :picker-options="{
                                                                      start: '00:00',
                                                                      step: '00:05',
                                                                      end: '24:00'
                                                                    }">
                                                    </el-time-select>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :offset="2" :span="11">
                                                <el-form-item prop="endTime">
                                                    <el-time-select style="width: 100%"
                                                                    placeholder="结束时间" :readonly="!isEdit"
                                                                    v-model="basicData.endTime"
                                                                    :picker-options="{
                                                                      start: '00:00',
                                                                      step: '00:05',
                                                                      end: '24:00',
                                                                      minTime: basicData.startTime
                                                                    }">
                                                    </el-time-select>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                            <el-col :offset="1" :span="14">
                                <div class="map-area">
                                    <map-show :park-name="basicData.parkName"
                                              :lng="basicData.lng" :lat="basicData.lat"
                                              :isShowButton="isEdit"
                                              @opt-coordinate="optCoordinate">
                                    </map-show>
                                </div>
                            </el-col>
                        </el-row>
                    </template>
                </body-card>
                <body-card card-name="停车场图片信息" style="margin-top: 20px">
                    <template slot="body">
                        <image-show :can-edit="imageCanEdit"></image-show>
                    </template>
                </body-card>
            </el-main>
        </el-container>
        <map-select-dialog ref="mapSelectDialog" @change-coordinate="changeCoordinate"></map-select-dialog>
    </div>
</template>

<script>

    import BodyCard from "components/webTemplate/cardTemplate/BodyCard";
    import MapShow from "./basicConfig/MapShow";
    import MapSelectDialog from "./basicConfig/MapSelectDialog";
    import ImageShow from "./basicConfig/ImageShow";

    export default {
        name: "BasicConfig",
        data(){
            return {
                timeStart: '00.00',
                timeEnd: '00.05',
                timeStep: '24.00',
                basicData: {
                    parkName: '齐鲁交通XXXXX停车场',
                    ownerName: '齐鲁交通',
                    telephone1: '13800000000',
                    telephone2: '13800000000',
                    address: '测试地址',
                    lng: 117.132651,
                    lat: 36.685697,
                    startTime: '00:10',
                    endTime: '00:15',
                    coordinateStr: '117.132651,36.685697',
                },
                oldBasicData: {},
                isEdit: false,
                rules: {
                    parkName: {required:true,message: '请输入停车场名称',trigger: 'blur'},
                    ownerName: {required:true,message: '请输入所有者名称',trigger: 'blur'},
                    address: {required:true,message: '请输入详细地址',trigger: ['change','blur']},
                    coordinateStr: {required:true,message: '请抓取经纬度信息',trigger: ['change','blur']}
                },
                //权限部分
                imageCanEdit: true,//能否编辑图片,默认false
                infoCanEdit: true,//能否编辑基本信息,默认false
            }
        },
        components: {
            ImageShow,
            MapSelectDialog,
            MapShow,
            BodyCard,
        },
        computed: {
            formRules(){
                if(this.isEdit){
                    return this.rules;
                }else{
                    return {};
                }
            },
        },
        methods: {
            cancelEdit(){
                //取消编辑
                this.$confirm('是否取消编辑，当前的内容将不会被保存？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.$refs.queryForm.clearValidate();
                    this.$refs.queryForm.resetFields();
                    //Object.assign(this.basicData,this.oldBasicData);
                    this.isEdit = false;
                })
            },
            saveEdit(){
                //保存编辑
                this.$confirm('是否保存编辑？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.$refs.queryForm.validate(valid => {
                        if(valid){
                            this.$message({
                                message: '保存成功！',
                                type: 'success'
                            });
                            this.isEdit = false;
                        }
                    })
                })
            },
            editBasic(){
                //编辑基础信息
                //Object.assign(this.oldBasicData,this.basicData);
                this.isEdit = true;
            },
            optCoordinate(){
                this.$refs.mapSelectDialog.initDialog(this.basicData.lng,this.basicData.lat,this.basicData.address);
            },
            startTimeChange(){
                this.basicData.endTime = '';
            },
            changeCoordinate(lng,lat,address){
                this.$confirm('是否修改坐标为：'+lng+','+lat+' ?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.basicData.lng = lng;
                    this.basicData.lat = lat;
                    this.basicData.coordinateStr = lng +","+ lat;
                    this.$confirm('是否修改详细地址为：'+address+'?','提示',{
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'info'
                    }).then(() => {
                        this.basicData.address = address;
                    })
                })
            }
        }
    }
</script>

<style scoped>
    .map-area {
        width: 100%;
        height: 350px;
    }
</style>