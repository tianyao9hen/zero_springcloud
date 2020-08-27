<template>
    <div class="add-area">
        <web-template web-name="新增区域" simple no-table>
            <template slot="body">
                <el-row>
                    <el-steps :active="active" finish-status="success">
                        <el-step title="基本信息"></el-step>
                        <el-step title="进出口设置"></el-step>
                        <el-step title="设备管理"></el-step>
                        <el-step title="计费绑定"></el-step>
                    </el-steps>
                </el-row>
                <div v-if="this.active==0" style="padding-top: 20px;">
                    <!-- 基本信息                 -->
                    <basic-info v-if="isReady" :isAdd=isAdd
                                @saveAndNext-basic="saveBasicData"></basic-info>
                </div>
                <div v-if="this.active==1" style="padding-top: 20px">
                    <!-- 进出口管理                  -->
                    <doorway-management v-if="isReady" :isAdd=isAdd :area-id="this.basicData.areaId"
                                        :area-no="this.basicData.areaNo" @prev-doorway="queryBasicData"
                                        @next-doorway="saveDoorway"></doorway-management>
                </div>

                <div v-if="this.active==2" style="padding-top: 20px">
                    <!-- 设备管理                  -->
                    <device-management :area-id="this.basicData.areaId" :area-no="this.basicData.areaNo"
                                       @prev-device="queryDoorway"
                                       @next-device="saveDevice" :isAdd=isAdd ></device-management>
                </div>
                <div v-if="this.active==3">
                    <!-- 计费绑定                 -->
                    <billing-binding :area-id="this.basicData.areaId" :area-no="this.basicData.areaNo"
                                     @prev-billing="queryDevice" @submit-billing="submitBilling"
                                     :isAdd=isAdd ></billing-binding>
                </div>

            </template>
        </web-template>
    </div>
</template>

<script>
    import bus from "components/common/bus";
    import DeviceManagement from "./addArea/DeviceManagement";
    import BillingBinding from "./addArea/BillingBinding";
    import BasicInfo from "./editArea/BasicInfo";
    import DoorwayManagement from "./editArea/doorwayManagement";

    export default {
        name: "AddArea",
        components: {DoorwayManagement, BasicInfo, BillingBinding, DeviceManagement,},
        data() {
            return {
                active: 0,
                isAdd: true,
                isReady: true,
                basicData: {}
            }
        },
        methods: {
            cancelArea() {
                //取消新增
                bus.$emit('close_current_tag_to_url', '/parkManagement/AreaManagement');
            },
            saveArea() {
                //保存新增
                bus.$emit('refresh_area');
                bus.$emit('close_current_tag_to_url', '/parkManagement/AreaManagement');
            },
            //基本信息得保存并下一步
            saveBasicData(data) {
                console.log(data);
                this.basicData = data;
                this.active++;
            },

            //在进出口设置点击上一步返回基本信息 与保存并下一步
            queryBasicData(areaIdAndareaNo) {
                //点击上一步时 传来的no 和name
                console.log(areaIdAndareaNo);
                this.isAdd = true;
                this.active--;
            },
            saveDoorway(areaIdAndareaNo) {
                console.log(areaIdAndareaNo);
                this.active++;
            },

            //设备管理点击上一步返回进出口设置 与保存并下一步
            queryDoorway(areaIdAndareaNo) {
                console.log(areaIdAndareaNo);
                this.active--;
            },
            saveDevice(areaIdAndareaNo) {
                console.log(areaIdAndareaNo);
                this.active++;
            },

            //收费绑定点击上一步返回设备管理 与提交
            queryDevice(areaIdAndareaNo) {
                console.log(areaIdAndareaNo);
                this.active--;
            },
            submitBilling(areaIdAndareaNo) {
                console.log(areaIdAndareaNo);
                this.saveArea();
            },


        }
    }
</script>

<style scoped>
    .footer-button {
        text-align: center;
        position: relative;
    }


</style>