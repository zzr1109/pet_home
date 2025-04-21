<!--评论区管理设计-->
<template>
    <div>
        <!-- 搜索功能区域 -->
        <div style="display: flex">
            <el-input v-model="pageRequest.q.content" style="width: 300px" placeholder="请输入你想搜索的评论内容？"clearable></el-input>
            <!-- 搜索按钮，点击触发load方法 -->
            <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
        </div>


        <!-- 批量删除功能区域 -->
        <div style="display: flex;margin-top: 10px">
            <!-- 创建弹出确认框，点击确认时触发delBatch方法 -->
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>


        <!-- 评论列表展示区域 -->
        <el-table border :data="tableData" @selection-change="handleSelectionChange">
            <!-- 添加复选框列，用户可以勾选多条评论以进行批量操作 -->
            <el-table-column type="selection" width="55"></el-table-column>
            <!-- 评论内容列 -->
            <el-table-column label="评论内容" prop="content" width="400" show-overflow-tooltip></el-table-column>
            <!-- 评论人名称列 -->
            <el-table-column label="评论人名称" prop="userName" width="200"></el-table-column>
            <!-- 评论时间列，显示格式化后的时间 -->
            <el-table-column label="评论时间">
                <template #default="{ row, $index }">
                    <span>{{ dateFormatHm(row.createTime) }}</span>
                </template>
            </el-table-column>
            <!-- 评论类型列，根据不同类型的值显示不同的文本 -->
            <el-table-column label="评论类型">
                <template #default="{ row, $index }">
                    <span v-if="row.type === 0">帖子评论</span>

                    <!-- 可尝试添加 -->
                    <!-- <span v-if="row.type === 1">互评</span> -->
                    <!-- <span v-if="row.type === 2">商品评价</span> -->

                </template>
            </el-table-column>
            <!-- 操作列 -->
            <el-table-column label="操作">
                <template #default="{ row, $index }">
                    <!-- 删除按钮，点击时触发del方法并传入当前评论的id -->
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>


        <!-- 分页组件区域 -->
        <div style="margin-top: 10px">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :page-sizes="pageSizeArr" :page-size="pageSizeArr[0]" layout="total,sizes,prev,pager,next,jumper"
                :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script setup>
import MyDrawer from "@/components/MyDrawer.vue";
import { ref, onMounted } from "vue";
import http, { baseURL } from "@/utils/Request.js";
import { Plus } from "@element-plus/icons-vue";
import { dbImages, splicingDomain } from "@/utils/Base.js";
import { ElNotification } from "element-plus";
import { dateFormatHm } from "../../utils/DateUtil.js";

const pageSizeArr = ref([10, 20, 30, 40])// 定义每页显示的评论数量选项
const total = ref(0)// 定义评论总数
const selectRow = ref({})// 定义当前选中的评论对象
const myDrawerRef = ref()// 创建MyDrawer组件的引用
const pageRequest = ref({ pageNum: 1, pageSize: 10, q: {} })// 定义分页请求参数的对象，包括当前页码、每页数量以及查询条件（q）
const delIds = ref([])// 定义要批量删除的评论id数组
const tableData = ref([])// 定义数据源，用于存储评论列表数据

//加载评论数据
onMounted(() => {
    load()
})

// 批量删除评论，如果未勾选任何评论则提示用户勾选数据，否则发送批量删除请求并重新加载评论列表
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({ title: "Warning", message: "请先勾选数据", type: "warning" })
        return
    }
    // 发送批量删除请求
    await http.post("/comment/deleteBatchByIds", delIds.value, "删除成功！")
    // 重新加载评论列表
    await load()
}

// 处理表格中选中行变化，将选中行的id收集到delIds数组中
const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id)
}

// 删除单条评论，通过id删除评论，并重新加载评论列表
const del = async (id) => {
    await http.del("/comment/" + id)
    await load()
}

// 处理每页显示评论数量变化，更新pageRequest中的pageSize并重新加载评论列表
const handleSizeChange = (size) => {
    pageRequest.value.pageSize = size
    load()
}

// 处理当前页码变化，更新pageRequest中的pageNum并重新加载评论列表
const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum
    load()
}

// 加载评论数据，向服务器发送分页请求并将返回的数据赋值给tableData和total
const load = async () => {
    const res = await http.post("/comment/page", pageRequest.value, "-1")
    tableData.value = res.data.list
    total.value = res.data.total
}
</script>
