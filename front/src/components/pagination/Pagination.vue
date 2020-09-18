<template>
    <div class="pagination" style="text-align:center">
        <el-pagination
                :current-page="pageNum"
                :page-sizes="pageSizes"
                :page-size="pageSize"
                :pager-count="5"
                :layout="layout"
                :total="total"
                @current-change="changePage"
                @size-change="changeSize"
        >
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: 'pagination',
        computed: {
            layout(){
                let layout = 'prev, pager, next'
                if(this.hide){
                    return layout
                }
                if(this.all){
                    return 'total,sizes,prev,pager,next,jumper'
                }else{
                    if(this.ifSizes) {
                        layout = 'sizes,' + layout
                    }
                    if(this.ifTotal) {
                        layout = 'total,'+layout
                    }
                    if(this.ifJumper) {
                        layout += ',jumper'
                    }
                }
                return layout
            }
        },
        props: {
            //当前页
            pageNum: {
                type: Number,
                default: 1
            },
            //每页条数列表
            pageSizes: {
                type: Array,
                default:()=>{
                    return [5,10,20,50];
                } 
            },
            //当前每页条数
            pageSize: {
                type: Number,
                default: 10
            },
            //总条数
            total: {
                type: Number,
                default: 20
            },
            //是否显示每页条数选择框
            ifSizes: {
                type: Boolean,
                default: false
            },
            //是否显示总条数
            ifTotal: {
                type: Boolean,
                default: false
            },
            //是否显示跳转页面
            ifJumper: {
                type: Boolean,
                default: false
            },
            //默认都显示
            all: {
                type: Boolean,
                default: true
            },
            //都不显示
            hide: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            changePage(currentPage){
                this.$emit('change-page',currentPage,this.pageSize)
            },
            changeSize(pageSize){
                this.$emit('change-page',1,pageSize)
            }
        }
    }

</script>

<style scoped>

</style>