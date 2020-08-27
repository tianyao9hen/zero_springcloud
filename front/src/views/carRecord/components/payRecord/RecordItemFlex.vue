<template>
    <div class="record-item-flex">
        <record-item v-for="recordItem in recordGroup"
                     :title="recordItem.title"
                     :value="recordItem.value"/>
    </div>
</template>

<script>
    import RecordItem from "./RecordItem";
    export default {
        components: {RecordItem},
        name: 'RecordItemFlex',
        props: {
            recordGroup: {
                type: Array,
                default(){
                    return [];
                }
            }
        }
    }

</script>

<style scoped>
    .record-item-flex {
        width: 100%;
        display: flex;
        display: -webkit-flex;
        flex-wrap: nowrap;
        justify-content: space-between;
        align-items: center;
    }
</style>