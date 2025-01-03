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
          console.log("View count changed to", data.productViews)
          this.viewCount = data.productViews
        }
      },
      displaySettings: {
        query: gql`subscription ($productId: ID!) {
          productDisplaySettings(productId: $productId) {
            productId
            displayStockLevel
            displayViews
          }
        }`,
        variables () {
          return { productId: this.productId }
        },
        result ({ data }) {
          console.log("Product Display Settings updated", data)
          delete data.productDisplaySettings.__typename // Remove this field, so that the object can be reused as an input type for mutations
          this.displaySettings = data.productDisplaySettings
        }
      }
    }
  },
  data() {
    return {
      productId: null,
      product: {},
      viewCount: null,
      displaySettings: {}
    }
  },
  watch: {
    '$route.params.id'(newVal, oldVal) {
      // React to changes of the route.
      // This is not called initially!
      this.productId = newVal
      this.stopViewing(oldVal)
      this.startViewing(newVal)
    }
  },
  methods: {
    // Notify the server that the product is being viewed
    startViewing(productId) {
      console.log("Start viewing product", productId)
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
      console.log("Stop viewing product", productId)
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
    stopViewingCurrentProduct() {
      this.stopViewing(this.productId)
    },
    updateDisplaySettings() {
      console.log("Update display settings ", this.displaySettings)
      this.$apollo.mutate({
        mutation: gql`mutation($productDisplaySettings: ProductDisplaySettingsInput!) {
            updateProductViewSettings(productDisplaySettings: $productDisplaySettings)
          }`,
        variables: {
          productDisplaySettings: this.displaySettings
        }
      })
    },
    getProductViewsMessage(viewCount) {
      if (viewCount > 1)
        return `currently viewed by ${viewCount} users`
      else
        return `currently viewed by 1 user`
    }
  },
  mounted() {
    console.log("Component ProductDetail mounted.");
    this.productId = this.$route.params.id
    this.startViewing(this.productId)
    window.addEventListener('beforeunload', this.stopViewingCurrentProduct) // Ensure a "stop viewing" message is sent to the server, when the user refreshes the page
  },
  beforeUnmount() {
    console.log("Before unmount ProductDetail.");
    this.stopViewing(this.productId)
    window.removeEventListener('beforeunload', this.stopViewingCurrentProduct)
  }
}
</script>

<template>
  <div class="container">
    <div class="productDetails">
      <h1 class="productName">{{ product.name }}</h1>
      <span class="productViews" v-if="displaySettings.displayViews" >{{ getProductViewsMessage(viewCount) }}</span>
      <span class="productStock" v-if="displaySettings.displayStockLevel">{{ product.stock }} in stock</span>
      <span class="productPrice">{{ product.price }}€</span>
    </div>

    <div class="divider"></div>


    <!-- TODO Move to separate component -->
    <div v-if="this.authState.isAdmin" class="productAdminOptions">
      <h3>Admin Options</h3>
      <label>
        <input v-model="displaySettings.displayViews"
               @change="event => updateDisplaySettings()"
               type="checkbox">
        Display current views
      </label>
      <label>
        <input v-model="displaySettings.displayStockLevel"
               @change="event => updateDisplaySettings()"
               type="checkbox">
        Display stock
      </label>
    </div>
  </div>
</template>

<style scoped>

</style>