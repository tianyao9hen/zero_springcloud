<template>
    <el-card>
        <el-form layout="inline"  :model="formData">
            <slot name="searchItems">

            </slot>

            <!--<el-row>
                <el-col :span="8">
                    <el-form-item  label="模板">
                        <el-select v-model="formData.value" placeholder="请选择模块" >
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="操作类型" >
                        <el-select v-model="formData.value1" placeholder="请选择操作类型" >
                            <el-option v-for="item in options1" :key="item.value" :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="4" :offset="4" style="text-align:right">
                    <el-button style="margin-right:8px;" type="primary">查询</el-button>
                    <el-button>重置</el-button>
                </el-col>
            </el-row>-->

        </el-form>
        <div>{{test}}</div>
        <search-item-list></search-item-list>
    </el-card>
</template>

<script>
    export default {
        name: 'searchCard',
        data(){
            return {
                formData:{
                    value:[],
                    value1:[],
                },
                test: 1,
                searchItems: null,
            }
        },
        components: {
            searchItemList: {
                render(){

                }
            }
        },
        mounted(){
            console.log(this.$slots.searchItems);
            this.searchItems = this.$slots.searchItems;
        }
    }

</script>

<style scoped>

</style>