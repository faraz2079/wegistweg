import { createRouter, createWebHashHistory } from "vue-router"
import TestCounter from "@/components/TestCounter.vue";
import TestHello from "@/components/TestHello.vue";
import TestApollo from "@/components/TestApollo.vue";
import productsMostViewed from "@/components/ProductsMostViewed.vue";
import productsAll from "@/components/ProductsAll.vue";
import productDetail from "@/components/ProductDetail.vue";

const routes = [
    { path: "/", component: productsMostViewed, name: "home" },

    { path: "/products", component: productsAll, name: "products" },
    { path: "/products/mostViewed", component: productsMostViewed, name: "productsMostViewed" },
    { path: "/products/:id", component: productDetail, name: "productDetail" },

    { path: "/test", component: TestHello },
    { path: "/test/:id", component: TestHello },
    { path: "/testCounter", component: TestCounter },
    { path: "/testApollo", component: TestApollo },
]

export default createRouter({
    history: createWebHashHistory(),
    routes: routes
});