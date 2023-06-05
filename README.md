***
## Sample REST Api Automation
***
### Set up project
Requires Java and Maven versions `openjdk 20 
Apache Maven 3.9.1`
- [Install Java](https://java.com/en/download/help/download_options.html)
- [Install Maven](https://maven.apache.org/install.html)

Install dependencies:
```bash
$ mvn clean install -DskipTests
```

### Run tests
#### Cucumber tests
```bash
$ mvn test -Dtest=CukeRunner -Dcucumber.filter.tags=@<your-tag>
```

#### TestNG tests
```bash
$ mvn clean test -Dtest=<TestNG-Test-Class>
# Example:
$ mvn clean test -Dtest=CreateBookingApiTests
```

---
### Reporting
```bash
# Install Allure for TestNG Allure reports and run below command after test exectuion:
$ allure serve
# Cucumber reports are generated with this file location: target/cucumber-report.html
```
***
