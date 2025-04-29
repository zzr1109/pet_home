<!-- 宠物领养管理设计 -->
<template>
    <div>
        <!-- 搜索栏 -->
        <div style="display: flex">
            <!-- 输入框，输入搜索的宠物名称 -->
            <el-input v-model="pageRequest.q.petCenterName" style="width: 300px" placeholder="请输入你想搜索的宠物名称？" clearable></el-input>
            <!-- 搜索按钮 -->
            <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
            </div>
            <!-- 批量删除功能 -->
            <div style="display: flex;margin-top: 10px">
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <!-- 表格,展示宠物领养信息 -->
        <el-table border :data="tableData" @selection-change="handleSelectionChange">
        <!-- 多选框列 -->
        <el-table-column type="selection" width="55"></el-table-column>
        <!-- 领养人名称列 -->
        <el-table-column label="领养人名称" prop="userName" width="200"></el-table-column>
        <!-- 领养人头像列 -->
        <el-table-column label="领养人头像" width="150">
            <template #default="{row,$index}">
            <el-image v-if="row.userImage" :preview-src-list="[row.userImage]" :src="row.userImage" fit="cover"></el-image>
            </template>
        </el-table-column>
        <!-- 宠物名称列 -->
        <el-table-column label="宠物名称" prop="petCenterName" width="150"></el-table-column>
        <!-- 联系方式列 -->
        <el-table-column label="联系方式" prop="userPhone"></el-table-column>
        <!-- 联系人地址列 -->
        <el-table-column label="联系人地址" prop="userAddress" width="150"></el-table-column>
        <!-- 领养状态列 -->
        <el-table-column label="领养状态">
            <template #default="{row,$index}">
            <span v-if="row.status === 0">领养中</span>
            <span v-if="row.status === 1">已归还</span>
            <span v-if="row.status === 2">申请中</span>
            </template>
        </el-table-column>

        <!-- 操作列 -->
        <el-table-column label="操作">
            <template #default="{row,$index}">

            <!-- 强制归还按钮，当前功能待完成 -->
            <el-button size="small">强制归还（待完成）</el-button>

            <!-- 删除按钮 -->
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
        >
        </el-pagination>
        </div>
    </div>
</template>

<script setup>
  import MyDrawer from "@/components/MyDrawer.vue"; 
  import {ref,onMounted} from "vue"; 
  import http, {baseURL} from "@/utils/Request.js"; 
  import {Plus} from "@element-plus/icons-vue"; 
  import {dbImages, splicingDomain} from "@/utils/Base.js"; 
  import {ElNotification} from "element-plus"; 
  import {dateFormatHm} from "../../utils/DateUtil.js"; 

const pageSizeArr = ref([10, 20, 30, 40])// 定义分页每页显示条数选项
const total = ref(0)// 定义总记录数
const selectRow = ref({})// 定义选中的行
const myDrawerRef = ref()// 创建MyDrawer组件的引用
const pageRequest = ref({pageNum: 1,pageSize: 10,q: {}})// 定义分页请求参数，包含当前页码、每页显示条数和查询参数
const delIds = ref([])// 定义批量删除时需要删除的记录ID数组
const tableData = ref([])// 定义表格的数据源
  
  //加载数据
  onMounted(() => {
    load()
  })

  // 批量删除方法
  const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) { 
      ElNotification({title: "Warning",message: "请先勾选数据",type: "warning"}) 
      return
    }
    await http.post("/petAdoptionCenter/deleteBatchByIds",delIds.value,"删除成功！") 
    await load() 
  }

  // 处理选择改变事件
  const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id) 
  }

  // 删除方法
  const del = async (id) => {
    await http.del("/petAdoptionCenter/" + id) 
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

  // 加载数据方法
  const load = async () => {
    const res = await http.post("/petAdoptionCenter/page",pageRequest.value,"-1") // 发送获取分页数据的请求
    tableData.value = res.data.list 
    total.value = res.data.total 
  }
</script>
