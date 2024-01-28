import { createApp } from 'vue'
import App from './App.vue'
import axios from './global/axiosConfig' // axios 글로벌 설정 (안하면 매 요청마다 axios import 하고 url 적어야 함)
import router from './router'; // 라우터 임포트



const app = createApp(App);
app.config.globalProperties.$axios = axios;
app.use(router);
app.mount('#app')
