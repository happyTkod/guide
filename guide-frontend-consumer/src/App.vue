<template>
<!--  <header>-->
<!--    <img alt="Vue logo" class="logo" src="./assets/logo.png" width="125" height="125" />-->

<!--  </header>-->

  <main>
    <FloorPlan></FloorPlan>
  </main>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import FloorPlan from "@/components/FloorPlan.vue";

export default {
  name: 'App',
  components: {
    FloorPlan
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  //margin-top: 60px;
}
</style>
