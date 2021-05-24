<template>
<div >
   <div class="myInfo">
    <van-card
      @click="dopose"
      :desc="username"
      :title="username"
    >
      <template #thumb>
        <van-image
          radius="10px"
          width="75px"
          height="75px"
          :src="image"
        />
      </template>
      <template #num>
         <van-icon name="arrow" size="18"/>
      </template>
    </van-card>
    <van-cell-group >
      <van-cell title="单元格" icon="location-o" is-link />
         <van-cell title="头像">
                <template #extra>
                    <img :src="image" alt="" style="border-radius:10px;height:50px;width:50px;">
                     <van-icon name="arrow" color="grey" size="20px"/>
                </template>
        </van-cell>
        <van-cell title="名字">
                <template #extra>
                    <van-button type="primary" color="white" :text="username" style="margin-top:5px;"/>
                     <van-icon name="arrow" color="grey" size="20px"/>
                </template>
        </van-cell>
        <van-cell title="ID账号">
                <template #extra>
                    <span>17371435883</span>
                </template>
        </van-cell>
        <van-cell title="我的二维码">
                <template #extra>
                    <img src="../assets/Qrcode.jpg" alt="" style="    height: 115px;width: 113px;margin-right: 105px;margin-top: 35px;">
                </template>
        </van-cell>
        <van-cell is-link @click="showPopup" >查看个人信息</van-cell>
        
        <van-popup v-model="show" position="bottom" :style="{ height: '30%' }" >完善个人信息
           <van-form @submit="onSubmit">
              <van-field
                v-model="birthday"
                type="text"
                name="出生年月"
                label="出生年月"
                placeholder="请输入出生年月"
                :rules="[{ required: true, message: '请输入正确的格式' }]"
              />
              <van-field
                v-model="phoneNumber"
                type="text"
                name="手机号"
                label="手机号"
                placeholder="请输入手机号"
                :rules="[{ required: true, message: '请输入有效长度的手机号' }]"
              />
              <van-field
                v-model="email"
                type="text"
                name="电子邮箱"
                label="电子邮箱"
                placeholder="请输入电子邮箱"
                :rules="[{ required: true, message: '请输入有效电子邮箱' }]"
              />
              <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit">
                  提交
                </van-button>
              </div>
          </van-form>
        </van-popup>
    </van-cell-group>
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
  name: 'MyInfo',
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
            username:'',
            image:'',
            picIp:"http://localhost:8081/pic/",
            password:"",
            password1:"",
            birthday:'',
            phoneNumber:'',
            email:'',
            pattern:'/^1[3456789]\d{9}$/',
    }
    },created(){
        var user = JSON.parse(localStorage.getItem("user"));
        if(user == null){
            Dialog.alert({
          title: '提示',
          message: '请先完成登录',
        }).then(() => {
          this.$router.push("/Regist");
        })
        }else{
          this.image = this.picIp + user.image;
          this.username = user.username;
        }
    }
    ,methods:{
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
      }
    }
}
</script>
<style scoped>
.myInfo{
  background:#ffffff;
  padding-top: 50px;
}
.myInfo .van-cell-group  .van-cell .van-icon-arrow{
  font-size:15px;
  margin-top:20px;
}
.van-nav-bar{
background:#f7f8fa
}
.wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }
.block {
    width: 250px;
    height: 250px;
    background-color: #fff;
}
.van-button__text{
  color: black;
}
.van-button--normal{
    margin-top: -10px;
    margin-right: -15px;
}

</style>