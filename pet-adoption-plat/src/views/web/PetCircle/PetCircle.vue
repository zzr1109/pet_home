<template>
  <div style="padding: 20px">
    <div style="position: relative">
      <img src="https://www.2008php.com/2012_Website_appreciate/2012-11-15/20121115193256.jpg" style="width: 1400px;height: 300px;object-fit: cover"/>
      <el-button style="position: absolute;top: 10px;right: 10px" type="danger" @click="dailyShare">日常分享</el-button>
    </div>

    <div style="width: 1400px;padding-top: 20px">
      <p style="display: flex;padding-right: 5px">
        <img src="@/assets/images/推荐宠物.png" style="height: 30px;width: 30px">
        <span style="align-self: center;color: var(--accent-200);margin-left: 10px;font-size: 18px;font-weight: bold">宠物日常</span>
      </p>
      <el-collapse style="margin-top: 10px" @change="seeComment" v-model="activeIndex">
        <el-collapse-item v-for="(item,index) in tableData" :key="item.id" :name="index">
          <template #title>
            <div style="display: flex;flex-direction: column">
              <div style="display: flex">
                <img :src="item.userImage" style="width: 45px;height: 45px"/>
                <div style="margin-left: 10px">
                  <p style="line-height: 1.2;text-align: left;font-size: 16px;font-weight: bold">{{item.userName}}</p>
                  <p style="line-height: 1.2;color: rgba(54,54,54,1);margin-top: 10px">{{dateFormatHm(item.createTime)}}发布</p>
                </div>
              </div>
              <div style="text-align: left;width: 1200px;margin-top: 10px;font-size: 14px">
                <p style="line-height: 1.5">{{item.content}}</p>
              </div>
              <div style="text-align: left;width: 1200px;margin-top: 10px;font-size: 14px;" v-if="item.menuImages && item.menuImages.length > 0">
                <el-image v-for="itemImage in item.menuImages" :preview-src-list="[baseURL() + itemImage]" :src="baseURL() + itemImage" style="width: 150px;height: 150px" fit="cover"></el-image>
              </div>
            </div>
          </template>
          <template #icon>
            <span style="position: absolute;right: 40px;top: 20px;color: var(--accent-200)">查看评论</span>
          </template>
          <div style="padding-left: 20px;padding-right: 40px">
            <Comment :type-id="item.id" :type="0" :link-id="item.id" :ref="(el) => handleRef(el)"></Comment>
          </div>
        </el-collapse-item>

      </el-collapse>

      <div style="margin-top: 10px">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="pageSizeArr"
            :page-size="pageSizeArr[0]"
            layout="total,sizes,prev,pager,next,jumper"
            :total="total"
        >
        </el-pagination>
      </div>
    </div>

    <MyDrawer ref="myDrawerRef" title="日常分享" size="30%" @saveOrUpdate="saveOrUpdate" button-name="发布日常">
      <el-input type="textarea" :rows="6" placeholder="这一刻的想法..." v-model="selectRow.content"></el-input>
      <div style="margin-top: 10px">
        <el-tabs type="border-card">
          <el-tab-pane label="日常分享">
            <el-upload
                class="avatar-uploader"
                :action="baseURL() + '/file/upload'"
                :file-list="fileList"
                list-type="picture-card"
                :show-file-list="true"
                :on-success="handleAvatarSuccess"
                multiple
            >
              <el-icon class="avatar-uploader-icon"><Plus/></el-icon>
            </el-upload>
          </el-tab-pane>
        </el-tabs>
      </div>
    </MyDrawer>
  </div>
</template>
<script setup>
import Comment from "@/components/Comment.vue";
import MyDrawer from "@/components/MyDrawer.vue";
import {ref,onMounted} from "vue";
import http, {baseURL} from "@/utils/Request.js";
import {Plus} from "@element-plus/icons-vue";
import {dbImages} from "@/utils/Base.js";
import {dateFormatHm} from "../../../utils/DateUtil.js";

const myDrawerRef = ref()
const selectRow = ref({})
const fileList = ref([])
const activeIndex = ref(-1)
const pageRequest = ref({pageNum: 1,pageSize: 10,q: {}})
const tableData = ref([])
const total = ref(0)
const pageSizeArr = ref([10,20,30])

const refList = ref([])

const handleRef = (el) => {
  refList.value.push(el)
}

const seeComment = (indexArr) => {
  refList.value[indexArr[indexArr.length - 1]].expandComment()
}

onMounted(() => {
  load()
})

const dailyShare = () => {
  selectRow.value = {}
  fileList.value = []
  myDrawerRef.value.open()
}

const handleAvatarSuccess = (res) => {
  if(res.code === "200") {
    if (!selectRow.value.menuImages) {
      selectRow.value.menuImages = []
    }
    selectRow.value.menuImages.push(res.data)
  }
}

const saveOrUpdate = async () => {
  selectRow.value.imageUrlList = ""
  selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
  await http.post("/plat/post/posting",selectRow.value,"发布成功！")
  myDrawerRef.value.close()
  await load()
}

const load = async () => {
  const res = await http.post("/plat/post/postPage",pageRequest.value,"-1")
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

<style lang="scss" scoped>
::v-deep(.el-collapse-item__header) {
  padding: 30px;
  height: auto;
  position: relative;
}
</style>