<template>
  <div class="customer-header">
    <!-- 检索条件-->
    <el-card>
      <el-form layout="inline">
        <el-row>
          <el-col :offset="14" :span="4">
            <el-form-item>
              <el-input placeholder="名称/联系电话" v-model="queryParam.name"></el-input>
            </el-form-item>
          </el-col>
          <template v-if="toggleSearchStatus && toggleSearchStatus=='收缩'">
            <el-col :offset="3" :span="4">
              <el-form-item>
                <el-input placeholder="拓展" v-model="queryParam.name"></el-input>
              </el-form-item>
            </el-col>
          </template>
          <el-col :span="5" style="margin-left:8px">
            <span style="float: right;overflow: hidden;" class="table-page-search-submitButtons">
              <el-button type="primary" @click="searchQuery" icon="search">查询</el-button>
              <el-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">
                重置</el-button>
              <el-link @click="handleToggleSearch()" style="font-size: 14px;">
                {{ toggleSearchStatus ? '收起' : '展开' }}<i
                  :class="toggleSearchStatus=='收起'?'el-icon-arrow-up':'el-icon-arrow-down'"></i>
              </el-link>
            </span>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  name: "CustomerHeader",
  data() {
    return {
      queryParam: {
        name: ""
      },
      toggleSearchStatus: "展开"
    }
  },
  methods: {
    searchQuery() {

    },
    searchReset() {

    },
    /**
     * 动态收缩筛选条件框
     */
    handleToggleSearch() {
      if (this.toggleSearchStatus == '展开') {
        this.toggleSearchStatus = '收缩';
      } else {
        this.toggleSearchStatus = '展开';
      }
    }

  },
}
</script>
<style rel="stylesheet/scss" lang="scss" >
.customer-header {
}
</style>