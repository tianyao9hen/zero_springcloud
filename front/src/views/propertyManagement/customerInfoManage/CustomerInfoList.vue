<template>
  <div class="customer-list">
    <el-container>
      <el-main>
        <!-- 筛选框 -->
        <!--  <CustomerHeader ref="customerHeader" class="common-header"></CustomerHeader> -->
        <el-card shadow="never" class="common-card">
          <div slot="header" style="padding: 24px 20px 0px 0px;">
            <div class="common-card-box">
            </div>
            <span class="common-card-span">业主信息</span>
            <div class="common-card-btn">
              <el-button class="el-icon-plus" type="primary" @click.prevent="addCustomer"
                style="margin-right:8px">新增
              </el-button>
              <el-input placeholder="查询" suffix-icon="el-icon-search" v-model="queryParam"
                style="width:190px">
              </el-input>
            </div>
          </div>
          <el-table ref="customerListTable" border :data="customerList" row-key="id"
            highlight-current-row>
            <el-table-column prop="typeName" align="left" label="客户类型"
              :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="name" align="center" label="名称" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="phone1" align="left" label="联系电话1" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="phone2" align="left" label="联系电话2" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="phone3" align="center" label="联系电话3"
              :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="cardType" align="right" label="证件类型"
              :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="cardNumber" align="left" label="证件号码"
              :show-overflow-tooltip="true"></el-table-column>
            <el-table-column prop="sexName" align="center" label="性别" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="date" align="center" label="出生日期" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="lastModifyTime" align="center" label="操作"
              :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <el-tooltip content="编辑" placement="top">
                  <el-button size="mini" type="text" @click="editCustomer(scope.row)"><i
                      class="el-icon-lx-edit"></i></el-button>
                </el-tooltip>
                <el-tooltip content="删除" placement="top">
                  <el-button size="mini" type="text" @click="del"><i class="el-icon-delete"></i>
                  </el-button>
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>

          <el-row style="margin-top: 1em;">
            <el-col :span="24" style="text-align: right;">
              <el-pagination layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[20, 30, 50, 100]" :current-page="customerListPage.pageNum"
                :page-size="customerListPage.pageSize" :total="customerListPage.total"
                @size-change="handleSizeChange" @current-change="handleCurrentChange">
              </el-pagination>
            </el-col>
          </el-row>
        </el-card>
      </el-main>
    </el-container>
    <CustomerEdit ref="customerEdit" @refresh="refreshData"></CustomerEdit>
  </div>
</template>
<script>
import CustomerHeader from "./components/CustomerHeader";
import CustomerEdit from "./components/CustomerEdit";
export default {
  name: "CustomerInfoList",
  components: { CustomerHeader, CustomerEdit },
  data() {
    return {
      customerListPage: {
        pageNum: 0,
        total: 0,
        pageSize: 10,
        data: []
      },
      queryParam: "",
      customerList: [
        { type: "0", typeName: "个人", name: "张正明", phone1: "15698418111", phone2: "15698418111", phone3: "15698418111", cardType: "身份证", cardNumber: "370785199909011811", sex: "0", sexName: "男" },
        { type: "1", typeName: "单位", name: "李志广", phone1: "15632412433", phone2: "15632412433", phone3: "15632412433", cardType: "身份证", cardNumber: "370785198301011323", sex: "0", sexName: "男" },
        { type: "1", typeName: "单位", name: "陈晓", phone1: "156324126746", phone2: "156324126746", phone3: "156324126746", cardType: "身份证", cardNumber: "370785199141511324", sex: "1", sexName: "女" },
      ]
    }
  },
  methods: {
    /**
      * 切换当前页面数据大小
     */
    handleSizeChange(size) {
      this.customerListPage.pageSize = size;
      this.customerListPage.pageNum = this.customerListPage.pageNum;
    },
    /**
     * 切换当前页面尺码
     */
    handleCurrentChange(page) {
      this.customerListPage.pageSize = this.customerListPage.pageSize;
      this.customerListPage.pageNum = page;
    },
    addCustomer() {
      this.$refs.customerEdit.initData({ type: "add", data: {} });
    },
    refreshData() {

    },
    del() {
      this.$confirm("确定要删除吗？", "提示", {
        onfirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      })
        .then(() => {
          this.info("删除成功");
        })
        .catch(() => { });
    },
    editCustomer(data) {
      this.$refs.customerEdit.initData({ type: "edit", data: data });
    }
  },
}
</script>
<style rel="stylesheet/scss" lang="scss" >
.customer-list {
}
</style>
