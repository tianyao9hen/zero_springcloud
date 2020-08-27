<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="cardStyle">
          <div class="InnerCardDiv" style="font-weight:600;">
            <div style="float:left;width:150px;">业主</div>
            <span style="fontSize:12px;color:white;backgroundColor:rgb(28,132,198);">总数</span>
          </div>
          <hr>
          <div>
            <div style="margin-top:10px;fontSize:35px;color:grey;">19</div>
            <div style="margin-top:5px;margin-left:5px;fontSize:12px;float:left;width:215px;">未迁入</div><span style="color:rgb(40,167,69)">8</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="cardStyle">
          <div class="InnerCardDiv" style="font-weight:600;">
            <div style="float:left;width:150px;">房屋</div>
            <span style="fontSize:12px;color:white;backgroundColor:rgb(35,198,200);">总数</span>
          </div>
          <hr>
          <div>
            <div style="margin-top:10px;fontSize:35px;color:grey;">22</div>
            <div style="margin-top:5px;margin-left:5px;fontSize:12px;float:left;width:215px;">空闲</div><span style="color:rgb(23,171,209)">8</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="cardStyle">
          <div class="InnerCardDiv" style="font-weight:600;">
            <div style="float:left;width:150px;">停车位</div>
            <span style="fontSize:12px;color:white;backgroundColor:rgb(26,179,148);">总数</span>
          </div>
          <hr>
          <div>
            <div style="margin-top:10px;fontSize:35px;color:grey;">33</div>
            <div style="margin-top:5px;margin-left:5px;fontSize:12px;float:left;width:215px;">空闲</div><span style="color:rgb(26,179,162)">8</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="cardStyle">
          <div class="InnerCardDiv" style="font-weight:600;">
            <div style="float:left;width:150px;">商铺</div>
            <span style="fontSize:12px;backgroundColor:rgb(209,218,222);">总数</span>
          </div>
          <hr>
          <div>
            <div style="margin-top:10px;fontSize:35px;color:grey;">0</div>
            <div style="margin-top:5px;margin-left:5px;fontSize:12px;float:left;width:215px;">空闲</div><span style="color:rgb(220,53,69)">8</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 表格
    <el-card class="box-card">
  <div slot="header" class="clearfix">
    <span>卡片名称</span>
    <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
  </div>
  <div v-for="o in 4" :key="o" class="text item">
    {{'列表内容 ' + o }}
  </div>
</el-card>
    -->
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>欠费信息</span>
            <el-radio-group v-model="radioButton" @change="toggerTable" style="float: right">
              <el-radio-button :label="true">物业费</el-radio-button>
              <el-radio-button :label="false">停车费</el-radio-button>
            </el-radio-group>
          </div>
          <div v-show="radioButton">
            <el-table :data="tableData" stripe style="width: 100%;">
              <el-table-column prop="date" label="费用ID" header-align="center" />
              <el-table-column prop="name" label="业主名称" header-align="center" />
              <el-table-column prop="phone" label="联系电话" header-align="center" />
              <el-table-column prop="address" label="房屋编号" header-align="center" />
              <el-table-column prop="start" label="费用开始时间" header-align="center" />
              <el-table-column prop="end" label="费用到期时间" header-align="center" />
            </el-table>
            <div class="pagination">
              <el-pagination background  layout="prev, pager, next" :total="100">
              </el-pagination>
            </div>
          </div>
          <div v-show="!radioButton">
            <el-table :data="tableDataStop" stripe style="width: 100%;">
              <el-table-column prop="date" label="费用ID" header-align="center" />
              <el-table-column prop="name" label="业主名称" header-align="center" />
              <el-table-column prop="phone" label="联系电话" header-align="center" />
              <el-table-column prop="carNo" label="车位编号" header-align="center" />
              <el-table-column prop="start" label="费用开始时间" header-align="center"  />
              <el-table-column prop="end" label="费用到期时间" header-align="center" />
            </el-table>
            <div class="pagination">
              <el-pagination background  layout="prev, pager, next" :total="100">
              </el-pagination>
            </div>
          </div>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "dashboard",
  data() {
    return {
      radioButton: true,
        tableDataStop: [{
            date: '902020042236630098',
            name: '王小二',
            phone: '12345678901',
            carNo: '鲁Ac00103',
            start: '2020-04-22 14:44:26',
            end: '2020-04-22 14:44:26'
        }, {
            date: '902020042236630098',
            name: '王小二',
            phone: '12345678901',
            carNo: '鲁Ac00103',
            start: '2020-04-22 14:44:26',
            end: '2020-04-22 14:44:26'
        }, {
            date: '902020042236630098',
            name: '王小二',
            phone: '12345678901',
            carNo: '鲁Ac00103',
            start: '2020-04-22 14:44:26',
            end: '2020-04-22 14:44:26'
        }],
      tableData: [{
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }, {
          date: '902020042236630098',
          name: '王小虎',
          phone: '12345678901',
          address: '	c00103',
          start: '2020-04-22 14:44:26',
          end: '2020-04-22 14:44:26'
        }]
    };
  },
  created() {
  },
  // 页面初始化
  activated() {
  },
  mounted(){
      //this.showEcharts()
  },
  methods: {
      toggerTable(){
          console.log("发送请求查询停车费信息");

      }
  }
};
</script>
<style scoped>
  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
</style>
