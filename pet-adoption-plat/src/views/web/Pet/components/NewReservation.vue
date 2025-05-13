<template>
   <div>
       <p style="display: flex;padding-right: 5px">
           <img src="@/assets/images/推荐宠物.png" style="height: 30px;width: 30px">
           <span style="align-self: center;color: var(--accent-200);margin-left: 10px;font-size: 18px;font-weight: bold">最新预约</span>
       </p>
       <div v-for="item in tableData" :key = item.id style="display: flex;padding-bottom: 10px;padding-top: 10px;border-bottom: 1px solid var(--accent-200)">
           <img :src="item.userImage" style="height: 120px;width: 120px;border-radius: 5px">
           <div style="margin-left: 10px;font-size: 14px">
               <p>领养人：{{item.userName}}</p>
               <p>宠物名称：{{item.petCenterName}}</p>
               <p>预约时间：{{dateFormatHm(item.createTime)}}</p>
               <p>领养宣言：{{item.remark}}</p>
           </div>
       </div>
   </div>
</template>
<script setup>

import http from "@/utils/Request.js";
import {ref,onMounted} from "vue";
import { dateFormatHm } from "@/utils/DateUtil.js";

const tableData = ref([])

onMounted(() => {
    load()
})
const load = async () => {
    const res = await http.get("/plat/index/adoptList",null,"-1")
    tableData.value = res.data
}
</script>