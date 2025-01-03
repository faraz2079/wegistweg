<script>
import router from "../router/router.js";
import gql from "graphql-tag";

export default {
  name: "ProductDetail",
  inject: ['authState'],
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
        return { id: this.productId }
      }
    },
    $subscribe: {
      viewCountChanged: {
        query: gql`subscription ($productId: ID!) {
          productViews(productId: $productId)
        }`,
        variables () {
          return { productId: this.productId }
        },
        result ({ data }) {
          this.viewCount = data.productViews
        }
      },
    }
  },
  data() {
    return {
      productId: null,
      product: {},
      viewCount: null,
    }
  },
  watch: {
    '$route.params.id'(newVal, oldVal) {
      // React to changes of the route.
      // This is not called initially!
      console.log(newVal)
      console.log(oldVal)

      this.productId = newVal
      this.stopViewing(oldVal)
      this.startViewing(newVal)
    }
  },
  methods: {
    // Notify the server that the product is being viewed
    startViewing(productId) {
      console.log("Starting viewing {productId}", productId)
      this.$apollo.mutate({
          mutation: gql`mutation($productId: ID!, $userId: ID, $guestSessionId: String) {
            startViewing(productId: $productId, userId: $userId, guestSessionId: $guestSessionId)
          }`,
        variables: {
            productId: productId,
            userId: this.authState.userId,
            guestSessionId: this.authState.guestSessionId
        }
      })
    },
    stopViewing(productId) {
      console.log("Stop viewing {productId}", productId)
      this.$apollo.mutate({
        mutation: gql`mutation($productId: ID!, $userId: ID, $guestSessionId: String) {
            stopViewing(productId: $productId, userId: $userId, guestSessionId: $guestSessionId)
          }`,
        variables: {
          productId: productId,
          userId: this.authState.userId,
          guestSessionId: this.authState.guestSessionId
        }
      })
    },
  },
  mounted() {
    console.log("Component mounted.");
    this.productId = this.$route.params.id
    this.startViewing(this.productId)
  },
  beforeUnmount() {
    console.log("Before unmount.");
    this.stopViewing(this.productId)
  }
}
</script>

<template>
  <div class="container">
    <div class="productDetails">
      <h1 class="productName">{{ product.name }}</h1>
      <span class="productViews">currently viewed by {{ viewCount }} users</span>
      <span class="productStock">{{ product.stock }} in stock</span>
      <span class="productPrice">{{ product.price }}€</span>
    </div>

    <div class="divider"></div>


    <!-- TODO Move to separate component -->
    <div v-if="this.authState.isAdmin" class="productAdminOptions">
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