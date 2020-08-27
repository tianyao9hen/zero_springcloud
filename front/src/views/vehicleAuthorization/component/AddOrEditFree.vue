<template>
    <dialog-template ref="dialogTemplate" dialog-name="编辑免费车辆"
                     @primaryClick="showMessage(true)"
                     @closeClick="showMessage(false)" width="50%">
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
                        <el-form-item label="身份归属" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.company" placeholder="请填写"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="车牌号" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.licensePlateNumber" placeholder="请填写"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="车位编号" :rules="[ { required: true, message: '不能为空'}]">
                            <el-input v-model="popup.parkingLotNumber" placeholder="请填写"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AddOrEditFree",
        data(){
            return{
                //弹窗form
                popup: {
                    area: '',
                    licensePlateNumber: "",
                    company: '',
                    parkingLotNumber: '',
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
            }
        },
        methods:{
            initData(rowData){
                if(rowData===null){
                    this.popup=null;
                }else {
                    this.popup=rowData;
                }
                this.$refs.dialogTemplate.show();
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
        }
    }
</script>

<style scoped>

</style>