<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.content" style="width: 300px" placeholder="请输入你想搜索的帖子内容？" clearable></el-input>
            <el-input v-model="pageRequest.q.title" style="width: 300px;margin-left: 20px" placeholder="请输入你想搜索的帖子标题？" clearable></el-input>
            <el-button style="margin-left: 20px" type="primary" @click="load">搜索</el-button>
        </div>
        <div class="xw-block" style="display: flex">
            <el-popconfirm style="margin-left: 5px;" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>
        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop='title' width="200" label='发布标题' show-overflow-tooltip></el-table-column>
            <el-table-column prop='content' width="600" label='发布内容' show-overflow-tooltip></el-table-column>
            <el-table-column width='160' label='发布时间'>
                <template #default="{row,$index}">
                    <span>{{dateFormatHm(row.createTime)}}</span>
                </template>
            </el-table-column>
            <el-table-column width='120' label='图片'>
                <template #default="{row,$index}">
                    <el-image preview-teleported v-if='row.menuImages && row.menuImages.length > 0' :preview-src-list='row.menuImages' fit='cover' :src='row.menuImages[0]' />
                </template>
            </el-table-column>
            <el-table-column prop='userName' label='发布人名称'></el-table-column>
            <el-table-column width='120' label='发布人头像'>
                <template #default="{row,$index}">
                    <el-image preview-teleported v-if='row.userImage' :preview-src-list='[row.userImage]' fit='cover' :src='row.userImage' />
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
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
const myDrawerRef = ref()
const fileList = ref([])
const user = ref(JSON.parse(localStorage.getItem("user")))
const delIds = ref([])
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
onMounted(() => {
    load()
})
const handleRemove = (file, fileList) => {
    let index = selectRow.value.menuImages.findIndex(u => u.indexOf(file.url) !== -1)
    if (index > -1) {
        selectRow.value.menuImages.splice(index, 1)
    }
}
const handleAvatarSuccess = (res) => {
    if (res.code === '200') {
        if (!selectRow.value.menuImages) {
            selectRow.value.menuImages = []
        }
        selectRow.value.menuImages.push(res.data)
    }
}
const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    if (!selectRow.value.menuImages) selectRow.value.menuImages = []
    fileList.value = selectRow.value.menuImages.map(x => {
        return {url: x}
    })
    myDrawerRef.value.open(selectRow.value)
}
const add = () => {
    fileList.value = []
    selectRow.value = {}
    myDrawerRef.value.open()
}
const del = async (id) => {
    await http.del("/postCode/" + id,null,'-1')
    await load()
}
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/postCode/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}
const saveOrUpdate = async (e) => {
    selectRow.value.imageUrlList = ''
    selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
    await http.post("/postCode", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}
const load = async () => {
    const res = await http.post("/postCode/page", pageRequest.value,'-1')
    tableData.value = res.data.list
    total.value = res.data.total
    tableData.value.forEach(item => {
        if (item.menuImages) {
            item.menuImages = splicingDomain(item.menuImages)
        }
    })
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