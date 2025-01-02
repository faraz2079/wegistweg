<script>
import gql from "graphql-tag";

export default {
  name: "ProductsAll",
  apollo: {
    products: gql`query {
      products {
        id
        name
        price
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
  <div class="container">
    <h1>All products</h1>
    <ul class="productList productList-all">
      <li v-for="product in products" class="productItem">
        <router-link :to="{ name: 'productDetail', params: {id: product.id}}" class="productLink">
          <span class="productName"> {{ product.name }} </span>
          <span class="productPrice">{{ product.price }}€</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<style scoped>

</style>