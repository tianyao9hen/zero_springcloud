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
        data(){
            return {
                //修改每页条数标识，因为在修改每页条数时，当前页码也会变化，为了防止触发当前页变化的事件，添加标识
                changeSizeFlag: false,
            }
        },
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
                //如果标识为true,则认为本次事件为修改每页条数触发的，这次事件不应该发送出去，同时因为修改每页条数触发的时间应该只触发一次，所以可以将标识修改掉方便后续正常的时间触发
                if(this.changeSizeFlag) {
                    this.changeSizeFlag = !this.changeSizeFlag;
                    return;
                }
                this.$emit('change-page',currentPage,this.pageSize)
            },
            changeSize(pageSize){
                //如果当前页不为1时，因为现在要修改为1，所以设置标识，这样在触发changePage可以使其无效
                if(this.pageNum !== 1)this.changeSizeFlag = true;
                this.$emit('change-page',1,pageSize)
            }
        }
    }

</script>

<style scoped>

</style>