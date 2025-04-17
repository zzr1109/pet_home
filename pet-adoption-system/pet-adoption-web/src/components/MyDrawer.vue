<template>
    <div>
        <el-drawer v-model="drawer" :size="props.size" :show-close="false">
            <template #header="{ close, titleId, titleClass }">
                <h4 :id="titleId" :class="titleClass">{{props.title}}</h4>
                <el-button size="small" type="danger" @click="close">
                    <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
                    Close
                </el-button>
            </template>

            <div class="content hiddenScrollbar">
                <slot/>
            </div>

            <div class="bottom" v-if="showButton">
                <el-button type="primary" @click="save()">保 存</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-drawer>
    </div>
</template>
<script setup>
import { ref } from 'vue';

const emit = defineEmits(['saveOrUpdate'])

const props = defineProps({
    title: {
        type: String,
        default: "信息"
    },
    size: {
        type: String,
        default: '25%'
    },
    showButton: {
        type: Boolean,
        default: true
    }
})

const drawer = ref(false)
const dataObj = ref('')

const open = (v) => {
    dataObj.value = v
    drawer.value = true
}

const cancel = () => {
    drawer.value = false
    dataObj.value = ''
}

const close = () => {
    drawer.value = false
}

const save = () => {
    emit('saveOrUpdate',dataObj.value)
}

defineExpose({open,cancel,close,save})

</script>
<style lang="scss" scoped>
    :deep(.el-drawer__header) {
        padding: 10px;
        color: #FFFFFF;
        margin-bottom: 0;
        border-bottom: 1px solid var(--bg-300);
    }

    :deep(.el-drawer__body) {
        overflow-y: hidden;
        padding: 10px;
        //background-color: var(--change-backgroud-color);

        .bottom {
            height: 50px;
            position: absolute;
            bottom: 10px;
            text-align: center;
            width: 100%;

        }

        .content {
            height: calc(100vh - 140px);
            overflow-y: scroll;
        }
    }
</style>