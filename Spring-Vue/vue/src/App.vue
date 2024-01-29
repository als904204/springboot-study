<template>
  <div id="app">
    <Navbar/>
<!--    <Header/>-->
    <router-view/>
    <Footer/>
  </div>
</template>


<script setup>
// import Header from "@/components/fragements/Header.vue";
import Navbar from "@/components/fragements/Navbar.vue";
import Footer from "@/components/fragements/Footer.vue";


</script>

<style>
#app {
  margin-top: 8px;
}
</style>
