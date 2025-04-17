<template>
    <div>
        <el-sub-menu :index="props.index + ''" class="menu-border">
            <template #title>
                <el-icon class="menu-icon">
                    <component :is="props.menu.meta.icon"></component>
                </el-icon>
                <span class="menu-name">
                    {{ props.menu.meta.menuName }}</span>
            </template>
            <div v-for="(menuChildren,childrenIndex) in props.menu.children" :key="childrenIndex" class="my-submenu">
                <el-menu-item :index="menuChildren.path" v-if="menuChildren.path" class="my-submenu">
                    <el-icon class="menu-icon">
                        <component :is="menuChildren.meta.icon"></component>
                    </el-icon>
                    <span class="menu-name" slot="title">{{ menuChildren.meta.menuName }}</span>
                </el-menu-item>
                <MenuItem v-else :menu="menuChildren" :index="props.index + '-' + childrenIndex"></MenuItem>
            </div>
        </el-sub-menu>
    </div>
</template>
<script setup>
const props = defineProps({
    menu: {
        type: Object,
        default: {}
    },
    index: {
        type: Number,
        default: 0
    }
})
</script>

<style lang="scss" scoped>
.menu-name {
    text-decoration: underline;
    text-underline-offset: 6px;
    text-decoration-color: var(--primary-100);
}
</style>