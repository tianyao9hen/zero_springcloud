<template>
    <div>
        <el-row style="padding-top: 20px">
            <el-col :span="2">
                <el-link class="linkTitle" type="info" :underline="false" @click="jump(1)"><h3 style="color:#595959">
                    本地服务器</h3></el-link>
            </el-col>
            <el-col :span="2">
                <el-link class="linkTitle" type="info" :underline="false" @click="jump(2)"><h3 style="color:#595959">
                    收费电脑</h3></el-link>
            </el-col>
            <el-col :span="2">
                <el-link class="linkTitle" type="info" :underline="false" @click="jump(3)"><h3 style="color:#595959">
                    球机设备</h3></el-link>
            </el-col>
            <el-col :span="2">
                <el-link class="linkTitle" type="info" :underline="false" @click="jump(4)"><h3 style="color:#595959">
                    车位相机</h3></el-link>
            </el-col>
        </el-row>
        <el-row>
            <!--本地服务器-->
            <div style="padding-top: 20px" v-if="jumpNumber===1">
                <LocalServer :area-id="areaId" :area-no="areaNo"></LocalServer>
            </div>
            <!--收费电脑  -->
            <div style="padding-top: 20px" v-if="jumpNumber===2 ">
                <PayComputer :area-id="areaId" :area-no="areaNo"></PayComputer>
            </div>
            <!--球机设备  -->
            <div style="padding-top: 20px" v-if="jumpNumber===3">
                <BallMachineEquipment :area-id="areaId" :area-no="areaNo"></BallMachineEquipment>
            </div>
            <!--车位相机-->
            <div style="padding-top: 20px" v-if="jumpNumber===4">
                <ParkingCamera :area-id="areaId" :area-no="areaNo"></ParkingCamera>
            </div>
        </el-row>
        <div class="footer-button" v-if="isAdd">
            <el-button style="margin-top: 12px;" @click="prevDevice">上一步</el-button>
            <el-button class="el-icon-edit-outline" type="primary" @click="nextDevice">下一步</el-button>
        </div>
    </div>
</template>

<script>
    import LocalServer from "./LocalServer";
    import BallMachineEquipment from "./BallMachineEquipment";
    import PayComputer from "./PayComputer";
    import ParkingCamera from "./ParkingCamera";

    export default {
        name: "deviceManagement",
        components: {
            ParkingCamera,
            PayComputer,
            BallMachineEquipment,
            LocalServer,
        },
        data() {
            return {
                jumpNumber: 1,
            }
        },
        props: {
            areaId: '',
            areaNo: '',
            isAdd:false,
        },
        methods: {
            jump(number) {
                this.jumpNumber = number;
            },
            prevDevice() {
                this.$emit('prev-device', [this.areaId, this.areaNo])
            },
            //点击下一步
            nextDevice() {
                this.$emit('next-device', [this.areaId, this.areaNo])
            },
        },
    }
</script>

<style scoped>
    .linkTitle {
        font-weight: bold;
        font-size: 18px;
        color: #485A6A;
    }

    .footer-button {
        text-align: center;
        position: relative;
    }
</style>