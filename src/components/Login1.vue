<template>
<div class="settings">
  <van-form @submit="onSubmit" label-align="center" scroll-to-error="true">
    <van-field name="uploader" label="" input-align="center" >
      <template #input>
        <van-uploader upload-text="点击上传头像" v-model="uploader" :max-count="1" :before-read="beforeRead">
        </van-uploader>
      </template>
    </van-field>
    <van-field
      v-model="username"
      name="用户名"
      label="用户名"
      placeholder="请填写账户名称"
      :rules="[{ required: true,  }]"
      @blur="checkName"
    />
    <van-field
      v-model="password"
      type="password"
      name="密码"
      label="密码"
      placeholder="请填写账户密码"
      :rules="[{ required: true }]"
    />
    <van-field
      v-model="repassword"
      type="password"
      name="密码"
      label="确认密码"
      placeholder="请确认账户密码"
      :rules="[{ required: true, }]"
    />
    <van-field name="radio" label="注册类别">
      <template #input>
        <van-radio-group v-model="radio" direction="horizontal" @change="change">
          <van-radio name="0">我是学生</van-radio>
          <van-radio name="1">我是老师</van-radio>
        </van-radio-group>
      </template>
    </van-field>
    <div class="info" style="padding:0px 5px 0px 5px;background:white" v-if="showBytype">
      <van-collapse v-model="activeNames" >
        <van-collapse-item title="点击填写更多信息" name="1" ref="more">
          <van-field
            readonly
            clickable
            name="picker"
            :value="value"
            label="教师类别"
            placeholder="点击选择类别"
            @click="showPicker = true"
          />
          <van-popup v-model="showPicker" position="bottom">
            <van-picker
              title="选择教师类别"
              show-toolbar
              :columns="columns"
              @confirm="onConfirm1"
              @cancel="showPicker = false"
              @change="onChange"
            />
          </van-popup>
          <van-field name="uploader" label="上传专业教师凭证" input-align="center" >
            <template #input>
              <van-uploader upload-text="上传教师凭证" :disabled="showImage" v-model="uploader1" :max-count="1" >
              </van-uploader>
            </template>
          </van-field>

          <van-field
          v-model="teachAge"
          name="教龄"
          label="教龄"
          placeholder="请输入教龄"
          :rules="[{ required: true  }]"
          />
          <van-field
          v-model="teachGrade"
          name="教师学历"
          label="教师学历"
          placeholder="请输入学历"
          :rules="[{ required: true  }]"
          />
          <van-field name="uploader" label="上传学历证书" input-align="center" >
            <template #input>
              <van-uploader upload-text="上传学历证书" v-model="uploader2" :max-count="1"  >
              </van-uploader>
            </template>
          </van-field>
          <van-field
          v-model="teachmajor"
          name="教师专业"
          label="教师专业"
          placeholder="请输入专业名称"
          :rules="[{ required: true,  }]"
          />
          <van-field name="uploader" label="上传简历证书" input-align="center" >
            <template #input>
              <van-uploader upload-text="请上传简历证书" v-model="uploader3" :max-count="1">
              </van-uploader>
            </template>
          </van-field>
          <!---->
        </van-collapse-item>
        <van-collapse-item title="点击填写可以教授的课程信息" name="2" ref="more">
          <van-row>
            <van-col span="6"><p style="color:#000" >已选课程</p></van-col>
            <van-col span="18">
              <p v-if="choose == false" style="color:#000">暂无选课，请点击课程添加</p>
              <div v-if="choose == true">
              <p style="color:#000" v-for="item in chooseClass" :key="item.course_id">
                {{item.course_name}}(最低薪资{{item.lowsalary}}~最高薪资{{item.highsalary}})
              </p>
              </div>
            </van-col>
          </van-row>
          <van-row>
            <van-field
              v-model="fieldValue1"
              is-link
              readonly
              label="课程"
              placeholder="请选择可以教授的课程"
              @click="showshowAndHide"
            />
            <van-popup v-model="show4" round position="bottom">
              <van-form @submit="onSubmit1">
                <van-field
                  v-model="Classes"
                  type="text"
                  name="classes"
                  label="选择课程"
                  placeholder="请选择课程"
                  @click="chooseClasses"
                  readonly
                  clickable
                />
                <van-popup v-model="showPicker1" position="bottom">
                  <van-picker
                    show-toolbar
                    :columns="ClassName"
                    @confirm="onConfirm2"
                    @cancel="onCancel"
                  />
                </van-popup>
                <van-field
                  v-model="lowSalary"
                  type="text"
                  name="lowsalary"
                  label="课程最高薪水"
                  placeholder="请输入可接收的最低薪水"
                  :rules="[{ required: true, message: '请输入薪资范围' }]"
                />
                <van-field
                  v-model="highSalary"
                  type="text"
                  name="maxsalary"
                  label="课程最低薪水"
                  placeholder="请输入可接收的最高薪水"
                  :rules="[{ required: true, message: '请输入薪资范围' }]"
                />
                <div style="margin: 16px;" v-if="makeSure1">
                  <van-button round block type="info" native-type="submit">
                    确认
                  </van-button>
                </div>
              </van-form>
            </van-popup>
          </van-row>
        </van-collapse-item>
      </van-collapse>

    </div>
    <div style="margin: 16px;" class="sureBtn" v-if="makeSure">
        <van-sticky :container="container">
            <van-button round block type="info" native-type="submit">{{process}}</van-button>
        </van-sticky>
    </div>
    </van-form>

</div>
</template>

<script>
import Vue from 'vue';
import { Form, Toast } from 'vant';

Vue.use(Form);
export default {
  name: 'Login1',
  data () {
    return {
      username: '',
      password: '',   
      repassword:'', 
      radio:'0',
      activeNames: ['0'], 
       value: '',
      showArea: false,
      areaList: {}, // 数据格式见 Area 组件文档
      container:'.settings',
      type:'',
      detLocation:'',
      columns: ['大学生教员', '专业教师'],
      value: '',
      teachAge:'',
      showPicker: false,
      showPicker1:false,
      teach_type:'0',//教师的类型
      process:'注册账号',
      teachmajor:'',
      uploader:[],
      uploader1:[],
      uploader2:[],
      uploader3:[],
      showBytype:false,
      showImage:false,
      upload_txt:'上传教师凭证',
      teachGrade:'',
      show: false,
      fieldValue: '',
      fieldValue1: '',
      cascaderValue: '',
      makeSure:true,
      show4:false,
      // 选项列表，children 代表子选项，支持多级嵌套
      lowSalary:'',
      highSalary:'',
      makeSure1:true,
      ClassList:[],
      ClassName:[],
      result:['a','b','c'],
      choose:false,
      chooseClass:[],
      tempclass:{},
      number:[],
      l_salary:[],
      h_salary:[],

    }
  },
  created(){
    this.getClassList();
  },
  methods:{
    checkName(){
      if(this.username.length > 0){
        this.axios.get("t-student/checkName?username="+this.username+"&type="+this.radio).then((response)=>{
          if(response.data.status == "1"){
           
              Toast("用户昵称可以使用");
          }else{
             this.username = '';
            Toast("用户昵称重复，请重试");
          }
        })
      }
    },
    getClassList(){
      this.axios.get("t-course/getCourseList").then((response)=>{
          this.ClassList = response.data;
          this.ClassList.forEach((item)=>{
              item.isChoose = false;
              this.ClassName.push(item.course_name);
          });
          console.log(this.ClassName);
      })
    },
    chooseClasses(){
      this.showPicker1 = true;
      this.lowSalary = '';
      this.highsalary = '';
      this.Classes = '';
      this.makeSure1 = false;
    },
    onCancel(){
      this.showPicker1= false;
      let _this = this;
       setTimeout(function(){
        _this.makeSure1 = true;
      },200);
    },
    onConfirm2(val){
      this.Classes = val;
      this.showPicker1= false;
      let _this = this;
      setTimeout(function(){
        _this.makeSure1 = true;
      },200);
      this.ClassList.forEach((item)=>{
      if(item.course_name == this.Classes){
          item.isChoose = true;
          this.tempclass.course_id = item.course_id;
          this.tempclass.course_name = item.course_name;
        }
      })
    },
    showshowAndHide(){
      this.makeSure = false;
        this.show = true;
        this.show4 = true;
        
    },
    onSubmit1(value){
      this.show4 = false;
      let _this = this;
      this.choose = true;
      setTimeout(function(){
        _this.makeSure = true;
        console.log("setTimeout");
      },1000);
      this.tempclass.lowsalary = this.lowSalary;
      this.tempclass.highsalary = this.highSalary;
      this.chooseClass.push(this.tempclass);
      this.number.push(this.tempclass.course_id);
      this.l_salary.push(this.tempclass.lowsalary);
      this.h_salary.push(this.tempclass.highsalary);
    },
    onSubmit(values) {
          console.log('submit', values);
          
          if(this.password!=this.repassword){
              Toast("密码和确认密码请保持一致");
              return ;
          }

          if(this.radio == '0'){
            let formData = new FormData();
            formData.append("imgFile",this.uploader[0].file);
            this.axios.post("t-student/regist?stuAccount="+this.username+"&stuPwsd="+this.password
              ,formData,{'Content-Type':'multipart/form-data'}).then((response)=>{
                this.$router.push("/MyInfo");
            })
            //this.$router.push("/CompleteInfo");
          }else{
            let formData = new FormData();
            console.log("value="+this.value);
            formData.append("imgFile",this.uploader[0].file);
            formData.append("teachCertify",this.uploader1[0].file); 
            formData.append("gradeCertify",this.uploader2[0].file); 
            formData.append("breifFile",this.uploader3[0].file);
            this.axios.post("t-teachaccount/regist?teachAccount="+this.username
            +"&teachPwsd="+this.password+"&teachGrade="+this.teachGrade
            +"&teachMajor="+this.teachmajor+"&teachExperience="+this.teachAge
            +"&teachType="+this.teach_type+"&number="+this.number+"&l_salary="+this.l_salary+"&h_salary="+this.h_salary
              ,formData,{'Content-Type':'multipart/form-data'}).then((response)=>{
                //this.$router.push("/MyInfo");
                //console.log("abc");
                if(response.data == "ok"){
                  this.$router.push("/CompleteInfo");      
                }else{
                  Toast("出现错误，请重试");
                }


            })
            //this.$router.push("/CompleteInfo");
          }

          
    },
     onConfirm(values) {
      this.value = values
        .filter((item) => !!item)
        .map((item) => item.name)
        .join('/');
      this.showArea = false;
    },
    change(name){
      if(name == '0'){
        this.process = '提交';
        this.showBytype = false;
        /*
        if(this.prev == false){
          this.$refs.more.toggle(false);
          console.log("有问题");
        }*/
        this.isDisabled=true;
      }else{
        this.showBytype = true;
        this.process = '下一步';
        this.$refs.more.toggle(true);
        this.isDisabled=false;
        this.prev = false;
       
        this.process = '下一步';
      }
    },
    profess(name){
      

    },
     onConfirm1(value) {
      this.value = value;
      if(this.teach_type == '0'){
        this.showImage=true;
        Toast('大学生教员无需上传教师凭证');
      }else{
         this.showImage=false;
        Toast('专业教师请上传教师凭证');
      }
      this.showPicker = false;
      
    },
    onChange(picker, value, index) {
      this.teach_type = index;
      Toast(`当前值：${value}, 当前索引：${index}`);
    },
    beforeRead(file){
      if(file.type !== 'image/jpeg'){
        Toast('请上传jpg格式的图片');
        return false;
      }
        return true;
    },
    onFinish({ selectedOptions }) {
      this.show = false;
      this.fieldValue = selectedOptions.map((option) => option.text).join('/');
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.van-button{
  z-index: 100;
}
</style>