<template>
<div class="regist">
  <div class="form">
      <h3 class="regi_head">
        使用账户密码登录
      </h3>
      <van-form @submit="onSubmit">
          <van-field name="radio" label="选择身份" input-align="left">
            <template #input>
              <van-radio-group v-model="radio" direction="horizontal">
                <van-radio name="0">学生用户</van-radio>
                <van-radio name="1">教师用户</van-radio>
              </van-radio-group>
            </template>
          </van-field>
          <van-field
            label-align="right"
            label-width="15%"
            v-model="username"
            name="用户名"
            label="用户名"
            placeholder="请填写账户名称"
            :rules="[{ required: true }]"
          />
          <van-field
            label-align="center"
            label-width="15%"
            v-model="password"
            type="password"
            name="密码"
            label="密码"
            placeholder="请填写账号密码"
            :rules="[{ required: true }]"
          />

          <div style="margin: 16px;">
            <van-button round block type="info" native-type="submit">
              登录
            </van-button>
          </div>
      </van-form>
    </div>
    <div class="regi_footer">
      <ul class="more">
        <li><router-link to="/Regist">找回密码</router-link></li>
        <li><router-link to="/Regist">注销账号</router-link></li>
        <li><router-link to="/Login1">注册账号</router-link></li>
      </ul>
    </div>
</div>
</template>


<script>
import Vue from 'vue';
import { Form } from 'vant';
import {Toast} from 'vant';

Vue.use(Toast);
Vue.use(Form);
export default {
  name: 'Regist',
  data () {
    return {
      username: '',
      password: '',  
      radio: '0',
      value1: 0,
      option1: [
        { text: '请选择身份', value: 0 },
        { text: '我是同学', value: 1 },
        { text: '我是老师', value: 2 },
      ],
      showlay:false,
    }
    },
  methods:{
    onSubmit(values) {
        if(this.radio == '2'){
            Toast("请选择当前登录身份");
            return ;
        }
        if(this.radio == '0'){
          this.axios.post("t-student/login",
            {"stuAccount":this.username,
            "stuPwsd":this.password}
          ).then((response)=>{
            if(response.data==""){
                Toast("验证失败，请重试");
            }else{
                Toast("验证成功，已成功登录"); 
                response.data.type=0; 
                response.data.userCode = response.data.stu_id;
                response.data.username = response.data.stu_account;
                response.data.headicon = response.data.stu_headicon;
                localStorage.setItem("user",JSON.stringify(response.data));
                this.$router.push("/NewIndex");
            }
          });  
        }else{
          this.axios.post("t-teachaccount/login",{
            teachAccount:this.username,
            teachPwsd:this.password
          }).then((response)=>{
            if(response.data==""){
              Toast("验证失败，请重试");
            }else{
              response.data.type=1;
              response.data.userCode=response.data.teach_id;
              response.data.username=response.data.teach_account;
              response.data.headicon = response.data.teach_headicon;
              Toast("验证成功，已成功登录");  
              localStorage.setItem("user",JSON.stringify(response.data));
              this.$router.push("/NewIndex");
            }
          })
        }
    },
  }
}
</script>

<style scoped>
.regist{

  height:667px;
  width:375px;
  background:#f7f8fa;
}
.regist .form{
  position: relative;
  top:10%;
  height:540px;

}
.regist .regi_head{
  text-align: left;
  padding-left: 10px;
}
.regi_footer{
    position: absolute;
    bottom: 8%;
    left: 25%;
    font-size: 10px;
}
.regi_footer .more{
  text-decoration: none;
  float: left;  
}
.regi_footer .more >li{
  float: left;
  margin-left:15px;
}
</style>