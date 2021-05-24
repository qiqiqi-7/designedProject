<template>
  <div id="app">
    <div class="middle">
          <router-view/>
    </div>
    <van-tabbar  active-color="#blue" v-model="active">
      <van-tabbar-item to="/" icon="wap-home">主页</van-tabbar-item>
      <van-tabbar-item  to="/MyCourse" icon="coupon">我的学习</van-tabbar-item>
      <van-tabbar-item   to="/TestMyInfo" icon="manager">账号</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>

export default {
  name: 'App',
    data(){
    return {
      active:0,
      value:'',
      fixed:'fixed',
      judge:true,
    };
  },
  created(){
   // this.$router.push("/Regist");
  },
  methods: {
    onClickLeft(){

    },
    onClickRight(){

    }
  },
  
}
</script>
<style>
#app{
  height:667px;
  width:375px;
  background:#ffffff;
}

</style>
