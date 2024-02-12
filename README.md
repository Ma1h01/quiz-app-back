# Quizapp
A complete backend of a quiz system. It allows clients to add, update, remove questions, and make and grade a quiz with questions that are randomly drawn from the database.

## How to run the full quiz app locally
Requirement: VS Code (Optional, but recommended), MySQL, and MySQL Workbench, Ngrok  
1. Clone this repo
2. Use MySQL Workbench to run the provided MySQL script in [databases](./src/main/resources/databases) folder to create the database
3. Export the localhost:8080 port with Ngrok
4. Run the Spring Boot server in VS Code
5. Open [https://cs-quiz-generator.netlify.app](https://cs-quiz-generator.netlify.app) to interact with the UI
