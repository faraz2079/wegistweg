import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router.js'
import apolloProvider from './apollo.js'

createApp(App)
    .use(router)
    .use(apolloProvider)
    .mount('#app')
