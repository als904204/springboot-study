<template>
      <Menubar :model="items"></Menubar>
</template>

<script setup>
import { ref } from "vue";

const items = ref([
  {
    label : '홈',
    icon : 'pi pi-home',
  },
  {
    label : '장르별 웹툰',
    icon : 'pi pi-book'
  },
  {
    label : '요일별 웹툰',
    icon : 'pi pi-book'
  },
  {
    label : '카카오 웹툰',
    icon : 'pi pi-book'
  },
  {
    label : '네이버 웹툰',
    icon : 'pi pi-book'
  },
  {
    label : '로그인/가입',
    icon : 'pi pi-user'
  },
]);


</script>


<style scoped>
.p-menubar {
  display: flex;
  justify-content: center;
}
.p-menubar-root-list{
  gap: 23px;
}
</style>