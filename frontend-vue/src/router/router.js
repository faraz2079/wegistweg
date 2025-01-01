import { createRouter, createWebHashHistory } from "vue-router"
import TestCounter from "@/components/TestCounter.vue";
import TestHello from "@/components/TestHello.vue";

const routes = [
    { path: "/", component: TestHello },
    { path: "/test", component: TestHello },
    { path: "/testCounter", component: TestCounter },
]

export default createRouter({
    history: createWebHashHistory(),
    routes: routes
});