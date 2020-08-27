<template>
    <div class="desktop-item">
        <div class="item-image">
            <!--<el-image style="width: 100px; height: 100px"
                    src="../../assets/img/5.png"
                    fit="fit" @click="gotoUrl"></el-image>-->
            <img :src="url" style="width: 100%;height: 100%">
<!--            <img src="../../../assets/img/4.png" style="width:100%;height: 170px">-->
        </div>
        <div class="item-title">
            {{itemTitle}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "DesktopItem",
        props: {
            itemImageSrc:String,
            itemTitle: '',
            orgUrl: '',
        },

        mounted() {
            let img = this.itemImageSrc
            if(img && img != null && img != '')
                this.url = require('@/assets/img/'+img)
        },
        data(){
            return {
                url :''
            }
        },
        methods: {
            gotoUrl(){
                console.log(1);
                window.location.href = "http://localhost:8080/"
            }
        },
        //监听事件  当itemImageSrc有变化时 赋值
        watch:{
            itemImageSrc(){
                let img = this.itemImageSrc
                if(img && img != null && img != '')
                    this.url = require('@/assets/img/'+img)
            }
        }
    }
</script>

<style scoped>
    .item-image {
        width: 100px;
        height: 100px;
        margin: 10px 20px 0px 20px;
    }
    .item-title {
        width: 100px;
        height: 50px;
        line-height: 50px;
        margin: 0 20px 0px 20px;
        text-align: center;
    }
</style>