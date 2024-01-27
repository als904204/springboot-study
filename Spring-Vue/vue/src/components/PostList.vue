<template>
  <div class="container">
    <h1 class="text-center">게시글 목록</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>게시글 아이디</th>
          <th>게시글 제목</th>
          <th>게시글 작성자</th>
        </tr>
      </thead>

      <tbody>
          <tr v-for="post in posts" :key="post.id">
            <td>{{ post.id }}</td>
            <td>{{ post.title }}</td>
            <td>{{ post.author }}</td>
          </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
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
        // axios.get('http://localhost:8080/api/posts')
        this.$axios.get('/posts')
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