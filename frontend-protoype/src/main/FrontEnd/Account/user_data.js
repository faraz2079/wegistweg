const userContainer = document.getElementById("user-container");

// Function to fetch and render users from REST API
async function fetchUsersRest() {
  try {
    const response = await fetch("http://localhost:8080/users");
    const users = await response.json();
    renderUsers(users);
  } catch (error) {
    console.error("Error fetching users from REST API:", error);
    userContainer.innerHTML = "<p>Error fetching users from REST API.</p>";
  }
}

// Function to fetch and render users from GraphQL API
async function fetchUsersGraphql() {
  try {
    const response = await fetch("http://localhost:8080/graphql", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        query: `
          query {
            users {
              id
              username
              email
            }
          }
        `,
      }),
    });
    const { data } = await response.json();
    renderUsers(data.users);
  } catch (error) {
    console.error("Error fetching users from GraphQL API:", error);
    userContainer.innerHTML = "<p>Error fetching users from GraphQL API.</p>";
  }
}

// Function to render users in the DOM
function renderUsers(users) {
  userContainer.innerHTML = ""; // Clear previous content
  if (users.length === 0) {
    userContainer.innerHTML = "<p>No users found.</p>";
    return;
  }
  users.forEach((user) => {
    const userDiv = document.createElement("div");
    userDiv.className = "user";
    userDiv.innerHTML = `
      <p><strong>ID:</strong> ${user.id}</p>
      <p><strong>Name:</strong> ${user.name}</p>
      <p><strong>Email:</strong> ${user.email}</p>
    `;
    userContainer.appendChild(userDiv);
  });
}

// Add event listeners to buttons
document.getElementById("fetch-rest").addEventListener("click", fetchUsersRest);
document.getElementById("fetch-graphql").addEventListener("click", fetchUsersGraphql);
