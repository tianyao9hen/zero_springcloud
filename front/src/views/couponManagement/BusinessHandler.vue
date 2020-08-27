<template>
    <div>
        <web-template web-name="业务办理" :table-data="tableData" @changePage="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData" label-width="80px">
                    <el-row>
                        <el-col :span="7">
                            <el-form-item label="业务单号" prop="userName">
                                <el-input placeholder="请输入单号" clearable v-model="formData.businessOrderNo"  style="width: 100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="商家名称" prop="businessName">
                                <el-input placeholder="请输入商家名称" clearable v-model="formData.businessName"  style="width: 100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="办理时间" prop="telephone">
                                <el-date-picker
                                        v-model="formData.processingTime"
                                        type="daterange"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期" style="width: 100%">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="7">
                            <el-form-item label="方案" prop="telephone">
                                <el-input placeholder="请输入手机号" clearable v-model="formData.programme"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" :offset="11" style="text-align: right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage(1,tableData.pageSize)">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="addMerchant">业务办理</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                    <el-table-column prop="businessOrderNo" label="业务单号" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="businessName" label="授权商家"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="programme" label="方案" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="rechargeAmount" label="充值金额" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="processingTime" label="办理时间" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="discount" label="优惠(张)" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="surplus" label="剩余(张)" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="area" label="区域" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="200px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="editInfo(scope.row)">
                                <i class="el-icon-edit">续费</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delInfo(scope.row)">
                                <i class="el-icon-close">删除</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>
        <business-management-dialog ref="businessDialog" />
    </div>
</template>

<script>
    import BusinessManagementDialog from "./BusinessManagement/BusinessManagementDialog";

    export default {
        name: "BusinessHandler",
        components: {BusinessManagementDialog,},
        data(){
            return {
                formData: {
                    businessOrderNo:'',
                    businessName: '',
                    processingTime:'',
                    programme: '',
                },
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            id: "1",
                            businessOrderNo: 'ESDXZSJR15423',
                            businessName: '万达广场',
                            programme: 1,
                            rechargeAmount:1000,
                            processingTime: '2020-08-17',
                            discount:10,
                            surplus:100,
                            area:'B区'
                        }, {
                            id: "1",
                            businessOrderNo: 'ESDXZSJR15423',
                            businessName: '柏蔓酒店',
                            programme: 1,
                            rechargeAmount:1000,
                            processingTime: '2020-08-17',
                            discount:10,
                            surplus:100,
                            area:'B区'
                        },{
                            id: "1",
                            businessOrderNo: 'ESDXZSJR154sfe3',
                            businessName: '隐家本格日式店',
                            programme: 1,
                            rechargeAmount:1000,
                            processingTime: '2020-08-17',
                            discount:10,
                            surplus:100,
                            area:'B区'
                        },
                    ]
                }
            }
        },
        methods: {
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
            addMerchant(){
                this.$refs.businessDialog.initData();
            },
            editInfo(row){
                this.$refs.businessDialog.initData(row);
            },
            resetPassword(row){
                this.$confirm('是否重置密码?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '重置成功!'
                    });
                })
            },
            delInfo(row){
                this.$confirm('是否删除账号?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                })
            },
            rest(){
                this.$refs.queryForm.resetField();
            },
        }
    }
</script>

<style scoped>

</style>