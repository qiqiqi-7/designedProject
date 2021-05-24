<template>
<div class="Search">
    <van-search
      v-model="value"
      shape="round"
      placeholder="请输入教师名称，选择下方信息，回车搜索"
      @search="onSearch"
    />
    <div class="history" v-if="showResult == true"> 
      <div v-for="(item,index) in  historyCourse"  :key="index" style="margin-top:10px;">
        <van-card
          :desc="item.Content"
          :title="item.headTitle"
          :thumb="picIp+item.image"
        />
      </div>
    </div>
    <div class="history"  v-if="showResult==false">
      <van-field
        v-model="fieldValue"
        is-link
        readonly
        label="地区"
        placeholder="请选择所在地区"
        @click="show1 = true"
      />
      <van-popup v-model="show1" round position="bottom">
        <van-cascader
          v-model="cascaderValue"
          title="请选择所在地区"
          :options="options"
          @close="show1 = false"
          @finish="onFinish"
        />
      </van-popup>
      <!--
      <van-field
        readonly
        clickable
        label="科目"
        :value="value"
        placeholder="请选择科目"
        @click="showPicker = true"
      />
      <van-popup v-model="showPicker" round position="bottom">
        <van-picker
          show-toolbar
          :columns="columns"
          @cancel="showPicker = false"
          @confirm="onConfirm"
        />
      </van-popup>-->



      <van-field
        v-model="fieldValue1"
        is-link
        readonly
        label="科目"
        placeholder="请请选择科目"
        @click="show3 = true"
      />
      <van-popup v-model="show3" round position="bottom">
        <van-tree-select
          @click-nav="clickLeft"
          @click-item="clickRight"
          :items="items"
          :active-id.sync="activeIds"
          :main-active-index.sync="activeIndex"

        />
      </van-popup>
      <van-field
        v-model="fieldValue2"
        is-link
        readonly
        label="薪资范围"
        placeholder="请选择可以接收薪资范围"
        @click="show2 = true"
      />
      <van-popup v-model="show2" round position="bottom">
        <van-picker ref="picker" show-toolbar title="薪资范围" :columns="columns1" @confirm="clickItem"/>
      </van-popup>
      
      <van-row>
          <van-col span="10"><span style="font-size:14px;font-weight:530">历史记录</span></van-col>
          <van-col span="2" offset="12">
            <van-icon name="delete" @click="clearHistory"/>
          </van-col>
      </van-row>
      <van-col span="12" v-for="item in historyList" :key="item" style="margin-top:10px;">
        {{item}}
      </van-col>
    </div>
</div>
</template>
<script>
import Vue from 'vue';
import {Toast} from 'vant';
Vue.use(Toast);
export default {
  name: 'Search',
  data () {
    return {
      value:"",
      historyList:[],   
      showResult:false,
      historyCourse:[],
      picIp:'http://localhost:8080/pic/',
      result: [],
      list: ['小学数学', '小学语文',"小学英语"],
      list1: ['初中数学', '初中语文',"初中英语","初中化学","初中物理"],
      result: [],
      value: '',
      showPicker: false,
      columns: ['小学', '初中','高中'],
      columns1: [
      // 第一列
      {
        values: ['预计可接受最低薪水','250', '300', '350', '400', '500'],
        defaultIndex: 2,
      },
      // 第二列
      {
        values: ["预计可接受最高薪水",'200', '300', '400'],
        defaultIndex: 1,
      },
      ],
      items:[
          {
            // 导航名称
            text: '小学',
            // 导航节点额外类名
            className: 'my-class',
            // 该导航下所有的可选项
            children: [
              {
                // 名称
                text: '小学数学',
                // id，作为匹配选中状态的标识符
                id: 1,
              },
              {
                text: '小学语文',
                id: 2,
              },
              {
                text: '小学英语',
                id: 3,
              },
            ],
          },
         {
            // 导航名称
            text: '初中',
            // 导航名称右上角徽标，2.5.6 版本开始支持
            // 是否在导航名称右上角显示小红点
            // 导航节点额外类名
            className: 'my-class',
            // 该导航下所有的可选项
            children: [
              {
                // 名称
                text: '初中数学',
                // id，作为匹配选中状态的标识符
                id: 1,
              },
              {
                text: '初中语文',
                id: 2,
              },
              {
                text: '初中英语',
                id: 3,
              },
              {
                text: '初中物理',
                id: 4,
              },
              {
                text: '初中化学',
                id: 5,
              },
            ],
          },
      ],
      activeIds: [],
      activeIndex: 0,
      show1: false,
      show2:false,
      show3:false,
      fieldValue: '',
      cascaderValue: '',
      // 选项列表，children 代表子选项，支持多级嵌套
      options: [
        {
          text: '浙江省',
          value: '330000',
          children: [{ text: '杭州市', value: '330100' }],
        },
        {
          text: '江苏省',
          value: '320000',
          children: [{ text: '南京市', value: '320100' }],
        },
      ],
      fieldValue1:'',
      fieldValue2:'',
      listcopy:[],
      prefix:0,
      list3:[1,2,3,4,5,6,7],
    }
  },
  created(){
    var history = localStorage.getItem("history");
    if(history!=null)
      this.historyList = history.split(",");

  },
  methods:{
    clearHistory(){
      this.historyList=[];
      localStorage.setItem("history","");
    },
    onSearch(val){
      this.axios.get("c?op=searchCourse&value="+val).then((response)=>{
            this.historyCourse = response.data;


          })
      this.showResult = true;
      for(var  i = 0; i < this.historyList.length; i++)
          if(val == this.historyList[i]){
            this.historyList.splice(i,1);
            break;
          }
      this.historyList.splice(0,0,val);
      localStorage.setItem("history",this.historyList.toString());
    },
    checkAll() {
      this.$refs.checkboxGroup.toggleAll(true);
    },
    toggleAll() {
      this.$refs.checkboxGroup.toggleAll();
    },
    toggle(index) {
      this.$refs.checkboxes[index].toggle();
    },
    onConfirm(value) {
      this.value = value;
      this.list = this.list1;
      this.showPicker = false;
    },
    onFinish({ selectedOptions }) {
      this.show = false;
      console.log(selectedOptions);
      this.fieldValue = selectedOptions.map((option) => option.text).join('/');
    },
    clickItem(value){
      this.show2 = false;  
      console.log(value); 
      this.fieldValue2 = '';
      this.fieldValue2 += `${value[0]}~${value[1]}`;
      /*   
      this.axios.post("getitem/value=",{
        "value":s
      }).then((resp)=>{
        console.log("dsadsadsa");
      })*/
     },
    clickLeft(index){
      this.activeIds.splice(0,this.activeIds.length);
    },
    clickRight(data){
       // this.fieldValue1 = data.map((option) => option.text).join('/');
        let index  = 0;
        this.fieldValue1 = '';
        console.log(data);
        console.log(this.activeIds);
        this.activeIds.forEach((item)=>{
          index = this.activeIds.indexOf(item);
          if(index == (this.activeIds.length-1)){
            console.log("length="+this.activeIds.length);
            console.log(this.items[this.activeIndex].children[item-1].text);
            this.fieldValue1+=`${this.items[this.activeIndex].children[item-1].text}`;
          }else
            this.fieldValue1+=`${this.items[this.activeIndex].children[item-1].text}/`;
        })
    }
  }
}
</script>
<style scoped>
.history{
  padding-left:10px;
}
</style>