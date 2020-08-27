<template>
    <div class="add-area">
        <web-template web-name="编辑区域" simple no-table>
            <template slot="headerButton">
                <el-button @click="returnPage" class="headerButton">返 回</el-button>
            </template>
            <template slot="body">
                <el-tabs v-model="activeName" @tab-click="clickTab()">
                    <el-tab-pane label="基本信息" name="basicInfo">
                        <basic-info v-if="isReady" :area-id="areaData.basicData.areaId" :area-no="areaData.basicData.areaNo" :isAdd=false></basic-info>
                    </el-tab-pane>
                    <el-tab-pane label="进出口管理" name="doorwayManagement">
                        <doorway-management v-if="isReady" :area-id="areaData.basicData.areaId" :area-no="areaData.basicData.areaNo" :isAdd=false ></doorway-management>
                    </el-tab-pane>
                    <el-tab-pane label="设备管理" name="equipmentManagement">
                        <device-management v-if="isReady" :area-id="areaData.basicData.areaId" :area-no="areaData.basicData.areaNo"></device-management>
                    </el-tab-pane>
                    <el-tab-pane label="计费标准" name="chargingStandard">
                        <billing-binding v-if="isReady" :area-id="areaData.basicData.areaId" :area-no="areaData.basicData.areaNo"></billing-binding>
                    </el-tab-pane>
                </el-tabs>
            </template>
        </web-template>
    </div>
</template>

<script>
    import bus from "components/common/bus";
    import BasicInfo from "./editArea/BasicInfo";
    import DoorwayManagement from "./editArea/doorwayManagement";
    import DeviceManagement from "./addArea/DeviceManagement";
    import BillingBinding from "./addArea/BillingBinding";
    export default {
        name: "EditArea",
        components: {BillingBinding, DeviceManagement, DoorwayManagement, BasicInfo},
        data(){
            return {
                activeName: 'basicInfo',
                areaData: {
                    basicData: {
                        areaId: 'LS001',
                        areaNo: '',
                        areaName: '测试名称1',
                        parkingSpotCount: '50',
                        areaType: '0',
                        areaStatus: '1',
                        areaAstrict: '',
                        parentArea: 'gd002',
                        isPermitBlackList: '1',
                        isPermitMoveCar: '1',
                        remarks: '备注内容测试',
                    },
                    doorway: [],
                },
                isReady: false,
            }
        },
        mounted() {
            let areaId = this.$route.params.id;
            let areaNo = this.$route.params.areaNo;
            if(areaId && areaNo){
                this.areaData.basicData.areaNo = areaNo;
                this.areaData.basicData.areaId = areaId;
                this.isReady = true;
            }else{
                bus.$emit('close_current_tag_to_url','/parkManagement/AreaManagement');
            }
        },
        methods: {
            returnPage(){
                //返回区域管理页面
                bus.$emit('close_current_tag_to_url','/parkManagement/AreaManagement');
            },
            clickTab(){
                //点击tab
            },
            cancelArea(){
                //取消编辑
                bus.$emit('close_current_tag_to_url','/parkManagement/AreaManagement');
            },
            saveArea(){
                //保存编辑
                bus.$emit('refresh_area');
                bus.$emit('close_current_tag_to_url','/parkManagement/AreaManagement');
            },
            doorwayQueryPage(currentPage = 1,pageSize = this.doorwayTable.pageSize){
                //进出口查询
                console.log(currentPage +":"+pageSize);
            }
        }
    }
</script>

<style scoped>
    .headerButton {
        margin-top: 5px;
    }
</style>