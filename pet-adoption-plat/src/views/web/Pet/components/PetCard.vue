<template>
  <div>
    <el-card style="max-width: 480px">
      <template #header>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <div style="display: flex;align-items: center">
            <el-avatar :size="30" :src="pet.petSex ? female : maleImage" style="background-color: #fff"></el-avatar>
            <span style="margin-right: 10px;font-size: 16px;font-weight: bold">{{pet.petName}}</span>
            <span style="margin-right: 10px;font-size: 16px;font-weight: bold">{{pet.petAge}}</span>
            <el-button size="small" type="primary" @click="dialogTableVisible = true">我要领养</el-button>
          </div>
          <div style="display: flex;cursor: pointer" @click="addStarValue">
            <img :src="starImg" style="height: 20px;width: 20px" />
            <h3 style="margin-left: 5px;color: #4864ED">{{Number(pet.starNum)}}</h3>
          </div>
        </div>
        <div style="padding-left: 30px;">{{pet.petRemark}}</div>
      </template>
      <el-image preview-teleported :src="baseURL() + pet.menuImages[0]" style="width: 100%;height: 260px" fit="cover"></el-image>
    </el-card>

    <el-dialog v-model="dialogTableVisible" destroy-on-close title="我要领养" width="650">
      <div style="display: flex">
        <el-image preview-teleported :src="baseURL() + pet.menuImages[0]" style="width: 420px;height: 490px" fit="cover"></el-image>
        <el-form label-position="top" style="margin-left: 10px">
          <el-form-item label="联系方式">
            <el-input v-model="selectRow.userPhone" placeholder="请填写联系方式？"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="selectRow.userAddress" placeholder="请填写地址？"></el-input>
          </el-form-item>
          <el-form-item label="领养宣言">
            <el-input type="textarea" :rows="4" v-model="selectRow.remark" placeholder="请填写领养宣言？"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="sureAdopt">确定领养</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import maleImage from '@/assets/images/男.png'
import female from '@/assets/images/女.png'
import starImg from '@/assets/images/明星值.png'
import http, {baseURL} from "@/utils/Request.js";
import {ref} from "vue";

const props = defineProps({
  pet: {
    type: Object,
    default: {}
  }
})
const selectRow = ref({})
const dialogTableVisible = ref(false)

// 添加明星值
const addStarValue = async () => {
  await http.get("/plat/index/addStar?petId=" + props.pet.id,null,'-1')
  location.reload()
}

const sureAdopt = async () => {
  selectRow.value.petCenterId = props.pet.id
  selectRow.value.petCenterName = props.pet.petName
  await http.post("/plat/index/adopt",selectRow.value,"发起预约成功！")
  dialogTableVisible.value = false
  location.reload()
}
</script>