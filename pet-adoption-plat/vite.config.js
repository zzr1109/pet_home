import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
  // 加入配置
  css: {
    preprocessorOptions: {
      scss: {
        api: 'modern-compiler', //  必须加上
      },
    },
  },
  plugins: [
    vue(),
  ],
  optimizeDeps: {
    include: ['vue', 'vue-router'], // 提前优化 Vue 和路由相关依赖
  },
  server: {
    host: '0.0.0.0',
    hmr: {
      overlay: false // 关闭 HMR 错误提示
    },
    fs: {
      strict: false // 允许访问外部文件
    },
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  }
})
