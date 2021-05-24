<template>
<div class="content">
  <van-nav-bar title="我的课程" left-text="返回" @click-left="onClickLeft">
       <template #left>
          <van-icon name="arrow-left" size="18"/>
      </template>
    </van-nav-bar>
  <van-tabs v-model="active" sticky>
  <van-tab v-for="(index,cate1) in title" :key="cate1">
    <template #title> <van-icon name="comment-o" />{{index}} </template>
  <van-cell v-for="(item,cate) in photo" v-if="cate >= cate1" :key="cate">
    <van-card
  :desc="item.title"
  :thumb="item.images"
/>
  </van-cell>
  </van-tab>


</van-tabs>
    
</div>
</template>
<script>
import Vue from 'vue';
import {Toast} from 'vant';
import { Tab, Tabs } from 'vant';

Vue.use(Tab);
Vue.use(Tabs);
Vue.use(Toast);
export default {
  name: 'Content',
  data () {
    return {
      value:"",
      active:0,
      cord:[],  
      title:["全部","小学","初中","高中","大学"],
      photo:[{"images":"https://edu-image.nosdn.127.net/0a6e7a0aa2844a93ab362b829cf1897c.jpg?imageView&quality=100&thumbnail=2943y2617",
"title":"高分写作：考研高分写作套路大揭秘"},
{"images":"https://edu-image.nosdn.127.net/9b3b1a18c36945c6887f062d4ae613b8.jpg?imageView&quality=100",
"title":"教师资格：还在为教资幼儿笔试发愁"},
{"images":"https://edu-image.nosdn.127.net/41cb435fb88d4bb2bdf542674ebf9e49.jpg?imageView&quality=100&thumbnail=1385y1495",
"title":"阅读提升：唐叔独家考研阅读逻辑提分法则"},
{"images":"https://edu-image.nosdn.127.net/8441b06bc39248f5a408c405f794cf00.png?imageView&quality=100",
"title":"考研数学：考研数学全年规划"},
{"images":"https://edu-image.nosdn.127.net/de3862dfb7844229b4a4a80d9ab5b7bf.png?imageView&quality=100&thumbnail=267y300",
"title":"四六级：四六级一战而过的核心方法论"},
{"images":"https://edu-image.nosdn.127.net/FD2FE06EDED557A8E8601663A5343065.png?imageView&thumbnail=582y582&quality=100",
"title":"考研英语：一节课打通你的英语任督二脉"},
{"images":"https://edu-image.nosdn.127.net/d9d4a9c275ac45dcb68c7089c01b303d.png?imageView&quality=100&thumbnail=180y180",
"title":"抽考研图书：22考研抢跑必听两节课—全程规划"},
{"images":"https://edu-image.nosdn.127.net/8441b06bc39248f5a408c405f794cf00.png?imageView&quality=100",
"title":"考研数学：考研数学近期规划及方法指导"},]
    }
  },
  created(){


  },
  methods:{
   onClickLeft(){
        this.$router.push("Main");
   }
  }
}
</script>
<style scoped>
.content span{
  font-size:16px;
  color:#555;
}
.van-icon{
  top:2px;
}
.van-tan{
  font-size:14px;
}

.van-nav-bar{
  background:linear-gradient(to top,#fff,#fef08f);
}
</style>