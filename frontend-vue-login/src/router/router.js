import { createRouter, createWebHistory } from "vue-router";
import UserLogin from "../components/UserLogin.vue";
import UserAccount from "../components/UserAccount.vue";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: UserLogin },
  { path: "/account", component: UserAccount },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
