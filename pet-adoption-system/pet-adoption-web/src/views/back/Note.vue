<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.content" style="width: 300px" placeholder="请输入你想搜索的内容？" clearable></el-input>
            <el-button style="margin-left: 20px" type="primary" @click="load">搜索</el-button>
        </div>

        <div class="xw-block" style="display: flex">
            <el-button type="primary" @click="add">新增公告</el-button>
            <el-popconfirm style="margin-left: 5px;" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="content" label="公告内容" width="300" show-overflow-tooltip></el-table-column>
            <el-table-column label="创建时间">
                <template #default="{row,$index}">
                    <span>{{ dateFormatHm(row.createTime) }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
                    <el-button size="small" @click="edit(row)">编辑</el-button>
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

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

        <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '更新':'新增'" @saveOrUpdate="saveOrUpdate">
            <el-form class="line" label-position="top">
                <el-form-item label="内容">
                    <el-input placeholder="请输入内容" type="textarea" :rows="4" v-model="selectRow.content"></el-input>
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

const myDrawerRef = ref()
const delIds = ref([])
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({
    pageNum: 1,
    pageSize: 10,
    q: {
        content: ''
    }
})

onMounted(() => {
    load()
})

const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    myDrawerRef.value.open(selectRow.value)
}

const add = () => {
    selectRow.value = {}
    myDrawerRef.value.open()
}

const del = async (id) => {
    await http.del("/note/" + id,null,'-1')
    await load()
}

const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/note/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}

const saveOrUpdate = async (e) => {
    await http.post("/note/saveOrUpdate", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}

const load = async () => {
    const res = await http.post("/note/page", pageRequest.value,'-1')
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

const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id)
}
</script>