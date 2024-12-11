<script>
export default {
  name: "CountDisplay",
  props: ["countProp"]
}
</script>

<template>
  <button disabled>Count (component): {{ countProp }}</button>
</template>

<style scoped>

</style>