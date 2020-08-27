<template>
    <div>
        <!--选项卡-->
        <el-container>
            <el-main>
                <el-row >
                    <el-col :span="24">
                        <el-card class="box-card">
                            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">

                                <!--房间仪表抄表选项卡-->
                                <el-tab-pane label="房间仪表抄表" name="interior">
                                    <el-row >
                                        <el-col :span="24">
                                            <el-card class="box-card">
                                                <div slot="header">
                                                    <el-row :gutter="20">
                                                        <el-col :span="24">
                                                            <el-form label-width="90px" layout="inline">
                                                                <el-row>
                                                                    <el-col :span="6">
                                                                        <el-form-item label="月份" label-width="50px">
                                                                            <el-date-picker v-model="interiorQueryData.month"  style="width: 100%" type="month" placeholder="请选择月份"></el-date-picker>
                                                                        </el-form-item>
                                                                    </el-col>
                                                                    <el-col :span="6">
                                                                        <el-form-item label="楼宇">
                                                                            <el-select v-model="interiorQueryData.buildInfo" style="width: 100%" multiple placeholder="请选择楼宇">
                                                                                <el-option v-for="item in buildInfoList" :key="item.value" :label="item.label" :value="item.value">
                                                                                </el-option>
                                                                            </el-select>
                                                                        </el-form-item>
                                                                    </el-col>
                                                                    <el-col :span="6">
                                                                        <el-form-item label="仪表种类">
                                                                            <el-select v-model="interiorQueryData.instrumentType" style="width: 100%" multiple placeholder="请选择仪表种类">
                                                                                <el-option v-for="item in instrumentTypeList" :key="item.value" :label="item.label" :value="item.value">
                                                                                </el-option>
                                                                            </el-select>
                                                                        </el-form-item>
                                                                    </el-col>
                                                                    <el-col :span="6" style="text-align:right">
                                                                        <el-button type="primary">查询</el-button>
                                                                        <el-button>重置</el-button>
                                                                    </el-col>
                                                                </el-row>
                                                            </el-form>
                                                        </el-col>
                                                    </el-row>
                                                </div>
                                                <div style="height: 50px">
                                                    <span class="common-card-span" style="font-weight: bolder">2020年6月-电表-抄表进度</span>
                                                    <div style="float: right">
                                                        <el-dropdown trigger="click">
                                                        <span class="el-dropdown-link">
                                                            <el-button type="primary" class="el-icon-plus" style="margin-right:8px">导入抄表设计<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></el-button>
                                                        </span>
                                                            <el-dropdown-menu slot="dropdown">
                                                                <el-dropdown-item><el-button type="text" @click="downloadModel">下载抄表模板</el-button></el-dropdown-item>
                                                                <el-dropdown-item><el-button type="text" @click="importModel">导入抄表数据</el-button></el-dropdown-item>
                                                            </el-dropdown-menu>
                                                        </el-dropdown>
                                                        <el-button @click.prevent="batchImport">批量录入</el-button>
                                                    </div>
                                                </div>
                                                <hr style="height:1px;border-width:0;color:#929292;background-color:#929292" /> ​
                                                <el-row style="margin-bottom: 10px">
                                                <el-col :span="10">
                                                    <span class="summary">
                                                        仪表总数：<span class="summary-num">98</span> 已抄表：<span class="summary-num">30</span> 未抄表：<span class="summary-num">68</span>
                                                    </span>
                                                </el-col>
                                                <el-col :span="10" style="float: right">
                                                    <el-progress :text-inside="true" :stroke-width="20" :percentage="30.61"></el-progress>
                                                </el-col>
                                            </el-row>
                                                <el-table :data="interiorTableData" style="width: 100%;margin-bottom: 20px;" border>
                                                    <el-table-column prop="buildInfo" :show-overflow-tooltip="true" label="楼宇"></el-table-column>
                                                    <el-table-column prop="schedule" :show-overflow-tooltip="true" label="本月抄表进度"></el-table-column>
                                                    <el-table-column prop="amount" :show-overflow-tooltip="true" label="仪表总数"></el-table-column>
                                                    <el-table-column prop="alreadyRead" :show-overflow-tooltip="true" label="已抄表"></el-table-column>
                                                    <el-table-column prop="notRead" :show-overflow-tooltip="true" label="未抄表"></el-table-column>
                                                </el-table>
                                                <pagination/>
                                            </el-card>
                                        </el-col>
                                    </el-row>
                                    <!--<el-row align="middle">
                                        <el-col :span="24">
                                            <el-card class="box-card">
                                                <el-row>
                                                    <el-col :span="18" :offset="3" style="text-align: center">
                                                        <el-form :model="importQueryData2" label-width="120px" :rules="rules" size="medium" style="margin-bottom: 20px">
                                                            <el-form-item label="来访人姓名" prop="name">
                                                                <el-input v-model="importQueryData2.name" placeholder="请输入来访人姓名"></el-input>
                                                            </el-form-item>
                                                            <el-form-item label="被访人姓名" prop="interviewee">
                                                                <el-input v-model="importQueryData2.interviewee" placeholder="请输入被访人姓名"></el-input>
                                                            </el-form-item>
                                                            <el-form-item label="来访时间" prop="startDate">
                                                                <el-date-picker
                                                                        v-model="importQueryData2.startDate"
                                                                        type="datetime"
                                                                        placeholder="请选择来访时间"
                                                                        align="right"
                                                                        style="width: 100%;"
                                                                        :picker-options="pickerOptions">
                                                                </el-date-picker>
                                                            </el-form-item>
                                                            <el-form-item label="离开时间" prop="endDate">
                                                                <el-date-picker
                                                                        v-model="importQueryData2.endDate"
                                                                        type="datetime"
                                                                        placeholder="请选择离开时间"
                                                                        align="right"
                                                                        style="width: 100%;"
                                                                        :picker-options="pickerOptions">
                                                                </el-date-picker>
                                                            </el-form-item>
                                                            <el-form-item label="证件号码" prop="papers">
                                                                <el-input v-model="importQueryData2.papers" placeholder="请输入被访人姓名"></el-input>
                                                            </el-form-item>
                                                            <el-form-item label="联系电话" prop="phone">
                                                                <el-input v-model="importQueryData2.phone" placeholder="请输入被访人姓名"></el-input>
                                                            </el-form-item>
                                                            <el-form-item label="事由" prop="thing">
                                                                <el-input v-model="importQueryData2.thing" type="textarea" :autosize="{ minRows: 4}" placeholder="请输入备注"></el-input>
                                                            </el-form-item>
                                                            <el-button @click="showMessage(false)">取 消</el-button>
                                                            <el-button type="primary" @click="showMessage(true)">确 定</el-button>
                                                        </el-form>
                                                    </el-col>
                                                </el-row>

                                            </el-card>
                                        </el-col>
                                    </el-row>-->
                                </el-tab-pane>

                                <!--公摊仪表抄表选项卡-->
                                <el-tab-pane label="公摊仪表抄表" name="common">
                                    <el-row >
                                        <el-col :span="24">
                                            <el-card class="box-card">
                                                <div slot="header">
                                                    <el-row :gutter="20">
                                                        <el-col :span="24">
                                                            <el-form layout="inline" label-width="90px">
                                                                <el-row>
                                                                    <el-col :span="6">
                                                                        <el-form-item label="月份" label-width="50px">
                                                                        <el-date-picker v-model="commonQueryData.month" style="width: 100%;" type="month" placeholder="请选择月份"></el-date-picker>
                                                                        </el-form-item>
                                                                    </el-col>
                                                                    <el-col :span="6">
                                                                        <el-form-item label="仪表种类">
                                                                            <el-select v-model="commonQueryData.instrumentType" multiple placeholder="请选择仪表种类" style="width: 100%">
                                                                                <el-option v-for="item in instrumentTypeList" :key="item.value" :label="item.label" :value="item.value">
                                                                                </el-option>
                                                                            </el-select>
                                                                        </el-form-item>
                                                                    </el-col>
                                                                    <el-col :span="6" :offset="6" style="text-align: right">
                                                                        <div style="float: right">
                                                                            <el-button  type="primary">查询</el-button>
                                                                            <el-button plain>重置</el-button>
                                                                        </div>
                                                                    </el-col>
                                                                </el-row>
                                                            </el-form>
                                                        </el-col>
                                                    </el-row>
                                                </div>
                                                <div style="height: 50px">
                                                    <div style="float: right">
                                                        <el-dropdown trigger="click">
                                                        <span class="el-dropdown-link">
                                                            <el-button type="primary" class="el-icon-plus" style="margin-right:8px">导入抄表设计<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></el-button>
                                                        </span>
                                                            <el-dropdown-menu slot="dropdown">
                                                                <el-dropdown-item><el-button type="text" @click="downloadModel">下载抄表模板</el-button></el-dropdown-item>
                                                                <el-dropdown-item><el-button type="text" @click="importModel">导入抄表数据</el-button></el-dropdown-item>
                                                            </el-dropdown-menu>
                                                        </el-dropdown>
                                                        <el-button @click.prevent="commonBatchImport">批量录入</el-button>
                                                    </div>
                                                </div>
                                                <el-table :data="commonTableData" style="width: 100%;margin-bottom: 20px;" border>
                                                    <el-table-column prop="instrumentType" :show-overflow-tooltip="true" label="仪表种类"></el-table-column>
                                                    <el-table-column prop="count" :show-overflow-tooltip="true" label="总数"></el-table-column>
                                                    <el-table-column prop="alreadyRead" :show-overflow-tooltip="true" label="已抄表"></el-table-column>
                                                    <el-table-column prop="notRead" :show-overflow-tooltip="true" label="未抄表"></el-table-column>
                                                    <el-table-column prop="meterSchedule" :show-overflow-tooltip="true" label="抄表进度">
                                                        <template slot-scope="scope">
                                                            <el-progress :text-inside="true" :stroke-width="20" :percentage="scope.row.alreadyRead / scope.row.count * 100"></el-progress>
                                                        </template>
                                                    </el-table-column>
                                                    <el-table-column prop="alreadyApportion" :show-overflow-tooltip="true" label="已分摊"></el-table-column>
                                                    <el-table-column prop="notApportion" :show-overflow-tooltip="true" label="未分摊"></el-table-column>
                                                    <el-table-column prop="apportionSchedule" :show-overflow-tooltip="true" label="分摊进度">
                                                        <template slot-scope="scope">
                                                            <el-progress :text-inside="true" :stroke-width="20" :percentage="scope.row.alreadyApportion / scope.row.count * 100"></el-progress>
                                                        </template>
                                                    </el-table-column>
                                                </el-table>
                                                <pagination/>
                                            </el-card>
                                        </el-col>
                                    </el-row>
                                </el-tab-pane>

                            </el-tabs>
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>

        <!--房间抄表批量录入弹窗-->
        <el-dialog title="批量录入" :visible.sync="dialogInteriorBatchImport" center width="80%">
            <el-row>
                <el-col :span="22" :offset="1">
                    <el-form :model="interiorImportQueryData" label-width="40px">
                        <el-row>
                            <el-col :span="5">
                                <el-form-item label="月份" label-width="40px">
                                    <el-date-picker v-model="interiorImportQueryData.month" style="width: 100%;" type="month" placeholder="请选择月份"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item label="楼宇" label-width="70px">
                                    <el-select v-model="interiorImportQueryData.buildInfo" multiple placeholder="请选择楼宇" style="width: 100%">
                                        <el-option v-for="item in buildInfoList" :key="item.value" :label="item.label" :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间代码" label-width="110px">
                                    <el-input v-model="interiorImportQueryData.roomNo" style="width: 100%" placeholder="请输入房间代码"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="仪表种类" label-width="110px">
                                    <el-radio v-model="interiorImportQueryData.instrumentType" style="width: 30px;" v-for="item in instrumentTypeList" :label="item.value">{{item.label}}</el-radio>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="20">
                                <div class="warn-info">
                                    您当前操作是2020年6月房间仪表读数批量录入；温馨提示：翻页和离开页面之前一定要保存数据以免数据丢失！
                               </div>
                            </el-col>
                            <el-col :span="4">
                                <div style="float: right;text-align: right">
                                    <el-button type="primary"><i class="el-icon-search"></i>查询</el-button>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <el-table :data="interiorTableData2" border>
                        <el-table-column prop="buildInfo" :show-overflow-tooltip="true" label="楼宇" width="50%"></el-table-column>
                        <el-table-column prop="roomNo" :show-overflow-tooltip="true" label="房间代码" width="80%"></el-table-column>
                        <el-table-column prop="owner" :show-overflow-tooltip="true" label="业主名称" width="80%"></el-table-column>
                        <el-table-column prop="instrumentName" :show-overflow-tooltip="true" label="仪表名称" width="80%"></el-table-column>
                        <el-table-column prop="lastMonthReadDate" :show-overflow-tooltip="true" label="上月读数日期" width="100%"></el-table-column>
                        <el-table-column prop="currentReadDate" :show-overflow-tooltip="true" label="本月读数日期" width="100%"></el-table-column>
                        <el-table-column prop="lastNum" :show-overflow-tooltip="true" label="上期读数"></el-table-column>
                        <el-table-column prop="currentNum" :show-overflow-tooltip="true" label="本期读数">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.currentNum" :disabled="scope.row.status != 0" placeholder="请输入本期读数"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="rate" :show-overflow-tooltip="true" label="倍率" width="50%"></el-table-column>
                        <el-table-column prop="dosage" :show-overflow-tooltip="true" label="用量" width="80%"></el-table-column>
                        <el-table-column prop="statusStr" :show-overflow-tooltip="true" label="状态" width="80%"></el-table-column>
                    </el-table>
                    <el-form :model="readDate" label-width="auto" style="margin-top: 10px">
                        <el-row>
                            <el-col :span="7">
                                <el-form-item label="抄表日期">
                                    <el-date-picker v-model="interiorImportQueryData.month" style="width: 100%;" type="date" placeholder="请选择抄表日期"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <pagination/>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="operationMeter(false)">取 消</el-button>
                <el-button type="primary" @click="operationMeter(true)">保 存</el-button>
            </div>
        </el-dialog>

        <!--公摊抄表批量录入弹窗-->
        <el-dialog title="批量录入" :visible.sync="dialogCommonBatchImport" center width="80%">
            <el-row>
                <el-col :span="22" :offset="1">
                    <el-form :model="commonImportQueryData" label-width="70px">
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="月份" label-width="40px">
                                    <el-date-picker v-model="commonImportQueryData.month" style="width: 100%;" type="month" placeholder="请选择月份"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="楼宇">
                                    <el-select v-model="commonImportQueryData.buildInfo" multiple placeholder="请选择楼宇" style="width: 100%">
                                        <el-option v-for="item in buildInfoList" :key="item.value" :label="item.label" :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="仪表种类">
                                    <el-select v-model="commonImportQueryData.instrumentType" multiple placeholder="请选择仪表种类" style="width: 100%">
                                        <el-option v-for="item in instrumentTypeList" :key="item.value" :label="item.label" :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="20">
                                <div class="warn-info">
                                    您当前操作是2020年6月公摊仪表读数批量录入；温馨提示：翻页和离开页面之前一定要保存数据以免数据丢失！
                               </div>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <el-button style="float: right;" type="primary"><i class="el-icon-search"></i>查询</el-button>
                            </el-col>
                        </el-row>
                    </el-form>
                    <el-table :data="commonTableData2" border>
                        <el-table-column prop="instrumentName" :show-overflow-tooltip="true" label="仪表名称" width="80%"></el-table-column>
                        <el-table-column prop="lastReadDate" :show-overflow-tooltip="true" label="上次读数日期" width="100%"></el-table-column>
                        <el-table-column prop="currentReadDate" :show-overflow-tooltip="true" label="本次读数日期" width="100%"></el-table-column>
                        <el-table-column prop="lastNum" :show-overflow-tooltip="true" label="上期读数"></el-table-column>
                        <el-table-column prop="currentNum" :show-overflow-tooltip="true" label="本期读数">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.currentNum" :disabled="scope.row.status != 0" placeholder="请输入本期读数"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="rate" :show-overflow-tooltip="true" label="倍率" width="80%"></el-table-column>
                        <el-table-column prop="dosage" :show-overflow-tooltip="true" label="用量" width="100%"></el-table-column>
                        <el-table-column prop="statusStr" :show-overflow-tooltip="true" label="状态" width="100%"></el-table-column>
                    </el-table>
                    <el-form :model="readDate" label-width="auto" style="margin-top: 10px">
                        <el-row>
                            <el-col :span="7">
                                <el-form-item label="抄表日期">
                                    <el-date-picker v-model="commonImportQueryData.month" style="width: 100%;" type="date" placeholder="请选择抄表日期"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <pagination/>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="operationMeter(false)">取 消</el-button>
                <el-button type="primary" @click="operationMeter(true)">保 存</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'instrumentMeter',
        data(){
            return {
                activeName: "interior",
                dialogInteriorBatchImport: false,
                dialogCommonBatchImport: false,
                interiorQueryData: {
                    month: '',
                    buildInfo: [],
                    instrumentType: 0,
                },
                commonQueryData: {
                    month: '',
                    buildInfo: [],
                    instrumentType: 0,
                },
                commonImportQueryData: {
                    month: '',
                    buildInfo: '',
                    instrumentType: '',
                    roomNo: '',
                },
                interiorImportQueryData: {
                    month: '',
                    buildInfo: '',
                    instrumentType: 0,
                    roomNo: '',
                },
                readDate: '',
                buildInfoList: [
                    {
                        label: 'A区',
                        value: 'A区'
                    },
                    {
                        label: 'B区',
                        value: 'B区'
                    }
                ],
                instrumentTypeList: [
                    {
                        label: '电表',
                        value: 0
                    }
                    ,{
                        label: '水表',
                        value: 1
                    }
                    ,{
                        label: '气表',
                        value: 2
                    }
                ],
                interiorTableData: [
                    {
                        buildInfo: '1栋',
                        schedule: '0.00%',
                        amount: '1',
                        alreadyRead: '0',
                        notRead: '1'
                    },
                    {
                        buildInfo: '1栋',
                        schedule: '20.00%',
                        amount: '5',
                        alreadyRead: '1',
                        notRead: '4'
                    },
                    {
                        buildInfo: '1号楼',
                        schedule: '0.00%',
                        amount: '1',
                        alreadyRead: '0',
                        notRead: '1'
                    }
                ],
                interiorTableData2: [
                    {
                        buildInfo: 'A区',
                        roomNo: 'A613',
                        owner: '张三',
                        instrumentName: '电表',
                        lastMonthReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '0.00',
                        currentNum: '0.00',
                        rate: '1.00',
                        dosage: '0.00',
                        status: 1,
                        statusStr: '初始录入',
                    },
                    {
                        buildInfo: 'A区',
                        roomNo: 'A613',
                        owner: '张三',
                        instrumentName: '电表',
                        lastMonthReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '0.00',
                        currentNum: '',
                        rate: '1.00',
                        dosage: '0.00',
                        status: 0,
                        statusStr: '未录入',
                    },
                    {
                        buildInfo: 'A区',
                        roomNo: 'A613',
                        owner: '张三',
                        instrumentName: '电表',
                        lastMonthReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '0.00',
                        currentNum: '',
                        rate: '1.00',
                        dosage: '0.00',
                        status: 0,
                        statusStr: '未录入',
                    },
                ],
                commonTableData: [
                    {
                        instrumentType: '电表',
                        count: 2,
                        alreadyRead: 2,
                        notRead: 0,
                        alreadyApportion: 2,
                        notApportion: 0,
                    },
                    {
                        instrumentType: '水泵电表',
                        count: 10,
                        alreadyRead: 5,
                        notRead: 5,
                        alreadyApportion: 6,
                        notApportion: 4,
                    },
                    {
                        instrumentType: '水表',
                        count: 2,
                        alreadyRead: 0,
                        notRead: 2,
                        alreadyApportion: 0,
                        notApportion: 2,
                    },
                ],
                commonTableData2: [
                    {
                        instrumentName: '1',
                        lastReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '0.00',
                        currentNum: '0.00',
                        rate: '1.00',
                        dosage: '0.00',
                        status: 1,
                        statusStr: '已分摊',
                    },
                    {
                        instrumentName: '123',
                        lastReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '500.00',
                        currentNum: '900.00',
                        rate: '1.00',
                        dosage: '400.00',
                        status: 1,
                        statusStr: '已分摊',
                    },
                    {
                        instrumentName: '电表总表',
                        lastReadDate: '2020/06/11',
                        currentReadDate: '2020/06/11',
                        lastNum: '500.00',
                        currentNum: '',
                        rate: '1.00',
                        dosage: '400.00',
                        status: 0,
                        statusStr: '未分摊',
                    },
                ],
            }
        },
        methods: {
            handleClick(tab, event) {
            },
            downloadModel(){
                this.info('下载模板成功');
            },
            importModel(){
                this.info('导入数据');
            },
            batchImport(){
                this.dialogInteriorBatchImport = true
            },
            commonBatchImport(){
                this.dialogCommonBatchImport = true
            },
            operationMeter(flag){
                this.dialogInteriorBatchImport = false
                this.dialogCommonBatchImport = false
                if(flag){
                    this.$message({
                        type: 'success',
                        message: '保存成功!'
                    });
                }
            }
        }
    }

</script>

<style scoped>
    .summary {
        font-size: 13px;
    }
    .summary-num {
        color: #0000ff;
        font-weight: bolder;
    }
    .warn-info {
        color: #0000ff;
        font-weight: bolder;
        text-align: left;
        font-size: 13px;
        height: 40px;
    }
</style>