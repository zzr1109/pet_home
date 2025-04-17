<template>
    <div class="main">
        <el-row :gutter="10">
            <el-col :span="24">
                <el-card>
                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div style="display: flex">
                                <el-image style="height: 160px;width: 160px;border-radius: 10px" fit="cover" :src="user.imageUrl"
                                          :preview-src-list="[user.imageUrl]"></el-image>
                                <div style="margin-left: 10px">
                                    <p style="margin-bottom: 13px">昵称：{{ user.nickName }}</p>
                                    <p style="margin-bottom: 13px">年龄：{{ user.age }}</p>
                                    <p style="margin-bottom: 13px">邮箱：{{ user.email }}</p>
                                    <p style="margin-bottom: 13px">手机：{{ user.phone }}</p>
                                    <div>
                                        <el-button @click="editUserInfo">编辑个人信息</el-button>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div style="display: flex;flex-direction: column;align-items: center;padding: 20px;background-color: var(--accent-100);border-radius: 5px">
                                <el-tooltip content="销售额" append-to="body">
                                    <img src="@/assets/images/销售额.png" style="height: 100%;width: 100%"/>
                                </el-tooltip>
                                <span>{{dataObj.orderPrice}}<span>¥</span></span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div style="display: flex;flex-direction: column;align-items: center;padding: 20px;background-color: var(--accent-100);border-radius: 5px">
                                <el-tooltip content="订单数" append-to="body">
                                    <img src="@/assets/images/订单.png" style="height: 100%;width: 100%"/>
                                </el-tooltip>
                                <span>{{dataObj.orderNum}}<span>个</span></span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div style="display: flex;flex-direction: column;align-items: center;padding: 20px;background-color: var(--accent-100);border-radius: 5px">
                                <el-tooltip content="商品数" append-to="body">
                                    <img src="@/assets/images/商品管理.png" style="height: 100%;width: 100%"/>
                                </el-tooltip>
                                <span>{{dataObj.goodsNum}}<span>件</span></span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div style="display: flex;flex-direction: column;align-items: center;padding: 20px;background-color: var(--accent-100);border-radius: 5px">
                                <el-tooltip content="宠物数" append-to="body">
                                    <img src="@/assets/images/宠物.png" style="height: 100%;width: 100%"/>
                                </el-tooltip>
                                <span>{{dataObj.petNum}}<span>个</span></span>
                            </div>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="10" style="margin-top: 10px">
            <el-col :span="12">
                <el-card>
                  <!--柱状图-->
                  <div id="barChart" style="width: 100%;height: 352px"></div>
                </el-card>
                <el-card style="margin-top: 10px">
                    <!--饼图-->
                    <div id="pieChart" style="width: 100%;height: 352px"></div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="hiddenScrollbar" style="height: 796px;overflow-y: scroll">
                    <div>
                        <div class="" style="display: flex;flex-direction: column">
                            <div style="display: flex;border-bottom: 1px dashed var(--bg-300);padding-bottom: 10px">
                                <img src="@/assets/images/系统公告.png" style="height: 40px;width: 40px">
                                <span style="align-self: center;margin-left: 10px;">系统公告</span>
                            </div>

                            <div v-for="(note,noteIndex) in noteList" :key="noteIndex" style="display: flex;border-bottom: 1px dashed var(--bg-300);padding-bottom: 10px;padding-top: 10px">
                                <img src="@/assets/images/公告.png" style="height: 35px;width: 35px">
                                <div style="display: flex;flex-direction: column;align-self: center;margin-left: 10px;color: var(--bg-300)">
                                    <span>{{dateFormatHm(note.createTime)}}</span>
                                    <span style="word-break: break-word;font-weight: bolder;text-align: justify">{{note.content}}</span>
                                </div>
                            </div>

                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <MyDrawer ref="myDrawerRef" :title="selectRow.id ? '更新':'新增'" @saveOrUpdate="saveOrUpdate">
            <el-form class="line" label-position="top">
                <el-form-item label="用户头像">
                    <el-upload
                        class="avatar-uploader"
                        :action="baseURL() + '/file/upload'"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="selectRow.imageUrl" :src="selectRow.imageUrl" class="avatar">
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus/>
                        </el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input placeholder="请输入昵称" v-model="selectRow.nickName"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input placeholder="请输入用户名" v-model="selectRow.userName"></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input placeholder="请输入年龄" type="number"
                              v-model="selectRow.age"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" v-model="selectRow.email"></el-input>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input placeholder="请输入联系方式" v-model="selectRow.phone"></el-input>
                </el-form-item>
            </el-form>
        </MyDrawer>
    </div>
</template>
<script setup>
import {ref, onMounted} from "vue";
import http, {baseURL} from "@/utils/Request.js"
import MyDrawer from "@/components/MyDrawer.vue";
import {ElNotification} from "element-plus";
import * as echarts from 'echarts';
import {dateFormatHm} from "@/utils/DateUtil.js";

const myDrawerRef = ref()
const user = ref({})
const selectRow = ref({})
const noteList = ref([])
const dataObj = ref("")

onMounted(() => {
    getUserInfo()
    barChart()
    pieChart()
    getNoteList()
    getData()
})

const getData = async () => {
    const res = await http.get("/data/dataStatistics",null,"-1")
    dataObj.value = res.data
}

const getUserInfo = async () => {
    const res = await http.get("/web/getPersonInfo",null,'-1')
    user.value = res.data
}

// 获取系统公告内容
const getNoteList = async () => {
    const res = await http.post("/note/page",{pageNum: 1,pageSize: 20,q: {}},'-1')
    noteList.value = res.data.list
}


// 柱状图
const barChart = async () => {
    const res = await http.get("/data/barChartOfSuccessfulAdoption",null,"-1")
    let chartDom = document.getElementById('barChart');
    let myChart = echarts.init(chartDom);
    let option;

    option = {
        title: {
            text: '宠物领养成功柱状图'
        },
        xAxis: {
            type: 'category',
            data: res.data.entryList
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: res.data.dataList,
                type: 'bar'
            }
        ]
    };
    option && myChart.setOption(option);
}

// 饼图
const pieChart = async () => {
    const res = await http.get("/data/petClassificationPieChart",null,"-1")
    let chartDom = document.getElementById('pieChart');
    let myChart = echarts.init(chartDom);
    let option;

    option = {
        title: {
            text: '宠物数量饼图'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: res.data
            }
        ]
    };

    option && myChart.setOption(option);
}

const handleAvatarSuccess = (e) => {
    if (e.code === '200') {
        selectRow.value.imageUrl = e.data
    }
}

const editUserInfo = () => {
    selectRow.value = JSON.parse(JSON.stringify(user.value))
    myDrawerRef.value.open(selectRow.value)
}

const saveOrUpdate = async (e) => {
    await http.post("/user/saveOrUpdate", selectRow.value, "-1")
    if (!selectRow.value.id) {
        ElNotification({title: "Success", message: "操作成功,初始密码为123456", type: "success"})
    } else {
        ElNotification({title: "Success", message: "操作成功", type: "success"})
    }
    myDrawerRef.value.close()
    await getUserInfo()
}

</script>