<template>
<div class="index">
    <van-search   @focus="onFocus" v-model="value" background="red" shape="round" placeholder="请输入搜索关键词"  input-align="center">
        <template #left>
          <div style="margin-right:1%">
           <van-icon size="27" name="expand-o" />
           </div> 
        </template>
    </van-search>
    <van-tabs v-model="active"  @click="clickBtn">
      <van-tab v-for="(item,index) in itemList" :title="item.courseName"  :name="item.courseId" :key="index">
        <van-swipe @change="onChange" :autoplay="3000">
          <van-swipe-item v-for="(item,index) in topPictures" :key="index">
            <van-image
              width="365"
              height="150"
              :src="picIp+item.courseImage"
            />
          </van-swipe-item>
          <template #indicator>
            <div class="custom-indicator">{{ current + 1 }}/4</div>
          </template>
        </van-swipe>
        <van-grid  :column-num="4">
          <van-grid-item icon="http://localhost:3700/pic/newtap1.png" text="名师荟萃" />
          <van-grid-item icon="http://localhost:3700/pic/newtap2.png" text="小学" />
          <van-grid-item icon="http://localhost:3700/pic/newtap3.png" text="中学" />
          <van-grid-item icon="http://localhost:3700/pic/newtap.png" text="高中" />
        </van-grid>
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
          >
          <!--<van-cell v-for="item in list" :key="item" :title="item" />-->
          <div class="showTeach" v-for="(item ,index) in list" :key="index">
          <van-row  gutter="10"> 
            <van-col span="12" >
              <van-card
                @click="test(item.teach_id)"
              >
                <template #thumb>
                  <van-image
                      round
                      width="55px"
                      height="55px"
                      :src="picIp+item.teach_headicon"
                    />
                </template>
                <template #title>
                   <p>{{item.teach_account}}</p>
                </template>
                <!--
                <template #price-top>
                  <van-tag plain type="danger">标签</van-tag>
                  <van-tag plain type="danger">标签</van-tag>
                </template>
                -->
              </van-card>
            </van-col>
            <van-col span="12">
              <van-card
                  @click="test(item.teach_id)"
                >
                   <template #thumb>
                    <van-image
                        round
                        width="55px"
                        height="55px"
                        :src="picIp +item.teach_headicon"
                      />
                   </template>
                   <template #title>
                      <p>{{item.teach_account}}</p>
                   </template>
                </van-card>
              </van-col>
            </van-row>
          </div>
        </van-list>
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
      picIp:'http://localhost:3700/pic/',
      videoList:[],
      images: [
        'https://img01.yzcdn.cn/vant/apple-1.jpg',
        'https://img01.yzcdn.cn/vant/apple-2.jpg',
      ],
      current:0,
      active:0,
      itemList:[],
      list: [],
      loading: false,
      finished: false,
      navids:[],
      topPictures:[],
    }
    },
  created(){

    this.getClassDivide();

    },
  methods:{
    clickBtn(name,title){
      Toast(name);
      this.axios.get("t-teachaccount/getTeachInfoById?courseId="+name).then((response)=>{
        this.list = response.data;
      }) 
    },
    onClick(name, title) {
      console.log(name,title);
      Toast(title);




    },
    onFocus(){
      this.$router.push("/Search");
    },
     onChange(index) {
      this.current = index;
    },
    onLoad() {
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      /*
       setTimeout(() => {
        for (let i = 0; i < 10; i++) {
          this.list.push(this.list.length + 1);
          console.log("abc");
        }
        // 加载状态结束
        this.loading = false;

        // 数据全部加载完成
        if (this.list.length >= 40) {
          this.finished = true;
        }
      }, 1000);
      */
    },
    test(val){
      Toast(val);
      this.$router.push({name: 'TeachInfo',params: {userCode: val}});
    },
    getClassDivide(){
        this.axios.get("t-course/getTopPicture").then((response)=>{
          this.topPictures = response.data.records;
          this.itemList = response.data.records;
          this.itemList[0].courseName="名师荟萃";
        })
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
.custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 5px;
    padding: 2px 5px;
    font-size: 12px;
    background: rgba(0, 0, 0, 0.1);
}
.showTeach{
  margin-top:10px;
}
.index{
  background:cornsilk;
}
</style>