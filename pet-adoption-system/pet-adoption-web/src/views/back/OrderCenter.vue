<template>
    <div>
        <div class="xw-block" style="display: flex">
            <el-input v-model="pageRequest.q.id" style="width: 300px" placeholder="请输入你想搜索的下单用户？" clearable></el-input>
            <el-button style="margin-left: 20px" type="primary" @click="load">搜索</el-button>
        </div>
        <el-table class="xw-block-table" :data="tableData" border @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop='orderNumber' label='订单编号'></el-table-column>
            <el-table-column prop='orderPrice' label='订单总金额（¥）'></el-table-column>
            <el-table-column prop='userName' label='购买人名称'></el-table-column>
            <el-table-column width='120' label='购买人头像'>
                <template #default="{row,$index}">
                    <el-image preview-teleported v-if='row.userImage' :preview-src-list='[row.userImage]' fit='cover' :src='row.userImage' />
                </template>
            </el-table-column>
            <el-table-column width='170' label='订单详情'>
                <template #default="{row,$index}">
                    <el-button @click="seeOrderDetail(row)">查看详情</el-button>
                </template>
            </el-table-column>
            <el-table-column width='120' label='下单状态'>
                <template #default="{row,$index}">
                    <span v-if='row.status===-1'>已下单</span>
                    <span v-if='row.status===0'>已支付</span>
                    <span v-if='row.status===1'>已取消</span>
                    <span v-if='row.status===2'>已完成</span>
                </template>
            </el-table-column>
            <el-table-column prop='payMode' label='支付方式'></el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{row,$index}">
                    <el-button size="small" @click="edit(row)">取消订单</el-button>
                    <el-popconfirm style="margin-left: 5px" title="您确定要删除吗？" @confirm="del(row.id)">
                        <template #reference>
                            <el-button size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div class="xw-block">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="pageSizeArr"
                :page-size="pageSizeArr[0]"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>

        <MyDrawer ref="myDrawerRef" title="订单详情" @saveOrUpdate="saveOrUpdate" :showButton="false">
            <el-table :data="orderDetailData" style="width: 100%">
                <el-table-column align="center" label="商品图片" width="120" prop="goodsName">
                    <template #default="scope">
                        <img :src="baseURL() +  scope.row.goodsCenter.imageUrlList" alt="" style="width: 120px;height: 120px">
                    </template>
                </el-table-column>
                <el-table-column align="center" label="商品名称" prop="goodsName" width="商品名称"/>
                <el-table-column align="center" label="价格">
                    <template #default="scope">
                        <span style="color: red">¥{{ scope.row.singlePrice }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="数量" prop="number"></el-table-column>
            </el-table>
        </MyDrawer>
    </div>
</template>
<script setup>
import MyDrawer from "@/components/MyDrawer.vue"
import { baseURL } from "@/utils/Request.js"
import http from "@/utils/Request.js"
import { ref,onMounted } from "vue"
import {ElNotification} from "element-plus";
import { dateFormatHm } from "@/utils/DateUtil.js";
import {splicingDomain,dbImages} from "@/utils/Base.js"
const myDrawerRef = ref()
const user = ref(JSON.parse(localStorage.getItem("user")))
const delIds = ref([])
const selectRow = ref({})
const pageSizeArr = ref([10, 20, 30, 40])
const tableData = ref([])
const total = ref(0)
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
const orderDetailData = ref([])
onMounted(() => {
    load()
})
// 查看订单详情
const seeOrderDetail = async (row) => {
    const res = await http.get("/plat/createOrder/seeOrderDetail?orderNumber=" + row.orderNumber,null,"-1")
    orderDetailData.value = res.data
    myDrawerRef.value.open()
}
const del = async (id) => {
    await http.del("/orderCenter/" + id,null,'-1')
    await load()
}
const delBatch = async () => {
    if (!delIds.value || delIds.value.length <= 0) {
        ElNotification({title: "Warning", message: "请先勾选数据", type: "warning"})
        return
    }
    await http.post("/orderCenter/deleteBatchByIds", delIds.value,'删除成功!')
    await load()
}
const saveOrUpdate = async (e) => {
    await http.post("/orderCenter", selectRow.value,'操作成功！')
    myDrawerRef.value.close()
    await load()
}
const load = async () => {
    const res = await http.post("/orderCenter/page", pageRequest.value,'-1')
    tableData.value = res.data.list
    total.value = res.data.total
}
const handleSizeChange = (size) => {
    pageRequest.value.pageSize = size
    load()
}
const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum
    load()
}
const handleSelectionChange = (e) => {
    delIds.value = e.map(x => x.id)
}
</script>