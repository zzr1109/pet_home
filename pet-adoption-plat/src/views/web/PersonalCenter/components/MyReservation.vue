<template>
  <div>
    <el-table class="xw-block-table" :data="tableData">
      <el-table-column width='160' label='预约时间'>
        <template #default="{row,$index}">
          <span>{{dateFormatHm(row.createTime)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop='remark' width="400" label='领养宣言' show-overflow-tooltip></el-table-column>
      <el-table-column prop='petCenterName' label='宠物名称'></el-table-column>
      <el-table-column width='120' label='预约状态'>
        <template #default="{row,$index}">
          <span v-if='row.status===0'>预约中</span>
          <span v-if='row.status===1'>已预约</span>
          <span v-if='row.status===2'>已领养</span>
          <span v-if='row.status===3'>取消预约</span>
          <span v-if='row.status===4'>放弃领养</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{row,$index}">
          <el-button :disabled="row.whetherToAdoptOrNot" size="small" @click="del(row.id)">放弃预约</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="xw-block" style="margin-top: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="pageSizeArr"
          :page-size="pageSizeArr[0]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script setup>
import http from "@/utils/Request.js"
import { ref,onMounted } from "vue"
import {ElNotification} from "element-plus";
import { dateFormatHm } from "@/utils/DateUtil.js";
const myDrawerRef = ref()
const user = ref(JSON.parse(localStorage.getItem("user")))
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
onMounted(() => {
  load()
})
const opStatus = async (row,status) => {
  let obj =  JSON.parse(JSON.stringify(row))
  obj.status = status
  await http.post("/petReservation/opStatus",obj)
  await load()
}
const del = async (id) => {
  await http.del("/petReservation/" + id,null,'-1')
  await load()
}
const saveOrUpdate = async (e) => {
  await http.post("/petReservation", selectRow.value,'操作成功！')
  myDrawerRef.value.close()
  await load()
}
const load = async () => {
  const res = await http.post("/plat/personCenter/reservationPage", pageRequest.value,'-1')
  tableData.value = res.data.list
  total.value = res.data.total
}
const handleSizeChange = (size) => {
  pageRequest.value.pageSize = size
  load()
}
const handleCurrentChange = (pageNum) => {
  pageRequest.value.pageNum = pageNum
  load()
}
</script>