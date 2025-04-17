import Login from "@/components/Login.vue";
import { render, h } from 'vue'
export const openLogin = function (){
    //1.创建一个div
    const dom = document.createElement('div')
    //2.将Dialog组件通过h函数，生成vNode虚拟dom节点
    const vNode = h(Login, "")
    //3.将vNode挂载到dom上
    render(vNode, dom)
    //4.将挂在好的dom插入进body
    document.body.appendChild(dom.firstChild)
    //5.调用组件内打开登陆弹框的方法
    vNode.component.exposed.loginOpen()
}