<template>
    <div style="position: relative;padding-top: 10px">
        <div>
             <el-input v-model="requestBody.q.name" placeholder="搜索想要的商品?" style="width: 300px" clearable></el-input>
             <el-button type="warning" style="margin-left: 10px" @click="getGoods">搜 索</el-button>
        </div>
        <div style="display: grid;grid-template-columns:repeat(4,1fr);gap: 10px;margin-top: 10px">
            <good-card @refresh="refresh" v-for="item in goodList" :commodity="item" :key="item.id"></good-card>
        </div>
        <footer style="display: flex;justify-content: center;margin: 25px 0">
            <el-pagination
                v-model:current-page="requestBody.pageNum"
                :default-page-size="requestBody.pageSize"
                @current-change="handleCurrentChange"
                size="small"
                background
                layout="prev, pager, next"
                :total="total"
            />
        </footer>
        <div v-if="numberOfShoppingCarts" style="position:fixed;right: 18px;top: 200px;">
            <el-badge :value="numberOfShoppingCarts" class="item">
                <div @click="openShoppingCart"
                     style="width: 30px;height: 30px;background-color: #fff;border-radius: 50%;cursor: pointer;display: flex;align-items: center;justify-content: center">
                    <img style="width: 20px;height: 20px" :src="shoppingCartImg" alt="">
                </div>
            </el-badge>
        </div>
        <ShoppingCartPop @refresh="getShoppingCartData" ref="shoppingCartPop"></ShoppingCartPop>
    </div>
</template>
<script setup>
import {onMounted, reactive, ref} from "vue";
import http from "@/utils/Request.js"
import goodCard from "./components/GoodCard.vue";
import shoppingCartImg from "@/assets/images/购物车.png"
import ShoppingCartPop from "@/views/web/PetGoods/components/ShoppingCartPop.vue";

const shoppingCartPop = ref()
//商品数据
const goodList = ref([])
const numberOfShoppingCarts = ref(0)
const total = ref(0)
const requestBody = reactive({
    "pageNum": 1,
    "pageSize": 8,
    "q": {}
})

const getGoods = async () => {
    const res = await http.post("/plat/goods/page", requestBody, '-1')
    goodList.value = res.data.list
    total.value = res.data.total
}
//获取购物车数量
const getShoppingCartData = async () => {
    const res = await http.post("/plat/orderCar/page", requestBody, '-1')
    numberOfShoppingCarts.value = res.data.total
}
const handleCurrentChange = (val) => {
    requestBody.pageNum = val
    getGoods()
}
//打开购物车
const openShoppingCart = () => {
    shoppingCartPop.value.open()
}
const refresh = () => {
    getGoods()
    getShoppingCartData()
}
onMounted(() => {
    getGoods()
    getShoppingCartData()
})
</script>
<style scoped lang="scss">

</style>