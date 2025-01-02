<script>
import router from "../router/router.js";
import gql from "graphql-tag";

export default {
  name: "ProductDetail",
  apollo: {
    product: {
      query: gql`query ($id: ID!) {
        product (id: $id) {
          id
          name
          stock
          price
        }
      }`,
      variables () {
        return { id: this.$route.params.id }
      }
    }
  },
  data() {
    return {
      product: {}
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="productDetails">
      <h1 class="productName">{{ product.name }}</h1>
      <span class="productViews">currrently viewed by 5 users</span>
      <span class="productStock">{{ product.stock }} in stock</span>
      <span class="productPrice">{{ product.price }}€</span>
    </div>

    <div class="divider"></div>

    <div class="productAdminOptions">
      <h3>Admin Options</h3>
      <label>
        <input type="checkbox">
        Display current views
      </label>
      <label>
        <input type="checkbox">
        Display stock
      </label>
    </div>
  </div>
</template>

<style scoped>

</style>