<script>
import gql from 'graphql-tag'

export default {
  name: "TestApollo",
  apollo: {
    products: gql`query {
      products {
        id
        name
      }
    }`
  },
  data() {
    return {
      products: []
    }
  }
}
</script>

<template>
  <h2>Apollo Test</h2>
  {{ products }}
</template>

<style scoped>

</style>