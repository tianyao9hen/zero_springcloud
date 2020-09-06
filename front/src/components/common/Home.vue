<template>
  <div class="wrapper">
    <v-head></v-head>
    <v-sidebar></v-sidebar>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <v-tags></v-tags>
      <div class="content">
        <transition name="move" mode="out-in">
          <keep-alive :include="tagsList">
            <router-view></router-view>
          </keep-alive>
        </transition>
      </div>
    </div>
    <el-dialog title="密码修改" :visible.sync="isShow" v-dialogDrag :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="原密码" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth">
            <el-input show-password v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="hideDailog">取 消</el-button>
        <el-button type="primary" @click="hideDailog">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import vHead from "./Header.vue";
import vSidebar from "./Sidebar.vue";
import vTags from "./Tags.vue";
import bus from "./bus";
import { mapGetters } from "vuex";//注册 action 和 state
import { mapActions } from "vuex";//注册 action 和 state
export default {
  data() {
    return {
      tagsList: [],
      collapse: false,
      form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
    };
  },
  computed: {
      //在这里映射 store.state.count，使用方法和 computed 里的其他属性一样
      //这里的isShow是修改密码弹窗是否显示，当前值为false
    ...mapGetters("dailog", {
      isShow: "isShow"
    })
  },
  methods: {
      //在这里映射 store.state.count，使用方法和 computed 里的其他属性一样
    ...mapActions("dailog", ["hideDailog", "showDailog"]),
    handleClose(){
       this.$confirm('确认关闭？')
          .then(_ => {
            this.hideDailog();
            //this.$store.dispatch('dailog/hideDailog')
          }).bind(this)
          .catch(_ => {});
    }
  },
  components: {
    vHead,
    vSidebar,
    vTags
  },
  created() {
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });

    this.$store.dispatch('auth/getUserEntityByToken').then(res => {
      console.log('123123');
      if(res.success){
        // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
        bus.$on("tags", msg => {
          let arr = [];
          for (let i = 0, len = msg.length; i < len; i++) {
            msg[i].name && arr.push(msg[i].name);
          }
          this.tagsList = arr;
        });
      }else{
        this.$router.push("/login");
      }
    }).catch(err => {
      console.log(err);
      this.$router.push("/login");
    })
  }
};
</script>
<style scoped>
  .content-box{
    top: 60px;
  }
</style>
