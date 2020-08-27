<template>
    <div>
        <el-table :data="tableData.dataList" style="margin-bottom: 20px;" border stripe>
            <el-table-column prop="date" label="日期" align="center"
                             :show-overflow-tooltip="true"></el-table-column>
            <el-table-column prop="total" label="合计（进/出）" align="center"
                             :show-overflow-tooltip="true"></el-table-column>
            <el-table-column prop="merchantDiscountCar" label="商户优惠车（进/出）" align="center"
                             :show-overflow-tooltip="true"></el-table-column>
            <el-table-column prop="authorizedVehicle" label="授权车（进/出）" align="center"
                             :show-overflow-tooltip="true">
                <el-table-column prop="totalAuthorization" label="合计" align="center"
                                 :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="monthlyCar" label="包月车" align="center"
                                 :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="freeCar" label="免费车" align="center"
                                 :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="visitingBus" label="访客车" align="center"
                                 :show-overflow-tooltip="true"></el-table-column>
            </el-table-column>

        </el-table>
        <Pagination></Pagination>
    </div>
</template>

<script>
    import Pagination from "../../../components/pagination/Pagination";
    export default {
        name: "InflowAndOutflowData",
        components:{Pagination},
        data() {
            return {
                tableData: {
                    total: 200,
                    currentPage: 1,
                    pageSize: 10,
                    dataList: [
                        {
                            date: "2020-07-06",
                            total: '276/273',
                            merchantDiscountCar: '259/252',
                            totalAuthorization: '17/21',
                            monthlyCar: '7/9',
                            freeCar: '2/2',
                            visitingBus: '2/1',
                        }, {
                            date: "2020-07-07",
                            total: '276/273',
                            merchantDiscountCar: '259/252',
                            totalAuthorization: '19/25',
                            monthlyCar: '7/9',
                            freeCar: '2/2',
                            visitingBus: '3/3',
                        }, {
                            date: "2020-07-08",
                            total: '276/273',
                            merchantDiscountCar: '259/252',
                            totalAuthorization: '18/37',
                            monthlyCar: '7/9',
                            freeCar: '2/2',
                            visitingBus: '4/5',
                        }, {
                            date: "2020-07-09",
                            total: '276/273',
                            merchantDiscountCar: '259/252',
                            totalAuthorization: '20/17',
                            monthlyCar: '7/9',
                            freeCar: '2/2',
                            visitingBus: '3/1',
                        }, {
                            date: "2020-07-10",
                            total: '276/273',
                            merchantDiscountCar: '259/252',
                            totalAuthorization: '21/17',
                            monthlyCar: '7/9',
                            freeCar: '2/2',
                            visitingBus: '2/5',
                        },
                    ]
                }
            }
        },

    }
</script>

<style scoped>

</style>