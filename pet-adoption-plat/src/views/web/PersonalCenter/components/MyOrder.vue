<template>
    <div>
        <el-table :expand-row-keys="expandedRowKeys" :row-key="row => row.id" :data="orderData" style="width: 100%" @expand-change="handlerExpandChange">
            <el-table-column type="expand">
                <template #default="scope">
                    <el-table v-loading="!scope.row.loadDetails" :data="scope.row.orderDetailCenterList" border stripe>
                        <el-table-column align="center" label="商品图片" prop="goodsName">
                            <template #default="scope">
                                <img :src="baseURL() + scope.row.goodsCenter.imageUrlList" alt=""
                                     style="width: 80px;height: 80px">
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="商品名称" prop="goodsName" width="商品名称"/>
                        <el-table-column align="center" label="购买数量" prop="number" width="商品名称"/>
                        <el-table-column align="center" label="单 价" prop="goodsName">
                            <template #default="scope">
                                <span style="color: red">¥{{ scope.row.singlePrice }}</span>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </el-table-column>
            <el-table-column align="center" label="订单号" prop="orderNumber"/>
            <el-table-column align="center" label="订单金额" prop="orderNumber">
                <template #default="scope">
                    <h4 style="color: red"> ¥{{ scope.row.orderPrice }}</h4>
                </template>
            </el-table-column>
            <el-table-column align="center" label="订单状态">
                <template #default="scope">
                    <el-tag :type="mapOrderStatus[scope.row.status].type">{{
                            mapOrderStatus[scope.row.status].name
                        }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操 作">
                <template #default="scope">
                    <el-button :disabled="scope.row.status === 1" size="small" type="danger"
                               @click="cancellationOfOrder(scope.row)">取消订单
                    </el-button>
                    <el-button :disabled="scope.row.status === 0 || scope.row.status === 1" size="small" type="success"
                               @click="payment(scope.row)">支 付
                    </el-button>
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
        <Pay ref="goToPayment"></Pay>
    </div>
</template>
<script setup>
import http, {baseURL} from "@/utils/Request.js"

import {onMounted, reactive, ref} from "vue";
import {ElMessageBox} from "element-plus";
import Pay from "@/components/Pay.vue";

const expandedRowKeys = ref([])
const goToPayment = ref()
const mapOrderStatus = reactive({
    '-1': {
        name: '已下单',
        type: 'primary'
    },
    '0': {
        name: '已支付',
        type: 'success'
    },
    '1': {
        name: '已取消',
        type: 'info'
    },
    '2': {
        name: '已完成',
        type: 'success'
    }
})
const requestBody = reactive({
    "pageNum": 1,
    "pageSize": 10,
    "q": {}
})
const total = ref(0)
const orderData = ref([])
//获取订单数据
const getOrderDatas = async () => {
    const res = await http.post("/plat/personCenter/page", requestBody, '-1')
    orderData.value = res.data.list
    total.value = res.data.total
}
const handleCurrentChange = (val) => {
    requestBody.pageNum = val
    getOrderDatas()
}
//取消订单
const cancellationOfOrder = (row) => {
    ElMessageBox.confirm(
        '您确定取消订单吗?',
        '提 示',
        {
            confirmButtonText: '确 定',
            cancelButtonText: '取 消',
        })
        .then(async () => {
            let obj = JSON.parse(JSON.stringify(row))
            obj.status = 1
            await http.post("/plat/createOrder/opOrder", obj, '取消成功!')
            await getOrderDatas()
        })
}

//支付
const payment = (row) => {
    goToPayment.value.open(row.id, row.orderPrice)
}
const handlerExpandChange = async (row, expandedRows) => {
    if (!row.orderDetailCenterList) {
        const res = await http.get(`/plat/createOrder/seeOrderDetail?orderNumber=${row.orderNumber}`, {}, '-1')
        row.orderDetailCenterList = res.data
        row.loadDetails = true
        expandedRowKeys.value = expandedRows.map(val => val.id)
    }
}
onMounted(() => {
    getOrderDatas()
})
</script>

<style lang="scss" scoped>

</style>