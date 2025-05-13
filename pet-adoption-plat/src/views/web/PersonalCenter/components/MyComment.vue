<template>
    <div>
        <el-table class="xw-block-table" :data="tableData">
            <el-table-column prop='content' width="400" label='评论内容' show-overflow-tooltip></el-table-column>
            <el-table-column label='评论时间'>
                <template #default="{row,$index}">
                    <span>{{dateFormatHm(row.createTime)}}</span>
                </template>
            </el-table-column>
            <el-table-column label='评论类型'>
                <template #default="{row,$index}">
                    <span v-if='row.type===0'>帖子评论</span>
                    <span v-if='row.type===1'>互评</span>
                    <span v-if='row.type===2'>商品评价</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button type="danger" size="small">删除</el-button>
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
import {ElNotification} from "element-plus";
import { dateFormatHm } from "@/utils/DateUtil.js";
const user = ref(JSON.parse(localStorage.getItem("user")))
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
onMounted(() => {
    load()
})
const del = async (id) => {
    await http.get("/plat/personCenter/delMyComment?commentId=" + id,null,'-1')
    await load()
}
const load = async () => {
    const res = await http.post("/plat/personCenter/postMyComment", pageRequest.value,'-1')
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