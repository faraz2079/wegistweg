<script>
import gql from "graphql-tag";

export default {
  name: "ProductsMostViewed",
  apollo: {
    productsMostViewed: gql`query {
      productsMostViewed {
          product {
            id
            name
            price
          },
          viewCount
      }
    }`
  },
  data() {
    return {
      productsMostViewed: [],
    }
  }
}
</script>

<template>
<div class="container">
  <h1>Most Viewed Today</h1>
  <ol class="productList productList-mostViewed">
    <li v-for="viewCount in productsMostViewed" class="productItem">
      <router-link :to="{ name: 'productDetail', params: {id: viewCount.product.id}}" class="productLink">
        <span class="productName">{{ viewCount.product.name }}</span>
        <span class="productPrice">{{ viewCount.product.name }}€</span>
        <span class="productViews">{{ viewCount.viewCount }} views today</span>
      </router-link>
    </li>
  </ol>

  <router-link class="buttonLink spaceAbove-large" :to="{ name: 'products'}">View All Products</router-link>
</div>
</template>

<style scoped>

</style>