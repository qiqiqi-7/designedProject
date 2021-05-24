<template>
<div class="myCourse">
  <van-nav-bar
  left-text="课程"
  left-arrow
  :fixed="fixed"
  @click-left="onClickLeft"
  />
  <van-tabs v-model="active" sticky >
    <van-tab title="我的课程">
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
         <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
              <div v-for="(item,index) in  historyList"  :key="index" style="margin-top:10px;">
                <van-card
                  :desc="item.Content"
                  :title="item.headTitle"
                  :thumb="picIp+item.image"
                />
              </div>
          </van-list>
      </van-pull-refresh>
    </van-tab>
  </van-tabs>
 
</div>
</template>
<script>
import Vue from 'vue';
// import { Swipe, SwipeItem } from 'vant';
import { Card } from 'vant';
import { Col, Row } from 'vant';

Vue.use(Col);
Vue.use(Row)
Vue.use(Card);
// Vue.use(Swipe);
// Vue.use(SwipeItem);
export default {
  name: 'MyCourse',
  data () {
    return {
      fixed:true, 
      refreshing:false,
      pageIndex:1,
      finished:false,
      historyList:[],   
      loading:false,
      isLoading:false,
      active:0,
      picIp:'http://localhost:8080/pic/'
    }
    },
    created(){
      this.onLoad();
    },
    methods:{
      onLoad() {
        if (this.refreshing) {
          this.historyList = [];
          this.refreshing = false;
        }
        var name = localStorage.getItem("username");
        if(name!=null){
            this.axios.get("c?op=getHistoryByUserId&username="+name+"&num="+this.pageIndex).then((response)=>{
            this.historyList=this.historyList.concat(response.data.records);
            this.loading = false;
            if(this.pageIndex>=response.data.totalPages){
              this.finished = true;
            }
            this.pageIndex++;
          })
        }
      },
      onRefresh() {
        // 清空列表数据
        this.finished = false;
        this.pageIndex = 1;
        // 重新加载数据
        // 将 loading 设置为 true，表示处于加载状态
        this.loading = true;
        this.onLoad();
      },
      onClickLeft(){

      }
  }
}
</script>
<style scoped>
.myCourse{
  background:white;
  margin-top:50px;
}
.myCourse>h3{
  height:25px;
  line-height:50px;
  padding-left:15px;
}
</style>