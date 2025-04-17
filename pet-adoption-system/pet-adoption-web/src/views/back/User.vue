<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.nickName" style="width: 300px" placeholder="请输入你想搜索的内容？" clearable></el-input>
            <el-button style="margin-left: 20px" type="primary" @click="load">搜索</el-button>
        </div>

        <div class="xw-block" style="display: flex">
            <el-button type="primary" @click="add">新增用户</el-button>
            <el-popconfirm style="margin-left: 5px;" title="您确定要删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="nickName" label="昵称"></el-table-column>
            <el-table-column prop="nickName" label="图像">
                <template #default="{row,$index}">
                    <el-image preview-teleported style="width: 70px;height: 70px" :src="row.imageUrl" :preview-src-list="[row.imageUrl]" fit="cover"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄"></el-table-column>
            <el-table-column prop="userName" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="联系方式"></el-table-column>
            <el-table-column label="角色">
                <template #default="{row,$index}">
                    <el-tag type="success">{{row.role}}</el-tag>
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
                <el-form-item label="用户头像">
                    <el-upload
                        class="avatar-uploader"
                        :action="baseURL() + '/file/upload'"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="selectRow.imageUrl" :src="selectRow.imageUrl" class="avatar">
                        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input placeholder="请输入昵称" v-model="selectRow.nickName"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input placeholder="请输入用户名" v-model="selectRow.userName"></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input placeholder="请输入年龄" type="number"
                              v-model="selectRow.age"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" v-model="selectRow.email"></el-input>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input placeholder="请输入联系方式" v-model="selectRow.phone"></el-input>
                </el-form-item>
                <el-form-item label="角色信息">
                    <el-autocomplete
                        style="width: 100%"
                        clearable
                        placeholder="请选择角色信息？"
                        v-model="selectRow.role"
                        value-key="name"
                        :fetch-suggestions="handleRoleFetch"
                        @select="handleRoleSelect"
                    >
                        <template #default="{ item }">
                            <div style="display: flex;justify-content: space-between">
                                <span>{{ item.name }}</span>
                                <span>{{ item.remark }}</span>
                            </div>
                        </template>
                    </el-autocomplete>
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
        nickName: ''
    }
})

onMounted(() => {
    load()
})

const handleRoleFetch = async (queryString, cb) => {
    const res = await http.get("/role/selectAll",null,'-1')
    cb(res.data)
}

const handleRoleSelect = (e) => {
}

const handleAvatarSuccess = (e) => {
    if (e.code === '200') {
        selectRow.value.imageUrl = e.data
    }
}

const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    myDrawerRef.value.open(selectRow.value)
}

const add = () => {
    selectRow.value = {}
    myDrawerRef.value.open()
}

const del = async (id) => {
    await http.del("/user/" + id)
    await load()
}

const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/user/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}

const saveOrUpdate = async (e) => {
    await http.post("/user/saveOrUpdate", selectRow.value)
    if (!selectRow.value.id) {
        ElNotification({title: "Success", message: "操作成功,初始密码为123456", type: "success"})
    } else {
        ElNotification({title: "Success", message: "操作成功", type: "success"})
    }
    myDrawerRef.value.close()
    await load()
}

const load = async () => {
    const res = await http.post("/user/page", pageRequest.value,"-1")
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