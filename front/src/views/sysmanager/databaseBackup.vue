<template>
  <div>
    <el-container>
      <el-main>
        <!--    列表表格+按钮-->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-card  class="common-card">
              <!--        列表按钮-->
              <div slot="header" style="padding: 24px 20px 0px 0px;">
                <div class="common-card-box">
                </div>
                <span class="common-card-span">数据库备份列表</span>
                <el-button type="primary" style="float: right; margin-left: 10px;"  @click="edit()"><i class="el-icon-edit"></i>设置自动备份</el-button>
                <el-button type="primary" style="float: right" @click="del()"><i class="el-icon-delete"></i>删除分贝</el-button>
                <el-button type="primary" style="float: right" @click="add()"><i class="el-icon-lx-add"></i>创建备份</el-button>
              </div>
    <!--          列表-->
              <el-table  :data="tableData" style="width: 100%;margin-bottom: 20px;" border>
                <el-table-column align="center" min-width="15%">
                  <template slot="header" slot-scope="scope">
                    <el-checkbox  v-model="checkAll"  @change="handleCheckAllChange"></el-checkbox>
                  </template>
                  <template slot-scope="scope">
                    <el-checkbox  v-model="scope.row.checked"></el-checkbox>
                  </template>
                </el-table-column>
                <el-table-column prop="name"   label="文件名" align="center" :show-overflow-tooltip="true" />
                <el-table-column prop="size"   label="文件大小" align="center" :show-overflow-tooltip="true" />
                <el-table-column prop="creator"   label="创建人" align="center" :show-overflow-tooltip="true" />
                <el-table-column prop="time"   label="创建时间" align="center" :show-overflow-tooltip="true"/>
                <el-table-column label="操作" align="center" :show-overflow-tooltip="true">
                  <template slot-scope="scope">
                    <el-tooltip content="删除" placement="top">
                      <el-button size="mini" type="text" @click="del"><i class="el-icon-delete"></i></el-button>
                    </el-tooltip>
                    <el-tooltip content="下载" placement="top">
                      <el-button size="mini" type="text" ><i class="el-icon-download"></i></el-button>
                    </el-tooltip>
                  </template>
                </el-table-column>
              </el-table>
              <div class="pagination" style="text-align: center">
                <pagination>
                </pagination>
              </div>
            </el-card>
          </el-col>
        </el-row>

    <!--    设置自动创建-->
        <el-dialog title="设置自动备份" :visible.sync="dialogFormVisible" center>
          <el-form :model="form" label-width="80px" size="medium">
            <el-form-item label="创建时间"  v-model="form.value" :rules="[ { required: true, message: '不能为空'}]" >
              <template>
                <el-radio v-model="form.radio" label="1">00:00</el-radio>
                <el-radio v-model="form.radio" label="2">01:00</el-radio>
                <el-radio v-model="form.radio" label="3">02:00</el-radio>
                <el-radio v-model="form.radio" label="4">03:00</el-radio>
                <el-radio v-model="form.radio" label="5">04:00</el-radio>
                <el-radio v-model="form.radio" label="6">05:00</el-radio>
                <el-radio v-model="form.radio" label="7">06:00</el-radio>
                <el-radio v-model="form.radio" label="8">07:00</el-radio>
                <el-radio v-model="form.radio" label="9">08:00</el-radio>
                <el-radio v-model="form.radio" label="10">09:00</el-radio>
                <el-radio v-model="form.radio" label="11">10:00</el-radio>
                <el-radio v-model="form.radio" label="12">11:00</el-radio>
                <el-radio v-model="form.radio" label="13">12:00</el-radio>
                <el-radio v-model="form.radio" label="14">13:00</el-radio>
                <el-radio v-model="form.radio" label="15">14:00</el-radio>
                <el-radio v-model="form.radio" label="16">15:00</el-radio>
                <el-radio v-model="form.radio" label="17">16:00</el-radio>
                <el-radio v-model="form.radio" label="18">17:00</el-radio>
                <el-radio v-model="form.radio" label="19">18:00</el-radio>
                <el-radio v-model="form.radio" label="20">19:00</el-radio>
                <el-radio v-model="form.radio" label="21">20:00</el-radio>
                <el-radio v-model="form.radio" label="22">21:00</el-radio>
                <el-radio v-model="form.radio" label="23">22:00</el-radio>
                <el-radio v-model="form.radio" label="24">23:00</el-radio>
              </template>
            </el-form-item>
            <el-form-item label="类型"   v-model="form.value" :rules="[{ required: true, message: '不能为空'}]">

                <el-checkbox label="周一"  name="type" ></el-checkbox>
                <el-checkbox label="周二" name="type" ></el-checkbox>
                <el-checkbox label="周三" name="type" ></el-checkbox>
                <el-checkbox label="周四" name="type" ></el-checkbox>
                <el-checkbox label="周五" name="type" ></el-checkbox>
                <el-checkbox label="周六" name="type" ></el-checkbox>
                <el-checkbox label="周日" name="type" ></el-checkbox>

            </el-form-item>
            <el-form-item label="保留时间"   v-model="form.value" :rules="[{ required: true, message: '不能为空'}]">
              <template>
                <el-input-number v-model="num" controls-position="right"  :min="1" :max="14" style="width: 120px"></el-input-number>
                <span> 天；取值范围1-14</span>
              </template>
            </el-form-item>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="showMessage(false)">取 消</el-button>
            <el-button type="primary" @click="showMessage(true)">确 定</el-button>
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </div>

</template>
<script>
export default {
  data() {
    return {
      num:1,
      dialogFormVisible: false,
      checkAll: false,
      tableData: [{
        'name': '0242730299a511ea92e2507b9df2c157',
        'size': '11373',
        'creator': '企业版',
        'time': "2010/06/10 12:05:15"
      }, {
        'name': '15dferg299a511ea92excv7b9df2c157',
        'size': '11373',
        'creator': '商业版',
        'time': "2015/07/25 10:37:15"
      }],
      form: {
        value: '',
      },
    }
  },
  methods: {
    handleCheckAllChange (val) {
      this.isIndeterminate = false
      this.tableData.forEach(item => {
        item.checked = val
      })
    },
    add(){
      this.$confirm('此操作将创建一条新记录, 是否创建?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '创建成功!'
        });
      }).catch(() => {

      });
    },
    del(){
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {

      });
    },
    edit(){
      this.dialogFormVisible=true;
    },
    showMessage(flag){
      this.dialogFormVisible = false
      if(flag){
        this.$message({
          type: 'success',
          message: '保存成功!'
        });
      }else{

      }
    },
  },
  mounted() {
    //this.getUsers();
    //this.testFn();
  }
};
</script>
<style>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
</style>

