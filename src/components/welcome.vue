<template>
<div class="welcome">
  <div class="headtitle">
    <h2 style="text-align:center;margin-top:45px">家教网，你身边辅导专家</h2>
  </div>
  <div class='content'>
    <div class="choose1">
      <van-button  @click="saveIdentifier('0')" :loading="isloading" loading-text="正在跳转" icon="user-o" color="#1989fa"  block size="large"  type="info">游客登录</van-button>
    </div>
    <div class="choose2">
      <van-button  @click="saveIdentifier('1')"  :loading="isloading1" block type="primary" loading-text="正在跳转，请稍等" size="large" icon="user-o" >我是教师</van-button>
    </div>
    <div class="choose3">
      <van-button @click="saveIdentifier('2')"  :loading="isloading2" block type="info" size="large" loading-text="正在跳转，小主请稍等" color="#00FFFF" icon="user-o">我是学生</van-button>
    </div>
  </div>
</div>
</template>
<script>
export default {
  name: 'welcome',
  data () {
    return {
        identifyClass:"",
        isloading:false,
        isloading1:false,
        isloading2:false, 
        timer:''
    }
  },
  methods:{
    saveIdentifier(identy){
      if(identy=='0'){
        this.isloading = true;
      }else if(identy =='1'){ 
        this.isloading1 = true;
      }else if(identy == '2'){
        this.isloading2 = true;
      }
      var router = this.$router;
      this.timer = setInterval(function(){
        console.log("param="+identy);
        if(identy == '0'){
          router.push("/NewIndex");
        }else {
          router.push("/Login")
        }
      },1000)
    }
  },
  beforeDestroy(){
    clearInterval(this.timer);
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.welcome{
  height:100%;
  width:100%;

}
.content{
  position: absolute;
  top:20%;
  height:100%;
  width:100%;
  background: #fff;
}
.content>.choose1{
  margin-top:10px;
}
.content>.choose2{
  margin-top:10px;
}
.content>.choose3{
  margin-top:10px;
}
</style>