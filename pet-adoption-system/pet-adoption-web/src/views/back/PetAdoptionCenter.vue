<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.petCenterName" style="width: 300px" placeholder="请输入你想搜索的宠物名称？" clearable></el-input>
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
            <el-table-column prop='userName' label='领养人名称'></el-table-column>
            <el-table-column width='120' label='领养人头像'>
                <template #default="{row,$index}">
                    <el-image preview-teleported v-if='row.userImage' :preview-src-list='[row.userImage]' fit='cover' :src='row.userImage' />
                </template>
            </el-table-column>
            <el-table-column prop='userPhone' label='领养人联系方式'></el-table-column>
            <el-table-column prop='userAddress' label='领养人地址'></el-table-column>
            <el-table-column prop='petCenterName' label='宠物名称'></el-table-column>
            <el-table-column width='120' label='领养状态'>
                <template #default="{row,$index}">
                    <span v-if='row.adoptionStatus===0'>领养中</span>
                    <span v-if='row.adoptionStatus===1'>已归还</span>
                    <span v-if='row.adoptionStatus===2'>申请中</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
                    <el-button :disabled="row.adoptionStatus!==0" size="small" @click="opStatus(row,1)">强制归还</el-button>
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button :disabled="row.adoptionStatus===0" size="small">删除</el-button>
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
const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    myDrawerRef.value.open(selectRow.value)
}
const opStatus = async (row,status) => {
    let obj = JSON.parse(JSON.stringify(row))
    obj.adoptionStatus = status
    await http.post("/petAdoptionCenter/opStatus",obj)
    await load()
}
const add = () => {
    selectRow.value = {}
    myDrawerRef.value.open()
}
const del = async (id) => {
    await http.del("/petAdoptionCenter/" + id,null,'-1')
    await load()
}
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/petAdoptionCenter/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}
const saveOrUpdate = async (e) => {
    await http.post("/petAdoptionCenter", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}
const load = async () => {
    const res = await http.post("/petAdoptionCenter/page", pageRequest.value,'-1')
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