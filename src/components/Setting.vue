<template>
<div >
   <van-nav-bar title="口袋家教学生" @click-left="onClickLeft">
  <template #left>
    <van-icon name="arrow-left" size="18"/>
  </template>
  </van-nav-bar>
<div class="content">
<div class="img">
    <router-link to="/MyInfo">
        <img :src="image" alt="">
        <van-icon name="records" size="30"/>
      </router-link>
      <p>{{user}}</p>
</div>

 <div class="icon">
   <div class="left">
        <p >累计学习</p>
        <p >{{total}}  Day</p>
   </div>
   <div class="flow" >
     <!-- <img src="../assets/logo.png" alt="" style="width:75px;height:75px;"> -->
   </div>
   <div class="right">
        <p >连续学习</p>
        <p>{{count}}  Day</p>
   </div>
    <div class="router">
      <router-link to="/Search">
        <img src="../assets/jump_1.jpg" alt="" style="height:150">
      </router-link>
    </div>
 </div>
</div>
<div class="setting">
    <van-cell-group >
         <van-cell :title="item.title" is-link :to="item.compoents" v-for="item in showList" :key="item.title"/>
    </van-cell-group>
</div>
</div>
</template>
<script>
import Vue from 'vue';
import { Cell, CellGroup } from 'vant';
Vue.use(Cell);
Vue.use(CellGroup);
export default {
  name: 'Setting',
  data () {
    return {
            showList:[{"title":"我的订阅","compoents":"/Favor"},
            {"title":"设置","compoents":"/Settings"},
            {"title":"家长入口","compoents":"/Entrance"},
            {"title":"帮助与反馈","compoents":"/Help"},
            {"title":"关于我们","compoents":"/About"},
            ],
            count:15,
            total:23,
            fixed:true,
            user:"Qijw",
            image:'',
            picIp:"http://localhost:8080/pic/",
    }
    },
    created(){
      let username = localStorage.getItem("username");
      let image = localStorage.getItem("img");
      if(username != null){
        this.user = username;
        this.image = this.picIp+image;
      }


    }
    ,methods:{
      onClickLeft(){
        this.$router.push("/Main");
      },
      onClickRight(){

      }
    }
}
</script>
<style scoped>
.content{
  position:relative;
}
.content .icon{
  height:350px;
  width:100%;
  background:#ffdb3d;
  text-align:center;
}
.content .icon .flow{
  height:170px;
  margin-top:-20px;
  width:150px;
  float: left;
  background: url("../assets/score_1.jpg");
}
.content .icon .left{
  float:left;
  height:75px;
  width:90px;
  margin-top:35px;
  text-align:right;
  padding-right:15px;
}
.content .icon .right{
  float:left;
  height:75px;
  margin-top:35px;
  width:75px;
  padding-left:15px;
  text-align:left;
}
.content .icon .router{
  position: absolute;
    left: 105px;
    top: 285px;
}
.content .icon .router img{
  height:100px;
  width:152px;
}
.content .icon p{
    font-size:1px;
    font-weight:none;
    display:inline-block;
}
.img{
  padding-top:10px;
  text-align:center;
  background:#fef08f;
  position: relative;
  height:100px;
}
.content .img P{
  position: relative;
    margin-top: -3px;
}
.img img{
  height:75px;
  width:75px;
  border-radius:50px;
}
.setting{
  margin-top:-50px;
  position:fixed;
}
.setting .van-cell-group>.van-cell{
  background:#ffbb00;
  font-weight:bold
}
.van-cell-group{
  height:225px;
  width:375px;
}
.van-cell{
  height:45px;
  width:370px;
}
.van-nav-bar{
background:linear-gradient(to top,#fff,#fef08f);
}


</style>