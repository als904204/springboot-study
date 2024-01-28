<template>
  <div id="app">
    <Navbar/>
    <Header/>
    <router-view/>
    <Footer/>
  </div>
</template>


<script setup>
import Header from "@/components/fragements/Header.vue";
import Navbar from "@/components/fragements/Navbar.vue";
import Footer from "@/components/fragements/Footer.vue";


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
