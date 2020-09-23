<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse"
      background-color="#3E7DEB" text-color="#F0F5FA" active-text-color="#FFFFFF"
      unique-opened router>
      <template v-for="item in menuData">
        <template v-if="item.childs && item.childs.length > 0">
          <el-submenu :index="item.id" :key="item.id">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.name }}</span>
            </template>
            <template v-for="child in item.childs">
              <!--<template v-if="child.childs">
                <el-submenu :index="child.sort" :key="child.sort">
                  <template slot="title">
                    <span slot="title">{{ child.name }}</span>
                  </template>
                  <el-menu-item v-for="(threeItem,i) in child.childs" :key="i"
                    :index="threeItem.url">{{  threeItem.name }}</el-menu-item>
                </el-submenu>
              </template>-->
              <!--<template v-else>-->
                <el-menu-item :index="child.url" :key="child.url">
                  <div class="space-circular"></div>
                  <span slot="title">{{ child.name }}</span>
                </el-menu-item>
              <!--</template>-->
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.url" :key="item.url" style="height: 56px;line-height: 56px">
            <i :class="item.icon" style="background-color:transparent !important;border:none;line-height: 56px"></i>
            <span slot="title">{{item.name }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "../common/bus";
import { getSysmenu } from '../../api/api'
export default {
  data() {
    return {
      // url: "./json/menu/menuData.json",
      collapse: false,
      menuItems: [],
      menuData: [],
    };
  },
  methods: {

  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
    // this.getMenuData("系统管理");
    //   this.getMenuData();
  },
  mounted() {
    //let data = this.$store.getters.getUserType;
    //this.menuData = this.$store.getters.getPropertyMenu;
    this.menuData = this.$store.getters["auth/getPropertyMenu"];
  },
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
}
.space-circular {
  width: 3px;
  margin-right: 4px;
  display: inline-block;
  height: 3px;
  background: transparent;
  border-radius: 50%;
  border: 1px solid white;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 240px;
}
.sidebar > ul {
  height: 100%;
}
.el-submenu__title {
  height: 42px;
  line-height: 42px;
}
/*系统首页item*/
.el-menu-item {
  height: 42px;
  line-height: 42px;
  background: #3E7DEB;
}
.el-menu-item:hover {
  border-radius: 5px;
  border: 1px #6f7180 solid;
  color: white !important;
}
/*鼠标放置小圆点颜色*/
.el-menu-item:hover > :first-child {
  border: 1px solid #ffffff;
  background-color: #ffffff;
}
.el-menu-item:focus {
  border-radius: 5px;
  margin: 0px 10px 5px 5px;
  border: 1px #6f7180 solid;
}
</style>
