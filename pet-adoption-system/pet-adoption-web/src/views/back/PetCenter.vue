<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.petName" style="width: 300px" placeholder="请输入你想搜索的宠物名称？" clearable></el-input>
            <el-button style="margin-left: 20px" type="primary" @click="load">搜索</el-button>
        </div>
        <div class="xw-block" style="display: flex">
            <el-button type="primary" @click="add">新增</el-button>
            <el-popconfirm style="margin-left: 5px;" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>
        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop='petCategoryName' label='宠物类别'></el-table-column>
            <el-table-column width='120' label='宠物图像'>
                <template #default="{row,$index}">
                    <el-image preview-teleported v-if='row.menuImages && row.menuImages.length > 0' :preview-src-list='row.menuImages' fit='cover' :src='row.menuImages[0]' />
                </template>
            </el-table-column>
            <el-table-column width='160' label='创建时间'>
                <template #default="{row,$index}">
                    <span>{{dateFormatHm(row.createTime)}}</span>
                </template>
            </el-table-column>
            <el-table-column width='120' label='领养状态'>
                <template #default="{row,$index}">
                    <span v-if='row.adoptionStatus===0'>待领养</span>
                    <span v-if='row.adoptionStatus===1'>已领养</span>
                </template>
            </el-table-column>
            <el-table-column prop='petName' label='宠物姓名'></el-table-column>
            <el-table-column width='120' label='宠物性别'>
                <template #default="{row,$index}">
                    <span v-if='row.petSex===0'>公</span>
                    <span v-if='row.petSex===1'>母</span>
                    <span v-if='row.petSex===2'>未知</span>
                </template>
            </el-table-column>
            <el-table-column prop='petAge' label='宠物年龄'></el-table-column>
            <el-table-column prop='petRemark' label='宠物描述'></el-table-column>
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
                <el-form-item label="宠物类别名称">
                    <el-select v-model="selectRow.petCategoryName" @change="selectCategory" value-key="category">
                        <el-option v-for="item in categoryList" :key="item" :value="JSON.stringify(item)" :label="item.category"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label='宠物图像'>
                    <el-upload
                        class="avatar-uploader"
                        :action="baseURL() + '/file/upload'"
                        :file-list='fileList'
                        list-type='picture-card'
                        :show-file-list='true'
                        :on-remove='handleRemove'
                        :on-success="handleAvatarSuccess" multiple>
                        <el-icon class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="宠物姓名">
                    <el-input v-model="selectRow.petName" placeholder="请填写宠物姓名？" style="width: 100%"/>
                </el-form-item>
                <el-form-item label="宠物性别">
                    <el-radio-group v-model="selectRow.petSex">
                        <el-radio :value="0">公</el-radio>
                        <el-radio :value="1">母</el-radio>
                        <el-radio :value="2">未知</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="宠物年龄">
                    <el-input v-model="selectRow.petAge" placeholder="请填写宠物年龄？" style="width: 100%"/>
                </el-form-item>
                <el-form-item label="宠物描述">
                    <el-input type='textarea' :rows=4 v-model="selectRow.petRemark" placeholder="请填写宠物描述？" style="width: 100%"/>
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
const myDrawerRef = ref()
const fileList = ref([])
const user = ref(JSON.parse(localStorage.getItem("user")))
const delIds = ref([])
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
const categoryList = ref([])

onMounted(() => {
    load()
    getCategoryList()
})

const selectCategory = (e) => {
    console.log("selectCategory",e)
    let petCategory = JSON.parse(e)
    selectRow.value.petCategoryId = petCategory.id
    selectRow.value.petCategoryName = petCategory.category
}

const getCategoryList = async () => {
    const res = await http.post("/petCategory/page",{pageNum: 1,pageSize: 100,q: {}},"-1")
    categoryList.value = res.data.list
}

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
    await http.del("/petCenter/" + id,null,'-1')
    await load()
}
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/petCenter/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}
const saveOrUpdate = async (e) => {
    selectRow.value.imageUrlList = ''
    selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
    await http.post("/petCenter", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}
const load = async () => {
    const res = await http.post("/petCenter/page", pageRequest.value,'-1')
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