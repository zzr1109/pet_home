<!--宠物中心设计-->
<template>
    <div>
        <div>
        <el-input v-model="pageRequest.q.petName" placeholder="请搜索您想要的宠物名称？" style="width: 300px" clearable></el-input>
        <el-button @click="load">搜索</el-button>
        </div>
        <!--搜索栏设计-->


        <div style="display: flex;margin-top: 10px">
        <el-button type="primary" @click="add">新增</el-button>
        <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
            <template #reference>
            <el-button>批量删除</el-button>
            </template>
        </el-popconfirm>
        </div>
        <!--新增和批量删除功能-->


        <el-table border :data="tableData" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="petCategoryName" label="宠物类别"></el-table-column>
        <el-table-column prop="userName" label="创建人名称"></el-table-column>
        <el-table-column label="创建时间">
            <template #default="{row,$index}">
            <span>{{dateFormatHm(row.createTime)}}</span>
            </template>
        </el-table-column>
        <el-table-column prop="petName" label="宠物名称"></el-table-column>
        <el-table-column width="220" label="宠物图片">
            <template #default="{row,$index}">
            <el-image preview-teleported v-if="row.menuImages && row.menuImages.length>0"
                :preview-src-list="row.menuImages" fit="cover" :src="row.menuImages[0]" style="height: 200px;width: 200px"></el-image>
            </template>
        </el-table-column>
        <el-table-column label="宠物性别">
            <template #default="{row,$index}">
            <span v-if="row.petSex === 0">公</span>
            <span v-if="row.petSex === 1">母</span>
            <span v-if="row.petSex === 2">未知</span>
            </template>
        </el-table-column>
        <el-table-column prop="petAge" label="宠物年龄"></el-table-column>
        <el-table-column prop="petRemark" label="宠物描述" width="200" show-overflow-tooltip></el-table-column>
        <!--复选框、宠物类别、创建人名称、创建时间、宠物名称、宠物图片、宠物性别、宠物年龄、宠物描述这些列的创建-->
        <el-table-column label="操作">
            <template #default="{row,$index}">
            <el-button size="small" @click="edit(row)">编辑</el-button>
            <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                <template #reference>
                <el-button size="small">删除</el-button>
                </template>
            </el-popconfirm>
            </template>
        </el-table-column>
        <!--操作与删除-->
        </el-table>


        <div style="margin-top: 10px">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="pageSizeArr"
            :page-size="pageSizeArr[0]"
            layout="total,sizes,prev,pager,next,jumper"
            :total="total"
            >
            </el-pagination>
        </div>
        <!--总条数、分页按钮、页码跳转、每页条数选择-->


            <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '更新' : '新增'" @saveOrUpdate="saveOrUpdate">
                <el-form label-position="top">
                    <el-form-item label="宠物图片">
                        <el-upload
                            class="avatar-uploader"
                            :action="baseURL() + '/file/upload'"
                            :file-list="fileList"
                            list-type="picture-card"
                            :show-file-list="true"
                            :on-remove="handleRemove"
                            :on-success="handleAvatarSuccess"
                            multiple
                        >
                            <el-icon class="avatar-uploader-icon">
                                <Plus></Plus>
                            </el-icon>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="宠物类别名称">
                        <el-select v-model="selectRow.petCategoryName" @change="selectCategory" value-key="category">
                            <el-option v-for="item in categoryList" :key="item.id" :value="JSON.stringify(item)" :label="item.category"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="宠物姓名">
                        <el-input v-model="selectRow.petName" placeholder="请输入宠物姓名？"></el-input>
                    </el-form-item>
                    <el-form-item label="宠物性别">
                        <el-radio-group v-model="selectRow.petSex">
                            <el-radio :value="0">公</el-radio>
                            <el-radio :value="1">母</el-radio>
                            <el-radio :value="2">未知</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="宠物年龄">
                        <el-input v-model="selectRow.petAge" placeholder="请输入宠物年龄？"></el-input>
                    </el-form-item>
                    <el-form-item label="宠物描述">
                        <el-input type="textarea" :rows="4" v-model="selectRow.petRemark" placeholder="请输入宠物描述？"></el-input>
                    </el-form-item>
                </el-form>
            </MyDrawer>
            <!--完成多种弹窗的设置-->
        </div>
</template>

<script setup>
import MyDrawer from "@/components/MyDrawer.vue";
import {ref,onMounted} from "vue";
import http, {baseURL} from "@/utils/Request.js";
import {Plus} from "@element-plus/icons-vue";
import {dbImages, splicingDomain} from "@/utils/Base.js";
import {ElNotification} from "element-plus";
import {dateFormatHm} from "../../utils/DateUtil.js";

const pageSizeArr = ref([10, 20, 30, 40])   // 每页条数选项  
const total = ref(0)                         // 总条数，用于分页显示  
const selectRow = ref({})                    // 当前选中要编辑或新增的宠物对象  
const fileList = ref([])                     // 上传组件的文件列表  
const myDrawerRef = ref()                    // 抽屉组件实例引用  
const pageRequest = ref({ pageNum: 1, pageSize: 10, q: {} }) // 分页请求和查询条件  
const delIds = ref([])                       // 批量删除时收集的id数组  
const categoryList = ref([])                 // 宠物类别列表，用于下拉选择  
const tableData = ref([])                    // 表格显示的宠物数据列表

onMounted(() => {
    load()//加载列表数据
    getCategoryList()//加载宠物类别
})

const selectCategory = (e) => {
    let petCategory = JSON.parse(e)
    selectRow.value.petCategoryId = petCategory.id
    selectRow.value.petCategoryName = petCategory.category
}
//选中类别后同步ID和名称到当前编辑的selectRow对象上

const getCategoryList = async () => {
    const res = await http.post("/petCategory/page",{pageNum: 1,pageSize: 1000,q: {}},"-1")
    categoryList.value = res.data.list
}
//请求宠物类别分页接口，默认查全部（pageSize取大值）

const handleRemove = (file, fileList) => {
    let index = selectRow.value.menuImages.findIndex(u => u.indexOf(file.url) !== -1)
    if (index > -1) {
        selectRow.value.menuImages.splice(index, 1)
    }
}
//找上传列表中图片能匹配file.url的下标的，找到即删除

const handleAvatarSuccess = (res) => {
    if (res.code === '200') {
        if (!selectRow.value.menuImages) {
            selectRow.value.menuImages = []
        }
        selectRow.value.menuImages.push(res.data)
    }
}
//上传图片到menuImages数组中

const add = () => {
    selectRow.value = {}
    fileList.value = []
    myDrawerRef.value.open()
}
//重置、清空、打开抽屉组件

const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {//判断是否有已勾选的元素
        ElNotification({title: "Warning",message: "请先勾选数据",type: "warning"})
        return
    }
    await http.post("/petCenter/deleteBatchByIds",delIds.value,"删除成功！")
    await load()//刷新列表
}
//批量删除

const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id)
}
//多选框选中

const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))//深拷贝，避免编辑时直接修改列表数据
    if (!selectRow.value.menuImages) {
        selectRow.value.menuImages = []
    }
    fileList.value = selectRow.value.menuImages.map(x => {
        return { url: x }
    })
    myDrawerRef.value.open(selectRow.value)
}
//编辑按钮

const del = async (id) => {
    await http.del("/petCenter/" + id)
    await load()
}
//单个删除

const handleSizeChange = (size) => {
    pageRequest.value.pageSize = size
    load()
}
//每页条数变化

const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum
    load()
}
//页数变化

const saveOrUpdate = async () => {
    selectRow.value.imageUrlList = ""
    selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
    await http.post("/petCenter",selectRow.value,"操作成功！")
    myDrawerRef.value.close()
    await load()
}
//保存或者更新数据

const load = async () => {
    const res = await http.post("/petCenter/page",pageRequest.value,"-1")
    tableData.value = res.data.list
    total.value = res.data.total
    tableData.value.forEach(item => {
        if (item.menuImages) {
            item.menuImages = splicingDomain(item.menuImages)
        }
    })
}
//加载宠物列表：更新数据，为图片添加域名前缀，使得图片可以正确显示
</script>