<template>
<div class="teachInfo">
     <van-card class="card">
          <template #title>
            <p style="color:#ffffff;font-size:20px;"><strong>{{teachAccount}}</strong></p>
          </template>
          <template #desc>
            <h style="color:#ffffff">{{teachGrade}} | {{teachMajor}}</h>
          </template>
          <template #thumb>
             <van-icon name="arrow-left" size="15" color="#ffffff"/>
            <van-image
              round
              width="75px"
              height="75px"
              :src="picIp+teachHeadicon"
            />
          </template>
          <template #footer>
            <van-button :icon="imageStatus" class="status" :plain="true" @click="setLike">
               {{status==true?"已关注":"关注"}}
            </van-button>
            <van-button icon="https://img01.yzcdn.cn/vant/user-active.png" @click="jumpToChat">
              私聊
            </van-button>
          </template>
          <!--
          <template #num>
              <van-icon name="arrow" size="18"/>
          </template>
          -->
        </van-card>
      <van-tabs v-model="active" sticky @click="clickTest">
        <van-tab title="基本信息" name='0'>
          <van-skeleton title :row="3" :loading="loading">
            <div>
              <van-list
                v-model="loading1"
                :finished="finished1"
                finished-text="没有更多了"
                @load="onLoad"
              >
                <van-cell-group>
                  <van-field
                    v-model="username"
                    label="教师姓名"
                    left-icon="user-o"
                    readonly 
                  />
                  <van-field
                    v-model="teachGrade"
                    label="教师学历"
                    left-icon="bulb-o"
                    readonly
                  />
                  <van-field
                    v-model="teachMajor"
                    label="教师专业"
                    left-icon="medal-o"
                    readonly
                  />
                  <van-field
                    v-model="value2"
                    label="教师类型"
                    left-icon="eye-o"
                    readonly
                  />
                  <van-field
                    v-model="teachExperience"
                    label="教学年龄"
                    left-icon="flag-o"
                    readonly
                  />
                  <van-field
                    v-model="userLocation"
                    label="住址"
                    left-icon="home-o"
                    placeholder="显示清除图标"
                    readonly
                  />
                  <!-- input-->
                  <van-field
                    v-model="teachInfo"
                    label="教师学历证书"
                    left-icon="bar-chart-o"
                  >
                    <template #input>
                        <van-uploader v-model="fileList" :deletable="deleteBtn"   :max-count="1"/>
                    </template>
                  </van-field>
                  <van-field
                    v-model="value2"
                    label="教师信誉等级"
                    left-icon="bar-chart-o"
                  >
                    <template #input>
                      <van-rate
                          v-model="value"
                          :size="25"
                          color="#ffd21e"
                          void-icon="star"
                          void-color="#eee"
                          readonly
                        />
                    </template>
                  </van-field>
                  <van-field
                    v-model="value2"
                    label="教师学历证书"
                    left-icon="bar-chart-o"
                  >
                    <template #input>
                      <!--<van-uploader v-model="fileList1" :deletable="deleteBtn" max-count="1"/>-->
                      <!--<a href="url" download="http://localhost:8081/pic/chrome.mp4">文件下载</a>-->
                      <van-button  @click="download" round type="info" size="small">点击下载教师学历证明</van-button>
                    </template>
                  </van-field>
                </van-cell-group>
              </van-list>
            </div>
          </van-skeleton>
        </van-tab>
        <van-tab title="主讲课程" name='1'>
          <van-row v-for="(item ,index) in classList" :key="index">
            <div class="pressY">
              <van-col span="10">
                <van-image
                  :src="picIp+item.tcourse.courseImage"
                >
                <template #default>
                  <p style="font-size:15px;font-weight:500">{{item.tcourse.courseName}}</p>
                </template>
                </van-image>
              </van-col>
              <van-col span="13">
                <div class="center">
                  <van-cell-group>
                    <van-field
                        v-model="item.abilityLowSalary"
                        label="课程最低薪水"
                        left-icon="bar-chart-o"
                        readonly 
                      />
                      <van-field
                        v-model="item.ablityHighSalary"
                        label="课程最高薪水"
                        left-icon="chart-trending-o"
                      />
                  </van-cell-group>
                </div>
              </van-col>
            </div>
          </van-row>
        </van-tab>
        <van-tab title="评论" name='2'>内容 2</van-tab>
      </van-tabs>
</div>
</template>

<script>
import Vue from 'vue';
import { Toast } from 'vant';
Vue.use(Toast);
export default {
  name: 'TeachInfo',
  data () {
    return {
      active:'0',
      username:'',
      teachAccount:'',
      teachExperience:'',
      teachGrade:'',
      teachHeadicon:'',
      teachId:'',
      teachMajor:'',
      userLocation:'',
      teachIntroduce:'',
      teachGracredit:'',
      image:'http://localhost:3700/pic/',
      picIp:'http://localhost:3700/pic/',
      status:true,
      imageStatus:'star-o',
      loading: true,
      loading1:true,
      finished1:true,
      value:0,
      value1:'12313213',
      value2:'123456789',
       fileList: [
        { url: 'https://img01.yzcdn.cn/vant/leaf.jpg' },
        // Uploader 根据文件后缀来判断是否为图片文件
        // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
      ],
      fileList1:[
        {url:'http://localhost:8081/pic/chrome.mp4'}
      ],
      deleteBtn:false,
      btnSendTem:false,
      loading:false,
      loadingText:'',
      teachInfo:{},
      user:JSON.parse(localStorage.getItem("user")),
      classList:[],
    }
  },
  created(){
    this.teachId = this.$route.params.userCode;
    console.log(this.teachId);
    this.getTeachInfo();
  }  
  ,
  methods:{


      getCourses(){
        


      }
      ,
      clickTest(name,title){
          console.log("name="+name+",title="+title);
          this.axios.get("t-ability/getListAbility?teachId="+this.teachId).then((response)=>{
              this.classList = response.data;
          })
      },
      setLike(){
        const toast = Toast.loading({
          duration: 0, // 持续展示 toast
          forbidClick: true,
          message: '正在加载',
        });
        let second = false;
        let _this = this;
        second = true;
        const timer = setInterval(() => {
            if (second ) {
              _this.axios.get("t-preferrence/addPreference?preferStuid="+_this.user.userCode+
                "&preferTeachid="+_this.teachId).then((response)=>{ 
                  if(response.data.result == "success"){
                    toast.message = `关注成功`;
                  }else{
                    toast.message = `操作失败，请重试`; 
                  }
              })
              clearInterval(timer);
              // 手动清除 Toast
              Toast.clear();
            }
          }, 1000);
          
          
          if(this.status == false){
            this.status = true;
            this.imageStatus = "star-o";
          }else{
            this.status = false;
            this.imageStatus = "star";
          }
    },
    //download
     download () {
      let url1 = this.teachIntroduce;
      this.btnSendTem = true
      // 加载loading
      this.loading = true
      this.loadingText = '正在导出模板...'
      this.axios({
        baseURL: 'http://localhost:3700/pic/',
        url: url1,
        method: 'get',
        responseType: 'blob', // 服务器返回的数据类型
        params: { // 其他参数
        },
        data: {}
      }).then((res) => {
        // 关闭loading
        this.loading = false
        // 此处有个坑。这里用content保存文件流，最初是content=res，但下载的test.xls里的内容如下图1，
        // 检查了下才发现，后端对文件流做了一层封装，所以将content指向res.data即可
        // 另外，流的转储属于浅拷贝，所以此处的content转储仅仅是便于理解，并没有实际作用=_=
        const content = res.data
        const blob = new Blob([content]) // 构造一个blob对象来处理数据
        const fileName = url1 // 导出文件名
        // 对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
        // IE10以上支持blob但是依然不支持download
        if ('download' in document.createElement('a')) { // 支持a标签download的浏览器
          const link = document.createElement('a') // 创建a标签
          link.download = fileName // a标签添加属性
          link.style.display = 'none'
          link.href = URL.createObjectURL(blob)
          document.body.appendChild(link)
          link.click() // 执行下载
          URL.revokeObjectURL(link.href) // 释放url
          document.body.removeChild(link) // 释放标签
        } else { // 其他浏览器
          navigator.msSaveBlob(blob, fileName)
        }
        this.btnSendTem = false
      }).catch((error) => {
        console.log(error)
        // 关闭loading
        this.loading = false
        this.btnSendTem = false
      })
    },
    onLoad(){



    },
    jumpToChat(){
      this.$router.push("/Chat");
    },
    getTeachInfo(){
      let userId;
      if(this.user ==null){
        userId = '1';
      }else{
        userId = this.user.userCode;
      }
      this.axios.get("t-teachaccount/getTeachInfo?userCode="+this.teachId+"&userId="+userId).then((response)=>{
        this.teachInfo=response.data.tTeachaccount;
        this.username = this.teachInfo.tuser.userName;
        this.teachGrade = this.teachInfo.teachGrade;
        this.teachMajor = this.teachInfo.teachMajor;
        this.teachExperience = this.teachInfo.teachExperience +"年";
        this.userLocation = this.teachInfo.tuser.userLocation;
        this.teachId = this.teachInfo.teachId;
        this.teachHeadicon = this.teachInfo.teachHeadicon;
        this.teachAccount = this.teachInfo.teachAccount;
        this.teachIntroduce = this.teachInfo.teachIntroduce;
        this.value = this.teachInfo.teachGracredit%6;

        this.status = response.data.isConcerning;
          /*
          if(this.status == false){
            this.status = true;
            this.imageStatus = "star-o";
          }else{
            this.status = false;
            this.imageStatus = "star";
          }*/
        if(this.status == true){
            this.imageStatus = "star";
        }else{
            this.imageStatus ="star-o";
        }
        if(this.teachInfo.teachType == "0"){
          this.value2 = "大学生教员"
        }else{
          this.value2 = "专业教师"
        }
        this.fileList[0].url =this.picIp+ this.teachInfo.teachLectfile;
      })
    }
  },
  mounted() {
    console.log("mouted函数执行");
    this.loading = false;
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header{
  background-color: #fafafa;
  background: rgba(250,250,250,0.1)。
}
.arrow{
  opacity: 1;
}
.card{
  background-color: #5c7ba3;
  z-index: 0;
}
.van-card__footer{
  margin-top:10px;
  border-top:1px solid white;
  padding-top: 10px;
  text-align: right;
}
.van-image{
  border-style:solid;
  border-color:rgb(236, 236, 236);
  border-width:1px;
}
.pressY{
  margin-top:10px;
}
.center{
  margin-top:15px;
}
/*
.van-card__footer .van-button__text{
  color:blue;
}*/




</style>