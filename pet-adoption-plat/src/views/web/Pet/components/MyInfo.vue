<template>
    <div style="display: flex">
        <el-image v-if="user" style="height: 160px;width: 160px;border-radius: 10px" fit="cover" :src="user.imageUrl"
                  :preview-src-list="[user.imageUrl]"></el-image>
        <img v-else style="height: 160px;width: 160px;border-radius: 10px" fit="cover" src="/logo.png"></img>
        <div style="margin-left: 10px;font-size: 14px" v-if="user">
            <p style="margin-bottom: 13px;line-height: 1.2">昵称：{{ user.nickName }}</p>
            <p style="margin-bottom: 13px">年龄：{{ user.age }}</p>
            <p style="margin-bottom: 13px">邮箱：{{ user.email }}</p>
            <p style="margin-bottom: 13px">手机：{{ user.phone }}</p>
            <div>
                <el-button @click="editUserInfo">编辑个人信息</el-button>
            </div>
        </div>
        <div style="display: flex;justify-content: center;align-items: center" v-else>
            <el-button @click="login" style="width: 200px;margin-left: 10px" type="danger">用户登陆</el-button>
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
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus/>
                        </el-icon>
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
            </el-form>
        </MyDrawer>
    </div>
</template>
<script setup>
import {ref,onMounted} from "vue";
import http from "@/utils/Request.js";
import {baseURL} from "@/utils/Request.js";
import MyDrawer from "@/components/MyDrawer.vue";
import {ElNotification} from "element-plus";
import {openLogin} from "@/plugins/LoginDialog.js";

const selectRow = ref({})
const editUserInfo = () => {
    selectRow.value = JSON.parse(JSON.stringify(user.value))
    myDrawerRef.value.open(selectRow.value)
}
const myDrawerRef = ref()
const user = ref("")

onMounted(() => {
    getPersonInfo()
})

const login = () => {
    openLogin()
}
const getPersonInfo = async () => {
    let userLocal = JSON.parse(localStorage.getItem("user"))
    const res = await http.get("/plat/index/getPersonInfo?userId=" + userLocal.userId, null, "-1")
    user.value = res.data
}
const saveOrUpdate = async (e) => {
    await http.post("/user/saveOrUpdate", selectRow.value, "-1")
    ElNotification({title: "Success", message: "操作成功", type: "success"})
    myDrawerRef.value.close()
    getPersonInfo()
}
const handleAvatarSuccess = (e) => {
    if (e.code === '200') {
        selectRow.value.imageUrl = e.data
    }
}
</script>