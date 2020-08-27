<template>
    <el-card class="common-card">
        <div slot="header" style="padding: 24px 20px 0px 0px;">
            <div class="common-card-box">
            </div>
            <span class="common-card-span"><h1>{{cardName}}</h1></span>
            <div class="common-card-btn">
                <slot name="headerButton"></slot>
            </div>
        </div>
        <slot name="body"></slot>
    </el-card>
</template>

<script>
    export default {
        name: "BodyCard",
        props: {
            cardName: {
                type: String,
                default(){
                    return "";
                }
            }
        }
    }
</script>

<style scoped>

</style>