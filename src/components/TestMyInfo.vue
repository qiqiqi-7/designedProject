<template>
<div >
   <div class="myInfo">
    <van-card
      @click="dopose"
    >
      <template #thumb>
        <van-image
          radius="10px"
          width="75px"
          height="75px"
          :src="image"
          @click="login"
        />
      </template>
      <template #title>
        <span style="font-size:20px;font-weight:bold">{{username}}</span>
      </template>
      <template #desc>
        <div class="header-describ ">
          <div  v-if="islogin" class="van-multi-ellipsis--l2">家教网账号：{{user.userCode}}</div>
        </div>
      </template>
      <template #num>
         <van-icon name="arrow" size="18"/>
      </template>
    </van-card>

    <div  class="groupBody">
        <van-cell-group >
            <van-cell is-link center @click="jumpToCollect" >
               <template #title>
                 <div class="icon">
                    <van-icon size="25" name="http://localhost:3700/pic/collect.png" />
                  </div>
                  <span>我的收藏</span>
              </template>
            </van-cell>
             <van-cell title="青少年模式" icon="friends" is-link />
        </van-cell-group>

        <van-cell-group class="test">
            <van-cell title="设置" icon="setting" is-link />
            <van-cell title="联系客服" icon="service" is-link />    
            <van-cell is-link icon="coupon" >查看个人信息</van-cell>                
        </van-cell-group>   
  </div>
</div>
</div>
</template>
<script>
import Vue from 'vue';
import { Cell, CellGroup } from 'vant';
import { Overlay } from 'vant';
import { Dialog } from 'vant';
import {Toast} from 'vant';
Vue.use(Overlay);
Vue.use(Cell);
Vue.use(CellGroup);
export default {
  name: 'TestMyInfo',
  data () {
    return {
             showList:[{"title":"头像","compoents":"/Favor"},
            {"title":"名字","compoents":"/Settings"},
            {"title":"ID账号","compoents":"/Entrance"},
            {"title":"我的二维码","compoents":"/Help"},
            ],
            fixed:true,
            show:false,
            casual:"",
            username:'dsasda',
            image:'https://img01.yzcdn.cn/vant/cat.jpeg',
            picIp:"http://localhost:3700/pic/",
            password:"",
            password1:"",
            birthday:'',
            phoneNumber:'',
            email:'',
            pattern:'/^1[3456789]\d{9}$/',
            islogin:false,
            user:{},
            user1:{"username":"dsadas"},
            user:JSON.parse(localStorage.getItem("user")),
    }
    },created(){
      
        this.user = JSON.parse(localStorage.getItem("user"));
        if(this.user == null){
          this.image = "http://localhost:3700/pic/emptyIcon.png";
          this.username = "点击头像进行登录";
          console.log("abcd");
        }else{
          this.islogin = true;
          this.username = this.user.username;
          this.image = this.picIp+this.user.headicon;
        }
    }
    ,methods:{
      jumpToCollect(){

        if(this.test()== false)return;
        Toast("正准备体跳转到我的收藏界面");
        this.$router.push({name:"Collect",params: {userCode: this.user.userCode}});
      },
      login(){
        if(this.islogin == true){
          return;
        }

        Toast("正在进行登录验证");
        // 将账号信息显示
        this.islogin  =!this.islogin;
        this.$router.push("/Login");

      },
      onClickLeft(){
          this.$router.push('Setting')
      },
      alter1(){
          this.show = false;
          this.user = this.casual;
      },
       showPopup() {
      this.show = true;
      },
      onSubmit(){
          let studentId = localStorage.getItem("studentId");
          this.axios.post("c?op=updatePerson&studentId="+studentId+"&birthday="+this.birthday+"&email="+this.email+"&phoneNumber="+this.phoneNumber).then((response)=>{
            Toast('完善成功');
        })
      },
      dopose(){
        console.log("abc");
      },
      test(){
        if(this.user == null){
          Toast("请先登录！")
          return false;
        }else 
          return true;
      }
    }
}
</script>
<style scoped>
.van-card{
  background: #fff;
  padding-top:50px;
}
.groupBody{
  margin-top:10px;
}
.van-cell-group{
  margin-top:10px;
  background:#f7f8fa;
}
.myInfo{
  background: #f7f8fa;  
  height:667px;
}
.header-describ{
  width: 100%;
  height: 45px;
}
.test .van-cell{

  margin-top:5px;
}
</style>