import axios from 'axios'
import {ElLoading,ElNotification} from 'element-plus';
import router from "@/router/index.js";
import {hexToRgb} from "@/utils/ColorUtil.js";
import { openLogin } from "@/plugins/LoginDialog.js";

const http = {}

export const baseURL = function () {
    // return "http://localhost:16667";
    return "http://localhost:16667";

}

// 获取缓存内的用户信息
export const getUserInfo = function () {
    if (!localStorage.getItem("user")) {
        return;
    }
    let userInfo = JSON.parse(localStorage.getItem("user"))
    return userInfo;
}

let loading;
//正在请求的数量
let requestCount = 0;
//显示loading
const showLoading = () => {
    if (requestCount === 0) {
        const root = document.documentElement
        const bgColor = getComputedStyle(root).getPropertyValue('--accent-200')
        loading = ElLoading.service({
            fullscreen: true, text: "Loading  ", background: hexToRgb(bgColor,0.5),
        });
    }
    requestCount++;
};

//隐藏loading
const hideLoading = () => {
    requestCount--;
    if (requestCount === 0) {
        loading.close();
    }
};

const Axios = axios.create({
    timeout: 200000, // withCredentials: true, // 自动携带cookie
    baseURL: baseURL(), // 接口地址
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }, validateStatus(status) {
        return status >= 200 && status < 300
    }
});


// 请求拦截
Axios.interceptors.request.use(config => {
    showLoading();
    // token配置
    const user = getUserInfo();
    if (user) {
        config.headers["token"] = user.token
    }
    return config
}, err => {
    return Promise.reject(err)
})

// 响应拦截
Axios.interceptors.response.use(res => {
    hideLoading()
    // 拿到的是后台返回的结果集
    let response = res.data
    // 代表该次请求未授权,跳转登陆页面，重新登陆
    if (response.code === '401') {
        setTimeout(() => {
            openLogin()
        },500)
    }
    //后端返回数据,可在此设置响应
    return res.data;
}, err => {
    switch (err.response.status) {
        case 400:
            ElNotification({
                title: 'Error',
                message: '请求出错', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            break;
        case 401:
            ElNotification({
                title: 'Error',
                message: '授权失败，请重新登录', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            return;
        case 422:
            ElNotification({
                title: 'Error',
                message: '参数错误，请检查填写的参数', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            return;
        case 403:
            ElNotification({
                title: 'Error',
                message: '拒绝访问', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            break;
        case 404:
            ElNotification({
                title: 'Error',
                message: '请求错误,未找到该资源', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            break;
        case 500:
            ElNotification({
                title: 'Error',
                message: '后端接口错误', // 此处可以换成接口返回的报错信息
                type: 'error'
            })
            setTimeout(() => {
                hideLoading()
            },5000)
            break;
    }
    return Promise.reject(err);
})

http.get = function (url, data = {},successMsg="操作成功！") {
    return new Promise((resolve, reject) => {
        Axios.get(url, {
            params: data
        }).then(res => {
            if (res.code !== "200") {
                ElNotification({title: 'Error', message: res.msg, type: 'error'})
                // 目的=>中断后面的代码执行
                new Error(res.msg);
            } else {
                if (successMsg !== "-1") {
                    ElNotification({title: 'Success', message: successMsg, type: 'success'})
                }
                resolve(res);
            }
        }).catch(err => {
            reject(err);
        })
    })
}

http.del = function (url, data = {},successMsg="删除成功！") {
    return new Promise((resolve, reject) => {
        Axios.delete(url, {
            params: data
        }).then(res => {
            if (res.code !== "200") {
                ElNotification({
                    title: 'Error',
                    message: res.msg, // 此处可以换成接口返回的报错信息
                    type: 'error'
                })
                // 目的=>中断后面的代码执行
                new Error(res.msg);
            } else {
                if (successMsg !== "-1") {
                    ElNotification({title: 'Success', message: successMsg, type: 'success'})
                }
                resolve(res);
            }
        }).catch(err => {
            reject(err);
        })
    })
}

http.post = function (url, data, successMsg="操作成功！") {
    return new Promise((resolve, reject) => {
        Axios.post(url, JSON.stringify(data)).then(res => {
            if (res.code !== "200") {
                ElNotification({
                    title: 'Error',
                    message: res.msg, // 此处可以换成接口返回的报错信息
                    type: 'error'
                })
                // 目的=>中断后面的代码执行
                new Error(res.msg);
            } else {
                if (successMsg !== "-1") {
                    ElNotification({title: 'Success', message: successMsg, type: 'success'})
                }
                resolve(res);
            }
        }).catch(err => {
            reject(err);
        })
    })
}


export default http
