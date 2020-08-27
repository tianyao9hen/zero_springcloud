<template>
    <dialog-template ref="dialogTemplate" :dialog-name="dialogName"
                     primary-button-title="保 存"
                     @primaryClick="saveComputer"
                     @closeClick="cancelAdjust" width="60%">
        <div slot="body">
            <el-form :model="computer" ref="computerForm" label-width="90px" size="medium" :rules="rules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电脑编号" prop="computerNo">
                            <el-input v-model="computer.computerNo" clearable placeholder="请填写电脑编号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="电脑名称" prop="computerName">
                            <el-input v-model="computer.computerName" clearable placeholder="请填写电脑名称"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="本地服务器" prop="serverId">
                            <el-select v-model="computer.serverId" filterable clearable placeholder="请选择服务器"
                                       style="width: 100%">
                                <el-option v-for="item in serverList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绑定进出口" prop="doorwayId">
                            <el-select v-model="computer.doorwayId" filterable clearable placeholder="请选择进出口"
                                       style="width: 100%">
                                <el-option v-for="item in doorwayList" :key="item.value" :label="item.label"
                                           :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电脑内网IP" prop="computerIP">
                            <el-input v-model="computer.computerIP" clearable placeholder="请填写内网IP"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="CPU" prop="CPU">
                            <el-input v-model="computer.CPU" clearable placeholder="请填写CPU"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="内存" prop="computerMemory">
                            <el-input v-model="computer.computerMemory" clearable placeholder="请填写内存"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="储存" prop="computerStorage">
                            <el-input v-model="computer.computerStorage" clearable placeholder="请填写储存量"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="带宽" prop="bandwidth">
                            <el-input v-model="computer.bandwidth" clearable placeholder="请填写带宽"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="安装时间" prop="installTime">
                            <el-date-picker
                                    v-model="computer.installTime"
                                    clearable
                                    type="datetime"
                                    style="width:100%"
                                    placeholder="请选择安装时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </dialog-template>
</template>

<script>
    export default {
        name: "AdjustPayComputer",
        data() {
            return {
                computer: {},
                serverList: [
                    {
                        value: 1,
                        label: '服务器1',
                    }, {
                        value: 0,
                        label: '服务器2',
                    },
                ],
                doorwayList: [
                    {
                        value: 1,
                        label: '入口1',
                    }, {
                        value: 0,
                        label: '出口1',
                    }
                ],
                areaId: '',
                areaNo: '',
                rules: {
                    computerNo: {required: true,message: '电脑编号不能为空！',trigger: 'blur'},
                    computerName: {required: true,message: '电脑名称不能为空！',trigger: 'blur'},
                    computerIP: {validator: this.isIP,message: 'IP地址不合法！',trigger: 'blur'},
                },
                isEdit: false,
            }
        },
        computed: {
            dialogName(){
                if(this.isEdit){
                    return '编辑收费电脑'
                }
                return '新增收费电脑'
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
            initData(areaId,areaNo,rowData) {
                if (rowData) {
                    //编辑
                    this.computer = Object.assign({},rowData);
                    this.isEdit = true;
                } else {
                    //新增
                    this.isEdit = false;
                }
                this.areaId = areaId;
                this.areaNo = areaNo;
                this.$refs.dialogTemplate.show();
            },
            saveComputer(){
                this.$refs.computerForm.validate(valid => {
                    if(valid){
                        this.$emit('refreshComputerTable');
                        this.$message({
                            type: 'success',
                            message: this.isEdit? '收费电脑编辑成功！': '收费电脑新增成功！'
                        })
                        this.computer = {};
                        this.$refs.dialogTemplate.close();
                    }
                })
            },
            cancelAdjust(){
                //取消
                this.computer = {};
                this.$refs.computerForm.clearValidate();
                this.$refs.dialogTemplate.close();
            },
        }
    }
</script>

<style scoped>

</style>