/* es lint-disable */

<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">Login</button>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "UserLogin",
  data() {
    return {
      email: "",
      password: "",
      errorMessage: "",
    };
  },
  methods: {
    async handleLogin() {
      try {
        // Send a POST request to the backend login endpoint
        const response = await axios.post("http://localhost:8080/users/login", {
          email: this.email,
          password: this.password,
        });

        // Extract user data and show success message
        const userData = response.data;
        localStorage.setItem("authUser", JSON.stringify(userData)); // Save user data in localStorage
        this.$router.push("/account"); // Redirect to the account page
      } catch (error) {
        // Handle errors
        if (error.response && error.response.data) {
          this.errorMessage = error.response.data.message;
        } else {
          this.errorMessage = "An error occurred. Please try again.";
        }
      }
    },
  },
};
</script>

<style scoped>

<style scoped>
/* General Login Page Styling */
.login {
  font-family: Arial, sans-serif;
  background-color: #ECEBDE; /* Match the background */
  text-align: center;
  padding: 40px;
}

.login-container {
  max-width: 400px;
  margin: auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

label {
  font-weight: bold;
  color: #333;
}

input[type="email"],
input[type="password"] {
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
}

button:hover {
  background-color: #1F509A;
}

.error {
  color: #e53622;
  font-weight: bold;
  margin-top: 10px;
}
</style>