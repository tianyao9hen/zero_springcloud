<template>
    <div class="map-show">
        <baidu-map class="bm-view" :center="getCenter"
                   :zoom='zoom' :scroll-wheel-zoom="true"
                   @ready="map_handler"
                   @click="getClickInfo">
            <bm-marker :position="getCenter" :dragging="false"></bm-marker>
            <bm-overlay
                    pane="labelPane"
                    v-if="show"
                    @draw="draw"
                    class="sample">
                <div class="title">{{parkName}}</div>
            </bm-overlay>
            <bm-control anchor="BMAP_ANCHOR_TOP_RIGHT" :offset="{width: 12, height: 5}">
                <el-button v-if="isShowButton && show" @click="optCoordinate">选择地点</el-button>
            </bm-control>
        </baidu-map>
    </div>
</template>

<script>
    export default {
        name: "MapShow",
        data(){
            return {
                zoom: 18,
                show: false
            }
        },
        props: {
            parkName: "",
            lng: {
                type: Number
            },
            lat: {
                type: Number
            },
            isShowButton: {
                type: Boolean,
                default(){
                    return false
                }
            }
        },
        computed: {
            getCenter(){
                return {
                    lng: this.lng,
                    lat: this.lat
                }
            },
        },
        methods: {
            map_handler({ BMap, map }){
                this.show = true;
            },
            optCoordinate(){
                this.$emit('opt-coordinate');
            },
            draw ({el, BMap, map}) {
                const pixel = map.pointToOverlayPixel(new BMap.Point(this.lng,this.lat))
                el.style.left = pixel.x - 300 + 'px'
                el.style.top = pixel.y + 5 + 'px'
            },
            getClickInfo(e){

            }
        }
    }
</script>

<style scoped>
    .map-show {
        width: 100%;
        height: 100%;
        background-color: #F0F5FA;
    }
    .bm-view {
        height: 100%;
        width: 100%;
    }
    .sample {
        width: 600px;
        height: 20px;
        line-height: 20px;
        color: #000000;
        text-align: center;
        font-size: 12px;
        position: absolute;
    }
    .title{
        width: auto;
        display:inline;
        margin: 0 auto;
        background: rgba(255,255,255,0.8);
    }
</style>