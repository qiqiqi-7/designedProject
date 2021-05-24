<template>
  <div class="collect">
    <van-nav-bar
      title="我的收藏"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
      fixed
    > 
      <template #right>
        <van-icon name="search" size="18" />
      </template>
    </van-nav-bar>
    <div class="content">
        <van-row  v-for="(item,index) in teachInfoList" :key="index">
          <van-col span="19">
            <van-cell title="设置" @click="test" >
                <template #title>
                  <div style="display:inline-block;float:left">
                    <img
                      style="height:50px;width:50px;border-radius:25px"
                      :src="picIp+item.teachHeadicon"
                    />
                  </div>
                  <div style="display:inline-block;float:left;margin-left:10px">
                     <span>{{item.teachAccount}}.</span>
                      <p style="font-size:10px;margin-top:-5px">{{item.teachGrade}}</p>
                  </div>
                </template>
                <!-- 描述信息 -->
            </van-cell>
          </van-col>
          <van-col span="5">
            <div style="margin-top:15px">
            <van-button size="mini" @click="cancel(item.teachId,index)"  icon="https://img01.yzcdn.cn/vant/user-active.png" type="info" plain>
              {{item.status}}
            </van-button>
            </div>
          </van-col>
        </van-row>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import { SwipeCell, Toast } from 'vant';
Vue.use(SwipeCell);
export default {
  name: 'Collect',
  data () {
    return {
      value: '',
      list :20,
      usercode:this.$route.params.userCode,
      teachInfoList:[],
      picIp:'http://localhost:3700/pic/',
      user:JSON.parse(localStorage.getItem("user")),
      content:'已收藏',
    }
    },
    created(){
      console.log(this.usercode);
      this.getCollectList();
    }
    ,
    methods:{
      getCollectList(){
          this.axios.get("t-preferrence/getCollectList?usercode="+this.usercode).then((response)=>{
            response.data.forEach(item => {
              item.tteachaccount.status = '已收藏';
              this.teachInfoList.push(item.tteachaccount);
            });
             console.log("test");
            console.log(this.teachInfoList);
          });
      },
      onClickLeft(){


      },
      onClickRight(){
          Toast("点击右侧事件触发");
      },
      test(){
        Toast("dsadsadas");
        console.log("dsadsadsa");
      },
      cancel(val,index){
        Toast(val);
        this.axios.get("t-preferrence/cancalPrefer?usercode="+this.user.userCode+"&teachId="+val).then((response)=>{
          let status = response.data.status;
          if(status>0){
            Toast("状态改变成功");
            this.teachInfoList[index].status='收藏';
             this.content = "关注";
          }else{
            Toast("状态改变失败"); 
          }
        })
      }

    }
}
</script>
<style scoped>
.goods-card {
  margin: 0;
  background-color: #fff;
}
.delete-button {
  height: 100%;
}
.collect{
  height:667px;
  background:#f7f8fa;
}
.content{
  margin-top:47px;
}
.van-nav-bar{
   background:#f7f8fa;
}
.van-swipe-cell{
  margin-top:10px;
  margin-left:10px;
  margin-right:10px;
  border-radius:15px;
}
.van-row{
  background: #fff;
  margin-top:5px;
}
.content{
  background:#f7f8fa;
  position: relative;
  padding-bottom: 75px;
}
</style>