<!-- 帖子管理设计 -->
<template>
    <div>
        <!-- 搜索栏设计 -->
        <div style="display: flex">
            <!-- 搜索帖子内容输入框 -->
            <el-input style="width: 300px" placeholder="请输入你想搜索的帖子内容？" v-model="pageRequest.q.content" clearable></el-input>
            <!-- 搜索帖子标题输入框 -->
            <el-input style="width: 300px;margin-left: 5px" placeholder="请输入你想搜索的标题？" v-model="pageRequest.q.title" clearable></el-input>
            <!-- 搜索按钮 -->
            <el-button @click="load" type="primary" style="margin-left: 5px">搜索</el-button>
        </div>


        <!-- 批量删除按钮设计 -->
        <div style="display: flex;margin-top: 10px">
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
            <template #reference>
                <el-button>批量删除</el-button>
            </template>
            </el-popconfirm>
        </div>


        <!-- 帖子列表展示 -->
        <el-table border :data="tableData" @selection-change="handleSelectionChange">
            <!-- 选择列 -->
            <el-table-column type="selection" width="55"></el-table-column>
            <!-- 发布人名称列 -->
            <el-table-column label="发布人名称" width="100" prop="userName"></el-table-column>
            <!-- 发布人头像列 -->
            <el-table-column label="发布人头像" width="200">
            <template #default="{row,$index}">
                <!-- 显示发布人头像，如果存在头像则显示 -->
                <el-image preview-teleported v-if="row.userImage"
                :preview-src-list="[row.userImage]" fit="cover" :src="row.userImage"></el-image>
            </template>
            </el-table-column>
            <!-- 帖子标题列 -->
            <el-table-column label="标题" prop="title"></el-table-column>
            <!-- 发布图片列 -->
            <el-table-column width="200" label="发布图片">
            <template #default="{row,$index}">
                <!-- 显示帖子的第一张图片，如果存在图片则显示 -->
                <el-image preview-teleported v-if="row.menuImages && row.menuImages.length>0"
                :preview-src-list="row.menuImages" fit="cover" :src="row.menuImages[0]"></el-image>
            </template>
            </el-table-column>
            <!-- 操作列 -->
            <el-table-column label="操作">
            <template #default="{row,$index}">
                <!-- 删除按钮，点击后弹出确认框 -->
                <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                <template #reference>
                    <el-button size="small">删除</el-button>
                </template>
                </el-popconfirm>
            </template>
            </el-table-column>
        </el-table>


        <!-- 分页组件设计 -->
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
</template>

<script setup>
 import MyDrawer from "@/components/MyDrawer.vue"; 
 import {ref, onMounted} from "vue"; 
 import http, {baseURL} from "@/utils/Request.js"; 
 import {Plus} from "@element-plus/icons-vue"; 
 import {dbImages, splicingDomain} from "@/utils/Base.js"; 
 import {ElNotification} from "element-plus"; 

const pageSizeArr = ref([10, 20, 30, 40])// 定义每页显示的评论数量选项
const total = ref(0)// 定义评论总数
const selectRow = ref({})// 定义当前选中的评论对象
const myDrawerRef = ref()// 创建MyDrawer组件的引用
const pageRequest = ref({ pageNum: 1, pageSize: 10, q: {} })// 定义分页请求参数的对象，包括当前页码、每页数量以及查询条件（q）
const delIds = ref([])// 定义要批量删除的评论id数组
const tableData = ref([])// 定义数据源，用于存储评论列表数据

 //加载数据
 onMounted(() => {
   load()
 })

 // 批量删除操作
 const delBatch = async () => {
   // 如果没有选中任何数据，则提示用户先勾选数据
   if (!delIds.value || delIds.value.length <= 0) {
     ElNotification({title: "警告", message: "请先勾选数据", type: "warning"})
     return
   }
   await http.post("/postCode/deleteBatchByIds", delIds.value, "删除成功！")
   await load()
 }

 // 处理表格选择改变事件
 const handleSelectionChange = (e) => {
   delIds.value = e.map(x => x.id)
 }

 // 删除单个帖子操作
 const del = async (id) => {
   await http.del("/postCode/" + id)
   await load()
 }

 // 处理每页显示条数改变事件
 const handleSizeChange = (size) => {
   pageRequest.value.pageSize = size
   load()
 }

 // 处理当前页码改变事件
 const handleCurrentChange = (pageNum) => {
   pageRequest.value.pageNum = pageNum
   load()
 }

 // 加载数据操作
 const load = async () => {
   const res = await http.post("/postCode/page", pageRequest.value, "-1")
   tableData.value = res.data.list
   total.value = res.data.total
   // 对每条数据中的图片URL进行拼接域名处理
   tableData.value.forEach(item => {
     if (item.menuImages) {
       item.menuImages = splicingDomain(item.menuImages)
     }
   })
 }
</script>
