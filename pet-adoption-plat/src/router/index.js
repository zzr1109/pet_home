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
                path: '/PetList',
                name: 'petList',
                component: () => import('@/views/web/Pet/PetList.vue'),
            },
            {
                path: '/PetGoods',
                name: 'petGoods',
                component: () => import('@/views/web/PetGoods/PetGoods.vue'),
            },
            {
                path: '/PetCircle',
                name: 'petCircle',
                component: () => import('@/views/web/PetCircle/PetCircle.vue'),
            },
            {
                path: '/PersonalCenter',
                name: 'personalCenter',
                component: () => import('@/views/web/PersonalCenter/PersonalCenter.vue'),
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
            next("/PetList")
        } else {
            next();
        }
    }
})


//导出router
export default router