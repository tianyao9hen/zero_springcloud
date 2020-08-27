<template>
    <div class="adjust-server">
        <el-card>
            <el-row>
                <el-col :span="10">
                    <h4>本地服务器列表数据</h4>
                </el-col>
                <el-col :span="14">
                    <el-button class="el-icon-plus" style="float: right" type="primary" @click="addOrEdit()">新增
                    </el-button>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <div style="padding-top: 20px;">
                        <el-table :data="tableData.dataList" style="margin-bottom: 20px" stripe border>
                            <el-table-column prop="serverNo" label="服务器编号" align="center"
                                             :show-overflow-tooltip="true"/>
                            <el-table-column prop="serverName" label="服务器名称" align="center"
                                             :show-overflow-tooltip="true"/>
                            <el-table-column prop="serverIP" label="内网IP" align="center"
                                             :show-overflow-tooltip="true"/>
                            <el-table-column prop="serverMAC" label="服务器MAC地址" align="center"
                                             :show-overflow-tooltip="true"/>
                            <el-table-column prop="serverMemory" label="内存" align="center"
                                             :show-overflow-tooltip="true"/>
                            <el-table-column prop="serverStorage" label="存储" align="center"
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
                                    <el-button size="mini" type="text" @click="delServer()"><i
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
        </el-card>

        <!--        本地服务器-新增/编辑  -->
        <adjust-server ref="adjustServer" @refreshServerTable="queryPage(1,tableData.pageSize)"/>
    </div>
</template>

<script>
    import AdjustServer from "./AdjustServer";

    export default {
        name: "LocalServer",
        components: {AdjustServer},
        props: {
            areaId: '',
            areaNo: '',
        },
        data() {
            return {
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
        methods: {
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                let that = this;
                setTimeout(function () {
                    that.tableData = {
                        total: 200,
                        currentPage: 1,
                        pageSize: 10,
                        dataList: [
                            {
                                serverNo: '101',
                                serverName: 'FW101',
                                serverIP: '198.168.1.1',
                                serverMAC: '125.125.125.125',
                                serverMemory: 12,
                                serverStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                serverNo: '101',
                                serverName: 'FW101',
                                serverIP: '198.168.1.1',
                                serverMAC: '125.125.125.125',
                                serverMemory: 12,
                                serverStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                serverNo: '101',
                                serverName: 'FW101',
                                serverIP: '198.168.1.1',
                                serverMAC: '125.125.125.125',
                                serverMemory: 12,
                                serverStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                serverNo: '101',
                                serverName: 'FW101',
                                serverIP: '198.168.1.1',
                                serverMAC: '125.125.125.125',
                                serverMemory: 12,
                                serverStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                serverNo: '101',
                                serverName: 'FW101',
                                serverIP: '198.168.1.1',
                                serverMAC: '125.125.125.125',
                                serverMemory: 12,
                                serverStorage: 10,
                                CPU: '测试',
                                bandwidth: 200,
                                installTime: '2020-07-13 9:59:00'
                            }
                        ]
                    }
                },1000)
            },
            addOrEdit(rowData) {
                this.$refs.adjustServer.initData(this.areaId,this.areaNo,rowData);
            },
            delServer() {
                this.$confirm('是否删除该本地服务器?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {

                });
            },
        }
    }
</script>

<style scoped>

</style>