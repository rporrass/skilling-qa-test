# Exercise 1:

Goal of this exercise is to demonstrate the knowledge on following areas:

- Unit-testing of RESTful API.
- Mocking.

Exercise consists of following:
- Implement a server that offers following RESTful API:
- GET endpoint that reports the status and up-time.
- POST endpoint that takes some payload and returns some result.
- Wire-mock or similar can be used to simplify the server implementation (link).
- Implement a client that runs unit-testing against the RESTful API offered by the server
(link).
- It is desirable to write client and server using Java, spring-boot (and JUnit in client).
- It's advisable to use some scripting and dependency management tools (shell, bash,
maven, gradle, etc) that would help to fetch the dependencies and run the applications.
- Expected outcome is code and scripts to be committed to some code repository like
github (GitHub is free) together with README file that explains how to run the code.

---

To execute tests, please run:
```bash
./gradlew test
```
