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
* We'll start with a really contrived example to see how Gherkin looks.

`Feature: Math
    Scenario: Double a number
    Given the number 5
    When I double the number
    Then I should get 10`
    
* Each `.feature` file contains one `Feature` block.
    * `Feature` blocks don't get turned into executable code.
    * They define a name and description of the feature being tested in the file.
    * The first line of the `Feature` block becomes the name while the rest becomes the description.
* The `Scenario` keyword defines a single test for the feature.
    * A `.feature` file will typically contain multiple `Scenarios` testing different execution paths for the feature being tested.
* The `Given`, `When` and `Then` keywords define the steps of the test.
    * A `Given` step describes the state needed to perform the test.
    * A `When` step describes how to execute the functionality being tested.
    * A `Then` step describes what the result of the execution should be.
    * A step can also start with the `And` and `But` keywords.
        * These can be used to added additional `Given`, `When` and `Then` steps so the scenario reads more naturally.
    * If desired, you can use an asterisk character instead of the `Given`, `When` and `Then` keywords to make the steps in the scenario look like a bullet list.
* Sometimes multiple scenarios within the same `.feature` file require the same set-up steps.
    * One way to do this is to repeat the same `Given` steps over and over again.
    * This can be avoided by moving the `Given` steps into a `Background` block directly after the `Feature` block.
    * `When` steps can also be included in a `Background` block.
    * All the steps in a `Background` block will be executed at the beginning of every scenario in the file.
* When you need to set up a large amount of similar state at the beginning of a scenario, you can use Gherkin's data table syntax instead of tons of repetitive `Given` steps.
    * The data table syntax looks like this:

`Given the menu contains these items:
| name            | price  |
| Big Mac         | 3.99   |
| Quarter Pounder | 3.79   |
| Filet-O-Fish    | 3.79   |`

    * Cucumber has built-in functionality to access and compare the data in data tables.
* Another situation that can occur in a `.feature` file is when you have multiple scenarios that are identical except for the value of some variables.
    * This can be fixed by using `Scenario Outline` blocks.
    * A `Scenario Outline` block starts by defining steps like a normal scenario.
    * However, unlike a normal scenario the steps contain placeholders marked with angle brackets.
    * The `Examples` keyword supplies multiple values for the placeholders using a data table.
    * Cucumber uses the outline steps and the example data to generate multiple scenarios automatically when the file is executed.
* Gherkin files can contain comments.
    * Comments start with a `#` character and continue until the end of the line.
    * However, using too many comments in a Gherkin file is frowned upon because the file itself is supposed to be readable and self-explanatory.
* Gherkin also supports multi-line strings or *doc strings*.
    * Doc strings are delineated using triple quotes.
    * The can be used to define a long input or output value like an error message or the text of an email.
* Scenarios can be tagged with tags that start with an `@` symbol.
    * A scenario can be tagged with more than one tag.
    * A feature can also be tagged which applies the tag to all of its scenarios.
    * Tags can be used as filters to only run certain tests.
    * They can also be used as hook to run code before all scenarios that contain a certain tag.
* Now that we are familiar with Gherkin syntax, let's see how a scenario can be turned into an executable test.

## Making a Scenario Executable

* We need to tell Cucumber how to turn a Gherkin scenario into an executable test.
* However, even before we do that we can already run the `.feature` file.
    * This is because Cucumber is designed to be run in a test-driven fashion where you write the test first and run them repeatedly as you add the pieces necessary to make them pass.
* Typically, in a real project you would use a build framework like Ant, Maven or Gradle to execute your Cucumber tests.
* However, to keep things simple we will run it using the built-in command line interface (CLI).
    * It's not *that* simple though since it requires manually wrestling with fussy Java classpaths.

    