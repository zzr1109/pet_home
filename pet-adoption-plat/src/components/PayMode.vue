<template>
    <div>
        <el-radio-group v-model="selectPayMode" style="display: flex;flex-wrap: wrap" @change="changePayMode">
            <div class="xw-enlarge" :class="selectPayMode===item.name ? 'selectPayMode' : ''"
                 style="margin-bottom: 5px;width: 120px;background-color: #FFFFFF;margin-right: 10px;padding: 10px;display: flex;"
                 v-for="(item,index) in tableData" :key="index">
                <img :src="item.pic" style="height: 30px;width: 30px"/>
                <el-radio :value="item.name" :label="item.name"
                          style="position: relative;align-self: center"></el-radio>
            </div>
        </el-radio-group>
    </div>
</template>
<script setup>
import {ref} from "vue";

const selectPayMode = ref("")
const tableData = ref([
        {
            name: '微信支付',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98.png',
            id: 1
        },
        {
            name: '支付宝支付',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E6%94%AF%E4%BB%98%E5%AE%9D%E6%94%AF%E4%BB%98.png',
            id: 2
        },
        {
            name: '建设银行',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E5%BB%BA%E8%AE%BE%E9%93%B6%E8%A1%8C%20%281%29.png',
            id: 3
        },
        {
            name: '农业银行',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E5%86%9C%E4%B8%9A%E9%93%B6%E8%A1%8C-05.png',
            id: 4
        },
        {
            name: '广发银行',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E5%B9%BF%E5%8F%91%E9%93%B6%E8%A1%8C.png',
            id: 5
        },
        {
            name: '中国银行',
            pic: 'https://no1-system.oss-cn-beijing.aliyuncs.com/%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C.png',
            id: 6
        }
    ])
const emit = defineEmits(['payMode'])
const changePayMode = (e) => {
    emit("payMode",e)
}
</script>

<style lang="scss" scoped>
::v-deep(.el-radio__input) {
    display: none;
}

.selectPayMode {
    border: 1px solid var(--theme-backgroud-color);
}
</style>