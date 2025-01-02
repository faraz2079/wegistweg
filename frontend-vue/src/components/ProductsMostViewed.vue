<script>
import gql from "graphql-tag";

export default {
  name: "ProductsMostViewed",
  apollo: {
    productsMostViewed: {
      query: gql`query {
        productsMostViewed {
            product {
              id
              name
              price
            },
            viewCount
        }
      }`,
      fetchPolicy: "no-cache"
    },
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
  <div v-if="productsMostViewed.length === 0" class="no-views-message">No products have been viewed yet today.</div>
  <ol v-else class="productList productList-mostViewed">
    <li v-for="viewCount in productsMostViewed" class="productItem">
      <router-link :to="{ name: 'productDetail', params: {id: viewCount.product.id}}" class="productLink">
        <span class="productName">{{ viewCount.product.name }}</span>
        <span class="productPrice">{{ viewCount.product.price }}€</span>
        <span class="productViews">{{ viewCount.viewCount }} views today</span>
      </router-link>
    </li>
  </ol>

  <router-link class="buttonLink spaceAbove-large" :to="{ name: 'products'}">View All Products</router-link>
</div>
</template>

<style scoped>
.no-views-message {
  margin: 1rem 0;
  padding: 0.75rem 1rem;
  color: #777;
  font-style: italic;
  border-radius: 4px;
  text-align: center;
  border: 1px dashed #777;
}
</style>