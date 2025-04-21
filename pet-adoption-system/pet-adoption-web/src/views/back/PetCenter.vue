<template>
  <div>
    <div>
      <el-input v-model="pageRequest.q.petName" placeholder="请搜索您想要的宠物名称？" style="width: 300px" clearable></el-input>
      <el-button @click="load">搜索</el-button>
    </div>
    <div style="display: flex;margin-top: 10px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="delBatch">
        <template #reference>
          <el-button>批量删除</el-button>
        </template>
      </el-popconfirm>
    </div>
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

const pageSizeArr = ref([10, 20, 30, 40])
const total = ref(0)
const selectRow = ref({})
const fileList = ref([])
const myDrawerRef = ref()
const pageRequest = ref({pageNum: 1,pageSize: 10,q: {}})
const delIds = ref([])
const categoryList = ref([])
// 数据源
const tableData = ref([])

const selectCategory = (e) => {
    let petCategory = JSON.parse(e)
    selectRow.value.petCategoryId = petCategory.id
    selectRow.value.petCategoryName = petCategory.category
}

const getCategoryList = async () => {
    const res = await http.post("/petCategory/page",{pageNum: 1,pageSize: 1000,q: {}},"-1")
    categoryList.value = res.data.list
}
const handleRemove = (file, fileList) => {
    let index = selectRow.value.menuImages.findIndex(u => u.indexOf(file.url) !== -1)
    if (index > -1) {
        selectRow.value.menuImages.splice(index, 1)
    }
}
onMounted(() => {
    load()
    getCategoryList()
})
const handleAvatarSuccess = (res) => {
    if (res.code === '200') {
        if (!selectRow.value.menuImages) {
            selectRow.value.menuImages = []
        }
        selectRow.value.menuImages.push(res.data)
    }
}
const add = () => {
    selectRow.value = {}
    fileList.value = []
    myDrawerRef.value.open()
}

const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning",message: "请先勾选数据",type: "warning"})
        return
    }
    await http.post("/petCenter/deleteBatchByIds",delIds.value,"删除成功！")
    await load()
}
const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id)
}
const edit = (row) => {
    selectRow.value = JSON.parse(JSON.stringify(row))
    if (!selectRow.value.menuImages) {
        selectRow.value.menuImages = []
    }
    fileList.value = selectRow.value.menuImages.map(x => {
        return { url: x }
    })
    myDrawerRef.value.open(selectRow.value)
}
const del = async (id) => {
    await http.del("/petCenter/" + id)
    await load()
}

const handleSizeChange = (size) => {
    pageRequest.value.pageSize = size
    load()
}

const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum
    load()
}

const saveOrUpdate = async () => {
    selectRow.value.imageUrlList = ""
    selectRow.value.menuImages = dbImages(selectRow.value.menuImages)
    await http.post("/petCenter",selectRow.value,"操作成功！")
    myDrawerRef.value.close()
    await load()
}

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
</script>