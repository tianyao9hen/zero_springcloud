<template>
    <div>
        <el-input
                placeholder="输入关键字进行过滤"
                v-model="filterText">
        </el-input>
        <!---->
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
    </div>
</template>
<script>
export default {
    data() {
        return {
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
        };
    },
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    methods: {
        handleNodeClick(data){
            debugger
            console.log(data)
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
};
</script>