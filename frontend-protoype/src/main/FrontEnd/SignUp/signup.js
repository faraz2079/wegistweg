document.getElementById('signup-form').addEventListener('submit', async function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Clear any previous message
    const messageDiv = document.getElementById('signup-message');
    messageDiv.textContent = '';
    messageDiv.style.color = '';

    try {
        const response = await fetch('http://localhost:8080/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username,
                email,
                password,
            }),
        });

        if (response.ok) {
            // Show success message
            messageDiv.textContent = 'User signed up successfully!';
            messageDiv.style.color = 'green';
        } else {
            // Show error message
            const error = await response.json();
            messageDiv.textContent = `Error: ${error.message}`;
            messageDiv.style.color = 'red';
        }
    } catch (error) {
        // Handle network or other errors
        messageDiv.textContent = 'Error: Unable to connect to the server.';
        messageDiv.style.color = 'red';
    }
});
