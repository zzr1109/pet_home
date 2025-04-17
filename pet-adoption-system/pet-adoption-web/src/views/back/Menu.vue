<template>
    <div>
        <div class="xw-block">
            <el-input placeholder="请输入你要搜索的菜单名称？" style="width: 300px"></el-input>
            <el-button type="primary" style="margin-left: 10px">搜索</el-button>
        </div>
        <div class="xw-block">
            <el-button type="primary" @click="addMenu">新增菜单</el-button>
        </div>

        <el-table
            class="xw-block-table"
            :data="tableData"
            style="width: 100%;margin-bottom: 20px;"
            row-key="id"
            border
            default-expand-all
            lazy
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column label="菜单图标">
                <template #default="{row,$index}">
                    <el-icon>
                        <component :is="row?.icon"></component>
                    </el-icon>
                </template>
            </el-table-column>
            <el-table-column prop="menuName" label="菜单名称"></el-table-column>
            <el-table-column prop="path" width="130" label="跳转路径"></el-table-column>
            <el-table-column prop="name" width="130" label="路由名称"></el-table-column>
            <el-table-column prop="filePath" label="文件名称" width="230" align="center"></el-table-column>
            <el-table-column label="令牌认证">
                <template #default="{row,$index}">
                    <el-tag v-if="row?.isToken === 0" type="warning">需要令牌</el-tag>
                    <el-tag v-if="row?.isToken === 1" type="success">不需要令牌</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="是否显示">
                <template #default="{row,$index}">
                    <el-tag v-if="row?.isShowMenu === 0" type="success">显示</el-tag>
                    <el-tag v-if="row?.isShowMenu === 1" type="warning">不显示</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="sort" label="排序数字"></el-table-column>
            <el-table-column label="操作" width="200" align="center">
                <template #default="{row,$index}">
                    <el-button type="primary" @click="edit(row)">编辑</el-button>
                    <el-popconfirm
                        confirm-button-text="Yes"
                        cancel-button-text="No"
                        icon-color="#626AEF"
                        title="您确定要删除吗?"
                        @confirm="del(row)"
                    >
                        <template #reference>
                            <el-button>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '修改菜单' : '新增菜单'" size="25%" @saveOrUpdate="saveOrUpdate">
            <el-form label-position="top" class="line">
                <el-form-item label="父菜单" v-if="!selectRow.id">
                    <el-select style="width: 100%" placeholder="请选择父菜单？" v-model="selectRow.parentId" clearable @change="selectMenu">
                        <el-option v-for="(parent,parentIndex) in parentMenu" :key="parentIndex" :label="parent.menuName" :value="parent.id">
                            <div v-if="parent.icon">
                                <el-icon>
                                    <component :is="parent.icon"></component>
                                </el-icon>
                                <span style="margin-left: 10px">{{ parent.menuName }}</span>
                            </div>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜单名称">
                    <el-input v-model="selectRow.menuName" placeholder="请填写菜单名称？"></el-input>
                </el-form-item>
                <el-form-item label="跳转路径">
                    <el-input v-model="selectRow.path" placeholder="请填写跳转路径？"></el-input>
                </el-form-item>
                <el-form-item label="路由名称">
                    <el-input v-model="selectRow.name" placeholder="请填写路由名称？"></el-input>
                </el-form-item>
                <el-form-item label="文件名称">
                    <el-input v-model="selectRow.filePath" placeholder="请填写文件名称?该文件只能在/views/back路径下"></el-input>
                </el-form-item>
                <el-form-item label="是否需要令牌验证">
                    <el-switch
                        v-model="selectRow.isToken"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        :active-value="0"
                        :inactive-value="1">
                    </el-switch>
                </el-form-item>
                <el-form-item label="是否显示在左侧菜单">
                    <el-switch
                        v-model="selectRow.isShowMenu"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        :active-value="0"
                        :inactive-value="1">
                    </el-switch>
                </el-form-item>
                <el-form-item label="排序">
                    <el-input v-model="selectRow.sort" type="number" placeholder="请输入排序数字？"></el-input>
                </el-form-item>
                <el-form-item label="菜单图标">
                    <el-select v-model="selectRow.icon" style="width: 100%" placeholder="请选择图标？">
                        <el-option v-for="(icon,iconIndex) in iconList" :key="iconIndex" :label="icon.value"
                                   :value="icon.value">
                            <div v-if="icon['value']">
                                <el-icon>
                                    <component :is="icon['value']"></component>
                                </el-icon>
                                <span style="margin-left: 10px">{{ icon.value }}</span>
                            </div>
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </MyDrawer>
    </div>
</template>
<script setup>
import {ref, onMounted} from "vue";
import http from "@/utils/Request.js";
import MyDrawer from "@/components/MyDrawer.vue";
import {ElNotification} from "element-plus";

const myDrawerRef = ref()
const iconList = ref([])
const parentMenu = ref([])
const selectRow = ref({
    isToken: 0,
    isShowMenu: 0
})
const tableData = ref([])

onMounted(() => {
    load()
    parentMenuList()
    getIconList()
})

const del = async (row) => {
    await http.del("/menu/delMenu?menuId=" + row.id,null,"-1")
    await load()
}

const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    myDrawerRef.value.open(selectRow.value)
}

const load = async () => {
    const res = await http.get("/menu/menuList",null,'-1')
    tableData.value = res.data
}

const addMenu = () => {
    selectRow.value = {isToken: 0, isShowMenu: 0}
    myDrawerRef.value.open();
}

const parentMenuList = async () => {
    const res = await http.get("/menu/parentMenuList",null,'-1')
    parentMenu.value = res.data
}

const getIconList = async () => {
    const res = await http.get("/menu/iconList",null,'-1')
    iconList.value = res.data
}

const saveOrUpdate = async () => {
    if (!selectRow.value?.id) {
        await http.post("/menu/addMenu", selectRow.value, "菜单新增成功!")
        myDrawerRef.value.close()
        await load()
    } else {
        await http.post("/menu/updateMenu", selectRow.value,"菜单修改成功!")
        myDrawerRef.value.close()
        await load()
    }
}

const selectMenu = (e) => {
}
</script>
