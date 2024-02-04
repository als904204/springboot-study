<template>
  <div class="banner-container">
    <Carousel :autoplay="2500" :wrap-around="true">
      <Slide v-for="(img, index) in images" :key="index">
        <div class="carousel__item">
          <img :src="img" :alt="`Slide ${index}`" class="carousel__image" />
        </div>
      </Slide>
      <template #addons>
        <Pagination />
      </template>
    </Carousel>
  </div>

<session>
  <div class="webtoon-container">
      <h2 class="title-webtoon">🔥이번주 인기 웹툰</h2>
      <div class="btn">
        <Button id="btn-review" label="리뷰순" severity="Primary" size="large"  rounded />
        <Button id="btn-rating" label="평점순" severity="Primary" size="large"  rounded />
      </div>
    <ul>
      <li v-for="post in posts" :key="post.id">
        <h3>{{post.title}}</h3>
      </li>
    </ul>
  </div>
</session>

<session>
  <div class="webtoon-container">
    <h2 class="title-webtoon">📆요일별 웹툰</h2>
    <div class="btn">
      <Button label="월" severity="Primary" size="large"  rounded />
      <Button label="화" severity="Primary" size="large"  rounded />
      <Button label="수" severity="Primary" size="large"  rounded />
      <Button label="목" severity="Primary" size="large"  rounded />
      <Button label="금" severity="Primary" size="large"  rounded />
      <Button label="토" severity="Primary" size="large"  rounded />
      <Button label="일" severity="Primary" size="large"  rounded />
    </div>
  </div>
</session>


<session>
  <div class="webtoon-container">
    <h2 class="title-webtoon">✍🏻플랫폼별 웹툰</h2>
    <div class="btn">
      <Button label="카카오" severity="Primary" size="large"  rounded />
      <Button label="네이버" severity="Primary" size="large"  rounded />
      <Button label="그외" severity="Primary" size="large"  rounded />
    </div>
  </div>
</session>

</template>

<script setup>
import { ref } from 'vue';
import notice1 from '@/assets/notice1.png';
import notice2 from '@/assets/notice2.png';
import { Carousel, Slide, Pagination } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';

const images = ref([
    notice1,
    notice2,
]);


</script>



<style scoped>
.banner-container{
  margin-bottom:52px;
}
.carousel__image {
  width: 100%;
  height:auto;
  border-radius: 8px;
}

.title-webtoon {
  color: rgb(0, 0, 0);
  font-size: 30px;
  font-style: normal;
  font-weight: 700;
  line-height: 100%;
  letter-spacing: -0.78px;
  margin: 0px 0px 24px;
}


.btn{
  display:flex;
  gap:1rem 10px;
}

.webtoon-container {
  margin-bottom : 80px;
}
</style>
