<script>
export default {
  name: "CountDisplay",
  props: ["countProp"],
  data() {
    return {
      count: 0
    }
  },
  methods: {
    increment() {
      this.count++
    }
  },
  mounted() {
    console.log('Component mounted.');
  }
}
</script>

<template>
  <div class="container">
    <h2>Test Counter</h2>

    Count: {{ count }}
    <br />
    <button @click="increment">Increase count to {{ count + 1 }}</button>
    <br />
    <router-link to="/test">Go to Home</router-link>
  </div>
</template>

<style scoped>
button {
  font-weight: bold;
}
</style>