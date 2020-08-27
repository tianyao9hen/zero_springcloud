<template>
  <div class="customer-edit">
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="800px">
      <el-form layout="inline" :model="customerModel" :rules="rules" label-width="150px">
        <el-card shadow="never" header="基础信息" style="margin-top:20px">
          <el-row>
            <el-col :offset="1" :span="9">
              <el-form-item label="客户类别" prop="customerType">
                <el-select v-model="customerType" placeholder="请选择类别">
                  <el-option v-for="item in customerTypeList" :key="item.code" :label="item.code"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="客户名称">
                <el-input placeholder="客户名称" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :offset="1" :span="9">
              <el-form-item label="编号">
                <el-input placeholder="编号" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" label="银行账号" v-if="customerType =='1'">
              <el-form-item label="银行账号">
                <el-input placeholder="银行账号" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='0'">
            <el-col :offset="1" :span="9">
              <el-form-item label="性别">
                <el-radio v-model="customerModel.sex" label="0">男</el-radio>
                <el-radio v-model="customerModel.sex" label="1">女</el-radio>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="出生日期">
                <el-date-picker v-model="customerModel.createData" type="date" placeholder="选择出生日期" style="width:100%">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='1'">
            <el-col :offset="1" :span="9">
              <el-form-item label="公司类型">
                <el-select v-model="companyType" placeholder="请选择类别">
                  <el-option v-for="item in companyTypeList" :key="item.code" :label="item.code"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="公司人数">
                <el-input placeholder="公司人数" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='1'">
            <el-col :offset="1" :span="9">
              <el-form-item label="公司开户名称">
                <el-input placeholder="公司开户名称" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="法人代表">
                <el-input placeholder="法人代表" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='0'">
            <el-col :offset="1" :span="9">
              <el-form-item label="证件类型">
                <el-select v-model="certificateType" placeholder="请选择类别">
                  <el-option v-for="item in certificateTypeList" :key="item.code" :label="item.code"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="身份证号码">
                <el-input placeholder="请输入身份证号码" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='1'">
            <el-col :offset="1" :span="9">
              <el-form-item label="统一社会信用代码">
                <el-input placeholder="请输入统一社会信用代码" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="执照有效期">
                <el-date-picker v-model="customerModel.createData" type="date" placeholder="选择出生日期" style="width:100%">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
        <el-card shadow="never" header="联系方式" style="margin-top:20px">
          <el-row>
            <el-col :offset="1" :span="9">
              <el-form-item label="联系电话1">
                <el-input placeholder="请输入联系电话1" v-model="customerModel.phone1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="联系电话2">
                <el-input placeholder="请输入联系电话2" v-model="customerModel.phone2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :offset="1" :span="9">
              <el-form-item label="联系电话3">
                <el-input placeholder="请输入联系电话3" v-model="customerModel.phone3"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="电子邮箱">
                <el-input placeholder="请输入邮箱" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='0'">
            <el-col :offset="1" :span="9">
              <el-form-item label="QQ">
                <el-input placeholder="请输入QQ" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="微信">
                <el-input placeholder="请输入微信" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='1'">
            <el-col :offset="1" :span="9">
              <el-form-item label="单位传真">
                <el-input placeholder="请输入单位传真" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='1'">
            <el-col :offset="1" :span="9">
              <el-form-item label="单位地址">
                <el-input placeholder="请输入单位地址" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='0'">
            <el-col :offset="1" :span="18">
              <el-form-item label="户籍地址">
                <el-input placeholder="请输入户籍地址" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="customerType =='0'">
            <el-col :offset="1" :span="18">
              <el-form-item label="现住地址">
                <el-input placeholder="请输入现住地址" v-model="customerModel.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :offset="1" :span="18">
              <el-form-item label="备注">
                <el-input type="textarea" placeholder="请输入备注" v-model="customerModel.name">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "CustomerEdit",
  data() {
    return {
      dialogTitle: "",
      sexType: "",
      dialogVisible: false,
      customerTypeList: [
        { code: "个人", value: "0" },
        { code: "单位", value: "1" }
      ],
      customerType: "0",
      companyType: "0",
      companyTypeList: [
        { code: "企业", value: "0" },
        { code: "个体工商户", value: "1" },
        { code: "其他", value: "2" }
      ],
      certificateTypeList: [
        { code: "身份证", value: "0" },
        { code: "军官证", value: "1" }
      ],
      certificateType: "0",
      customerModel: {
        name: "",
        createData: ""
      },
      rules: {
        customerType: [
          { required: true, message: "请选择客户类型", trigger: "blur" },
        ]

      }
    }
  },
  methods: {
    /*
     * 初始化信息
     */
    initData(data) {
      if (data && data.type == 'add') {
        Object.assign(this.customerModel, {});
        this.customerType = "0";
        this.dialogTitle = "新增客户档案";
      } else if (data && data.type == 'edit') {
        Object.assign(this.customerModel, data.data);
        this.customerType = data.data.type;
        this.dialogTitle = "修改客户档案";
      }
      this.$nextTick(() => {
        this.dialogVisible = true;
      })
    }

  },
}
</script>
<style rel="stylesheet/scss" lang="scss" >
.customer-edit {
  .el-form-item__label {
    font-size: 12px;
  }
  .el-form-item__content {
    font-size: 12px;
  }
  .el-dialog__body {
    padding: 0;
    margin: 0;
  }
  .el-select.el-select--small {
    width: 100%;
  }
}
</style>
