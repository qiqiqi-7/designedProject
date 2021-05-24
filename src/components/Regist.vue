<template>
<div class="login"> 
  <div class="form">
      <h3 class="regi_head">
        欢迎注册用户              
      </h3>
      <van-form @submit="onSubmit">
          <van-field
            v-model="username"
            name="用户名"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
          />
          <van-field
            v-model="password"
            type="password"
            name="密码"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
          />
          <van-field
            v-model="password1"
            type="password"
            name="确认密码"
            label="确认密码"
            placeholder="请再次输入密码"
            :rules="[{ required: true, message: '请再次输入密码' }]"
          />
          <van-field name="uploader" label="用户头像">
            <template #input>
              <van-uploader v-model="uploader" :max-count="1" :before-read="beforeRead"/>
            </template>
          </van-field>
          <div style="margin: 16px;">
            <van-button round block type="info" native-type="submit">
              注册
            </van-button>
          </div>
      </van-form>
    </div>
</div>
</template>

<script>
import Vue from 'vue';
import { Form } from 'vant';
import {Toast} from 'vant';
Vue.use(Toast);
Vue.use(Form);
Toast.setDefaultOptions({duration: 2000});
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',  
      password1:'',
      uploader: [],
    }
    },
  methods:{
    beforeRead(file){
      if(file.type !== 'image/jpeg'){
        Toast('请上传jpg格式的图片');
        return false;
      }
        return true;
    },
    onSubmit(values) {
      if(this.password != this.password1){
         Toast('密码和确认密码必须保持一致');
          return;
       }
        let formData = new FormData();
        formData.append("imgFile",this.uploader[0].file);
        this.axios.post("c?op=regist&username="+this.username+"&password="+this.password
        ,formData,{'Content-Type':'multipart/form-data'}).then((response)=>{
          this.$router.push("/MyInfo");
      })
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.regist{
  margin-top:-22px;
  padding-top:10px;
  height:668px;
  width:100%;
  background:#ffb957;
}
.regist .regi_head{
  text-align: left;
}
.regi_footer{
      position: absolute;
    bottom: 225px;
    padding-left: 94px;
    font-size: 10px;
}
</style>