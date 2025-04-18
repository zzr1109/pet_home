<template>
    首页设计中，代码中需要删除后续注释(视图层那部分需要再加上注释)
</template>
<!-- <template>
    <div>
        <div class="topNav">
            <div class="iconName">
                <img src="/logo.png"/>
                <span style="color: var(--accent-200)">宠物领养后台管理系统</span>
            </div>
            <div class="logout xw-enlarge" @click="logout">
                <img src="@/assets/images/logout.png"/>
                <span style="color: var(--accent-200)">Logout</span>
            </div>
        </div>
        <div style="display: flex">
            <div class="leftSidebar">
                <LeftMenu></LeftMenu>
            </div>
            视图层
            <div style="padding: 10px;width: calc(100vw - 240px);">
                <el-page-header icon="" @back="router.go(-1)">
                    <template #content>
                        <div class="flex items-center">
                            <div style="display: flex">
                                <el-icon class="menu-icon">
                                    <component :is="route.meta.icon"></component>
                                </el-icon>
                                <span style="margin-left: 5px"> {{route.meta.menuName}} </span>
                            </div>
                        </div>
                    </template>
                </el-page-header>
                <router-view class="hiddenScrollbar" style="margin-top: 10px;overflow-y: scroll;height: calc(100vh - 110px);"></router-view>
            </div>
        </div>
    </div>
</template>

<script setup>
import LeftMenu from "@/sidebar/LeftMenu.vue";
import http from "@/utils/Request.js"
import {useRouter,useRoute} from "vue-router";
const router = useRouter()
const route = useRoute()


const logout = async () => {
    await http.get("/web/logout",null,"-1")
    localStorage.clear()
    await router.push({name: "login"})
}
</script>

<style lang="scss" scoped>
.leftSidebar {
    width: 240px;
}

.topNav {
    width: 100vw;
    height: 50px;
    border-bottom: 1px solid var(--bg-300);
    display: flex;
    justify-content: space-between;

    .iconName {
        display: flex;
        align-self: center;
        margin-left: 20px;

        > img {
            height: 35px;
            width: 35px;
        }

        > span {
            align-self: center;
            font-size: 20px;
            margin-left: 10px;
        }
    }

    .logout {
        display: flex;
        align-self: center;
        margin-right: 20px;

        > img {
            height: 35px;
            width: 35px;
        }

        > span {
            align-self: center;
            font-size: 20px;
            margin-left: 10px;
        }
    }
}
</style> -->