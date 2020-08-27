<template>
    <dialog-template ref="dialogTemplate" :dialog-name="dialogName"
                     primary-button-title="保 存"
                     @primaryClick="saveServer"
                     @closeClick="cancelAdjust" width="60%">
        <div slot="body">
            <el-form ref="serverForm" :model="server" label-width="150px" size="medium" :rules="rules">
                <el-row>
                    <el-col :span="22">
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="服务器编号：" prop="serverNo">
                                    <el-input v-model="server.serverNo" clearable placeholder="请填写服务器编号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="服务器名称：" prop="serverName">
                                    <el-input v-model="server.serverName" clearable placeholder="请填写服务器名称"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="内网IP：" prop="serverIP">
                                    <el-input v-model="server.serverIP" clearable placeholder="请填写内网IP地址"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="服务器MAC地址：" prop="serverMAC">
                                    <el-input v-model="server.serverMAC" clearable placeholder="请填写服务器MAC地址"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="内存：" prop="serverMemory">
                                    <el-input v-model="server.serverMemory" clearable placeholder="请填写内存"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="储存：" prop="serverStorage">
                                    <el-input v-model="server.serverStorage" clearable placeholder="请填写存储空间"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="带宽：" prop="bandwidth">
                                    <el-input v-model="server.bandwidth" clearable placeholder="请填写带宽"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="安装时间：" prop="installTime">
                                    <el-date-picker
                                            v-model="server.installTime"
                                            clearable
                                            type="datetime"
                                            style="width:100%"
                                            placeholder="请选择安装时间">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustServer",
        data() {
            return {
                addServerDialog: false,
                server: {},
                isEdit: false,
                areaId: '',
                areaNo: '',
                rules: {
                    serverNo:  {required: true,message: '服务器编号不能为空！',trigger: 'blur'},
                    serverName: {required: true,message: '服务器名称不能为空！',trigger: 'blur'},
                    serverIP: {validator: this.isIP,message: 'IP地址不合法！',trigger: 'blur'},
                    serverMAC: {validator: this.isMAC,message: 'MAC地址不合法！',trigger: 'blur'},
                }
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑本地服务器'
                }
                return '新增本地服务器'
            }
        },
        methods: {
            isIP(rule,value,callback){
                if(value && value !== ''){
                    const ipReg = this.BaseConstants.IS_IP_REG
                    if(!ipReg.test(value)){
                        callback(new Error('IP地址不合法！'))
                        return;
                    }
                }
                callback()
            },
            isMAC(rule,value,callback){
                if(value && value !== ''){
                    const macReg = this.BaseConstants.IS_MAC
                    if(!macReg.test(value)){
                        callback(new Error('MAC地址不合法！'))
                        return;
                    }
                }
                callback()
            },
            initData(areaId,areaNo,rowData) {
                if (rowData) {
                    //编辑
                    this.server = Object.assign({},rowData);
                    this.isEdit = true
                }else{
                    //新增
                    this.isEdit = false
                }
                this.areaId = areaId;
                this.areaNo = areaNo;
                this.$refs.dialogTemplate.show();
            },
            saveServer(){
                //保存服务器
                this.$refs.serverForm.validate((valid) => {
                    if(valid){
                        //校验通过
                        this.$emit('refreshServerTable');
                        this.$message({
                            type: 'success',
                            message: this.isEdit?'本地服务器编辑成功！':'本地服务器新增成功！'
                        });
                        this.server = {};
                        this.$refs.dialogTemplate.close();
                    }
                })
            },
            cancelAdjust(){
                //取消
                this.server = {};
                this.$refs.serverForm.clearValidate();
                this.$refs.dialogTemplate.close();
            },
        }
    }
</script>

<style scoped>

</style>