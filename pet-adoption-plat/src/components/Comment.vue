<template>
    <div style="border-top: 1px solid rgba(237,240,248,1);width: inherit">
        <div style="display: flex">
            <el-button style="margin-top: 10px" @click="comment" v-if="props.type === 0 || props.type === 2" icon="ChatDotSquare" size="small">留言</el-button>
            <h4 style="color: rgb(93,95,99);margin-top: 12px;margin-left: 10px" v-if="props.type === 0 || props.type === 2">评论条数（{{tableOpenData.total || 0}}条）</h4>
        </div>
        <!--展开先显示-->
        <div v-if="tableOpenData">
            <div>
                <div v-for="item in tableOpenData.list" :key="item.id">
                    <div style="text-align: left;width: 1400px;margin-top: 10px;font-size: 14px;display: flex;justify-content: space-between">
                        <div style="width: 1200px">
                            <div style="display: flex;justify-content: space-between">
                                <div>
                                    <span style="font-size: 16px;">{{ item.userName }}</span>
                                    <span v-if="item.nameOfTheEvaluatedPerson">
                                        <span style="font-size: 16px;font-weight: bold"> 回复 </span>
                                        <span style="font-size: 16px;">{{item.nameOfTheEvaluatedPerson}}</span>
                                    </span>
                                </div>
                                <div style="display: flex">
                                    <span style="font-size: 12px;color: rgb(156,159,160);align-self: center;margin-right: 10px">{{dateFormatHm(item.createTime)}}评论</span>
                                    <el-tooltip content="评论">
                                        <ChatDotSquare @click="childrenComment(item)" style="width: 2em; height: 2em; margin-right: 8px" />
                                    </el-tooltip>
                                </div>
                            </div>
                            <div style="background-color: rgb(248,249,251);padding: 10px;border-radius: 5px">
                                <p style="word-wrap: break-word;word-break: break-all;">{{item.content}}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="margin-top: 10px;display: flex;justify-content: center">
            <el-pagination
                background
                @current-change="handleCurrentChange"
                layout="prev, pager, next"
                :total="total">
            </el-pagination>
        </div>
        <!--我要评论弹框-->
        <el-dialog title="评论" v-model="commentVisible" :show-close="false" :append-to-body="true" width="45%">
            <div style="display: flex">
                <el-input type="textarea" :rows="4" v-model="selectRow.content"
                          placeholder="请输入评论内容？"></el-input>
            </div>
            <div style="text-align: right;margin-top: 10px">
                <el-button type="primary" @click="sendComment">发布评论</el-button>
                <el-button style="margin-left: 10px" @click="commentVisible = false">取消</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script setup>

import {ref} from "vue";
import http from "@/utils/Request.js";
import {ElNotification} from "element-plus";
import {dateFormatHm} from "@/utils/DateUtil.js";

const pageSizeArr = ref([10, 20, 30, 40])
const pageRequest = ref({pageNum: 1, pageSize: 10, q: {}})
// 展开显示
const tableOpenData = ref("")
const commentVisible = ref(false)
const selectRow = ref({})
const selectItem = ref("")
const id = ref(null)
const total = ref(0)

const props = defineProps({
    typeId: {
        type: Number,
        default: 0
    },
    // 默认帖子评论
    type: {
        type: Number,
        default: 0
    },
    linkId: {
        type: Number,
        default: -1
    }
})

// 展开评论
const expandComment = () => {
    pageRequest.value.q.type = props.type
    pageRequest.value.q.typeIds = [props.typeId]
    load(pageRequest.value)
}

defineExpose({expandComment})

// 发布评论弹框
const comment = () => {
    selectItem.value = ""
    selectRow.value = {}
    commentVisible.value = true
}

const childrenComment = (item) => {
    selectItem.value = ""
    commentVisible.value = true
    selectItem.value = JSON.parse(JSON.stringify(item))
}

// 发布评论
const sendComment = async () => {
    if (props.typeId === 0) {
        ElNotification({title: "Warning", message: "类型ID不能为空！", type: "warning"})
        return
    }
    if (props.linkId === -1) {
        ElNotification({title: "Warning", message: "评论主题ID不能为空！", type: "warning"})
        return
    }
    if (selectItem.value) {
        selectRow.value.linkId = selectItem.value.id
    } else {
        selectRow.value.linkId = props.linkId
    }
    selectRow.value.type = props.type
    selectRow.value.typeId = props.typeId
    pageRequest.value.q.type = selectRow.value.type
    pageRequest.value.q.typeIds = [props.typeId]
    await http.post("/plat/comment/postComments",selectRow.value,"评论成功！")
    commentVisible.value = false
    await load(pageRequest.value)
}


const load = async (pageRequestObj) => {
    const res = await http.post("/plat/comment/recursiveCommentList", pageRequestObj, "-1")
    tableOpenData.value = res.data.pageOpenData
    console.log("tableOpenData.value11",tableOpenData.value)
    total.value = tableOpenData.value.total
    pageRequest.value = res.data.pageRequest
}
const handleCurrentChange = (pageNum) => {
    pageRequest.value.pageNum = pageNum
    load(pageRequest.value)
}


</script>