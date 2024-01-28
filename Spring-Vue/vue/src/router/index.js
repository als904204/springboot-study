import { createRouter, createWebHistory } from 'vue-router';
import PostList from '@/components/PostList.vue';
import PostDetails from '@/components/PostDetails.vue';
import Home from "@/components/Home.vue";
import Naver from "@/components/webtoon/Naver.vue";

const routes = [
  {path: '/', component: Home},
  {path: '/naver', component: Naver},
  {path: '/posts', component: PostList},
  {path: '/posts/:id', component: PostDetails, name: 'PostDetails'},
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
