<template>
<div class="myCourse">
  <van-nav-bar
  left-text="课程"
  left-arrow
  fixed="true"
  @click-left="onClickLeft"
/>
  <van-tabs v-model="active" sticky v-if="showCourse">
    <van-tab title="我的课程">
      <div v-for="item in historyList" :key="item.id" style="width:90%;height:75px;padding-top:5px;margin-left:5%">
        <van-cell  @click="review(item.id)">
            <van-card
              :title="item.headTitle"
              :desc="item.Content"
              tag="火热"
              :thumb="picIp+item.image"
            />
            </van-cell>
      </div> 





      <van-row gutter="20">
          <van-col span="12"  >
            <van-card
                  desc="描述信息"
                  title="商品标题"
                  thumb="https://img.yzcdn.cn/vant/ipad.jpeg"
                />
          </van-col>
          <van-col span="12">
            <van-card
                  desc="描述信息"
                  title="商品标题"
                  thumb="https://img.yzcdn.cn/vant/ipad.jpeg"
                />
          </van-col>
      </van-row>
    </van-tab>
  </van-tabs>
 
</div>
</template>
<script>
import Vue from 'vue';
// import { Swipe, SwipeItem } from 'vant';
import { Card } from 'vant';
import { Col, Row } from 'vant';

Vue.use(Col);
Vue.use(Row)
Vue.use(Card);
// Vue.use(Swipe);
// Vue.use(SwipeItem);
export default {
  name: 'MyCourse',
  data () {
    return {
      checked:false, 
      checked1:true ,
      checked2:false,
      fixed:true, 
      showCourse:true,
      historyList:[],
      picIp:'http://localhost:8080/pic/',

    }
    },
    created:{




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
.myCourse{
  background:white;
  margin-top:50px;
}
.myCourse>h3{
  height:25px;
  line-height:50px;
  padding-left:15px;
}
</style>