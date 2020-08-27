<template>
    <el-card>
        <el-row>
            <el-col :span="10">
                <h4>车位相机列表数据</h4>
            </el-col>
            <el-col :span="14">
                <div style="float: right">
                    <el-input placeholder="按车位号搜索" style="width: 200px;margin-right: 8px" v-model="parkingLotNumberSearch">
                        <el-button slot="append" @click="queryPage" style="width: 20px;min-width: 20px" icon="el-icon-search"></el-button>
                    </el-input>
                    <el-button class="el-icon-plus" type="primary" @click="addOrEdit">新增</el-button>
                </div>

            </el-col>
            <el-col :span="4">

            </el-col>
        </el-row>
        <el-row>
            <div style="padding-top: 20px;">
                <el-table :data="tableData.dataList" style="margin-bottom: 20px" border stripe>
                    <el-table-column prop="cameraNo" label="相机编号" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="cameraName" label="相机名称" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="parkingLotNumber" label="车位号" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="videoIP" label="视频IP" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="videoPort" label="视频端口" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="passageway" label="通道" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="bitstream" label="码流" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="manufacturer" label="生产厂家" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="productNo" label="产品编号" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column prop="installTime" label="安装时间" align="center"
                                     :show-overflow-tooltip="true"/>
                    <el-table-column label="操作" align="center" width="120px">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text"
                                       @click="addOrEdit(scope.row)"><i
                                    class="el-icon-edit">编辑</i>
                            </el-button>
                            <el-button size="mini" type="text" @click="del()"><i
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
        </el-row>
        <!--        车位相机-新增/编辑-->
        <adjust-parking-camera ref="parkingCamera" @refreshParkingCameraTable="queryPage(1,tableData.pageSize)"></adjust-parking-camera>
    </el-card>
</template>

<script>
    import AdjustParkingCamera from "./AdjustParkingCamera";
    export default {
        name: "ParkingCamera",
        components: {AdjustParkingCamera},
        props: {
            areaId:'',
            areaNo:'',
        },
        data() {
            return {
                //按车位号搜索
                parkingLotNumberSearch: '',
                //车位相机
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: []
                },
            }
        },
        mounted(){
            if(this.areaId && this.areaNo){
                this.queryPage(1,this.tableData.pageSize);
            }
        },
        methods: {
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                console.log('queryPage');
                let that = this;
                setTimeout(function () {
                    that.tableData = {
                        total: 200,
                        currentPage: 1,
                        pageSize: 10,
                        dataList:  [
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                            {
                                cameraNo: '101',
                                cameraName: 'QJ101',
                                parkingLotNumber: '701',
                                videoIP: '198.168.1.1',
                                videoPort: 5000,
                                passageway: 12,
                                bitstream: 10,
                                manufacturer: '测试',
                                productNo: 200,
                                installTime: '2020-07-13 9:59:00'
                            },
                        ]
                    }
                },1000)
            },
            addOrEdit(rowData) {
                this.$refs.parkingCamera.initData(this.areaId,this.areaNo,rowData);
            },
            del() {
                this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
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