<template>
  <el-dialog v-model="centerDialogVisible" title="支付" width="500" center>
    <h3 style="margin-left: 10px;margin-bottom: 20px">支付金额为：<span style="color: red">¥{{paymentAmount}}</span></h3>
      <PayMode @payMode="payMode"></PayMode>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmPayment">
          确定支付
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script setup>
import {reactive, ref} from "vue";
    import PayMode from "@/components/PayMode.vue";
import {ElMessage} from "element-plus";
import http from "@/utils/Request.js";
    const centerDialogVisible = ref(false)
    const paymentAmount = ref(0)
    //支付参数
    const paymentData = reactive({
          id:null,
          payMode:null,
    })
    const open = (id,amount) => {
      paymentData.id = id
      paymentAmount.value = amount
      centerDialogVisible.value = true
    }
    //选择的支付方式
    const payMode = (val) => {
      paymentData.payMode = val
    }
    //确定支付
     const confirmPayment = async () => {
          if(!paymentData.payMode) {
            return  ElMessage({
              type: 'warning',
              message: '请选择支付方式!',
            })
          }
       await http.post("/plat/pay", paymentData, '支付成功!')
       centerDialogVisible.value = false
       location.reload();
     }
    defineExpose({
      open
    })
</script>
<style scoped lang="scss">

</style>