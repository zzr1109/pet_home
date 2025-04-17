<template>
    <div class="hiddenScrollbar" style="width: 100vw; height: 100vh;background-color: #EEF2F9;overflow-y: scroll">
        <div
            style="height: 65px;background-color: rgba(233,102,64,0.6);display: flex;align-items: center;justify-content: space-between;padding: 0 20px;">
            <div style="display: flex">
                <div style="display: flex">
                    <el-avatar :size="45" src="/logo.png"></el-avatar>
                    <span style="color: var(--accent-200);font-size: 18px;font-weight: bold;align-self: center;margin-left: 20px">宠物领养中心</span>
                </div>
                <div style="display: flex;align-items: center;margin-left: 65px">
                    <div @click="switchPages(item)" :class="{active:item.pathName ===  route.name}" class="tags"
                         style="padding:5px 0;width: 100px;text-align: center;font-size: 16px;background-color: red;border-radius: 5px;cursor: pointer;background-color: #fff"
                         v-for="item in tags" :key="item.name">
                        {{ item.name }}
                    </div>
                </div>
            </div>
            <div class="xw-enlarge">
                <div v-if="userImageUrl" style="display: flex;align-items: center">
                    <el-avatar :size="45" :src="userImageUrl"></el-avatar>
                    <span @click="logOutAndLogIn" style="color: var(--accent-200);font-size: 16px;margin-left: 10px">退出登录</span>
                </div>
            </div>
        </div>
        <div style="width: 1400px;height:100vh;margin: 0 auto;display: flex;flex-direction: column;">
            <div style="flex: 1;overflow-y: scroll;" class="hiddenScrollbar">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>
<script setup>
import http, {baseURL} from "@/utils/Request.js"
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import starImg from '@/assets/images/明星值.png'
import logo from '@/assets/images/logo.jpeg'

const route = useRoute();
const router = useRouter()
const tags = ref([
    {
        name: '宠物中心',
        pathName: 'petList'
    },
    {
        name: '宠物圈',
        pathName: 'petCircle'
    },
    {
        name: '宠物商品',
        pathName: 'petGoods'
    },
    {
        name: '个人中心',
        pathName: 'personalCenter'

    }
])
const userImageUrl = ref(localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).imageUrl : '')


//退出登陆
const logOutAndLogIn = () => {
    localStorage.clear()
    location.reload()
}
//切换页面
const switchPages = (item) => {
    router.push({name: item.pathName})
}
</script>
<style scoped lang="scss">
.active {
    background-color: #9E6531 !important;
    color: #fff;
    font-weight: bold;
}

.tags {
    &:not(:last-child) {
        margin-right: 20px;
    }
}
</style>