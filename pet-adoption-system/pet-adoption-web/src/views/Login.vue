<template>
    <div class="mainss">
        <div class="login">
            <div class="loginText">
                <img src="/logo.png" />
                <span>宠物领养后台管理</span>
            </div>
            <div class="field">
                <input v-model="form.userName" type="text" placeholder="请输入账号？">
            </div>
            <div class="field">
                <input v-model="form.password" type="password" placeholder="请输入密码？">
            </div>
            <div class="loginBtn" @click="login">登陆</div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import http from "@/utils/Request.js"
import { ElNotification } from 'element-plus'
import { useRouter } from "vue-router"
import { setRoutes } from "@/router/index.js";

const router = useRouter()
const form = ref({
    userName: "",
    password: ""
})

const login = async () => {
    const res = await http.post("/web/login", form.value, "登陆成功！")
    localStorage.setItem("user", JSON.stringify(res.data))
    setRoutes()
    await router.push({name: "main"})
}
</script>

<style lang="scss">
.mainss{
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url("@/assets/images/login.png");
    background-size: 100% 100%;
}
.login {
    margin: 0 auto;
    //background: #151617;
    background-color: rgba(21,22,23,0.7);
    width: 20rem;
    padding: 1.5rem;
    border-radius: 1.5rem;
    display: grid;
    gap: 0.5rem;
    margin-top: -100px;
}

.loginText {
    color: #6779f5;
    text-align: center;
    font-size: 2rem;
    font-weight: 600;
    margin-bottom: 1rem;
    display: flex;
    >img {
        height: 50px;
        width: 50px;
    }
    >span {
        margin-left: 10px;
        align-self: center;
    }
}


.field {
    background: #6779f5;
    border-radius: 0.75rem;
    position: relative;
    height: 2.5rem;
    --anim: 0;
    transition: --anim 500ms ease;

    //background:
    //    linear-gradient(to right,
    //        #6779f5 calc(clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
    //        transparent calc(clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
    //        transparent calc(100% - clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
    //        #6779f5 calc(100% - clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%)),
    //
    //    linear-gradient(to top,
    //        transparent calc(15% + clamp(0, (var(--anim) - 0.65) / 0.1, 1) * 70%),
    //        #202122 calc(15% + clamp(0, (var(--anim) - 0.65) / 0.1, 1) * 70%)),
    //
    //    linear-gradient(to right,
    //        transparent calc(50% - clamp(0, var(--anim) / 0.65, 1) * 50%),
    //        #6779f5 calc(50% - clamp(0, var(--anim) / 0.65, 1) * 50%),
    //        #6779f5 calc(50% + clamp(0, var(--anim) / 0.65, 1) * 50%),
    //        transparent calc(50% + clamp(0, var(--anim) / 0.65, 1) * 50%)),
    //
    //    linear-gradient(#202122, #202122);
}

//.field:has(input:focus) {
//    --anim: 1;
//}


.field>.placeholder {
    pointer-events: none;
    position: absolute;
    inset: 0;
    display: grid;
    place-content: center;
    color: #7d8dff;
    font-family: "Inter";
    transition: transform 500ms ease;
}

//.field:has(input:focus)>.placeholder,
//.field:has(input:not(:placeholder-shown))>.placeholder {
//    transform: translateY(-50%) scale(0.85)
//}

.field>input {
    display: flex;
    align-items: center;
    padding-left: 1rem;
    color: white;
    position: absolute;
    inset: 0.125rem;
    border-radius: 0.625rem;
    border: none;
    outline: none;
    background: #202122;
}

.loginBtn {
    margin-top: 0.5rem;
    background: radial-gradient(circle at center, #6779f5 calc(-50% + var(--anim) * 150%), #202122 calc(var(--anim) * 100%));
    border-radius: 0.75rem;
    position: relative;
    height: 2.5rem;
    display: grid;
    place-content: center;
    color: #7d8dff;
    font-family: "Inter";
    --anim: 0;
    transition: --anim 500ms ease, color 500ms ease;
}

.loginBtn:hover {
    --anim: 1;
    color: white;
    cursor: pointer;
}
</style>