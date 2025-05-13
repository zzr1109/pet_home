<template>
  <div style="display: flex;align-items: center;margin-top: 10px">
    <el-container>
      <el-aside width="200px">
        <div v-for="item in tagList" :key="item.name"
             :class="{active:currentItem === item.name}"
             style="width: 100%;background-color: #fff;margin-bottom: 20px;height: 40px;display: flex;align-items: center;justify-content: center;color: #000;cursor: pointer"
             @click="handlerSwitchClick(item)">
          {{ item.name }}
        </div>
      </el-aside>
      <el-main style="padding: 0 0 0 10px;overflow: hidden">
        <MyOrder v-if="currentItem === '我的订单'"></MyOrder>
        <MyAdoption v-else-if="currentItem === '我的领养'"></MyAdoption>
        <MyReservation v-else-if="currentItem === '我的预约'"></MyReservation>
        <MyPost v-else-if="currentItem === '我的发帖'"></MyPost>
        <MyComment v-else-if="currentItem === '我的评论'"></MyComment>
        <PersonalInformation v-else></PersonalInformation>
      </el-main>
    </el-container>
  </div>
</template>
<script setup>

import {reactive, ref} from "vue";
import MyComment from "./components/MyComment.vue";
import MyInfo from "../Pet/components/MyInfo.vue";
import MyPost from "./components/MyPost.vue"
import MyOrder from './components/MyOrder.vue'
import MyAdoption from './components/MyAdoption.vue'
import MyReservation from './components/MyReservation.vue'
import PersonalInformation from './components/PersonalInformation.vue'

const tagList = reactive([
  {
    name: '我的订单',
    component: 'MyOrder'
  },
  {
    name: '我的预约',
    component: 'MyReservation'
  },
  {
    name: '我的领养',
    component: 'MyAdoption'
  },
  {
    name: '我的发帖',
    component: 'MyPost'
  },
  {
    name: '我的评论',
    component: 'MyComment'
  }
])
const currentItem = ref('我的订单')
const handlerSwitchClick = (val) => {
  currentItem.value = val.name
}
</script>
<style lang="scss" scoped>
.active {
  background-color: #E9AF65 !important;
  color: #fff !important;
}
</style>