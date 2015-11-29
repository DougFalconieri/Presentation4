#Presentation 4: Cucumber

##Information Sources

* [Cucumber Website](https://cucumber.io/)
* Rose, Seb, Wynne, Matt and Hellesoy, Aslak. *The Cucumber for Java Book.* Pragmatic Bookshelf, February 16, 2015.

##What is Cucumber?

* A framework for integration testing.
* Tests are written in a domain-specific language that is close to English and understandable by users and developers.
    * This allows the tests to be very similar to or synonymous with user stories or requirements.
* This style of testing is known as Behaviour-Driven Development (BDD).
* Cucumber originated in the Ruby community, but now works to test apps written in Java, .NET, Flex and web applications regardless of which language they are written in.

##Why is it Interesting?

* At my job, user-acceptance testing is often a process reminiscent of the movie "Office Space".
* A systems engineer writes a set of test cases in an elaborate SharePoint list.
    * In theory, the customers help write and approve the test cases, but they are noticeably less engaged when talking about test cases than when talking about new functionality.
    * In practice, the systems engineer mostly writes them with the customers then rubber-stamping them.
* Customers are then expected to test the application and mark each test case as passed or failed.
* Since the customers have real jobs to do, they tend to procrastinate this tedious task while the system engineer nags them to do it.
* As the deadline for deploying the release approaches, the systems engineer nags the customers more aggressively and schedules lots of follow-up meetings.
    * At this point, the customers usually give in and complete their testing or withdraw from the testing altogether.
* Meanwhile, while waiting for the testing process to complete the software development team is left with little to do (or some welcome time to devote to another project).
* This highly-inefficient process absurdly takes nearly as long as developing the software.
* The best solution to this problem would be to replace the whole testing process with a more agile approach.
    * But until management can be convinced of that, automating some of the user testing process could save a huge amount of time.
* In this presentation, I want to see if and how much Cucumber can help.

##Installing Cucumber

* Cucumber works with my different programming languages and the installation depends which one you are using.
* For the original Ruby version of Cucumber, you can easily install it as a jem.
* The implementation of Cucumber is called SpecFlow.
    * It can be installed using Microsoft's NuGet package management system.
    * Integration with Visual Studio can be downloaded using the IDE's built-in extension manager.
* Any implementation of Cucumber can be combined with Selenium to test web applications by driving a web browser.
* The implementation that I am planning to focus on is the Java implementation.
    * To use Cucumber for Java, you need several JAR files.
    * You always need `cucumber-core-<version>.jar`, `cucumber-jvm-deps-<version>.jar` and `gherkin-<version>.jar`.
    * You also need a back-end JAR file.
        * For Java 8, this is `cucumber-java8-<version>.jar`.
        * For earlier versions of Java, it's `cucumber-java-<version>.jar`.
        * There are alternate back-end JAR files for many other JVM languages including Groovy, Scala, Clojure, jRuby and Jython.
    * The version number of the Cucumber core JAR and back-end JAR should match.
        * The Gherkin and JVM Deps JARs will have different version numbers.
    * With these four JARs on the classpath, you can run Cucumber from the command line with the command `java cucumber.api.cli.Main -p pretty'.
    * There are several additional JARs you can download from the Cucumber website to extend the capabilities of Cucumber.
        * There are JARs to enable integration with the test frameworks jUnit and TestNG.
        * There are also JARs to enable integration with several Java dependency injection frameworks including Spring, Guice, PicoContainer and others.
        
##Testing a Feature With Cucumber

* The tests for a feature of your application are defined in a plain-text file with an extension of `.feature`.
* In this file, the tests are described using *Gherkin*, Cucumber's DSL for test cases.
* In Cucumber terminology, tests are called *scenarios* and there can be multiple scenarios for each feature.
* Each scenario consists of multiple *steps* for Cucumber to execute.

##Gherkin

* Now we will look at the syntax of the Gherkin test-definition language.
* The syntax is designed to be very close to English.
    * For non-English speakers, there are also versions of Gherkin for 40 other natural languages.
