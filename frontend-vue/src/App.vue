<script>
export default {
}
</script>

<template>
  <header>
    <div class="logo">Wegistweg</div>
    <nav>
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/testCounter">Sign Up</router-link></li>
      </ul>
    </nav>
  </header>

  <main>
      <router-view></router-view>
  </main>

  <footer>
    <div class="container-horizontal">
      <div class="footer-section">
        <h2>Products</h2>
        <ul>
          <li><router-link to="/testCounter">All products</router-link></li>
          <li><router-link to="/testCounter">Most viewed</router-link></li>
        </ul>
      </div>
      <div class="footer-section">
        <h2>Account</h2>
        <ul>
          <li><router-link to="/testCounter">My Account</router-link></li>
          <li><router-link to="/testCounter">Login / Register</router-link></li>
        </ul>
      </div>
      <div class="footer-section">
        <h2>Contact</h2>
        <ul>
          <li><a href="#">faraz@mail</a></li>
          <li><a href="#">adam@mail</a></li>
        </ul>
      </div>
    </div>
    <p class="copyright">&copy; The Springers 2024</p>
  </footer>
</template>

<style scoped>

</style>
