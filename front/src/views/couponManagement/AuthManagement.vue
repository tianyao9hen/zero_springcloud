<template>
    <div>
        <web-template web-name="授权管理" :table-data="tableData" @changePage="queryPage">
            <div slot="searchForm">
                <el-form layout="inline" ref="queryForm" :model="formData" label-width="120px">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="商家名称" prop="businessName">
                                <el-input placeholder="请输入商家名称" clearable v-model="formData.businessName" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="用户名" prop="userName">
                                <el-input placeholder="请输入用户名" clearable v-model="formData.userName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="手机号" prop="telephone">
                                <el-input placeholder="请输入手机号" clearable v-model="formData.telephone"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align: right">
                            <el-button style="margin-right:2px;" type="primary" @click="queryPage(1,tableData.pageSize)">查询</el-button>
                            <el-button @click="rest">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="addMerchant">新增</el-button>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border>
                    <el-table-column prop="businessName" label="商家名称" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="userName" label="用户名"  align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="linkName" label="联系人" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="telephone" label="手机号" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="address" label="地址" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="操作" align="center" width="200px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="editInfo(scope.row)">
                                <i class="el-icon-edit">编辑</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="resetPassword(scope.row)">
                                <i class="el-icon-s-check">重置密码</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="delInfo(scope.row)">
                                <i class="el-icon-close">删除</i>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>
        <business-auth-dialog ref="authDialog" />
    </div>
</template>

<script>
    import BusinessAuthDialog from "./authManagement/BusinessAuthDialog";
    export default {
        name: "AuthManagement",
        components: {BusinessAuthDialog},
        data(){
            return {
                formData: {
                    businessName: '',
                    userName: '',
                    telephone: '',
                },
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            id: "1",
                            businessName: '万达广场',
                            userName: 'wandaguangchang',
                            linkName: '张三',
                            telephone: '15724578425',
                            address: '济南历下区工业南路73号',
                        }, {
                            id: "1",
                            businessName: '柏蔓艺术花园酒店',
                            userName: 'baiMan',
                            linkName: '柏蔓',
                            telephone: '13548751504',
                            address: '济南历下区舜华路',
                        },  {
                            id: "1",
                            businessName: '隐家本格日式店',
                            userName: 'benGe',
                            linkName: '隐家本格',
                            telephone: '13756845578',
                            address: '济南历下区会展中心',
                        }
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
                this.$refs.authDialog.initData();
            },
            editInfo(row){
                this.$refs.authDialog.initData(row);
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