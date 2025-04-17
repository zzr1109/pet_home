//引入路由对象
import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/login',
        meta: {isToken: false,menuName: "登陆",role: []},
        name: 'login',
        component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 排序
function compare(property) {
    return function (a, b) {
        var value1 = a[property];
        var value2 = b[property];
        return value1 - value2;
    }
}


// 设置动态路由
export const setRoutes = () => {
    localStorage.removeItem("menus")
    // 从缓存里面获取菜单列表
    const storeMenus = JSON.parse(localStorage.getItem("user"));
    if (storeMenus && storeMenus.menuList) {
        // 拼装动态路由
        const backRoute = {
            path: '/',
            meta: {isToken: true,menuName: "首页"},
            name: 'home',
            component: () => import(/* webpackChunkName: "about" */ '../views/back/Home.vue'),
            children: []
        }
        let singleRouteArr = []
        storeMenus.menuList.forEach(item => {
            let singleRoute = ""
            singleRouteArr.push(recursionSetMenu(item,singleRoute))
        })
        backRoute.children.push(...singleRouteArr)
        // 把数据设置到缓存
        backRoute.children = backRoute.children.sort(compare("sort"))
        localStorage.setItem("menus",JSON.stringify(backRoute))
        let routeMenuChildrenList = recursionMenu(backRoute.children,[])
        backRoute.children = routeMenuChildrenList.sort(compare("sort"))
        // 获取当前的路由对象名称数组
        const currentRouteNames = router.getRoutes().map(v => v.name)
        if (!currentRouteNames.includes('home')) {
            // 动态添加到现在的路由对象中去
            router.addRoute(backRoute)
        }
    }
}

export const recursionMenu = function (backRouteChildren,childrenList) {
    backRouteChildren.forEach(item => {
        if (item.path) {
            childrenList.push(item)
        } else {
            recursionMenu(item.children,childrenList)
        }
    })
    return childrenList;
}

/**
 * 递归塞入菜单项.生成菜单的层级关系
 * @param item 当前设置的菜单
 * @param singleRoute 容器
 */
export const recursionSetMenu = function (item,singleRoute) {
    let singleRouteObj = {
        path: item.path ? "/" + item.path : "",
        meta: { isToken: item.isToken == 0 ? true : false,menuName: item.menuName,isShowMenu: item.isShowMenu == 0 ? true : false, icon: item.icon },
        name: item.name ? item.name : "",
        sort: item.sort,
        children: []
    }

    if (item.filePath) {
        singleRouteObj.component = () => import(`@/views/back/${item.filePath}.vue`)
        // singleRouteObj.component = (resolve) => require([`@/views/back/${item.filePath}.vue`],resolve)
    }
    if (singleRoute) {
        singleRoute.children.push(singleRouteObj)
    } else {
        singleRoute = singleRouteObj
    }
    if (item.children && item.children.length > 0) {
        item.children.forEach(itemChildren => {
            recursionSetMenu(itemChildren,singleRoute)
        })
    }
    return singleRoute
}

// 每次刷新页面都要重新设置路由，否则路由就会被重置
setRoutes();

// 路由前置守卫
router.beforeEach((to, from, next) => {
    setRoutes()
    // 获取当前缓存中的用户信息
    let user = JSON.parse(localStorage.getItem("user"))
    // 如果是去不需要Token的页面直接放行
    if (JSON.stringify(to.meta) !== '{}' && !to.meta.isToken) {
        next();
    }
    // 如果去需要Token的页面
    else {
        console.log("login",to,user)
        if (!user || to.path === "/") {
            localStorage.clear()
            next("/login")
        } else {
            next();
        }
    }
})


//导出router
export default router