<template>
<div class="settings">
    <h1>这是About</h1>

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
  name: 'About',
  data () {
    return {
        ids:[1,2,3]  
    }
  },
  created(){

    this.axios.get("t-course/test?ids="+this.ids).then((Response)=>[
      
    ])
  },
  methods:{





  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* .van-card_title{
  font-size:16px;
  line-height:30px;
}
.van-card_desc{
  font-size:13px;
  line-height: 25px;
}
.van-swipe-item img{
  width:100%;
  height:100%;
} */



</style>