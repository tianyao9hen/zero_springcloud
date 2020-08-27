<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <label style="font-size: 20px;">{{treeName}}</label>
                <el-button style="float: right;" type="primary" @click="editInfo=true">编辑</el-button>
            </div>
            <el-row>
                <el-col :span="12">
                    <label for="mc">名称：</label>
                    <span id="mc">{{treeName}}</span>
                </el-col>
                <el-col :span="12">
                    <label for="dz">地址：</label>
                    <span id="dz">山东省济南市高新区</span>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <label for="jzmj">建筑面积：</label>
                    <span id="jzmj">12000m²</span>
                </el-col>
                <el-col :span="12">
                    <label for="lhmj">绿化面积：</label>
                    <span id="lhmj">2000m²</span>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <label for="symj">使用面积：</label>
                    <span id="symj">11000m²</span>
                </el-col>
                <el-col :span="12">
                    <label for="gtmj">公摊面积：</label>
                    <span id="gtmj">1000m²</span>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <label for="lhl">绿化率：</label>
                    <span id="lhl">15%</span>
                </el-col>
                <el-col :span="12">
                    <label for="rjl">容积率：</label>
                    <span id="rjl">10%</span>
                </el-col>
            </el-row>
        </el-card>
        <el-dialog
                title="编辑"
                :visible.sync="editInfo"
                center>
            <el-form :model="form" label-width="80px" size="medium">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="名称">
                            <el-input v-model="form.mc" placeholder="请填写名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="地址">
                            <el-input v-model="form.dz" placeholder="请填写地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="建筑面积">
                            <el-input type="number" v-model="form.jzmj" placeholder="请填写建筑面积"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="绿化面积">
                            <el-input type="number" v-model="form.lhmj" placeholder="请填写绿化面积"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="使用面积">
                            <el-input type="number" v-model="form.symj" placeholder="请填写使用面积"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="公摊面积">
                            <el-input type="number" v-model="form.gtmj" placeholder="请填写公摊面积"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="绿化率">
                            <el-input type="number" v-model="form.lhl" placeholder="请填写绿化率"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="容积率">
                            <el-input type="number" v-model="form.rjl" placeholder="请填写容积率"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showMessage(false)">取 消</el-button>
                <el-button type="primary" @click="showMessage(true)">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    props:["treeId","treeName"],
    data() {
        return {
            editInfo:false,
            form: {
                mc:'',
                dz:'',
                jzmj:'',
                lhmj:'',
                symj:'',
                gtmj:'',
                lhl:'',
                rjl:'',
            },
        };
    },
    methods: {
        showMessage(flag){
            this.editInfo = false
            if(flag){
                this.$message({
                    type: 'success',
                    message: '保存成功!'
                });
            }
        }
    }
};
</script>
<style scoped>
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}
.box-card{
    height: 550px;
}
.el-row {
    margin-bottom: 50px;
}
</style>