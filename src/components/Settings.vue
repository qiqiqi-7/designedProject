<template>
<div class="settings">
   <van-nav-bar title="设置" left-text="返回" @click-left="onClickLeft">
  <template #left>
    <van-icon name="arrow-left" size="18"/>
  </template>
  </van-nav-bar>
  <div>
    <van-cell-group>
      <van-cell title="账号与安全" class="">
          <template #extra>
              <van-icon name="arrow" color="red" size="20"/>
          </template>
      </van-cell>
      <van-cell title="非WIFI状态提醒">
          <template #extra>
              <van-switch size="20px" v-model="checked" active-color="#07c160" inactive-color="rgb(120, 136, 127)" />
          </template>
      </van-cell>
      <van-cell title="护眼提醒">
            <template #extra>
              <van-switch size="20px" v-model="checked1" active-color="#07c160" inactive-color="rgb(120, 136, 127)" />
          </template>
      </van-cell>
      <van-cell title="清除缓存">
        <template #extra>
              <van-icon name="arrow" color="red" size="20"/>
          </template>
      </van-cell>
      <van-cell title="消息推送">
          <template #extra>
              <van-switch size="20px" v-model="checked2" active-color="#07c160" inactive-color="rgb(120, 136, 127)" />
          </template>
      </van-cell>
    </van-cell-group>
  </div>
  <van-button type="danger" @click="loginOut">退出登录</van-button>

</div>
</template>

<script>
// import Vue from 'vue';
// import { Swipe, SwipeItem } from 'vant';
// import { Card } from 'vant';

// Vue.use(Card);
// Vue.use(Swipe);
// Vue.use(SwipeItem);
export default {
  name: 'Settings',
  data () {
    return {
      checked:false, 
      checked1:true ,
      checked2:false 
    }
    },
    methods:{
      onClickLeft(){
        this.$router.push("/Setting")
      },
      loginOut(){
        localStorage.removeItem("username");
        localStorage.removeItem("img");
        this.$router.push("/regist");
      }
  }
}
</script>
<style scoped>
.van-nav-bar{
  background:linear-gradient(to top,#fff,#fef08f);
}
.van-button--danger{
  width:100%;
  position:absolute;
  bottom:0px;
  background:#af0a1cc7;
}
</style>