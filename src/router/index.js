import Vue from 'vue'
import Router from 'vue-router'
import Setting from '@/components/Setting'
import Settings from '@/components/Settings'
import Complete from '@/components/Complete'
import Entrance from '@/components/Entrance'
import Collect from '@/components/Collect'
import About from '@/components/About'
import MyInfo from '@/components/MyInfo'
import Search from '@/components/Search'
import Main from '@/components/Main'
import Content from '@/components/Content'
import Regist from '@/components/Regist'
import Login from '@/components/Login'
import ShowVideo from '@/components/ShowVideo'
import Index from '@/components/Index'
import MyCourse from '@/components/MyCourse'
import Login1 from '@/components/Login1'
import welcome from '@/components/welcome'
import NewIndex from '@/components/Newindex'
import CompleteInfo from '@/components/CompleteInfo'
import TeachInfo from '@/components/TeachInfo'
import Chat from '@/components/Chat'
import TestMyInfo from '@/components/TestMyInfo'
Vue.use(Router)
export default new Router({
  mode:'history',
  routes: [
    {
      path:'/Setting',
      name:'Setting',
      component:Setting
     },
     {
        path:'/Entrance',
        name:'Etrance',
        component:Entrance
    },
    { 
        path:'/Collect',
        name:'Collect',
        component:Collect
    },
    {
      path:'/Settings',
      name:'Settings',
      component:Settings
    },
    {
      path:'/About',
      name:'About',
      component:About
    },
    {
      path:'/MyInfo',
      name:'MyInfo',
      component:MyInfo
    },
    {
      path:'/Search',
      name:'Search',
      component:Search
    },
    {
      path:'/Main',
      name:'Main',
      component:Main
    },{
      path:'/Content',
      name:'Content',
      component:Content
    },{
      path:'/Regist',
      name:'Regist',
      component:Regist
    },{
      path:'/Login',
      name:'Login',
      component:Login
    },
    {
      path:'/ShowVideo/:videoId',
      name:'ShowVideo',
      component:ShowVideo
    },{
      path:'/MyCourse',
      name:'MyCourse',
      component:MyCourse
    },{
      path:'/Login1',
      name:'Login1',
      component:Login1
    },{
      path:'/',
      name:'welcome',
      component:welcome
    },{
      path:'/Complete',
      name:'Complete',
      component:Complete
    },{
      path:'/Index',
      name:'Index',
      component:Index
    },{
      path:'/NewIndex',
      name:'NewIndex',
      component:NewIndex
    },
    {
      path:'/CompleteInfo',
      name:'CompleteInfo',
      component:CompleteInfo
    },
    {
      //path:'/TeachInfo:teachInfoId',
      path:'/TeachInfo',
      name:'TeachInfo',
      component:TeachInfo
    },
    {
      path:'/Chat',
      name:'Chat',
      component:Chat
    },
    {
      path:'/TestMyInfo',
      name:'TestMyInfo',
      component:TestMyInfo
    },
  ]
})
