# About

A demo of a Spring Boot Application where we use the OAuth2Client in combination with a React / Vite Frontend.

# How to run

1. Create a new OAuth2 App on GitHub
2. Set the Authorization callback URL to http://localhost:8080/login/oauth2/code/github
3. Set the Homepage URL to http://localhost:5173
4. Create a application-secrets.properties file in the backend/src/main/resources with the following content:

```
spring.security.oauth2.client.registration.github.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_CLIENT_SECRET
spring.security.oauth2.client.registration.github.scope=user
```

5. Activate the application-secrets.properties in IntelliJ

![drop-down.png](img%2Fdrop-down.png)

![config-window.png](img%2Fconfig-window.png)

6. Connect a MongoDB instance to the application (e.g. via Docker)
7. Start the backend
8. Start the frontend
9. Open http://localhost:5173 in your browser
10. Click on "Login with GitHub"
11. You should be redirected to GitHub and asked to login
12. After logging in you should be redirected back to the application and see a welcome message with your username
