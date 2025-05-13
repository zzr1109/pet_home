<template>
    <div>
        <el-card style="max-width: 480px">
            <template #header>
                <div style="display: flex;align-items: center">
                    <p style="margin-right: 10px;font-size: 16px;font-weight:bold;flex: 1">{{ commodity.name }}</p>
                    <p style="margin-left: 10px;font-size: 16px;font-weight:bold;font-size: 12px;color: #8c939d;font-weight: 400">已售:{{ commodity.buyNumber }}</p>
                    <p style="margin-left: 10px;font-size: 16px;font-weight:bold;font-size: 12px;color: #8c939d;font-weight: 400">库存:{{ commodity.stockNumber - commodity.buyNumber }}</p>
                </div>
            </template>
            <el-image style="width: 100%;height: 260px" :src="baseURL() +  commodity.imageUrlList"/>
            <template #footer>
                <p>{{ commodity.remark }}</p>
                <div style="display: flex;justify-content: space-between;align-items: center">
                    <span style="color: red;font-size: 20px;font-weight: bold">¥{{ commodity.price }}</span>
                    <div>
                        <el-popover :visible="visible" placement="bottom" :width="180" trigger="click">
                            <template #reference>
                                <el-button @click="visible = true" type="primary" :icon="Sell"></el-button>
                            </template>
                            <div style="display: flex;align-items: center;margin-bottom: 10px">
                                数量:
                                <el-input-number size="small" v-model="submitData.number" :min="1"
                                                 :max="commodity.stockNumber - commodity.buyNumber"/>
                            </div>
                            <div style="display: flex;align-items: center;justify-content: center">
                                <el-button size="small" @click="visible = false">取 消</el-button>
                                <el-button type="danger" size="small" @click="addToCart">加 入</el-button>
                            </div>
                        </el-popover>
                        <el-button @click="payNow" type="success" :icon="CreditCard">立即购买</el-button>
                    </div>
                </div>
            </template>
        </el-card>
        <Pay ref="goToPayment"></Pay>
    </div>
</template>
<script setup>
import {CreditCard, Sell} from "@element-plus/icons-vue";
import http, {baseURL} from "@/utils/Request.js";
import {ref} from "vue";
import Pay from "@/components/Pay.vue";

const props = defineProps({
    commodity: {
        type: Object,
        default: () => {
        }
    }
})
const visible = ref(false)
const goToPayment = ref()
const submitData = ref(
    {
        goodsName: props.commodity.name,//商品名称
        number: 1,//购买数量
        singlePrice: props.commodity.price,//购买单价
        goodsCenterId: props.commodity.id,//商品ID
    }
)
const emits = defineEmits(['refresh'])
const addToCart = async () => {
    await http.post("/plat/goods/addCar", submitData.value, '加入成功!')
    visible.value = false
    emits('refresh')
}
//创建订单
const payNow = async () => {
    //创建订单请求参数
    let obj = {
        orderDetailCenterList: [submitData.value]
    }
    const res = await http.post("/plat/createOrder", obj, '创建订单成功!')

    //打开支付弹框
    goToPayment.value.open(res.data.id, res.data.orderPrice)
}

</script>


<style scoped lang="scss">

</style>