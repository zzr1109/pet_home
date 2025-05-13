<template>
  <div>
    <el-table class="xw-block-table" :data="tableData">
      <el-table-column prop='userPhone' label='联系方式'></el-table-column>
      <el-table-column prop='userAddress' label='领养人地址'></el-table-column>
      <el-table-column prop='petCenterName' label='宠物名称'></el-table-column>
      <el-table-column width='120' label='领养状态'>
        <template #default="{row,$index}">
          <span v-if='row.adoptionStatus===0'>领养中</span>
          <span v-if='row.adoptionStatus===1'>已归还</span>
          <span v-if='row.adoptionStatus===2'>申请中</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{row,$index}">
          <el-button :disabled="row.adoptionStatus!==0" size="small" @click="opStatus(row,1)">归还宠物</el-button>
          <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
            <template #reference>
              <el-button :disabled="row.adoptionStatus===0" size="small">删除</el-button>
            </template>
          </el-popconfirm>
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
  let obj = JSON.parse(JSON.stringify(row))
  obj.adoptionStatus = status
  await http.post("/petAdoptionCenter/opStatus",obj)
  await load()
}

const del = async (id) => {
  await http.del("/petAdoptionCenter/" + id,null,'-1')
  await load()
}
const saveOrUpdate = async (e) => {
  await http.post("/petAdoptionCenter", selectRow.value,'操作成功！')
  myDrawerRef.value.close()
  await load()
}
const load = async () => {
  const res = await http.post("/plat/personCenter/adoptPage", pageRequest.value,'-1')
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