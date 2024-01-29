<template>
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
.carousel__image {
  width: 100%;
  height:100%; /* 높이를 조절하여 이미지가 차지하는 공간을 제어할 수 있습니다. */
  overflow: hidden; /* 이미지가 박스 밖으로 나가지 않도록 설정 */
  border-radius: 8px; /* 필요한 경우 모서리를 둥글게 할 수 있습니다. */
}
</style>
