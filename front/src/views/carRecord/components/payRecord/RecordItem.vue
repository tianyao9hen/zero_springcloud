<template>
    <div class="record-item">
        <div class="record-title">
            <h4>{{title}}</h4>
        </div>
        <div class="record-text">
            {{value}}
        </div>
    </div>
</template>

<script>
    export default {
        name: 'RecordItem',
        props: {
            title: {
                type: String,
                default(){
                    return ""
                }
            },
            value: ""
        }
    }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .record-item {
        width: 120px;
        height: 120px;
        border: solid 1px #DCDFE6;
        border-radius: 10px;
        flex-shrink: 1;
        -webkit-border-radius: 10px;
        .record-title {
            width: 100%;
            height: 50%;
            border-bottom: solid 1px #DCDFE6;
            background-color: #FAFAFA;
            border-top-right-radius: 10px;
            border-top-left-radius: 10px;
            -webkit-border-top-left-radius: 10px;
            -webkit-border-top-right-radius: 10px;
            text-align: center;
            line-height: 60px;
        }
        .record-text{
            width: 100%;
            height: 50%;
            text-align: center;
            line-height: 60px;
            font-size: 14px;
        }
    }

</style>