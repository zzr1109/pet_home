//引入路由对象
import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/webHome',
        meta: {menuName: "web首页"},
        name: 'webHome',
        component: () => import('../views/web/WebHome.vue'),
        children: [
            {
                path: '/petList',
                meta: {menuName: "主页"},
                name: 'petList',
                component: () => import('../views/web/Pet/PetList.vue'),
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})


// 路由前置守卫
router.beforeEach((to, from, next) => {
    // 获取当前缓存中的用户信息
    let user = JSON.parse(localStorage.getItem("user"))
    // 如果是去不需要Token的页面直接放行
    if (JSON.stringify(to.meta) !== '{}') {
        next();
    }
    // 如果去需要Token的页面
    else {
        if (!user || to.path === "/") {
            localStorage.clear()
            next("/petList")
        } else {
            next();
        }
    }
})


//导出router
export default router