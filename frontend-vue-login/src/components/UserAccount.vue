/* es lint-disable */

<template>
  <div>
    <header>
      <div class="logo">Exclusive</div>
      <nav>
        <ul>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/contact">Contact</router-link></li>
          <li><router-link to="/about">About</router-link></li>
          <li><router-link to="/signup">Sign Up</router-link></li>
          <li><a href="#"><i class="fa fa-search"></i></a></li>
          <li><a href="#"><i class="fa fa-heart"></i></a></li>
          <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
          <li><a>Welcome {{ userName }}</a></li>
        </ul>
      </nav>
    </header>

    <main>
      <div class="container">
        <div class="left-section">
          <ul>
            <li><a href="#">My Profile</a></li>
            <li><a href="#">Address Book</a></li>
            <li><a href="#">My Payment Options</a></li>
            <li><a href="#">My Orders</a></li>
            <li><a href="#">My Returns</a></li>
            <li><a href="#">My Cancellations</a></li>
            <li><a href="#">My Wishlist</a></li>
          </ul>
        </div>
        <div class="right-section">
          <h2>Edit Your Profile</h2>
          <form @submit.prevent="saveChanges">
            <div class="form-group">
              <label for="first-name">First Name</label>
              <input v-model="form.firstName" type="text" id="first-name" required>
            </div>
            <div class="form-group">
              <label for="last-name">Last Name</label>
              <input v-model="form.lastName" type="text" id="last-name" required>
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <input v-model="form.email" type="email" id="email" required>
            </div>
            <div class="form-group">
              <label for="address">Address</label>
              <input v-model="form.address" type="text" id="address" required>
            </div>
            <div class="form-group">
              <label for="current-password">Current Password</label>
              <input v-model="form.currentPassword" type="password" id="current-password" required>
            </div>
            <div class="form-group">
              <label for="new-password">New Password</label>
              <input v-model="form.newPassword" type="password" id="new-password" required>
            </div>
            <div class="form-group">
              <label for="confirm-password">Confirm Password</label>
              <input v-model="form.confirmPassword" type="password" id="confirm-password" required>
            </div>
            <button type="submit">Save Changes</button>
            <button type="button" @click="cancelChanges">Cancel</button>
          </form>
        </div>
      </div>
    </main>

    <footer>
      <div class="container">
        <div class="footer-section">
          <h2>Exclusive</h2>
          <p>Get 10% off your first order</p>
          <button>Enter your email</button>
        </div>
        <div class="footer-section">
          <h2>Support</h2>
          <p>Fachhochschule Dortmund 42 Building</p>
          <p>fhdo.de</p>
          <p>+4912341234</p>
        </div>
        <div class="footer-section">
          <h2>Account</h2>
          <ul>
            <li><router-link to="/account">My Account</router-link></li>
            <li><router-link to="/login">Login / Register</router-link></li>
            <li><router-link to="/cart">Cart</router-link></li>
            <li><router-link to="/wishlist">Wishlist</router-link></li>
            <li><router-link to="/shop">Shop</router-link></li>
          </ul>
        </div>
        <div class="footer-section">
          <h2>Quick Link</h2>
          <ul>
            <li><router-link to="/privacy-policy">Privacy Policy</router-link></li>
            <li><router-link to="/terms">Terms Of Use</router-link></li>
            <li><router-link to="/faq">FAQ</router-link></li>
            <li><router-link to="/contact">Contact</router-link></li>
          </ul>
        </div>
      </div>
      <p class="copyright">&copy; Exclusive E-commerce 2023. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: "User",
      form: {
        firstName: "",
        lastName: "",
        email: "",
        address: "",
        currentPassword: "",
        newPassword: "",
        confirmPassword: "",
      },
    };
  },
  methods: {
    saveChanges() {
      // Save form data to the backend
      alert("Profile updated successfully!");
    },
    cancelChanges() {
      // Reset form
      this.form = {
        firstName: "",
        lastName: "",
        email: "",
        address: "",
        currentPassword: "",
        newPassword: "",
        confirmPassword: "",
      };
    },
  },
};
</script>

<style scoped>

<style scoped>
/* General Layout */
header {
  background-color: #ECEBDE; /* Match background */
  padding: 20px;
  border-bottom: 1px solid #a6a59c;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  font-weight: bold;
  font-size: 24px;
}

nav ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
}

nav li {
  margin-right: 20px;
}

nav a {
  text-decoration: none;
  color: #333;
}

main {
  padding: 40px;
}

.container {
  display: flex;
  justify-content: space-between;
}

.left-section {
  width: 25%;
  border-right: 1px solid #ccc;
  padding-right: 20px;
}

.left-section ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.left-section li {
  margin-bottom: 15px;
}

.left-section a {
  text-decoration: none;
  color: #333;
  font-weight: bold;
}

.left-section a:hover {
  color: #89A8B2;
}

.right-section {
  width: 70%;
  padding-left: 20px;
}

.right-section h2 {
  color: #333;
  margin-bottom: 20px;
}

.right-section form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #89A8B2;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

button:hover {
  background-color: #1F509A;
}

footer {
  background-color: #1F509A;
  padding: 40px;
  margin-top: 20px;
}

.footer-section {
  color: #fff;
}

.footer-section h2 {
  margin-bottom: 20px;
}

.footer-section a {
  text-decoration: none;
  color: #fff;
}

.footer-section a:hover {
  color: #89A8B2;
}

.copyright {
  text-align: center;
  margin-top: 20px;
  color: #4a70a8;
}
</style>
