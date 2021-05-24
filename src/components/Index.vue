<template>
<div class="index">
    <van-search   @focus="onFocus" v-model="value" background="white" shape="round" placeholder="请输入搜索关键词"  input-align="center">
        <template #left>
          <div style="margin-right:1%">
           <van-icon size="27" name="expand-o" />
           </div> 
        </template>
    </van-search>
      <van-tabs @click="onClick"  sticky >
        <van-tab v-for="(index,index1) in tabs" :title="index.name" :name="index.videoclass" :key="index.videoclass" line-width="20%" @click="temp(index1)">
          <div class="content" style="margin-top:10px">
          <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
              <template #default>
                <div style="width:100%;height:75px">
                  <van-row>
                    <van-col span="7">
                      <van-image  
                      width="125px"
                      height="125px"
                      :src="picIp + 'stuInfo4.jpg'"
                    />
                    </van-col>
                    <van-col span="13" offset="3">
                      <h5>哈哈哈哈哈哈</h5>
                      <p>dsadas</p>
                    </van-col>
                  </van-row>
                </div>
              </template>
            </van-swipe-item>
          </van-swipe>
          <div v-for="item in videoList" :key="item.id" style="width:90%;height:75px;padding-top:5px;margin-left:5%">
            <van-cell  @click="review(item.id)">
                <van-card
                  :title="item.headTitle"
                  :desc="item.content"
                  tag="火热"
                  :thumb="picIp+item.image"
                />
                </van-cell>
          </div> 
         </div>
        </van-tab>
      </van-tabs>
</div>
</template>

<script>
import Vue from 'vue';
import { Form } from 'vant';
import {Toast} from 'vant';
import { Search } from 'vant';
import { Icon } from 'vant';
import { Swipe, SwipeItem } from 'vant';
import { Col, Row } from 'vant';

Vue.use(Col);
Vue.use(Row);
Vue.use(Swipe);
Vue.use(SwipeItem);
Vue.use(Icon);
Vue.use(Search);
Vue.use(Toast);
Vue.use(Form);
export default {
  name: 'Index',
  data () {
    return {
      value:'',
      tabs:["1","2","3","4","5","6","7","8","9"],
      picIp:'http://localhost:8081/pic/',
      videoList:[],
    }
    },
  created(){
    this.getVideoclass();
    this.getVideos(1);
      
    },
  methods:{
    onClick(name, title) {
      console.log(name,title);
      Toast(title);
      this.getVideos(name);
    },
    getVideoclass(){
      this.axios.post("videoclass/getVideoclass").then((response)=>{
        this.tabs = response.data;
      })

    },
    getVideos(videoclass){
          this.axios.get("videoinfo/getVideos?videoclass="+videoclass).then((response)=>{
            this.videoList = response.data.records;
          })
    },
    review(videoId){
        this.$router.push("ShowVideo/"+videoId);
    },
    onFocus(){
      this.$router.push("/Search");
    }
  }
}
</script>

<style>
 .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    background-color: #39a9ed;
  }
div.van-tabs__content{
  padding:0px 5px 0px 5px;
}  
div.van_cell{
  width:85%;
}
.van-swipe {
    height:125px;
}
</style>