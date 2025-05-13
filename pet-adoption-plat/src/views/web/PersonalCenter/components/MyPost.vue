<template>
  <div>
    <!--帖子列表-->
    <div style="width: 1200px;">
      <el-collapse style="margin-top: 10px" v-model="activeIndex">
        <el-collapse-item v-for="(item,index) in tableData" :key="item.id" :name="index">
          <template #title>
            <div style="display: flex;flex-direction: column">
              <div style="display: flex">
                <img :src="item.userImage" style="height: 45px;width: 45px">
                <div style="margin-left: 10px">
                  <p style="line-height: 1.2;text-align: left;font-size: 16px;font-weight: bold;">{{ item.userName }}</p>
                  <p style="line-height: 1.2;color: rgba(54,54,54,1);margin-top: 10px">{{dateFormatHm(item.createTime)}}发布</p>
                </div>
              </div>
              <div style="text-align: left;width: 1000px;margin-top: 10px;font-size: 14px">
                <p style="line-height: 1.5">{{item.content}}</p>
              </div>
              <!--图片-->
              <div style="text-align: left;width: 1000px;margin-top: 10px;font-size: 14px" v-if="item.menuImages && item.menuImages.length > 0">
                <el-image v-for="itemImage in item.menuImages" :preview-src-list="[baseURL() + itemImage]" :src="baseURL() + itemImage" style="width: 150px;height: 150px;margin-right: 5px" fit="cover"></el-image>
              </div>
            </div>
          </template>
          <template #icon>
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(item.id)">
              <template #reference>
                <el-button style="position: absolute;right: 40px;top: 20px;" type="danger">删除帖子</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-collapse-item>
      </el-collapse>
      <div style="margin-top: 10px">
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
  </div>
</template>
<script setup>
import {ref, onMounted} from "vue";
import http, {baseURL} from "@/utils/Request.js";
import {dateFormatHm} from "@/utils/DateUtil.js";
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
const tableData = ref([])
const total = ref(0)
const pageSizeArr = ref([10, 20, 30, 40])
const activeIndex = ref([])

onMounted(() => {
  load()
})

// 删除帖子
const del = async (postId) => {
  await http.get("/plat/personCenter/delPostById?postId=" + postId)
  await load()
}

// 获取我的帖子列表
const load = async () => {
  const res = await http.post("/plat/personCenter/postMyPage", pageRequest.value, "-1")
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

<style scoped lang="scss">
.icon-ele {
  margin: 0 8px 0 auto;
  color: #409eff;
}
::v-deep(.el-collapse-item__header) {
  padding: 30px;
  height: auto;
  position: relative;
}
::v-deep(.el-collapse) {
  margin-top: 0 !important;
}
</style>