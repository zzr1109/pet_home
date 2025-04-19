<!--宠物轮播图设计-->
<template>
    <div>
        <!-- 按钮容器，使用flex使其水平排列 -->
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
        <!--"新增"和"批量删除"按钮的创建,分别调用add和delBatch两个方法-->

        <!-- 表格组件，绑定数据tableData，并设置边框，选择改变时调用handleSelectionChange方法 -->
        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <!-- 选择列，宽度为55px -->
            <el-table-column type="selection" width="55"></el-table-column>
            <!-- 轮播图片列，宽度为200px，标签为'轮播图片' -->
            <el-table-column width='200' label='轮播图片'>
                <template #default="{ row, $index }">
                    <!-- 如果row.menuImages存在且长度大于0，则显示图片 -->
                    <el-image preview-teleported v-if='row.menuImages && row.menuImages.length > 0'
                        :preview-src-list='row.menuImages' fit='cover' :src='row.menuImages[0]' />
                    <!-- preview-teleported：预览图片时使用teleported方式 -->
                    <!-- preview-src-list：预览时的图片列表 -->
                    <!-- fit：图片的适应方式，cover表示覆盖整个容器 -->
                    <!-- src：显示的第一张图片的URL -->
                </template>
            </el-table-column>

            <!-- 是否显示列，标签为'是否显示' -->
            <el-table-column label='是否显示'>
                <template #default="{ row, $index }">
                    <!-- row.showStatus的值来决定显示什么 -->
                    <span v-if='row.showStatus === 0'>不显示</span>
                    <span v-if='row.showStatus === 1'>显示</span>
                </template>
            </el-table-column>

            <!-- 操作列，标签为'操作'-->
            <el-table-column label="操作" width="200">
                <template #default="{ row, $index }">
                    <!-- 编辑按钮 -->
                    <el-button size="small" @click="edit(row)">编辑</el-button>
                    <!-- 删除按钮 -->
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <!-- 设置表格并绑定"tableData"数据，-->

        <!-- 分页容器 -->
        <div class="xw-block">
            <!-- 分页组件，每页大小改变时调用handleSizeChange方法，当前页改变时调用handleCurrentChange方法 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :page-sizes="pageSizeArr" :page-size="pageSizeArr[0]" layout="total, sizes, prev, pager, next, jumper"
                :total="total">
                <!-- page-sizes：每页可选的条目数 -->
                <!-- page-size：当前每页显示的条目数 -->
                <!-- layout：分页组件的布局，包括总条数、每页大小选择器、上一页、页码、下一页、跳转 -->
                <!-- total：总条目数 -->
            </el-pagination>
        </div>

        <!-- 新增/更新表单组件，引用myDrawerRef，根据selectRow.id决定标题是'更新'还是'新增'，保存或更新时调用saveOrUpdate方法 -->
        <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '更新' : '新增'" @saveOrUpdate="saveOrUpdate">
            <!-- 表单组件 -->
            <el-form class="line" label-position="top">
                <!-- 轮播图片表单项，标签为'轮播图片' -->
                <el-form-item label='轮播图片'>
                    <!-- 图片上传组件，指定上传地址、文件列表、列表类型、是否显示文件列表、移除文件时调用handleRemove方法、上传成功时调用handleAvatarSuccess方法，允许多个文件上传 -->
                    <el-upload class="avatar-uploader" :action="baseURL() + '/file/upload'" :file-list='fileList'
                        list-type='picture-card' :show-file-list='true' :on-remove='handleRemove'
                        :on-success="handleAvatarSuccess" multiple>
                        <el-icon class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>

                <!-- 是否显示表单项，标签为'是否显示' -->
                <el-form-item label="是否显示">
                    <!-- 单选按钮组，绑定selectRow.showStatus -->
                    <el-radio-group v-model="selectRow.showStatus">
                        <!-- 单选按钮根据值来决定是否显示 -->
                        <el-radio :value="0">不显示</el-radio>
                        <el-radio :value="1">显示</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </MyDrawer>
    </div>
</template>



<script setup>
//脚本组件
import MyDrawer from "@/components/MyDrawer.vue" 
import { baseURL } from "@/utils/Request.js" 
import http from "@/utils/Request.js" 
import { ref, onMounted } from "vue" 
import { ElNotification } from "element-plus"; 
import { dateFormatHm } from "@/utils/DateUtil.js"; 
import { splicingDomain, dbImages } from "@/utils/Base.js" 

// 创建MyDrawer组件的引用
const myDrawerRef = ref()

// 创建文件列表的响应式数据
const fileList = ref([])

// 从localStorage中获取用户信息
const user = ref(JSON.parse(localStorage.getItem("user")))

// 创建要批量删除的ID列表的响应式数据
const delIds = ref([])

// 创建选中行的响应式数据
const selectRow = ref({})

// 定义每页可选的条目数
const pageSizeArr = ref([10, 20, 30, 40])

// 创建表格数据的响应式数据
const tableData = ref([])

// 创建总条目数的响应式数据
const total = ref(0)

// 创建分页请求参数的响应式数据，包括当前页号、每页大小和查询条件
const pageRequest = ref({ pageNum: 1, pageSize: 10, q: {} })

// 组件挂载后调用load方法，加载初始数据
onMounted(() => {
    load()
})

// 图片移除时的处理方法
const handleRemove = (file, fileList) => {
    // 找到selectRow.value.menuImages中包含file.url的索引
    let index = selectRow.value.menuImages.findIndex(u => u.indexOf(file.url) !== -1)
    // 如果找到，则从menuImages中移除相应的图片URL
    if (index > -1) {
        selectRow.value.menuImages.splice(index, 1)
    }
}

// 图片上传成功时的处理方法
const handleAvatarSuccess = (res) => {
    // 如果上传成功（res.code === '200'）
    if (res.code === '200') {
        // 如果selectRow.value.menuImages不存在，则初始化为空数组
        if (!selectRow.value.menuImages) {
            selectRow.value.menuImages = []
        }
        // 将上传的图片URL添加到menuImages中
        selectRow.value.menuImages.push(res.data)
    }
}

// 编辑方法，点击编辑按钮时调用
const edit = (row) => {
    // 将选中的行数据复制到selectRow.value中
    selectRow.value = JSON.parse(JSON.stringify(row))
    // 如果selectRow.value.menuImages不存在，则初始化为空数组
    if (!selectRow.value.menuImages) selectRow.value.menuImages = []
    // 将selectRow.value.menuImages转换为文件列表格式，并赋值给fileList
    fileList.value = selectRow.value.menuImages.map(x => {
        return { url: x }
    })
    // 打开MyDrawer组件进行编辑
    myDrawerRef.value.open(selectRow.value)
}

// 新增方法，点击新增按钮时调用
const add = () => {
    // 清空fileList
    fileList.value = []
    // 清空selectRow.value
    selectRow.value = {}
    // 打开MyDrawer组件进行新增
    myDrawerRef.value.open()
}

// 删除方法，点击删除按钮时调用
const del = async (id) => {
    // 发送删除请求
    await http.del("/petCrousel/" + id, null, '-1')
    // 重新加载数据
    await load()
}

// 批量删除方法，点击批量删除按钮时调用
const delBatch = async () => {
    // 如果delIds为空或长度为0
    if (!delIds.value || delIds.value.length <= 0) {
        // 显示警告通知
        ElNotification({ title: "Warning", message: "请先勾选数据", type: "warning" })
        return
    }
    // 发送批量删除请求
    await http.post("/petCrousel/deleteBatchByIds", delIds.value, '删除成功!')
    // 重新加载数据
    await load()
}

// 保存或更新方法，MyDrawer组件保存或更新时调用
const saveOrUpdate = async (e) => {
    // 清空selectRow.value.imageUrlList
    selectRow.value.imageUrlList = ''
    // 将selectRow.value.menuImages转换为数据库格式
    selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
    // 发送保存或更新请求
    await http.post("/petCrousel", selectRow.value, '操作成功！')
    // 关闭MyDrawer组件
    myDrawerRef.value.close()
    // 重新加载数据
    await load()
}

// 加载数据方法
const load = async () => {
    // 发送分页请求
    const res = await http.post("/petCrousel/page", pageRequest.value, '-1')
    // 将返回的数据列表赋值给tableData
    tableData.value = res.data.list
    // 将返回的总条目数赋值给total
    total.value = res.data.total
    // 对tableData中的每一项进行处理
    tableData.value.forEach(item => {
        // 如果item.menuImages存在
        if (item.menuImages) {
            // 将menuImages中的图片URL进行域名拼接处理
            item.menuImages = splicingDomain(item.menuImages)
        }
    })
}

// 每页大小改变时的处理方法
const handleSizeChange = (size) => {
    // 更新pageRequest.value.pageSize为新的每页大小
    pageRequest.value.pageSize = size
    // 重新加载数据
    load()
}

// 当前页改变时的处理方法
const handleCurrentChange = (pageNum) => {
    // 更新pageRequest.value.pageNum为新的当前页号
    pageRequest.value.pageNum = pageNum
    // 重新加载数据
    load()
}

// 表格选择改变时的处理方法
const handleSelectionChange = (e) => {
    // 将选中的行的ID提取出来，并赋值给delIds
    delIds.value = e.map(x => x.id)
}
</script>
