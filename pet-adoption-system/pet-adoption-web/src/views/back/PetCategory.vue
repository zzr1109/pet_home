<!-- 宠物分类管理设计 -->
<template>
    <div>
        <!-- 操作按钮区域，实现新增和批量删除两个功能 -->
        <div class="xw-block" style="display: flex">
            <!-- 新增按钮 -->
            <el-button type="primary" @click="add">新增</el-button>
            <!-- 批量删除按钮 -->
            <el-popconfirm style="margin-left: 5px;" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <!-- 宠物类别表格，展示从后端获取的宠物类别数据 -->
        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <!-- 表格的第一列是多选框 -->
            <el-table-column type="selection" width="55"></el-table-column>
            <!-- 第二列展示宠物类别的名称 -->
            <el-table-column prop='category' label='宠物类别'></el-table-column>
            <!-- 第三列是操作列，包含编辑和删除按钮 -->
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
                    <!-- 编辑按钮，点击后触发edit方法，传递当前行的数据 -->
                    <el-button size="small" @click="edit(row)">编辑</el-button>
                    <!-- 删除按钮，使用el-popconfirm组件以确认删除操作，点击后触发del方法，传递当前行的id -->
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页组件，用于表格数据的分页显示 -->
        <div class="xw-block">
            <el-pagination
                @size-change="handleSizeChange"       
                @current-change="handleCurrentChange" 
                :page-sizes="pageSizeArr" 
                :page-size="pageSizeArr[0]" 
                layout="total, sizes, prev, pager, next, jumper" 
                :total="total"> 
            </el-pagination>
        </div>

        <!-- 抽屉组件，用于新增或编辑宠物类别 -->
        <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '更新':'新增'" @saveOrUpdate="saveOrUpdate">
            <el-form class="line" label-position="top">
                <el-form-item label="宠物类别">
                    <!-- 输入框，用于输入宠物类别的名称 -->
                    <el-input v-model="selectRow.category" placeholder="请填写宠物类别？" style="width: 100%"/>
                </el-form-item>
            </el-form>
        </MyDrawer>
    </div>
</template>


<script setup>
import MyDrawer from "@/components/MyDrawer.vue"
import { baseURL } from "@/utils/Request.js"
import http from "@/utils/Request.js"
import { ref,onMounted } from "vue"
import {ElNotification} from "element-plus";
import { dateFormatHm } from "@/utils/DateUtil.js";
import {splicingDomain,dbImages} from "@/utils/Base.js"

const myDrawerRef = ref()// 定义MyDrawer组件的引用
const user = ref(JSON.parse(localStorage.getItem("user")))// 定义用户信息，从localStorage中获取
const delIds = ref([])// 定义要删除的宠物类别id数组
const selectRow = ref({})// 定义选中的宠物类别对象，用于新增或编辑操作
const pageSizeArr = ref([10, 20, 30, 40])// 定义每页显示数量的选择数组
const tableData = ref([])// 定义表格数据
const total = ref(0)// 定义总记录数
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})// 定义分页请求参数对象，包含当前页码、每页显示数量和查询条件

onMounted(() => {
    load()
})
    
// 编辑方法，接收行对象，将行数据复制到selectRow.value并打开抽屉组件
const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    myDrawerRef.value.open(selectRow.value)
}
    
// 新增方法，初始化selectRow.value为空对象并打开抽屉组件
const add = () => {
    selectRow.value = {}
    myDrawerRef.value.open()
}
    
// 删除方法，接收id，发送删除请求后重新加载数据
const del = async (id) => {
    await http.del("/petCategory/" + id,null,'-1') // 发送删除请求
    await load() // 重新加载数据
}
    
// 批量删除方法，检查是否有选中的id，如果有则发送批量删除请求并重新加载数据
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) { // 检查是否有选中的id
        ElNotification({title: "警告", message: "请先勾选数据", type: "warning"}) // 提示用户勾选数据
        return
    }
    await http.post("/petCategory/deleteBatchByIds", delIds.value,'删除成功!') // 发送批量删除请求
    await load() // 重新加载数据
}
    
// 保存或更新方法，发送新增或更新请求后关闭抽屉组件并重新加载数据
const saveOrUpdate = async (e) => {
    await http.post("/petCategory", selectRow.value,'操作成功！') // 发送新增或更新请求
    myDrawerRef.value.close() // 关闭抽屉组件
    await load() // 重新加载数据
}
    
// 加载方法，发送分页请求后更新表格数据和总记录数
const load = async () => {
    const res = await http.post("/petCategory/page", pageRequest.value,'-1') // 发送分页请求
    tableData.value = res.data.list // 更新表格数据
    total.value = res.data.total // 更新总记录数
}
    
// 每页显示数量改变时的方法，更新pageRequest.value.pageSize并重新加载数据
const handleSizeChange = (size) => {
    pageRequest.value.pageSize = size // 更新每页显示数量
    load() // 重新加载数据
}
    
// 当前页码改变时的方法，更新pageRequest.value.pageNum并重新加载数据
const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum // 更新当前页码
    load() // 重新加载数据
}
    
// 表格复选框选中状态改变时的方法，更新delIds.value为选中的id数组
const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id) // 更新要删除的id数组
}

</script>
