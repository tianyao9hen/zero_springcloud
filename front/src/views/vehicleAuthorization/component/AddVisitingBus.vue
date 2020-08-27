<template>
    <!--        新增访客车辆-->
    <dialog-template ref="dialogTemplate" dialog-name="新增访客车辆"
                     @primaryClick="showMessage(true)"
                     @closeClick="showMessage(false)" width="60%">

        <div slot="body">
            <el-form :model="popup" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电话">
                            <el-input v-model="popup.phone" placeholder="请填写手机号"></el-input>
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
                        <el-form-item label="被访公司">
                            <el-input v-model="popup.companiesInterviewed" placeholder="请填写姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="被访人">
                            <el-input v-model="popup.people" placeholder="请填写公司"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AddVisitingBus",
        data(){
          return{
              //弹窗
              popup: {
                  companiesInterviewed: '',
                  people: "",
                  name: '',
                  phone: '',

              },
          }
        },
        methods:{
            initData(){
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