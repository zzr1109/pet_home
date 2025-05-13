<template>
    <div style="position: relative;" class="hiddenScrollbar">
        <div style="display: flex;margin: 10px 0;">
            <div style="flex: 3;margin-right: 10px">
                <el-carousel trigger="click" height="350px">
                    <el-carousel-item v-for="item in carouselData" :key="item.id">
                        <el-image style="width: 100%;height: 100%" :src="baseURL() + item.imageUrlList" fit="cover"/>
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div style="flex: 2;display: flex;flex-direction: column;background-color: #fff">
                <p style="display: flex;padding: 5px">
                    <img src="@/assets/images/推荐宠物.png" style="height: 30px;width: 30px">
                    <span
                        style="align-self: center;color: var(--accent-200);margin-left: 10px;font-size: 18px;font-weight: bold">明星宠物</span>
                </p>
                <div class="hiddenScrollbar" style="height: 300px;overflow-y: scroll;">
                    <div
                        style="height: 60px;background-color: #fff;display: flex;align-items: center;justify-content: space-between;border-bottom: 2px solid var(--accent-200);padding: 10px"
                        v-for="item in recommendedPets" :key="item.id">
                        <div style="display: flex;align-items: center;">
                            <el-image :src="baseURL() +  item.imageUrlList"
                                      style="width: 60px; height: 60px;margin-right: 10px"/>
                            <div style="display: flex;flex-direction: column;height: 60px">
                                <span style="font-weight: bold;margin-right: 10px;font-size: 16px">{{
                                        item.petName
                                    }}({{ item.petAge }})</span>
                                <span>{{ item.petRemark }}</span>
                            </div>
                        </div>
                        <div style="padding: 0 20px;display: flex">
                            <div>
                                <span style="font-weight: bold;font-size: 18px;color: #4065F6">{{ item.starNum }}</span>
                            </div>
                            <img style="width: 25px;height: 25px;margin-left: 5px" :src="starImg" alt="">
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div style="display: flex;justify-content: space-between">
            <div style="width: 1000px;margin-right: 10px">
                <div style="margin-bottom: 5px">
                    <div style="display: flex;justify-content: space-between">
                        <p style="display: flex;padding-right: 5px">
                            <img src="@/assets/images/推荐宠物.png" style="height: 30px;width: 30px">
                            <span
                                style="align-self: center;color: var(--accent-200);margin-left: 10px;font-size: 18px;font-weight: bold">宠物合集</span>
                        </p>
                        <el-radio-group @change="handlerPetCategoryClick" v-model="requestBody.q.petCategoryId">
                            <el-radio-button label="全" value="null"/>
                            <el-radio-button v-for="item in petCategoryList" :key="item.id" :label="item.category"
                                             :value="item.id"/>
                        </el-radio-group>
                    </div>
                </div>
                <div style="display: grid;grid-template-columns:repeat(3,1fr);gap: 10px">
                    <pet-card @refresh="refresh" :pet="item" v-for="item in petList" :key="item.id"></pet-card>
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
            </div>
            <div class="hiddenScrollbar" style="flex: 1;height: 828px;overflow-y: scroll">
                <MyInfo style="background-color: #fff;padding: 10px;border-radius: 5px"></MyInfo>
                <NewReservation style="background-color: #fff;padding: 10px;border-radius: 5px;margin-top: 10px"></NewReservation>
            </div>
        </div>

    </div>
</template>
<script setup>
import NewReservation from "@/views/web/Pet/components/NewReservation.vue";
import MyInfo from "@/views/web/Pet/components/MyInfo.vue";
import {onMounted, reactive, ref} from "vue";
import http, {baseURL} from "@/utils/Request.js"
import PetCard from "@/views/web/Pet/components/PetCard.vue";
import starImg from "@/assets/images/明星值.png";
import {splicingDomain} from "@/utils/Base.js";

//宠物数据
const petList = ref([])//宠物数据
const petCategoryList = ref([])
const total = ref(0)
const requestBody = reactive({
    "pageNum": 1,
    "pageSize": 6,
    "q": {
        petCategoryId: 'null'
    }
})
const carouselData = ref([]) //轮播图
const recommendedPets = ref([]) //推荐宠物
const emits = defineEmits(['refreshRecommendedPets'])
//获取轮播图数据
const getCarouselData = async () => {
    const res = await http.get("/plat/index/crousel", {}, '-1')
    carouselData.value = res.data
}
//获取推荐宠物
const getRecommendedPets = async () => {
    const res = await http.get("/plat/index/recommendedPets", {}, '-1')
    recommendedPets.value = res.data
}
//获取宠物数据
const getPetList = async () => {
    const res = await http.post("/plat/index/petDisplay", requestBody, '-1')
    petList.value = res.data.list
    total.value = res.data.total
    petList.value.forEach(item => {
        if (item.menuImages) {
            item.menuImages = splicingDomain(item.menuImages)
        }
    })
}
const handlerPetCategoryClick = (val) => {
    getPetList()
}
//获取宠物类别
const petCategory = async () => {
    const res = await http.get("/plat/index/petCategory", '', '-1')
    petCategoryList.value = res.data
}
const handleCurrentChange = (val) => {
    requestBody.pageNum = val
    getPetList()
}
const refresh = () => {
    getPetList()
    getRecommendedPets()
}
onMounted(() => {
    getPetList()
    petCategory()
    getCarouselData()
    getRecommendedPets()
})
</script>
<style scoped lang="scss">

</style>