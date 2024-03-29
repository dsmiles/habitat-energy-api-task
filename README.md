## Habitat Energy API Task (Part 1 of 2)

In November/December 2022, I interviewed for the position of Software Engineer in Test at Habitat Energy in Oxford.

As part of the interview process, I undertook a challenge to develop two automation frameworks — one for an API and one
for a web UI.

I successfully completed the challenge and the interview process, ultimately receiving an offer for the position in 
December 2022. However, I made the decision to decline the offer. In hindsight, I realize I probably should have 
accepted it, given the interesting nature of the role. Oh well, live and learn.

The task description is [here](TECHNICAL-TASK.md). The original task description in PDF format is [here](docs/pdf/Software-in-Test-Challenge.pdf).

My list of suggested CI/CD and testing recommendations is [here](RECOMMENDATIONS.md).

## Framework

I built an API test framework that combined Behavior-Driven Development (BDD) with Cucumber and REST-assured to develop
tests based on acceptance criteria.  The framework also utilises a number of other components for assertions, JSON 
marshalling, etc., but Cucumber and REST-assured are the main ones.

### BDD Automation with Cucumber

Cucumber is a framework for writing automated tests in a BDD fashion. Its syntax (Gherkin language) allows for more 
readable and comprehensive tests which can be understood by any member of the team. Cucumber can be combined with many 
API or UI frameworks.

### REST-assured

REST-assured is a Java library that is used for writing automated API tests. Basically, REST-assured acts like an HTTP 
client which can perform HTTP operations (`GET`, `POST`, `PUT`, `DELETE`, etc.) against a Restful server. They can be highly 
customizable so that we can have the flexibility of performing many different combinations of test cases to cover the
overall business logic.

### Deserialization with REST-assured

I used REST-assured to deserialize the API response from a JSON object into a Java POJO (Plain Old Java Object). 
REST-assured internally handles the deserialization process and maps the JSON response to a POJO object. Once the 
deserialization is complete, the data can be accessed using the getter methods of the POJO class.

### Reducing Code Complexity with Lombok

To further simplify the creation of POJO classes, I used Lombok, a Java library that reduces boilerplate code. Lombok 
provides annotations such as `@Data`, `@Getter`, `@Setter`, and `@NoArgsConstructor` that automatically generate getter
and setter methods, constructors, and other common code. This helps reduce the complexity and improves code readability.

## Installation and Prerequisites

The following tools and libraries were used in the test framework:

1. Java JDK 11
2. Maven build manager 
3. IntelliJ IDE / or other
4. Required IDE plugins:
    - Maven
    - Cucumber
    - Lombok
    - JUnit / AssertJ
    - REST-assured

## Running the tests

1. Clone / download the project into your local
2. Open a command prompt and navigate to project location
3. Execute the following Maven command's:

To clean the Maven repository:
- `mvn clean`

To install the Maven requirements / dependencies 
- `mvn install`

To execute the test scenarios
- `mvn test`

You can also run the tests from your IDE, by running the Cucumber feature files.

### Pipelines

_This repository was originally hosted in Atlassian BitBucket and used their pipelines. I've since migrated
the repository to GitHub (Jan 2024) so that all of my repositories are in a single location._

I implemented a simple pipeline using BitBucket to run the test suite whenever a changed was pushed to the repository.

The pipeline ran the following:

- Create a Docker contain to run maven:3.6.3
- Checkout the code from the repository
- Run the following steps in parallel:
   - Build and execute the tests (collecting any results)
   - Perform a security scan on the code (collecting any results)

Note: Atlassian scripts are like GH Actions.
