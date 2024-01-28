import { createRouter, createWebHistory } from 'vue-router';
import PostList from '@/components/PostList.vue';
import PostDetails from '@/components/PostDetails.vue';
import Home from "@/components/Home.vue";
import Naver from "@/components/webtoon/Naver.vue";
import Genre from "@/components/webtoon/Genre.vue";
import Publish from "@/components/webtoon/Publish.vue";
import Kakao from "@/components/webtoon/Kakao.vue";
import Login from "@/components/fragements/Login.vue";

const routes = [
  {path: '/', component: Home},
  {path: '/naver', component: Naver},
  {path: '/genre', component: Genre},
  {path: '/publish', component: Publish},
  {path: '/kakao', component: Kakao},
  {path: '/login', component: Login},

  {path: '/posts', component: PostList},
  {path: '/posts/:id', component: PostDetails, name: 'PostDetails'},
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
