<template>
    <el-card>
        <el-row>
            <el-col :span="10">
                <h4>收费电脑列表数据</h4>
            </el-col>
            <el-col :span="14">
                <el-button class="el-icon-plus" style="float: right" type="primary" @click="addOrEdit()">新增
                </el-button>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <div style="padding-top: 20px;">
                    <el-table :data="tableData.dataList" style="margin-bottom: 20px" border stripe>
                        <el-table-column prop="computerNo" label="电脑编号" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="computerName" label="电脑名称" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="computerIP" label="电脑内网IP" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="computerMemory" label="内存" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="computerStorage" label="存储" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="CPU" label="CPU" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="bandwidth" label="带宽" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column prop="installTime" label="安装时间" align="center"
                                         :show-overflow-tooltip="true"/>
                        <el-table-column label="操作" align="center" width="120px">
                            <template slot-scope="scope">
                                <el-button size="mini" type="text" @click="addOrEdit(scope.row)"><i
                                        class="el-icon-edit">编辑</i>
                                </el-button>
                                <el-button size="mini" type="text" @click="delComputer()"><i
                                        class="el-icon-delete">删除</i>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <pagination :currentPage="tableData?tableData.currentPage:undefined"
                                :pageSize="tableData?tableData.pageSize:undefined"
                                :total="tableData?tableData.total:undefined"
                                @change-page="queryPage"/>
                </div>
            </el-col>
        </el-row>
        <!--        收费电脑-新增/编辑  -->
        <AddOrEditPayComputer ref="addOrEditPayComputer" @refreshComputerTable="queryPage(1,tableData.pageSize)"></AddOrEditPayComputer>
    </el-card>
</template>

<script>
    import AddOrEditPayComputer from "./AdjustPayComputer";
    export default {
        name: "PayComputer",
        components: {AddOrEditPayComputer},
        props: {
            areaId: '',
            areaNo: '',
        },
        data(){
            return{
                //收费电脑表格
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: []
                },
            }
        },
        mounted() {
            if(this.areaId && this.areaNo){
                this.queryPage(1,this.tableData.pageSize);
            }
        },
        methods:{
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                let that = this;
                setTimeout(function () {
                    that.tableData = {
                        total: 200,
                        currentPage: 1,
                        pageSize: 10,
                        dataList:  [
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                            {
                                computerNo: '101',
                                computerName: 'FW101',
                                computerIP: '198.168.1.1',
                                computerMemory: 12,
                                computerStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00',
                                doorwayId: 1,
                                doorwayNo: 'doorway001',
                                doorwayName: '入口01',
                                serverId: 1,
                                serverNo: 'server001',
                                serverName: '服务器1',
                            },
                        ]
                    }
                },1000)
            },
            addOrEdit(rowData) {
                this.$refs.addOrEditPayComputer.initData(this.areaId,this.areaNo,rowData);
            },
            delComputer() {
                this.$confirm('是否删除该收费电脑?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                })
            },
        }
    }
</script>

<style scoped>

</style>