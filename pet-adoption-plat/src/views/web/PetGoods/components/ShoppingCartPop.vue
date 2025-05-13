<template>
    <div>
        <el-drawer @close="$emit('refresh')" v-model="drawer" destroy-on-close size="50%" title="购物车">
            <div style="height: 80vh;display: flex;flex-direction: column;">
                <div style="flex: 1;">
                    <el-table :data="shoppingCartData" style="width: 100%" @selection-change="handlerSelectionChange">
                        <el-table-column align="center" type="selection" width="55"/>
                        <el-table-column align="center" label="商品图片" prop="goodsName">
                            <template #default="scope">
                                <img :src="baseURL() +  scope.row.goodsCenter.imageUrlList" alt=""
                                     style="width: 80px;height: 80px">
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="商品名称" prop="goodsName" width="商品名称"/>
                        <el-table-column align="center" label="价格" prop="goodsName">
                            <template #default="scope">
                                <span style="color: red">¥{{ scope.row.singlePrice }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="数量变更" prop="goodsName">
                            <template #default="scope">
                                <el-input-number v-model="scope.row.number"
                                                 :max="scope.row.goodsCenter.stockNumber - scope.row.goodsCenter.buyNumber"
                                                 :min="1"
                                                 size="small"/>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="操作">
                            <template #default="scope">
                                <el-button type="danger" @click="delShoppingCart(scope.row.id)">删 除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <footer style="display: flex;justify-content: center;margin: 25px 0">
                        <el-pagination
                            v-model:current-page="requestBody.pageNum"
                            :default-page-size="requestBody.pageSize"
                            :total="total"
                            background
                            layout="prev, pager, next"
                            size="small"
                            @current-change="handleCurrentChange"
                        />
                    </footer>
                </div>
            </div>
            <div style="text-align: center;margin-top: 20px">
                <el-button style="width: 250px" type="success" @click="createAnOrder">创建订单</el-button>
            </div>
        </el-drawer>
        <Pay ref="goToPayment"></Pay>
    </div>
</template>


<script setup>
import {reactive, ref} from "vue";
import http, {baseURL} from "@/utils/Request.js"
import {ElNotification} from "element-plus";
import Pay from "@/components/Pay.vue";

const goToPayment = ref()
const drawer = ref(false)
const total = ref(0)
const open = () => {
    getShoppingCartData()
    drawer.value = true
}
const selectList = ref([]) //勾选的商品数据
const shoppingCartData = ref([])
const requestBody = reactive({
    "pageNum": 1,
    "pageSize": 5,
    "q": {}
})
//勾选的数据
const handlerSelectionChange = (list) => {
    selectList.value = list
}
//删除购物车
const delShoppingCart = async (id) => {
    await http.get(`/plat/orderCar/delOrderCar?orderCarId=${id}`, {}, '删除成功!')
    await getShoppingCartData()
}
//点击支付
const createAnOrder = async () => {
    if (!selectList.value.length) {
        return ElNotification({
            title: 'Error',
            message: '请勾选需要支付的商品!', // 此处可以换成接口返回的报错信息
            type: 'error'
        })
    }
    //创建订单请求参数
    const obj = {
        orderDetailCenterList: selectList.value.map(n => {
                return {
                    goodsName: n.goodsName,//商品名称
                    number: n.number,//购买数量
                    singlePrice: n.singlePrice,//购买单价
                    goodsCenterId: n.goodsCenterId,//商品ID
                }
            }
        )
    }
    const res = await http.post("/plat/createOrder", obj, '创建订单成功!')
    drawer.value = false
    //打开支付弹框
    goToPayment.value.open(res.data.id, res.data.orderPrice)
}
//获取购物车列表数据
const getShoppingCartData = async () => {
    const res = await http.post("/plat/orderCar/page", requestBody, '-1')
    shoppingCartData.value = res.data.list
    total.value = res.data.total
}

const handleCurrentChange = (val) => {
    requestBody.pageNum = val
    getShoppingCartData()
}
defineExpose({
    open
})
</script>

<style lang="scss" scoped>

</style>