<script>
import CountDisplay from "@/components/CountDisplay.vue";

export default {
  components: {CountDisplay},
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
    helper();
  }
}

let helper = function () {
  console.log('helper.')
}
</script>

<template>
  <main>
    Count: {{ count }}

    <br />

    <button @click="increment">Increase count ({{ count }})</button>

    <br />
    <CountDisplay :count-prop="count"></CountDisplay>
  </main>
</template>

<style scoped>
button {
  font-weight: bold;
}
</style>
