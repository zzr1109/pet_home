<template>
    <div>
        <el-card style="max-width: 480px">
            <template #header>
                <div style="display: flex;align-items: center;justify-content: space-between">
                    <div style="display: flex;align-items: center">
                        <el-avatar :size="30" :src="pet.petSex ? female : maleImg" style="background-color: #fff"></el-avatar>
                        <span style="margin-right: 10px;font-size: 16px;font-weight:bold">{{ pet.petName }}</span>
                        <span style="font-weight: bold;font-size: 16px;margin-right: 5px">{{ pet.petAge }}</span>
                        <el-tag v-if="pet.adoptionStatus" type="success">已领养</el-tag>
                        <el-button v-else size="small" type="primary" @click="dialogTableVisible = true">我要领养
                        </el-button>
                    </div>
                    <div @click="addStarValue" style="display: flex;cursor: pointer">
                        <img :src="starImg" alt="" style="width:20px;height: 20px ">
                        <h3 style="margin-left: 5px;color: #4864ED">{{ Number(pet.starNum) }}</h3>
                    </div>
                </div>
                <div style="padding-left: 30px;color: darkgray;">{{ pet.petRemark }}</div>
            </template>
            <el-image preview-teleported :src="pet.menuImages[0]" style="width: 100%;height: 260px" :preview-src-list="pet.menuImages" fit="cover"/>
        </el-card>
        <el-dialog v-model="dialogTableVisible" destroy-on-close title="领养信息" width="650">
            <div style="display: flex">
                <el-image preview-teleported :src="pet.menuImages[0]" style="min-width:420px;height: 490px;flex: 2;margin-right: 20px" :preview-src-list-="pet.menuImages" fit="cover"/>
                <el-form
                    ref="ruleFormRef"
                    :model="ruleForm"
                    :rules="rules"
                    class="demo-ruleForm"
                    label-position="top"
                    label-width="auto"
                    status-icon
                    style="max-width: 600px"
                >
                    <el-form-item label="联系方式:" prop="userPhone">
                        <el-input v-model="ruleForm.userPhone" placeholder="请填写联系方式"/>
                    </el-form-item>
                    <el-form-item label="地址" prop="userPhone">
                        <el-input v-model="ruleForm.userAddress" placeholder="请填写联系地址" type="textarea"/>
                    </el-form-item>
                    <el-form-item label="领养宣言" prop="remark">
                        <el-input v-model="ruleForm.remark" placeholder="请填写领养宣言" type="textarea"/>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm(ruleFormRef)">
                            确定领养
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>
<script setup>
import http, {baseURL} from "@/utils/Request.js";
import maleImg from '@/assets/images/男.png'
import female from '@/assets/images/女.png'
import starImg from '@/assets/images/明星值.png'

import {reactive, ref} from "vue";

const props = defineProps({
    pet: {
        type: Object,
        default: () => {
        }
    }
})
const dialogTableVisible = ref(false)
const ruleFormRef = ref()
const ruleForm = reactive({
    userPhone: null,//领养人联系方式
    userAddress: null,//领养人地址
    remark: null,//领养宣言
    petCenterId: props.pet.id,//宠物ID
    petCenterName: props.pet.petName,//宠物名称
})


const rules = reactive({
    userPhone: [
        {
            required: true,
            message: '类型方式不能为空!',
            trigger: 'change',
        },
    ],
    userAddress: [
        {
            required: true,
            message: '领养人地址不能为空!',
            trigger: 'change',
        },
    ],
    remark: [
        {
            required: true,
            message: '领养宣言不能为空!',
            trigger: 'change',
        },
    ],
})

const emits = defineEmits(["refresh"])
//添加明星值
const addStarValue = async () => {
    await http.get(`/plat/index/addStar?petId=${props.pet.id}`, {}, '-1')
    emits('refresh')
}
const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            await http.post("/plat/index/adopt", ruleForm, '领养成功！')
            emits('refresh')
            dialogTableVisible.value = false
            location.reload()
        }
    })
}
</script>
<style lang="scss" scoped>

</style>