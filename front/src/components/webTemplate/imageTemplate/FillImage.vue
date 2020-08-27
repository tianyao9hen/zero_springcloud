<template>
    <div class="fill-image">
        <el-image :src="src"
                  :preview-src-list="srcList"
                  fit="fill">
        </el-image>
        <span class="imageTextTL">
            <slot name="imageTextTL"></slot>
        </span>
        <span class="imageTextTR">
            <slot name="imageTextTR"></slot>
        </span>
        <span class="imageTextBL">
            <slot name="imageTextBL"></slot>
        </span>
        <span class="imageTextBR">
            <slot name="imageTextBR"></slot>
        </span>
    </div>
</template>

<script>
    export default {
        name: 'FillImage',
        props: {
            src: {
                type: String,
                default(){
                    return "";
                }
            },
            srcList: {
                type: Array,
                default(){
                    return [""]
                }
            }
        }
    }

</script>

<style scoped>
    .fill-image {
        position: relative;
        width: 100%;
        height: 100%;
    }
    .el-image {
        width: 100%;
        height: 100%;
    }
    .imageTextTL {
        position: absolute;
        top: 5px;
        left: 5px;
    }
    .imageTextTR {
        position: absolute;
        top: 5px;
        right: 5px;
    }
    .imageTextBL {
        position: absolute;
        bottom: 5px;
        left: 5px;
    }
    .imageTextBR {
        position: absolute;
        bottom: 5px;
        right: 5px;
    }
</style>