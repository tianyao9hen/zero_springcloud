<template>
    <div>
        <dialog-template ref="dictItemDialog" :dialog-name="dialogName"
                         @primaryClick="saveDictItem" @closeClick="closeDialog"
                         :dialog-loading="dialogLoading"
                         primary-button-title="保 存" width="50%">
            <template slot="body">
                <el-form :model="dictItemInfo" ref="dictItemForm" label-width="100px" size="medium" :rules="rules">
                    <el-row>
                        <el-col>
                            <template v-if="!isEdit">
                                <el-tabs v-model="activeName" @tab-click="handleClick">
                                    <el-tab-pane label="新增字典组" name="addGroup">
                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="字典组编号" prop="groupCode">
                                                    <el-input v-model="dictItemInfo.groupCode" placeholder="请填写字典组编号"></el-input>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :span="12">
                                                <el-form-item label="字典组名称" prop="groupName" :required="activeName ==='addGroup'">
                                                    <el-input v-model="dictItemInfo.groupName" placeholder="请填写字典组名称"></el-input>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                    </el-tab-pane>
                                    <el-tab-pane label="新增字典项" name="addItem">
                                        <el-row>
                                            <el-col>
                                                <el-form-item label="选择字典组" prop="groupCode">
                                                    <el-select v-model="dictItemInfo.groupCode" filterable clearable
                                                               placeholder="请选择字典字典组" style="width: 100%;"
                                                               @change="changeGroup">
                                                        <el-option v-for="item in groupList" :key="item.groupCode"
                                                                   :label="item.groupName"
                                                                   :value="item.groupCode">
                                                        </el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                    </el-tab-pane>
                                </el-tabs>
                            </template>
                            <template v-else>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="字典组编号">
                                            <el-input v-model="dictItemInfo.groupCode" readonly placeholder="请填写字典组编号"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="字典组名称">
                                            <el-input v-model="dictItemInfo.groupName" readonly placeholder="请填写字典组名称"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="字典项编号" prop="itemCode">
                                <el-input v-model="dictItemInfo.itemCode" placeholder="请填写字典项编号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="字典项名称" prop="itemName">
                                <el-input v-model="dictItemInfo.itemName" placeholder="请填写字典项名称"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="状态" prop="status">
                                <el-select v-model="dictItemInfo.status" filterable clearable
                                           placeholder="请选择字典项状态" style="width: 100%;">
                                    <el-option v-for="item in statusList" :key="item.value"
                                               :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item label="备注">
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="dictItemInfo.remarks" placeholder="请填写备注"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>

        </dialog-template>
    </div>
</template>

<script>
    import {insertDict,selectDictGroup,updateDict} from 'network/sysManager';
    import {getOptions} from 'network/commons';
    export default {
        name: "DictItemDialog",
        data(){
            return {
                //是否编辑
                isEdit: false,
                //当前是添加字典组还是仅为字典组添加字典项
                activeName: "addGroup",
                //字典项信息
                dictItemInfo: {
                    groupCode: "",
                    groupName: "",
                    itemCode: "",
                    itemName: "",
                    status: "",
                    remarks: "",
                },
                //字典项状态列表
                statusList: [],
                //字典组列表
                groupList: [],
                //form表单规则
                rules: {
                    groupCode: {required: true,message: '字典组编号不能为空',trigger: ['blur','change']},
                    groupName: {validator: this.groupNameRule,message: '字典组名称不能为空',trigger: ['blur','change']},
                    itemCode: {required: true,message: '字典项编号不能为空',trigger: 'blur'},
                    itemName: {required: true,message: '字典项名称不能为空',trigger: 'blur'},
                    status: {required: true,message: '字典项状态不能为空',trigger: ['blur','change']},
                },
                //弹窗是否加载中
                dialogLoading: false,
            }
        },
        mounted() {
            //页面完成后加载
            this.selectDictGroup();
            //查询字典项状态字典
            getOptions({
                groupCode: 'dict_status'
            }).then(res => {
                if(res.success){
                    this.statusList = Object.assign([],res.result);
                }
            })
        },
        computed: {
            //判断弹窗名称
            dialogName(){
                if(this.isEdit){
                    return "编辑字典项"
                }
                return "新增字典项"
            }
        },
        methods: {
            //查询所有的字典组
            selectDictGroup(){
                selectDictGroup().then(res => {
                    if(res.success){
                        this.groupList = res.result;
                    }
                })
            },
            //初始化数据，判断是编辑还是新增
            initData(rowData){
                if(rowData){
                    //修改
                    this.$nextTick(() => {
                        this.dictItemInfo = Object.assign({},rowData);
                        this.isEdit = true;
                    })
                }else{
                    this.isEdit = false;
                }
                this.$refs['dictItemDialog'].show();
            },
            //保存字典项
            saveDictItem(){
                this.$refs['dictItemForm'].validate(valid => {
                    if(valid){
                        if(this.isEdit){
                            this.dialogLoading = true;
                            updateDict(this.dictItemInfo).then(res => {
                                if(res.success){
                                    this.closeDialog();
                                    this.$emit('refreshQueryPage');
                                    this.$message({
                                        message: '修改成功！',
                                        type: 'success'
                                    })
                                }
                                this.dialogLoading = false;
                            }).catch(err => {
                                this.$message.error(err.error);
                                this.dialogLoading = false;
                            })
                        }else{
                            this.dialogLoading = true;
                            insertDict(this.dictItemInfo).then(res => {
                                if(res.success){
                                    this.closeDialog();
                                    this.$emit('refreshQueryPage');
                                    this.$message({
                                        message: '新增成功！',
                                        type: 'success'
                                    })
                                }else{
                                    this.$message.error(res.error);
                                }
                                this.dialogLoading = false;
                            }).catch(err => {
                                this.$message.error(err.error);
                                this.dialogLoading = false;
                            })
                        }
                    }
                })
            },
            //关闭弹窗
            closeDialog(){
                this.$refs['dictItemForm'].clearValidate()
                this.dictItemInfo = {}
                this.$refs['dictItemDialog'].close()
            },
            //tab切换事件
            handleClick(tab, event) {
                this.dictItemInfo.groupCode = "";
                this.dictItemInfo.groupName = "";
            },
            //改变选择的字典组事件
            changeGroup(groupCode){
                console.log(this.groupList);
                let newGroupName = "";
                for(let group of this.groupList){
                    if(group.groupCode === groupCode){
                        newGroupName = group.groupName;
                    }
                }
                //let newGroupName = Array.from(this.groupList).filter(group => group.groupCode === groupCode)[0].groupName;
                this.dictItemInfo.groupName = newGroupName;
            },
            //字典名称填写规则
            groupNameRule(rule,value,callback){
                if(this.activeName === 'addGroup'){
                    if(!value || value === ''){
                        callback(new Error('字典组名称不能为空！'))
                        return
                    }
                }
                callback();
            }
        }
    }
</script>

<style scoped>

</style>