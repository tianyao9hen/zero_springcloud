<template>
    <div class="access-record">
        <web-template :webName="webName" simple :tableData="tableData" @change-page="queryPage">
            <div slot="headerButton">
                <el-button class="el-icon-plus" type="primary" @click="addOreEdit()"
                           style="margin-right:8px">新增
                </el-button>
                <el-input placeholder="按车牌号搜索" suffix-icon="el-icon-search"
                          style="width:190px">
                </el-input>
            </div>
            <div slot="webTable">
                <el-table :data="tableData.dataList"
                          style="margin-bottom: 20px;"
                          border>
                    <el-table-column prop="licensePlateNumber" label="车牌号" align="center"/>
                    <el-table-column prop="parkingSpaceType" label="车辆类型" align="center"/>
                    <el-table-column prop="abnormalRemarks" label="异常备注" align="center"/>
                    <el-table-column label="操作" align="center">
                        <template slot-scope="scope">
                            <el-tooltip content="编辑" placement="top">
                                <el-button size="mini" type="text" @click="addOreEdit(scope.row)"><i
                                        class="el-icon-edit"></i></el-button>
                            </el-tooltip>
                            <el-tooltip content="删除" placement="top">
                                <el-button size="mini" type="text" @click="del"><i class="el-icon-delete"></i>
                                </el-button>
                            </el-tooltip>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </web-template>
        <AbnormalCar ref="AbnormalCar"></AbnormalCar>
    </div>

</template>

<script>
    import AbnormalCar from './component/AbnormalCar'

    export default {
        name: "abnormalVehicle",
        components: {AbnormalCar,},
        data() {
            return {
                webName: "异常车辆",
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            licensePlateNumber: "鲁AEKL25",
                            parkingSpaceType: "小型车",
                            abnormalRemarks: "由于XX原因，此车禁止入内",
                        }, {
                            licensePlateNumber: "鲁CO15LQ",
                            parkingSpaceType: "小型车",
                            abnormalRemarks: "由于XX原因，此车禁止入内",
                        },
                    ],
                },
            }
        },
        methods: {
            //新增或编辑
            addOreEdit(rowData) {
                this.$refs.AbnormalCar.initData(rowData);
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
            queryPage(currentPage = 1,pageSize = this.tableData.pageSize){
                //查询
                console.log(currentPage +":"+pageSize);
            },
        },
    }
</script>

<style scoped>

</style>