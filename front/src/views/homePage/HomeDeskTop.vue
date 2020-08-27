<template>
    <div class="home-desktop">
        <div class="flex-box">
            <desktop-item :item-image-src="url" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="5.png" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="6.png" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="3.png" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="6.png" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="5.png" item-title="测试测试"></desktop-item>
            <desktop-item item-image-src="6.png" item-title="测试测试"></desktop-item>
        </div>
    </div>
</template>

<script>
    import DesktopItem from "./homeDeskTop/DesktopItem";
    export default {
        name: "HomeDeskTop",
        components: {
            DesktopItem,
        },
        data(){
            return{
                url:'4.png',
                url1:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3631187780,3518519287&fm=15&gp=0.jpg',
            }
        }

    }
</script>

<style scoped>
    .flex-box {
        margin: 50px auto;
        height: 1000px;
        width: 900px;
        display: flex;
        display: -webkit-flex;
        flex-wrap: wrap;
        align-items: flex-start;
        align-content: flex-start;
        justify-content: flex-start;
    }
</style>