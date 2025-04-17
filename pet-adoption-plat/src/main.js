import { createApp } from 'vue'
import BaiduMap from 'vue-baidu-map-3x'
import App from './App.vue'
import ElementPlus from 'element-plus'
import router from "@/router/index.js";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import "@/styles/index.scss"

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(BaiduMap, {
    ak: 'sLh8ZFChXHODuBNk2YtAiIXBiBQpO3to',
    v:'2.0',  // 默认使用3.0
    // type: 'WebGL' // ||API 默认API  (使用此模式 BMap=BMapGL)
});
app.use(ElementPlus, {size: 'default',locale: zhCn})
app.use(router)
app.mount('#app')