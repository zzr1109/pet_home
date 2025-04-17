<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-button type="primary" @click="add">新增角色</el-button>
            <el-popconfirm
                style="margin-left: 5px"
                title="您确定要删除吗？"
                @confirm="delBatch"
            >
                <template #reference>
                    <el-button>批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>

        <el-table
            class="xw-block-table"
            :data="tableData"
            border
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="name" label="角色名称"></el-table-column>
            <el-table-column prop="remark" label="角色描述" width="400" show-overflow-tooltip></el-table-column>
            <el-table-column
                label="操作"
                width="400">
                <template #default="{row,$index}">
                    <el-button size="small" :disabled="row.name === 'ROLE_ADMIN'" @click="edit(row)">编辑</el-button>
                    <el-popconfirm
                        title="您确定要删除吗？"
                        @confirm="del(row.id)"
                    >
                        <template #reference>
                            <el-button size="small" :disabled="row.name === 'ROLE_ADMIN'">删除</el-button>
                        </template>
                    </el-popconfirm>
                    <el-button :disabled="row.name === 'ROLE_ADMIN'" size="small" style="margin-left: 10px"
                               @click="allocationMenu(row.id)">分配菜单
                    </el-button>
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

        <MyDrawer ref="myDrawerRef" size="25%" :title="selectRow.id ? '更新':'新增'" @saveOrUpdate="saveOrUpdate">
            <el-form class="line" label-position="top">
                <el-form-item label="角色名称">
                    <el-input placeholder="请输入角色名称？" v-model="selectRow.name"></el-input>
                </el-form-item>
                <el-form-item label="角色描述">
                    <el-input type="textarea" :rows="4" placeholder="请输入角色名称描述？"
                              v-model="selectRow.remark"></el-input>
                </el-form-item>
            </el-form>
        </MyDrawer>

        <MyDrawer ref="allocationMenuRef" size="25%" title="分配菜单" @saveOrUpdate="saveOrUpdateMenu">
            <el-tree
                ref="tree"
                :data="menuData"
                default-expand-all
                :default-checked-keys="checkedKeys"
                show-checkbox
                node-key="id"
                :props="defaultProps">
            </el-tree>
        </MyDrawer>
    </div>
</template>

<script setup>
import MyDrawer from "@/components/MyDrawer.vue"
import {ref, reactive, onMounted} from "vue";
import http from "@/utils/Request.js";
import { ElNotification } from "element-plus";

const tree = ref()
const allocationMenuRef = ref()
const myDrawerRef = ref()
const menuData = ref([])
const checkedKeys = ref([])
const defaultProps = ref({children: 'children', label: 'menuName'})
const selectRoleRow = reactive({})
const delIds = ref([])
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
const total = ref(0)
const user = ref(JSON.parse(localStorage.getItem("user")))

onMounted(() => {
    load()
})

const allocationMenu = async (roleId) => {
    const res = await http.get("/role/selectRoleByMenu?roleId=" + roleId,null,'-1')
    menuData.value = res.data.menus
    checkedKeys.value = res.data.checkedKeys
    selectRoleRow.roleId = roleId
    allocationMenuRef.value.open()
}

const saveOrUpdateMenu = async () => {
    let node = tree.value.getCheckedKeys()
    let halfNode = tree.value.getHalfCheckedKeys()
    let roleMenuReq = [...node, ...halfNode]
    if (!roleMenuReq || roleMenuReq.length === 0) {
        ElNotification({title: "Warning", message: "请选择菜单!", type: "warning"})
        return
    }
    selectRoleRow.menuIds = roleMenuReq
    await http.post("/role/addRoleMenu", selectRoleRow,'菜单分配成功!')
    allocationMenuRef.value.close()
    await load()
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
    await http.del("/role/" + id,null ,'-1')
    await load()
}

const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/role/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}

const saveOrUpdate = async (e) => {
    await http.post("/role/saveOrUpdate", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}

const load = async () => {
    const res = await http.post("/role/page", pageRequest.value,'-1')
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
