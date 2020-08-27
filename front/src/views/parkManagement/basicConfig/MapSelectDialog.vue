<template>
    <dialog-template v-if="showMap" ref="mapSelectDialog"
                     :is-show-title="false"
                     :is-show-bottom-empty="false"
                     :is-show-close="false"
                     :is-show-footer="false"
                     :is-show-body-padding="false"
                     top="3vh"
                     width="1000px">
        <template slot="body">
            <div class="map-body">
                <baidu-map class="bm-view" :center="getCenter"
                           :zoom="zoom" :scroll-wheel-zoom="true"
                           @ready="mapHandler"
                           @click="getClickInfo">
                    <bm-marker v-if="hasCoordinate" :position="{lng,lat}" :dragging="false"></bm-marker>
                    <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :offset="{width: 20, height: 60}" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
                    <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>
                    <bm-local-search :keyword="keyword" :location="location"
                                     :auto-viewport="true" :forceLocal="true"
                                     :pageCapacity="5"
                                     class="search-frame"
                                     ref="localSearch"
                                     @infohtmlset="selectSearchInfo"></bm-local-search>
                    <bm-control anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :offset="{width: 20, height: 20}">
                        <span class="map-label">当前地址：</span><el-input v-model="address" readonly style="width: 350px;margin-right: 10px"></el-input>
                        <span class="map-label">经度：</span><el-input v-model="lng" @focus="changeMoveCenter()" style="width: 100px;margin-right: 10px"></el-input>
                        <span class="map-label">纬度：</span><el-input v-model="lat" @focus="changeMoveCenter()" style="width: 100px;margin-right: 10px"></el-input>
                        <el-button type="primary" class="map-button" @click="confirmCoordinate">确 定</el-button>
                    </bm-control>
                    <bm-control anchor="BMAP_ANCHOR_TOP_LEFT" :offset="{width: 20, height: 20}">
                        <el-input v-model="keyword" placeholder="请输入搜索关键词" clearable style="width: 300px;margin-right: 10px"></el-input>
                    </bm-control>
                    <bm-control anchor="BMAP_ANCHOR_TOP_RIGHT" :offset="{width:20,height:20}">
                        <el-button icon="el-icon-close" style="color:#FF5464;width: 32px;min-width: 32px;height: 32px;min-height: 32px" @click="closeDialog" circle></el-button>
                    </bm-control>
                </baidu-map>
            </div>
        </template>
    </dialog-template>
</template>

<script>
    export default {
        name: "MapSelectDialog",
        data(){
            return {
                zoom: 15,
                showMap: false,
                keyword: '',
                lng: "",
                lat: "",
                oldCoordinate: ["",""],
                location: '',
                address: '',
                hasCoordinate: true,
                bMap: null,
                map: null,
                moveCenter: true,
            }
        },
        computed: {
            getCenter(){
                //这里应该要两套逻辑，点击时不会反馈新的Center,输入时才会反馈新的Center
                if(this.moveCenter){
                    return {
                        lng:this.lng,
                        lat:this.lat
                    }
                }else{
                    return {
                        lng:this.oldCoordinate[0],
                        lat:this.oldCoordinate[1],
                    }
                }
            },
        },
        watch: {
            lng(val){
                this.checkCoordinate()
            },
            lat(val){
                this.checkCoordinate()
            }
        },
        methods: {
            closeDialog(){
                this.lng = "";
                this.lat = "";
                this.oldCoordinate = ["",""];
                this.location = "";
                this.address = "";
                this.keyword = "";
                this.$refs.mapSelectDialog.close();
            },
            selectSearchInfo(e){
                this.address = e.address + e.title;
                this.moveCenter = true;
                this.lng = e.point.lng;
                this.lat = e.point.lat;
                //this.$refs.localSearch.search();
            },
            mapHandler({BMap,map}){
                this.bMap = BMap;
                this.map = map;
            },
            initDialog(lng,lat,address) {
                this.lng = lng
                this.lat = lat;
                this.oldCoordinate = [lng,lat];
                this.address = address;
                this.showMap = true;
                this.$nextTick(() => {
                    this.$refs.mapSelectDialog.show();
                })
            },
            checkCoordinate(){
                if(Number(this.lng) >= 180 ||Number(this.lng) <= -180){
                    this.lng = "";
                    this.address = "";
                }
                if(Number(this.lat) >= 90 || Number(this.lat) <= -90){
                    this.lng = "";
                    this.address = "";
                }
                const reg = /(^[\-0-9][0-9]*(.[0-9]+)?)$/
                if(!reg.test(this.lng+"") || !reg.test(this.lat)){
                    this.hasCoordinate = false
                    return;
                }
                if(this.moveCenter){
                    this.oldCoordinate = [this.lng,this.lat];
                }
                this.hasCoordinate = true;
            },
            getClickInfo(e){
                //通过点击百度地图，可以获取到对应的point, 由point的lng、lat属性就可以获取对应的经度纬度
                this.moveCenter = false;
                let pt = e.point;
                let geoc = new BMap.Geocoder();
                let _this = this;
                geoc.getLocation(pt, function(rs){
                    //addressComponents对象可以获取到详细的地址信息
                    let addComp = rs.addressComponents;
                    let site = "";
                    if(addComp.province && addComp.province !== "") site += addComp.province;
                    if(addComp.city && addComp.city !== "") site += addComp.city;
                    if(addComp.district && addComp.district !== "") site += addComp.district;
                    if(addComp.street && addComp.street !== "") site += addComp.street;
                    if(addComp.streetNumber && (addComp.streetNumer+"") !== "") site += addComp.streetNumber;
                    _this.lng = pt.lng;
                    _this.lat = pt.lat;
                    _this.address = site;
                });
            },
            confirmCoordinate(){
                const reg = /(^[\-0-9][0-9]*(.[0-9]+)?)$/
                if(Number(this.lng) >= 180 ||Number(this.lng) <= -180 || Number(this.lat) >= 90 || Number(this.lat) <= -90 || !reg.test(this.lng+"") || !reg.test(this.lat)){
                    this.$alert('当前抓取的坐标有误，请重新抓取！', '错误',{
                        type: 'error'
                    });
                    return;
                }
                this.$emit('change-coordinate',this.lng,this.lat,this.address);
                this.showMap = false;
            },
            changeMoveCenter(){
                this.moveCenter = true;
            }
        }
    }
</script>

<style scoped>
    .map-body {
        height: 600px;
        width: 100%;
    }
    .bm-view {
        height: 100%;
        width: 100%;
        position: relative;
    }
    .searchResult {
        color: red;
    }
    .map-label {
        font-size: 12px;
        font-weight: bold;
    }
    .search-frame {
        width: 300px;
        position: absolute;
        top: 55px;
        left: 20px;
    }
</style>