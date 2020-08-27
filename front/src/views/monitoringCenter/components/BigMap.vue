<template>
    <div class="wrapper">
        <baidu-map class="map" :center="map.center" :zoom="map.zoom" @ready="handler" :map-style="mapStyle">
            <!--定位-->
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true"
                            :autoLocation="true"></bm-geolocation>
            <!--点-->
            <bm-marker :position="map.center" :dragging="map.dragging" animation="BMAP_ANIMATION_DROP">
                <bm-label :content="parkName" :labelStyle="{fontSize : '12px'}" :offset="{width: -30, height: 25}" style="background: rgba(255,255,255,0.8)"/>
            </bm-marker>

        </baidu-map>


    </div>
</template>

<script>
    import "echarts/extension/bmap/bmap";

    export default {
        name: "bigMap",
        data() {
            return {
                parkName:'齐鲁XXX停车场',
                map: {
                    center: {lng: 117.132477, lat: 36.686067},
                    zoom: 17,
                    show: true,
                    dragging: true
                },
                mapStyle: {
                    styleJson: [{
                        "featureType": "background",
                        "elementType": "geometry",
                        "stylers": {
                            "color": "#022866ff"
                        }
                    }, {
                        "featureType": "poilabel",
                        "elementType": "labels.text",
                        "stylers": {
                            "fontsize": 26
                        }
                    }],
                },

            }

        },
        methods: {
            handler({BMap, map}) {
                let me = this;
                console.log(BMap, map)
                // 鼠标缩放
                map.enableScrollWheelZoom(true);
                // 点击事件获取经纬度
                map.addEventListener('click', function (e) {
                    console.log(e.point.lng, e.point.lat)
                })
            }
        }
    }
</script>

<style scoped>
    .wrapper {
        height: 97%;
        width: 99%;
        padding: 5px 5px 5px 5px;
    }

    .map {
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
