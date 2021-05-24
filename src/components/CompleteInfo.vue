<template>
<div >
  <van-nav-bar >
    <template #left>
      <van-icon name="arrow-left" size="18"/>&nbsp;完善个人信息
    </template>
  </van-nav-bar>
   <div class="myInfo">
     <van-form @submit="onSubmit">
        <van-field
          readonly
          clickable
          name="area"
          :value="value"
          label="地区选择"
          placeholder="点击选择省市区"
          @click="showArea = true"
        />
        <van-popup v-model="showArea" position="bottom">
          <van-area
            :area-list="areaList"
            @confirm="onConfirm"
            @cancel="showArea = false"
          />
        </van-popup>
        <van-field
          v-model="detLocation"
          name="详细地址"
          label="详细地址"
          placeholder="小区楼栋/乡村名称"
          :rules="[{ required: true,  }]"
        />
        <van-field
          v-model="realname"
          name="用户名"
          label="真实姓名"
          placeholder="请填写真实姓名"
          :rules="[{ required: true, }]"
        />
        <van-field
          v-model="birthday"
          name="出生日期"
          label="出生日期"
          placeholder="请输入出生日期(格式为1970-01-01)"
          :rules="[{ validator:validation,required: true,message:'输入日期格式有问题请重试'  }]"
        />
        <van-field
          v-model="phone"
          name="手机号"
          label="手机号"
          placeholder="请填写手机号码"
          :rules="[{ validator: asyncValidator,message:'请输入正确的手机号'}]"
        />
        <van-field name="radio" label="性别">
          <template #input>
            <van-radio-group v-model="radio" direction="horizontal">
              <van-radio name="0">男</van-radio>
              <van-radio name="1">女</van-radio>
            </van-radio-group>
          </template>
        </van-field>
        <van-field
          v-model="email"
          name="email"
          label="邮箱地址"
          placeholder="请填写邮箱地址"
          :rules="[{ required: true,  }]"
        />
        <van-field
          v-model="vericode"
          name="vericode"
          label="验证码"
          placeholder="点击获取验证码"
          :rules="[{ required: true,  }]"
        >
          <template #button>
            <van-button native-type="button" type="primary"  @click="getVericode">获取我的验证码</van-button>
          </template>
        </van-field>
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
     </van-form>


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
            vericode:'',
            fixed:true,
            casual:"",
            picIp:"http://localhost:8081/pic/",
            birthday:'',
            phone:'',
            email:'',
            pattern:'/^1[3456789]\d{9}$/',
            value:'',
            showArea:false,
            areaList:{ 
              province_list: {
              "110000": '北京市',
              "120000": '天津市',
              },
              city_list: {
                "110100": '北京市',
                "120100": '天津市',
              },
              county_list: {
                "110101": '东城区',
                "110102": '西城区',
                // ....
              },},  
            detLocation:'',
            realname:'',
            radio:'-1',
            user: JSON.parse(localStorage.getItem("user")),
    }
    },created(){
        this.setAreaList();
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
          //this.$router.push("/NewIndex");
          this.axios.post("t-user/compelteInfo",
          {
            userCode:this.user.userCode,
            userName:this.realname,
            userLocation:this.value+'-'+this.detLocation,
            userPhone:this.phone,
            userSex:this.radio,
            userEmail:this.email,
            userType:this.user.type,
            userBirthdate:this.birthday,
            vericode:this.vericode
          }).then(response=>{
            if(response.data.result == "error"){
              Toast("网络线路正忙，请重试");
            }else{
              Toast("完善个人信息成功");
              this.$router.push("/NewIndex");
            }
          })
          /*
          let studentId = localStorage.getItem("studentId");
          this.axios.post("c?op=updatePerson&studentId="+studentId+"&birthday="+this.birthday+"&email="+this.email+"&phoneNumber="+this.phoneNumber).then((response)=>{
            Toast('完善成功');
        })*/
      },
      onConfirm(values) {
        this.value = values
          .filter((item) => !!item)
          .map((item) => item.name.replace(" ",""))
          .join('-');
        this.showArea = false;
      },
      setAreaList(){
        this.axios.get("t-provinces/list").then(response=>{
              this.areaList = response.data;
        })
      },
      asyncValidator(val){
        return new Promise((resolve) => {
          Toast.loading('验证中...');
          //console.log("result="+/((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\d{8}/.text)
          setTimeout(() => {
            Toast.clear();
            resolve(/((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\d{8}$/.test(val));
          }, 1000);
        });
      },
      validation(val){
        console.log("val="+val);

        var result =/(\d{4})-((1[0-2])|(0[1-9]))-((0[1-9])|(1[0-9])|(2[0-9])|(3[0-1]))$/.test(val);
        var year = val.split("-")[0];
        console.log("year="+year);
        return result;
        //return /(\d{4})(([1][0-2])|[1-9])((0([1-9])|1[0-9]|2[0-9]|3[0-1]))/.test(val);
      },
      getVericode(){
        if(this.email.length>0){
          this.axios.get("t-user/sendMail?toUser="+this.email).then((response)=>{
            if(response.data.status == "false"){
              Toast("网络波动，请重试");
            }else{
              Toast("已发送，请注意接收");
            }
          })
        }else{
          Toast("请先输入邮箱地址");
        }
      }
    }
}
</script>
<style scoped>
.myInfo{
  font-weight:550;
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