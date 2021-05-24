<template>
<div class="chat">
   <van-nav-bar :title="`${username}老师`"  left-arrow>
     <template #left>
       <van-icon name="arrow-left" color="#ee0a24" size="25"/>
     </template>
     <template #title>
      <h4>{{username}}老师</h4>
     </template>
   </van-nav-bar>
   <div class="content">
     <van-popover
      placement="right"
      v-model="showPopover"
      trigger="click"
      :actions="actions"
      @select="onSelect"
    >
      <template #reference>
        <van-image
          round
          width="55px"
          height="55px"
          src="https://img01.yzcdn.cn/vant/cat.jpeg"
          style="float:left"
        />
        <span style="position:relative;top:0px;float:left">duanyujie</span>
      </template>
      
      <template #default>
      <div>
          <p>dsadsadsadsa</p>
          dsadsadsadasdssssssssss
        </div>
      </template>
    </van-popover>
   </div>





</div>
</template>

<script>
// import Vue from 'vue';
// import { Swipe, SwipeItem } from 'vant';
// import { Card } from 'vant';

// Vue.use(Card);
// Vue.use(Swipe);
// Vue.use(SwipeItem);
export default {
  name: 'Chat',
  data () {
    return {
      username:'张青凤',
      showPopover: true,
      // 通过 actions 属性来定义菜单选项
      actions: [{ text: '同学你好，我是张青凤老师，请问有什么可以帮助你吗？' }],
    }
  },
  methods:{
    onSelect(action) {
      Toast(action.text);
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content{
  padding-top: 10px;
  padding-left:10px;
}

.van-popup,.van-popover,.van-popover--light{
    position: relative;
    left: -70px;
    top: 26px;
}


</style>