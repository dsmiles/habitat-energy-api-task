## CI/CD Pipeline

The aim is to deliver software faster. To achieve that you need a build pipeline to automatically test your software
and deploy it to your testing and production environments.

The pipeline should be setup to follow the Test Pyramid concept as outlined by Mike Cohn. Mike Cohn's original test
pyramid consists of three layers that your test suite should consist of (bottom to top):

1.	Unit Tests
2.	Service Tests
3.	User Interface Tests

However, in practice there are many more layers. Your best bet is to remember two things from Cohn's original test
pyramid:

1.	Write tests with different granularity
2.	The more high-level you get the fewer tests you should have

Stick to the pyramid shape to come up with a healthy, fast and maintainable test suite. Write lots of small and fast
unit tests. Write some more coarse-grained tests and very few high-level tests that test your application from end to
end. Care must be taken to avoid ending up with an ice cream code test suite that will be a nightmare to maintain and
takes way too long to run.

Given the shortcomings of the original names it's okay to come up with other names for your test layers, as long as you
keep it consistent within your codebase and your team's discussions.

## General

- Write fast unit tests
- Don’t test getters and setters.
- Don’t test trivial code.
- Only test the public interfaces. Internal interfaces will get implicitly tested when executing the public interface tests

## Integration testing

There are different types of integration testing:
a)	Integration testing classes
b)	Integration testing components / services / sub-systems

Try to test your services / sub-systems in isolation. Make use of test doubles or containers to isolate the
component/service/sub-system under tests from the outside world. Keep your pipeline stable, don’t closely couple your
integration tests to some external data provider that could suffer an outage and fail your pipeline/

Look at this article for ideas:
https://testing.googleblog.com/2012/10/hermetic-servers.html

## UI Testing

The aim is to execute these tests are fast as possible.  Therefore, use static data or test doubles isolate the UI from
external dependencies to improve stability.  Using real data in your tests should be kept to a bare minimum and further
up the pyramid (later in the pipeline)

When testing the UI to ensure it functions correctly (i.e. user input should trigger the right actions, data should be
presented to the user, the UI state should change as expected) you should try and use mocked data or test doubles.

## End-to-End tests

- This applies to API and the Web UI.
- End-to-end tests tend to run pretty slowly. Due to their high maintenance cost you should aim to reduce the number of end-to-end tests to a bare minimum.
- This is where real data could be used. So, write your tests to execute, but not be blocked by failures of external systems.