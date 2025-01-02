<script>
export default {
  name: "TestHello",
  data() {

  },
  methods: {

  },
  mounted() {
    console.log("Component 'TestHello' mounted: " + this.$route.params.id);

  }
}
</script>

<template>
  <div class="container">
    <h2>Hello!</h2>
    <router-link to="/testCounter">Go to Counter</router-link>
  </div>
</template>

<style scoped>

</style>