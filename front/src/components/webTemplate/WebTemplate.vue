<template>
    <div class="web-template">
        <el-container>
            <el-main>
                <el-card v-if="!simple">
                    <slot name="searchForm"></slot>
                </el-card>
                <slot v-if="self" name="selfBody"></slot>
                <body-card v-if="!self" :class="{simpleWeb:!simple}" :card-name="webName">
                    <template slot="headerButton">
                        <slot name="headerButton"></slot>
                    </template>
                    <template slot="body">
                        <slot v-if="noTable" name="body"></slot>
                        <div class="webTable" v-if="!noTable">
                            <slot name="webTable"></slot>
                            <pagination :currentPage="tableData?tableData.currentPage:undefined"
                                        :pageSize="tableData?tableData.pageSize:undefined"
                                        :total="tableData?tableData.total:undefined"
                                        :pageSizes="tableData?tableData.pageSizes:undefined"
                                        @change-page="changePage"
                            />
                        </div>
                    </template>
                </body-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Pagination from "../pagination/Pagination";
    import BodyCard from "./cardTemplate/BodyCard";
    export default {
        name: 'WebTemplate',
        components: {
            BodyCard,
            Pagination
        },
        props: {
            webName: {
                type: String,
                default: "",
            },
            tableData: {
                type: Object,
                default: null,
            },
            //是否显示搜索框
            simple: {
                type: Boolean,
                default(){
                    return false;
                }
            },
            //自定义页面内容
            self: {
                type: Boolean,
                default(){
                    return false
                }
            },
            noTable: {
                type: Boolean,
                default(){
                    return false;
                }
            }
        },
        methods: {
            changePage(currentPage,pageSize){
                console.log(currentPage +":"+pageSize);
                this.$emit('change-page',currentPage,pageSize)
            }
        }
    }

</script>

<style scoped lang="scss" >
.web-template {
    .el-form-item--small.el-form-item {
        margin-bottom: 16px;
    }
    .el-card__body {
        padding: 30px;
    }
    .common-card-btn {
        margin-right: 0px;
    }
    .simpleWeb {
        margin-top: 20px;
    }
}
</style>