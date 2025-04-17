<template>
    <div>
        <ElDialog class="login-dialog" destroy-on-close :show-close="false" v-model="centerDialogVisible" width="500"
                  center>
            <div class="login">
                <div class="left">
                    <div style="display: flex">
                        <img :size="45" src="/logo.png"></img>
                        <span style="color: var(--accent-200);font-size: 18px;font-weight: bold;align-self: center;margin-left: 20px">宠物领养中心</span>
                    </div>
                    <form id="myForm" class="form">
                        <div class="input-block" v-if="!isLogin" style="display: flex;justify-content: center;padding-top: 10px">
                            <ElUpload
                                class="avatar-uploader"
                                :action="baseURL() + '/file/upload'"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess">
                                <img v-if="form.imageUrl" :src="form.imageUrl" class="avatar" alt="">
                                <ElIcon v-else class="avatar-uploader-icon">
                                    <Plus/>
                                </ElIcon>
                            </ElUpload>
                        </div>
                        <div class="input-block">
                            <input v-model="form.userName" class="input" type="text" id="email" required="">
                            <label for="email">Username</label>
                        </div>
                        <div class="input-block">
                            <input v-model="form.password" class="input" type="password" id="pass" required>
                            <label for="pass">Password</label>
                        </div>
                        <div v-if="!isLogin">
                            <div class="input-block">
                                <input v-model="form.confirmPassword" class="input" type="text" id="qrpass" required>
                                <label for="text">confirmPassword</label>
                            </div>
                            <div class="input-block">
                                <input v-model="form.nickName" class="input" type="text" id="nickName" required>
                                <label for="text">nickName</label>
                            </div>
                            <div class="input-block">
                                <input v-model="form.email" class="input" type="email" id="email" required>
                                <label for="email">email</label>
                            </div>
                        </div>
                        <div class="input-block">
                            <span @click="isLogin = !isLogin"
                                  class="forgot"><span>{{ isLogin ? '没有用户点击注册?' : '前往登陆' }} </span></span>
                            <button style="cursor: pointer" @click="submit">{{ isLogin ? '登 录' : '注册并登陆' }}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </ElDialog>
    </div>
</template>

<script setup>
import http, {baseURL} from "@/utils/Request.js"
import {ref} from "vue";
import {Plus} from "@element-plus/icons-vue";
import {ElDialog, ElUpload, ElIcon} from "element-plus";

const isLogin = ref(true)
const centerDialogVisible = ref(false)
const loginOpen = () => {
    centerDialogVisible.value = true
}
const form = ref(
    {
        imageUrl: null,
        role: 'ROLE_USER'
    }
)
const handleAvatarSuccess = (e) => {
    if (e.code === '200') {
        form.value.imageUrl = e.data
    }
}
const submit = () => {
    const formDom = document.getElementById('myForm');
    formDom.addEventListener('submit', async function (event) {
        event.preventDefault(); // 防止默认提交行为
        //登录
        if (isLogin.value) {
            const res = await http.post("/web/login", form.value, "登陆成功！")
            localStorage.setItem("user", JSON.stringify(res.data))
            centerDialogVisible.value = false
            location.reload()
        } else {
            //注册
            await http.post("/web/register",form.value,"注册成功！")
            isLogin.value = !isLogin.value
        }
    });
}
defineExpose({
    loginOpen
})

</script>

<style scoped lang="scss">
::v-deep(.login-dialog) {
    padding: 0;
}

::v-deep(.el-dialog__header) {
    padding: 0;
}

.login {
    display: flex;
    width: 520px;
    // height: 400px;
    max-width: 100%;
    align-items: center;
    justify-content: center;
    position: relative;
    overflow: hidden;
    background-color: #ffffff25;
    box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.03);
    border: 0.1px solid rgba(128, 128, 128, 0.178);
}

.left {
    width: 86%;
    height: 100%;
    padding-bottom: 30px;
}

.form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100%;
    width: 100%;
    left: 0;
    backdrop-filter: blur(20px);
    position: relative;
}

.form::before {
    position: absolute;
    content: "";
    width: 40%;
    height: 40%;
    right: 1%;
    z-index: -1;
    background: radial-gradient(circle,
        rgb(194, 13, 170) 20%,
        rgb(26, 186, 235) 60%,
        rgb(26, 186, 235) 100%);
    filter: blur(70px);
    border-radius: 50%;
}

.right {
    width: 34%;
    height: 100%;
}

.img {
    width: 100%;
    height: 100%;
}

.login::after {
    position: absolute;
    content: "";
    width: 80%;
    height: 80%;
    right: -40%;
    background: rgb(157, 173, 203);
    background: radial-gradient(circle,
        rgba(157, 173, 203, 1) 61%,
        rgba(99, 122, 159, 1) 100%);
    border-radius: 50%;
    z-index: -1;
}

.input,
button {
    background: rgba(253, 253, 253, 0);
    outline: none;
    border: 1px solid rgba(255, 0, 0, 0);
    border-radius: 0.5rem;
    padding: 10px;
    margin: 10px auto;
    width: 80%;
    display: block;
    color: #425981;
    font-weight: 500;
    font-size: 1.1em;
}

.input-block {
    position: relative;
}

label {
    position: absolute;
    left: 15%;
    top: 37%;
    pointer-events: none;
    color: gray;
}

.forgot {
    display: block;
    margin: 5px 0 10px 0;
    margin-left: 35px;
    color: #5e7eb6;
    font-size: 0.9em;
    cursor: pointer;

    &:hover {
        text-decoration: underline;
    }
}

.input:focus + label,
.input:valid + label {
    transform: translateY(-120%) scale(0.9);
    transition: all 0.4s;
}

button {
    background-color: #5e7eb6;
    color: white;
    font-size: medium;
    box-shadow: 2px 4px 8px rgba(70, 70, 70, 0.178);
}

a {
    color: #5e7eb6;
}

.input {
    box-shadow: inset 4px 4px 4px rgba(165, 163, 163, 0.315),
    4px 4px 4px rgba(218, 218, 218, 0.13);
}

</style>
