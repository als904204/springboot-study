<template>
<div>
  <h1>게시글 상세보기</h1>
    <div>
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <p>작성자: {{ post.author }}</p>
    </div>
</div>
</template>

<script>
export default {
  name: 'PostDetails',
  data(){
    return {
      post: {}
    };
  },
  created() {
    this.fetchPost();
  },
  methods: {
    fetchPost() {
      const postId = this.$route.params.id;
      this.$axios.get(`/posts/${postId}`)
      .then(response => {
        this.post = response.data;
      })
      .catch(error => {
        console.error(error);
      })

    }
  }
}
</script>