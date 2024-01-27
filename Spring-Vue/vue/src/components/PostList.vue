<template>
  <div>
    <h1 class="text-primary">게시글 목록</h1>
      <ul>
        <li v-for="post in posts" :key="post.id">
          {{ post.title }}
        </li>
      </ul>
  </div>
</template>

<script>
import axios from "axios";
  export default {
    name: 'PostList',
    data() {
      return {
        posts: [] // 게시글 목록 담을 배열 생성
      };
    },

    created() {
      this.fetchPosts(); // 컴포넌트가 생성될 때 게시글 목록을 가져온다
    },

    methods: {
      fetchPosts() {
        axios.get('http://localhost:8080/api/posts')
        .then(response => {
          this.posts = response.data; // 응답 데이터를 posts 배열에 할당
        })
        .catch(error => {
          console.error("에러 : ", error);
        });
      },
    }
  }
</script>