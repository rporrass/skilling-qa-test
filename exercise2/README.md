# Exercise 2:

Goal of this exercise is to demonstrate the knowledge on following areas:
- GUI testing.
- Selenium.

Exercise consists of following:
- Using following sample web application http://computer-database.gatling.io/computers
that implements a catalog of computer models.
- Implement a test that uses selenium framework to test the following flow:
- Insert a new computer model.
- Search and find the computer model inserted.
- Delete the computer model.
- It is desirable to write the test using Java and jUnit framework.
- It's advisable to use some scripting and dependency management tools (shell, bash,
maven, gradle, etc) that would help to fetch the dependencies and run the applications.
- Expected outcome is code and scripts to be committed to some code repository like
github (GitHub is free) together with README file that explains how to run the code.

---

To execute tests, please run:
```bash
./gradlew test -Pbrowser.to.test=browser
```

Parameter _browser.to.test_ is not required, default value is _firefox_. Possible values are:
- firefox
- chrome

Browser to be used needs to be installed and in path.
