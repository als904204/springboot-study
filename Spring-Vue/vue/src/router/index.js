import { createRouter, createWebHistory } from 'vue-router';
import PostList from '@/components/PostList.vue';
import PostDetails from '@/components/PostDetails.vue';

const routes = [
  { path: '/posts', component: PostList },
  { path: '/posts/:id', component: PostDetails, name: 'PostDetails' },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
