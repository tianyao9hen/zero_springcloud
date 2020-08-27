<template>
    <div class="dialog-template">
        <el-dialog :visible.sync="isShow"
                   :show-close="isShowClose"
                   v-if="isShow"
                   :width="width"
                   :top="top"
                   @close="closeClick" left>
            <div class="header-template" slot="title" v-if="isShowTitle">
                <div class="dialog-title" style="height: 38px">
                    <h2 style="height: 37px">{{dialogName}}</h2>
                </div>
                <el-divider></el-divider>
            </div>

            <div :class="{'body-template':isShowBodyPadding}">
                <slot name="body"></slot>
            </div>

            <div slot="footer" v-if="isShowFooter" class="dialog-footer">
                <slot name="firstButton"></slot>
                <el-button @click="closeClick">{{cancelButtonTitle}}</el-button>
                <slot name="middleButton"></slot>
                <el-button type="primary" @click="primaryClick">{{primaryButtonTitle}}</el-button>
                <slot name="lastButton"></slot>
                <!--
                <template slot="firstButton">
                    <el-button >firstButton</el-button>
                </template>
                <template slot="middleButton">
                    <el-button >middleButton</el-button>
                </template>
                <template slot="lastButton">
                    <el-button >lastButton</el-button>
                </template>
                -->
            </div>
            <div class="empty" v-if="!isShowFooter && isShowBottomEmpty"></div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DialogTemplate",
        data(){
            return {
                isShow: false,
            }
        },
        props: {
            dialogName: {
                type:String,
                default(){
                    return '';
                }
            },
            cancelButtonTitle: {
                type:String,
                default(){
                    return '取 消'
                }
            },
            primaryButtonTitle: {
                type: String,
                default(){
                    return '确 定';
                }
            },
            width:{
                type:String,
                default(){
                    return '30%'
                }
            },
            top: {
                type:String,
                default(){
                    return '15vh';
                }
            },
            //是否显示底部按钮
            isShowFooter: {
                type:Boolean,
                default(){
                    return true;
                }
            },
            //是否显示窗口标题
            isShowTitle: {
                type: Boolean,
                default(){
                    return true;
                }
            },
            //是否显示底部空间，主要为了窗口美观
            isShowBottomEmpty: {
                type: Boolean,
                default(){
                    return true;
                }
            },
            //是否显示右上角关闭图标
            isShowClose: {
                type: Boolean,
                default(){
                    return true;
                }
            },
            //是否显示正文到标题的间距，美观
            isShowBodyPadding: {
                type: Boolean,
                default(){
                    return true;
                }
            }
        },
        methods: {
            show(){
                this.isShow = true;
            },
            close(){
                this.isShow = false;
            },
            showMessage(flag) {
                this.dialogFormVisible = false
                if (flag) {
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                } else {
                }
            },
            primaryClick(){
                this.$emit('primaryClick');
            },
            closeClick(){
                this.close()
                this.$emit('closeClick');
            },
        }
    }
</script>

<style scoped>
    .el-divider {
        margin: 0px;
    }
    .dialog-title {
        padding: 0 40px;
    }
    .empty {
        height: 20px;
    }
    .body-template {
        padding: 30px 40px;
        height: auto;
    }
    .header-template {
        padding-top: 20px;
    }
</style>