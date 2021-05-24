<!--
<template>
<div class="entrance">
    <van-nav-bar title="家长入口" left-text="返回" @click-left="onClickLeft">
       <template #left>
          <van-icon name="arrow-left" size="18"/>
      </template>
    </van-nav-bar>
    <van-cell-group>
      <van-cell title="密码设置" to="/MyInfo">
          <template #extra>
              <van-icon name="arrow" color="red" size="20" />
          </template>
      </van-cell>
  </van-cell-group>
</div>
</template>
-->
<!--
<script>
// import Vue from 'vue';
// import { Swipe, SwipeItem } from 'vant';
// import { Card } from 'vant';

// Vue.use(Card);
// Vue.use(Swipe);
// Vue.use(SwipeItem);
export default {
  ame: 'Entrance',
  data () {
    return {
           
    }
    },
  methods:{
        onClickLeft(){
            this.$router.push("Setting");
        }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--
<style scoped>
.van-nav-bar{
  background:linear-gradient(to top,#fff,#fef08f);
}
</style>-->
<template>
  <div class="hello">
    <!-- 主体区域 -->
    <div class="view-container pdt70">
      <section>
        <!-- 缺省图 · 状态 -->
        <!-- <van-image
            width="10rem"
            height="10rem"
            fit="cover"
            lazy-load
            src="https://img.yzcdn.cn/vant/cat.jpeg"
            alt="缺省图"
          />
          <p class="status_tips">支付成功！</p> -->
        <van-empty
          class="iconpic"
          image="../../../static/images/icon_pay_success.jpg"
          description="支付成功！"
        >          
          <div class="btnbox">
            <van-button class="h44" size="small" color="linear-gradient(to right, #2FB1E7, #0048A9)">继续买菜</van-button>
            <van-button class="h44" size="small" color="#CECECE" to="/" plain>我的篮子</van-button>
          </div>
        </van-empty>
      </section>
    </div>


  </div>
</template>
<script>
import Vue from 'vue';
// import { Lazyload } from 'vant';
// Vue.use(Lazyload);
import { Empty } from 'vant';
Vue.use(Empty);

  export default {
    name: "VIPStatus",
    data () {
      return {

      }
    },
    mounted () {

    },
    methods: {

    }
  }
</script>
<style scoped>
.status_tips{font-size: 30px;color: #333333;font-weight: 600;}
.btnbox{text-align: center;padding-top: 70px;}
.btnbox .van-button{margin: 10px 12px;border-radius: 5px;}
.btnbox .van-button.h44{font-size: 18px!important;min-width: 147px!important;}


</style>
<style>
/* 缺省图 · 状态 */
.custom-status-image .van-empty__image {
    width: 230px;
    height: auto;
}
.custom-status-image .van-empty__description{font-size: 30px;color: #0048A9;font-weight: 600;}

</style>
