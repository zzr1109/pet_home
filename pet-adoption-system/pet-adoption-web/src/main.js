import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import router from "@/router/index.js";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import "@/assets/fonts/font.css"
import 'highlight.js/styles/atom-one-dark.css'
import 'highlight.js/lib/common'
import "@/styles/index.scss"


const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(ElementPlus, {size: 'default',locale: zhCn})
app.use(router)
app.mount('#app')