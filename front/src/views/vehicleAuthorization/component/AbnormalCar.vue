<template>
    <dialog-template ref="abnormalCar" dialog-name="新增/修改异常车辆"
                     @primaryClick="showMessage(true)"
                     @closeClick="showMessage(false)">
        <template slot="body">
            <el-form :model="addForm" label-width="70px" size="medium">
                <el-form-item label="车牌号">
                    <el-input v-model="addForm.licensePlateNumber" placeholder="请填写车牌号"
                              style="width: 100%"></el-input>
                </el-form-item>
                <el-form-item label="车辆类型">
                    <el-select v-model="addForm.parkingSpaceType" placeholder="请选择" style="width: 100%">
                        <el-option v-for="item in parkingSpaceType" :key="item.value" :label="item.label"
                                   :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="异常备注">
                    <el-input type="abnormalRemarks" v-model="addForm.abnormalRemarks"
                              placeholder="请填写备注信息"></el-input>
                </el-form-item>
            </el-form>
        </template>
    </dialog-template>
</template>

<script>
    export default {
        name: "AbnormalCar",
        components: {},
        data(){
            return{
                dialogFormVisible: false,
                addForm: {
                    licensePlateNumber: '',
                    parkingSpaceType: '',
                    abnormalRemarks: '',
                },
                parkingSpaceType:[
                    {
                        value: '选项1',
                        label: '小型车'
                    },
                    {
                        value: '选项2',
                        label: '中型车'
                    },
                    {
                        value: '选项3',
                        label: '大型车'
                    },
                ],
            }
        },
        methods:{
            initData(rowDate) {
                if(rowDate!=null) {
                    this.$nextTick(() => {
                        this.addForm = rowDate;
                    })
                }else {
                    this.addForm = {};
                }
                this.$refs.abnormalCar.show()
            },
            close(){
                this.$refs.abnormalCar.close()
            },
            showMessage(flag) {
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                } else {
                }
            },
        },
    }
</script>

<style scoped>

</style>