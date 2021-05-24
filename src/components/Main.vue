<template>
<div class="Main">
  <div class="header">
    <van-card
        desc="0"
        :title="user"
      >
      <template #thumb>
        <router-link to='/Setting'>  
            <img :src="image" alt="" style="height:60px;width:60px;border-radius:30px;border:1px solid black">
        </router-link>
      </template>  
    </van-card>
    <div class="showImage" id="myWork">
      <img src="../assets/myWork.jpg" alt="">
      <div style="height:60px;width:125px;margin-top:15px;margin-left:10px">
          <van-cell title="MODULE unit 1">
  <!-- 使用 right-icon 插槽来自定义右侧图标 -->
  <template #right-icon>
    <van-dropdown-menu :overlay="flag">

  <van-dropdown-item title="to do" ref="item">
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh" style="height:469px;">
         <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
            <div style="height:75px;width:369px;position:relative;border-radius:15px;border-top:1px solid black; background:linear-gradient(to top,#fff,#fef08f);" v-for="(item,index) in list" :key="index" >
              <div @click="review(item.id)">
                <img :src="picIp+item.image" alt="" style="display:inline-block;width:55px;height:55px;border-radius:15px;">
                <p style="color:black;position:absolute;top:-10px;left:70px;">{{item.headTitle}}</p> 
                <p style="color:black;position:absolute;top:10px;left:70px;font-weight:1">{{item.Content}}</p>
                <van-icon @click="del(item.title)" name="delete" color="red" style="display:inline-block;position:absolute;top:10px;right:0px;" size="30px"/>
              </div> 
            </div>
          </van-list>
      </van-pull-refresh>
  </van-dropdown-item>

</van-dropdown-menu>
  </template>
</van-cell>
      </div>
    </div>
    <div class="showImage" id="person">
      <img src="../assets/person.jpg" alt="">
      </div>
  </div>    
  <div class="body">
    <div class="week"> 
       <ul>
         <li>Mon</li>
         <li>Tue</li>
         <li>Wed</li>
         <li>Thu</li>
         <li>Fri</li>
         <li>Sat</li>
         <li>Sun</li>
       </ul> 
    </div>
    <div class="white">{{course}}</div>
  </div>  
  <div class="footer">
      <router-link to="/Setting">
        <img src="../assets/footer_1.jpg" alt="">
      </router-link >
      <router-link to="/Setting"> 
        <img src="../assets/footer_2.jpg" alt="">
      </router-link>
      <router-link to="/Setting">
        <img src="../assets/footer_3.jpg" alt="">
      </router-link>
      <router-link to="/Content">
        <img src="../assets/footer_4.jpg" alt="">
      </router-link>
      <a href="/Setting" onClick="Dianji()">
        <img src="../assets/footer_5.jpg" alt="" style="height:80px;width:165px">
      </a>
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
  name: 'Main',
  data () {
    return {
      course:"该学生没有添加任何课程",
      user:'',
      value1: 0,
       option1: [
        { text: '1', value: 0 },
        { text: '2', value: 1 },
        { text: '3', value: 2 },
      ],
      flag:true,
      currentRate: 0,
      learned:[{"image":"http://www.dzkbw.com/books/rjb/yuwen/xs3x_2019/coverbig.jpg","title":"三年级下册语文","desc":"当前进度：4、怀乡的孩子"},
      {"image":"https://www.sskk8.com/d/file/keben/9bb5b0af0f8f89617c20a3901300f645.jpg","title":"三年级下册英语","desc":"当前进度：Unit1 Welcome back to school"},
      {"image":"http://www.dzkbw.com/books/rjb/shuxue/xs3x_new/coverbig.jpg","title":"三年级下册数学","desc":"当前进度：四、认识图形"},
      {"image":"http://www.dzkbw.com/books/jkb/kexue/3x/coverbig.jpg","title":"三年级下册科学","desc":"当前进度：动图1、寻访小动物"},
      {"image":"https://www.dianzanla.com/tu/202003/202003300738-36.jpg","title":"三年级下册卫生与健康","desc":"当前进度：第一课：冬春季节预防流感"},
      {"image":"http://www.dzkbw.com/books/hjb/zhengzhi/3x/coverbig.jpg","title":"三年级下册思想与品德","desc":"当前进度：祖国妈妈，我爱你"},],
      isLoading:false,
      image:'',
      picIp:"http://localhost:8080/pic/",
      refreshing:false,
      finished:false,
      loading:false,
      list:[],
      pageIndex:1,
    }
    },
     computed: {
    text() {
      return this.currentRate.toFixed(0) + '%';
    },
  },
  created(){
    var img = localStorage.getItem("img");
    let name = localStorage.getItem("username");
    if(name!=null){
      this.image = this.picIp + img;
      this.user = name;
    }
    this.onLoad();
  }
  ,
    methods:{
      dianji(){

       
      return false;
      },
      del(id){
        var index = this.learned.findIndex(item=>{
          if(item.id == id)
          {
            return true;
          }
        })
        this.learned.splice(index,1);
      },
      onLoad() {
        if (this.refreshing) {
          this.list = [];
          this.refreshing = false;
        }
        var name = localStorage.getItem("username");
        if(name!=null){
            this.axios.get("c?op=getHistoryByUserId&username="+name+"&num="+this.pageIndex).then((response)=>{
            this.list=this.list.concat(response.data.records);
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
      review(videoId){
        this.$router.push("ShowVideo/"+videoId);
      }


    }
}
</script>
<style scoped>
.header{
  height:250px;
  width:369px;
  background:#feee8c;
}
.header .van-card__title{
  margin-top:20px;
}
.header>#myWork>div>.van-cell{
  color:white;
  box-shadow: 0 0 0px black;
  font-weight: bold;
  background-color:#ffb957;
  border-top-left-radius:13px;
  border-top-right-radius:13px;
}
.header>#myWork>div>.van-cell>.van-dropdown-menu>.van-dropdown-item.van-dropdown-item--down{
  height:150px;
  width:369px;
}
.van-dropdown-menu__item{
  background:#ffb957;
}
.van-card__content>div>.van-card__title{
  color:lightblue;
}
.header .van-card__thumb{
  width:60px;
  height:60px;
}
.van-dropdown-item--down
{
  width:369px;
  background:black;
}
.showImage{
  float:left;
}
#person{
      margin-top: -50px;
      margin-left:35px;
}
#myWork{
  height:145px;
  margin-left: 32px;
  width: 99px;
}
#person >img{
      border-radius:35px;
}
/* body的内容 */
.body{
  height:250px;
  width:369px;
  background:#ffdb3d;
  margin-top:-2px;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif
}
.body .week {
  width:369px;
  height:52px;
  margin-left:23px;
}
.body .week ul{
  width:315px;
  height:52px;
  background: rgb(241, 28, 28);
  border-radius:66px;
    box-shadow: 0 0 3px black;
}
.body .week > ul >li{
  color:white;
  float:left;
  text-decoration:none;
  width:45px;
  height:52px;
  text-align: center;
  line-height: 52px;
}
.body .white{
  background:white;
  height: 187px;
  width: 293px;
  margin-left: 33px;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  box-shadow: 0 0 2px black;
  line-height:187px;
  text-align:center;
}
.footer{
  padding-top: 20px;
  height: 170px;
  width: 328px;
  background: #ffdb3d;
  padding-left: 41px;
}
.footer a{
  margin-left:10px;
}
.footer img{
    height:75px;
    width:75px;
    border-radius:15px;
}
.van-card{
  background:none;
}
</style>