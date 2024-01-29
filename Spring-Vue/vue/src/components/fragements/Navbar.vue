<template>
      <Menubar :model="items" class="menubar"></Menubar>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();

const items = ref([
  {
    label : '홈',
    icon : 'pi pi-home',
    command: () => {
      router.push("/");
    }
  },
  {
    label : '장르별 웹툰',
    icon : 'pi pi-book',
    command: () => {
      router.push("/genre");
    }
  },
  {
    label : '요일별 웹툰',
    icon : 'pi pi-book',
    command: () => {
      router.push("/publish");
    }
  },
  {
    label : '카카오 웹툰',
    icon : 'pi pi-book',
    command: () => {
      router.push("/kakao");
    }

  },
  {
    label : '네이버 웹툰',
    icon : 'pi pi-book',
    command: () => {
      router.push("/naver");
    }
  },
  {
    label : '로그인/가입',
    icon : 'pi pi-user',
    command: () => {
      router.push("/login");
    }
  },
]);


</script>


<style scoped>
.menubar {
  margin-bottom : 40px;
}

.p-menubar {
  display: flex;
  justify-content: center;
}
.p-menubar-root-list{
  gap: 23px;
}
</style>