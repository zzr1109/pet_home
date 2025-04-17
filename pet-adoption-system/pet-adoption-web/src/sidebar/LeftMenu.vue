<template>
    <div class="LeftMenu hiddenScrollbar" style="overflow-y: scroll;">
        <el-menu router>
            <div v-for="(menu,index) in allMenus" :key="index">
                <el-menu-item :index="menu.path" v-if="menu.path" class="menu-border"
                >
                    <el-icon class="menu-icon">
                        <component :is="menu.meta.icon"></component>
                    </el-icon>
                    <span class="menu-name" slot="title">{{ menu.meta.menuName }}</span>
                </el-menu-item>
                <MenuItem v-else :menu="menu" :index="index"></MenuItem>
            </div>
        </el-menu>
    </div>
</template>
<script setup>
import MenuItem from "./MenuItem.vue"
import {ref, reactive, onMounted} from "vue";

const allMenus = reactive([])
const user = ref(JSON.parse(localStorage.getItem("user")))

onMounted(() => {
    allMenus.push(...JSON.parse(localStorage.getItem("menus"))?.children?.filter(x => x.meta.isShowMenu))
})
</script>
<style scoped>
@keyframes rotate {
    from {
        transform: rotate(0deg);
    }
    to {
        transform: rotate(360deg);
    }
}

.rotating-element {
    animation: rotate 8s linear infinite;
}

.LeftMenu {
    height: calc(100vh - 50px);

    .el-menu {
        height: calc(100vh - 50px);
    }
}
.menu-name {
    text-decoration: underline;
    text-underline-offset: 6px;
    text-decoration-color: var(--primary-100);
}
</style>