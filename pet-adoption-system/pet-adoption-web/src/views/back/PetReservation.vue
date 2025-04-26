<!-- 宠物领养预约管理设计 -->
<template>
    <div>
        <!-- 搜索栏 -->
        <div style="display: flex">
            <!-- 输入框，输入搜索的宠物名称 -->
            <el-input v-model="pageRequest.q.petCenterName" style="width: 300px" placeholder="请输入你想搜索的宠物名称？" clearable></el-input>
            <!-- 搜索按钮 -->
            <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
            </div>
            <!-- 批量删除按钮 -->
            <div style="display: flex;margin-top: 10px">
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <!-- 表格组件，展示预约信息 -->
        <el-table border :data="tableData" @selection-change="handleSelectionChange">
        <!-- 多选框列 -->
        <el-table-column type="selection" width="55"></el-table-column>
        <!-- 预约人名称列 -->
        <el-table-column label="预约人名称" prop="userName" width="200"></el-table-column>
        <!-- 预约人头像列 -->
        <el-table-column label="预约人头像" width="150">
            <template #default="{row,$index}">
            <!-- 如果row.userImage存在，则显示图片，支持预览 -->
            <el-image v-if="row.userImage" :preview-src-list="[row.userImage]" :src="row.userImage" fit="cover"></el-image>
            </template>
        </el-table-column>
        <!-- 领养宣言列 -->
        <el-table-column label="领养宣言" prop="remark" width="400" show-overflow-tooltip></el-table-column>
        <!-- 宠物名称列 -->
        <el-table-column label="宠物名称" prop="petCenterName" width="150"></el-table-column>
        <!-- 联系方式列 -->
        <el-table-column label="联系方式" prop="userPhone"></el-table-column>
        <!-- 联系人地址列 -->
        <el-table-column label="联系人地址" prop="userAddress" width="150"></el-table-column>
        <!-- 领养状态列 -->
        <el-table-column label="领养状态">
            <template #default="{row,$index}">
            <!-- 根据row.status的值显示不同的状态文本 -->
            <span v-if="row.status === 0">预约中</span>
            <span v-if="row.status === 1">已预约</span>
            <span v-if="row.status === 2">已领养</span>
            <span v-if="row.status === 3">取消预约</span>
            <span v-if="row.status === 4">放弃领养</span>
            </template>
        </el-table-column>
        <!-- 领养时间列 -->
        <el-table-column label="领养时间">
            <template #default="{row,$index}">
            <!-- 使用dateFormatHm方法格式化日期 -->
            <span>{{dateFormatHm(row.createTime)}}</span>
            </template>
        </el-table-column>

        <!-- 操作列 -->
        <el-table-column label="操作">
            <template #default="{row,$index}">

            <!-- 同意领养按钮，待实现功能 -->
            <el-button size="small">同意领养（待完成）</el-button>

            <!-- 删除按钮，点击时触发del方法 -->
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                <template #reference>
                <el-button size="small">删除</el-button>
                </template>
            </el-popconfirm>
            </template>
        </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <div style="margin-top: 10px">
        <el-pagination
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange" 
            :page-sizes="pageSizeArr" 
            :page-size="pageSizeArr[0]" 
            layout="total,sizes,prev,pager,next,jumper" 
            :total="total" 
        ></el-pagination>
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
import {dateFormatHm} from "../../utils/DateUtil.js"; 

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
  // 如果没有选择任何行，提示用户选择数据
  if (!delIds.value || delIds.value.length <= 0) {
    ElNotification({ title: "Warning", message: "请先勾选数据", type: "warning" })
    return
  }
  await http.post("/petReservation/deleteBatchByIds", delIds.value, "删除成功！")
  await load()
}

// 处理选择变化的操作
const handleSelectionChange = (e) => {
  delIds.value = e.map(x => x.id)
}

// 删除单行操作
const del = async (id) => {
  await http.del("/petReservation/" + id)
  await load()
}

// 处理每页显示数量变化的操作
const handleSizeChange = (size) => {
  pageRequest.value.pageSize = size
  load()
}

// 处理当前页码变化的操作
const handleCurrentChange = (pageNum) => {
  pageRequest.value.pageNum = pageNum
  load()
}

// 加载数据的操作
const load = async () => {
  const res = await http.post("/petReservation/page", pageRequest.value, "-1")
  tableData.value = res.data.list
  total.value = res.data.total
}
</script>
