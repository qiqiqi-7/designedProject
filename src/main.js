// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vant from 'vant';
import 'vant/lib/index.css';
//import "vant/lib/index.less";
import { Lazyload } from 'vant';
import { Search } from 'vant';
import axios from 'axios'
import VueAxios from 'vue-axios'


Vue.use(VueAxios, axios)
Vue.use(Search);
Vue.use(Lazyload);
Vue.use(Vant);
// import { Lazyload } from 'vant';
// import { Search } from 'vant';

// Vue.use(Search);
// Vue.use(Lazyload);
// Vue.config.productionTip = false
Vue.filter("Filter",function(dateStr,pattern=""){
  var dt = new Date(dateStr);
  var y = dt.getFullYear();
  var  m = dt.getMonth()+1;
  var d = dt.getDate();
  if(pattern.toLowerCase()=="yyyy-mm-dd")
      return `${y}年${m}月${d}日`
    else  
    {
      var hh =dt.getHours();
      var mm = dt.getMinutes();
      var ss = dt.getSeconds();
      return `${y}年${m}月${d}日  ${hh}:${mm}:${ss}`  
    }
});
axios.defaults.baseURL = 'http://localhost:3700/education'
axios.defaults.withCredentials=true
Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
