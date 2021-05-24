<template>
<div class="ShowVideo">
      <div class="video" style="position:fixed;width:100%;top:0px;z-index:1000" >
        <video controls="controls" width="100%" :src="picIp+video.url" :poster="picIp+video.image">
        </video>
        <div class="choose" style="background:red;background: beige;margin-top: -4px;box-shadow: 5px 1px 1px white;">
            <ul id="choose" >
              <li class="li left" @click="choose=false"><a>简介</a></li>
              <li class="li right" @click="choose=true"><a>评论{{countsComment}}</a></li>
            </ul>  
        </div>
      </div>
      <div class="comment" v-if="choose==false">
        <van-col span="4">
            <van-image  
                round
                width="3rem"
                height="3rem"
                :src="picIp + teachInfo.image"
            />
            </van-col>
              <van-col span="19">
                <p class="user" > 
                  {{teachInfo.teachname}}
                </p>
              </van-col>
              <p class="content">{{teachInfo.introduce}}</p>
              <ul class="like" style="text-decoration:none">
                <li style="float:left"><van-icon name="good-job" size="2rem" :color="job" @click="change"/></li>
                <li style="float:left"><van-icon name="hot" size="2rem" :color="hot" @click="change1"/></li>
                <li style="float:left"><van-icon name="gold-coin" size="2rem"  :color="coin" @click="change2"/></li>
                <li style="float:left"><van-icon name="star" size="2rem" @click="addMyfavor"  :color="star"   /></li>
                <li style="float:left"><van-icon name="share" size="2rem"  :color="share" @click="change4"/> </li>
              </ul> 
              <div v-for="item in videoList" :key="item.id" style="width:100%;height:75px;padding-top:5px;">
                <van-cell @click="show">
                  <van-card
                    :desc="item.Content"
                    :thumb="picIp+item.image"
                  />
              </van-cell>
               </div> 
      </div>
       <div class="comment" v-if="choose==true">
          <van-row v-for="(item,index) in commentList" :key="index" style="border-top: 1.5px solid black;padding-top:5px;padding-left:5px;">
            <van-col span="6">
              <van-image  
              round
              width="3rem"
              height="3rem"
              :src="picIp + item.image"
              />
              </van-col>
              <van-col span="18">
                <p class="user" > 
                  {{item.username}}
                </p>
                <span style="font-size:15px">{{item.commentTime}}</span>
                  <p class="content">{{item.content}}</p>
              </van-col>
          </van-row>
          <van-form @submit="onSubmit" style="position:fixed;bottom:0px;z-index:1000;background:white;width:100%">
            <van-field  
              v-model="content"
              :clearable="clearable"
              placeholder="请输入评论内容"
              :rules="[{required:true,message:'请输入评论内容'}]"
            />
            <div style="margin:16px;">
              <van-button round block type="info" native-type="submit">
                发表
              </van-button>
            </div>
          </van-form>
      </div>
</div>
</template>
<script>
import Vue from 'vue';
import { Toast } from 'vant';
import { Circle } from 'vant';
import { SwipeCell } from 'vant';

Vue.use(SwipeCell);
Vue.use(Circle);
Vue.use(Toast);
export default {
  name: 'ShowVideo',
  data () {
    return {
      videoId:this.$route.params.videoId,
      content:"",
      video:{ },
      teachInfo:{},
      clearable:true,
      picIp:"http://localhost:8081/pic/",
      commentList:[],    
      choose:true,
      countsComment:0,
      videoList:[],
      teacherId:0,
      job:"grey",
      hot:"grey",
      star:"grey",
      share:"grey",
      coin:"grey"
    }
    },
     computed: {
    text() {
      return this.currentRate.toFixed(0) + '%';
    },
  },
  created(){
    this.getOneVideo();
    this.getComments();
    this.getCountcomments();
    
    ///this.getTeacherInfo();
  }
  ,
    methods:{
      onSubmit(values){
        var user = JSON.parse(localStorage.getItem("user"));
        if(user==null){
          Toast('您还未登陆，请先登陆后发表评论!');
          return;
        }
        this.axios.post("commentinfo/addComment?id="+this.videoId+"&username="+user.username+"&content="+this.content+"&image="+user.image).then((response)=>{
            this.getComments();
            this.content="";
          })
      },
      getComments(){
        this.axios.get("commentinfo/getCommentById?videoid="+this.videoId).then((response)=>{
            this.commentList = response.data;
          })
      },
      getCountcomments(){
        this.axios.get("commentinfo/counts?videoid="+this.videoId).then((response)=>{
            this.countsComment = response.data;
          })
      },
      getOneVideo(){
        this.axios.get("")
        this.axios.get("midvideo/getContentVideo?videoid="+this.videoId).then((response)=>{
            this.video = response.data;
            this.teacherId = this.video.teacherid;
           this.getTeacherInfo();
          })
      },
      /*
      getVideos(){
          this.axios.get("discrib-info/getVideos&teacherId="+this.teacherId).then((response)=>{
            this.videoList = response.data;
          })
      }*/
      getTeacherInfo(){
          this.axios.get("discrib-info/getTeachInfo?teacherid="+ this.video.teacherid).then((response)=>{
            this.teachInfo = response.data;
          })
      },
      change(){
        this.job = "red";
      },
      change1(){
        this.hot = "red";
      },
      change2(){
        this.coin = "red";
      },
      change4(){
        this.share = "red";
      },
      show(){
        var item = 1;
      },
      addMyfavor(){
        let studentId = localStorage.getItem("studentId");
        let username = localStorage.getItem("username");
        this.star = "red";
        if(studentId!=null){
          this.axios.post("c?op=addMyfavor&videoId="+this.videoId+"&username="+username).then((response)=>{
              var result = response.data;
              this.teacherId = this.video.teachInfo.teacherId;
              Toast('添加到我的课程成功');
            })

        }

      }
    }
}
</script>
<style scoped>
.user{
  color:grey;
  font-size:16px;
  font-weight: bold;
  line-height:2px;
}
.content{
  font-weight: 500;
}
.comment{
  margin-top:295px;
  width:100%;
  padding:0px 5px 0px 5px;
}
#choose{
     background: pink;
    text-decoration: none;
    height: 57px;
}
#choose .li{
    float:left;
    height:57px;
    line-height:57px;
    text-align: center;
}
#choose .left{
  margin-left:13px;
}
#choose .right{
  margin-left:70px;
}
.like{
  text-decoration: none;
  width:100%;
  height:45px;
  border-bottom:1px solid black;
}
.like>li{
  float:left;
  height:45px;
  width:20%;
  text-align:center;
}
.like>li>i{

  color:red;
}
.change{
  color:red;
}
</style>