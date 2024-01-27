import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from './global/axiosConfig' // axios 글로벌 설정 (안하면 매 요청마다 axios import 하고 url 적어야 함)

const app = createApp(App);

app.config.globalProperties.$axios = axios;
app.mount('#app')
