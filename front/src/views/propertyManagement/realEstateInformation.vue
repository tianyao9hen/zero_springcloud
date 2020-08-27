<template>
    <div class="customer-list">
        <el-container>
            <el-aside width="250px">
                <el-card class="box-card">
                    <el-input
                            placeholder="输入关键字进行过滤"
                            v-model="filterText">
                    </el-input>
                    <el-tree
                            class="filter-tree"
                            :data="data"
                            :expand-on-click-node="false"
                            :props="defaultProps"
                            default-expand-all
                            node-key="id"
                            @node-click="handleNodeClick"
                            :filter-node-method="filterNode"
                            ref="tree">
                    </el-tree>
                </el-card>
            </el-aside>
            <el-main>
                <first-page :treeId = "treeId" :treeName="treeName" v-if="first"></first-page>
                <second-page :treeId = "treeId" :treeName="treeName"  v-if="second"></second-page>
                <third-page :treeId = "treeId" :treeName="treeName"  v-if="third"></third-page>
                <fourth-page :treeId = "treeId" :treeName="treeName"  v-if="fourth"></fourth-page>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import houseTree from './components/houseTree'
import firstPage from './components/firstPage'
import secondPage from './components/secondPage'
import thirdPage from './components/thirdPage'
import fourthPage from './components/fourthPage'
export default {
    components: {
        houseTree,
        firstPage,
        secondPage,
        thirdPage,
        fourthPage,
    },
    name: 'realEstateInformation',
    data() {
        return {
            treeId:'1',
            treeName:'齐鲁交通集团',
            first:true,
            second:false,
            third:false,
            fourth:false,
            filterText: '',
            data: [{
                id: 1,
                label: '齐鲁交通集团',
                level:1,
                children: [{
                    id: 2,
                    label: 'A区',
                    level:2,
                    children: [{
                        id: 21,
                        label: '1号楼',
                        level:3,
                        children: [{
                            id: 211,
                            label: '1单元',
                            level:4,
                        },{
                            id: 212,
                            label: '2单元',
                            level:4,
                        },{
                            id: 213,
                            label: '3单元',
                            level:4,
                        }]
                    }, {
                        id: 22,
                        label: '2号楼',
                        level:3,
                        children: [{
                            id: 221,
                            label: '1单元',
                            level:4,
                        },{
                            id: 222,
                            label: '2单元',
                            level:4,
                        },{
                            id: 223,
                            label: '3单元',
                            level:4,
                        }]
                    }]
                },{
                    id: 3,
                    label: 'B区',
                    level:2,
                    children: [{
                        id: 31,
                        label: '3号楼',
                        level:3,
                        children: [{
                            id: 311,
                            label: '1单元',
                            level:4,
                        },{
                            id: 312,
                            label: '2单元',
                            level:4,
                        },{
                            id: 313,
                            label: '3单元',
                            level:4,
                        }]
                    }, {
                        id: 32,
                        label: '4号楼',
                        level:3,
                        children: [{
                            id: 321,
                            label: '1单元',
                            level:4,
                        },{
                            id: 322,
                            label: '2单元',
                            level:4,
                        },{
                            id: 323,
                            label: '3单元',
                            level:4,
                        }]
                    }]
                }]
            }],
            defaultProps: {
                children: 'children',
                label: 'label'
            }
        }
    },
    created() {
    },
    computed: {

    },
    methods: {
        handleNodeClick(data){
            let level = data.level
            this.treeId = data.id
            this.treeName = data.label
            this.first = false
            this.second = false
            this.third = false
            this.fourth = false
            if(level == 1){
                this.first = true
            }else if(level == 2){
                this.second = true
            }else if(level == 3){
                this.third = true
            }else{
                this.fourth = true
            }
        },
        loadNode(node, resolve){
            debugger
            let level = node.level
        },
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
    }
}

</script>

<style scoped>

</style>
