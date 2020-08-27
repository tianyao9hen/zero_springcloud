<template>
    <div id="top-header">
        <dv-border-box-11 >
            <div class="header-left-decoration" />
            <div class="header-center-decoration" />
            <div class="header-right-decoration" :reverse="true" />
            <div class="center-title">监控中心</div>
        </dv-border-box-11>

    </div>
</template>

<script>
    export default {
        name: "TopHeader"
    }
</script>

<style lang="less">
    #top-header {
        position: relative;
        width: 100%;
        height: 50px;
        display: flex;
        justify-content: space-between;
        flex-shrink: 0;

        .header-center-decoration {
            width: 40%;
            height: 60px;
            margin-top: 30px;
        }

        .header-left-decoration, .header-right-decoration {
            width: 25%;
            height: 60px;
        }

        .center-title {
            position: absolute;
            font-size: 30px;
            font-weight: bold;
            left: 49%;
            top: 9px;
            transform: translateX(-50%);
        }
    }
</style>

