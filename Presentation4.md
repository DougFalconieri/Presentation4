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
* For the original Ruby version of Cucumber, you can easily install it as a gem.
* The implementation of Cucumber for C# is called SpecFlow.
    * It can be installed using Microsoft's NuGet package management system.
    * Integration with Visual Studio can be downloaded using the IDE's built-in extension manager.
* The implementation that I am planning to focus on is the Java implementation.
    * To use Cucumber for Java, you need several JAR files.
    * You always need `cucumber-core-<version>.jar`, `cucumber-jvm-deps-<version>.jar` and `gherkin-<version>.jar`.
    * You also need a back-end JAR file.
        * For Java 8, this is `cucumber-java8-<version>.jar`.
        * For earlier versions of Java, it's `cucumber-java-<version>.jar`.
        * There are alternate back-end JAR files for many other JVM languages including Groovy, Scala, Clojure, jRuby and Jython.
    * The version number of the Cucumber core JAR and back-end JAR should match.
        * The Gherkin and JVM Deps JARs will have different version numbers.
    * With these four JARs on the classpath, you can run Cucumber from the command line with the command `java cucumber.api.cli.Main -p pretty`.
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
* We'll write a test for a class that provides a method that doubles an integer.

Feature: Math
   Scenario: Double a number
   Given the number 5
   When I double the number
   Then I should get 10
    
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

Given the menu contains these items:
| name            | price  |
| Big Mac         | 3.99   |
| Quarter Pounder | 3.79   |
| Filet-O-Fish    | 3.79   |

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
* Cucumber for Java is an executable JAR file so it can be run with the following command: `java cucumber.api.cli.Main`.
* We need to make sure all of Cucumber's dependency JARs are on the classpath.
    * We can do this with the `-cp` argument to the `java` executable.
    * In this case, we will put all the dependencies in a directory called `jars` under our project root directory.
    * So the command to run our feature becomes: `java -cp "jars/*" cucumber.api.cli.Main`.
* We also need to pass an argument to Cucumber to tell it where to find our feature file.
    * In this example, we'll put all of our `.feature` files in a directory under the project root called `features`.
    * Our Cucumber command becomes: `java -cp "jars/*;bin" cucumber.api.cli.Main features`.
* The Cucumber CLI also supports some other arguments that I found useful.
    * The `-p` argument tells Cucumber to apply a plugin.
    * The `pretty` pluging modifies the output that the CLI writes to the console to be easier to read.
    * By default, the console output uses a feature called ANSI colors which is not supported natively on Windows.
        * If you are running Cucumber on Windows like I am, you need to either install a special app or apply the `--monochrome` or `-m` argument to the CLI.
    * After applying these additional arguments, the command looks like `java -cp "jars/*;bin" cucumber.api.cli.Main -p pretty features --monochrome`.
* Our Cucumber command now runs, finds our feature file and prints out output saying that we have one scenario and three steps that all have a status of "undefined".
* To define the steps, we need to create "step definitions" that explain how to map the steps to executable code.
* Step definitions are implemented as code blocks in whichever language you are using Cucumber in -- Java in our case.
* We'll put the step definitions for our `Math` feature in a class called `MathStepDefinitions` in a package called `step_definitions`.
    * Cucumber doesn't actually care what you name this class.
* In Java 8, your class implements the `cucumber.api.java8.En` interface.
* Cucumber provides methods for each type of step (`Given`, `Then`, `When`, etc.).
* Each method takes a regular expression as its first argument and a closure as its second argument.
    * The closure code tells Cucumber how to run the step.
    * The regular expression must match the step text in the feature file after the initial keyword in order for Cucumber to call the associated closure.
    * Any numbers or quoted strings in the step text get converted to parameters to the closure.
        * When Cucumber calls the closure, it will pass in the appropriate value parsed from the feature file.
    * Nicely, you don't need to worry about writing a correct regular expression to match each one of your steps.
        * When you run Cucumber on an undefined feature, the console output will contain suggested skeleton methods for each step.
            * All you need to do is paste them into your step definition class and supply the actual behavior.
    * If you are a regular expression ninja, you can write a custom regex to match multiple steps at once, but this is really not necessary.
    * Cucumber considers any step definition that throws an exception of any kind to be a failed step.
        * Otherwise, its considered passed.
        * Cucumber includes a special type of exception called `PendingException` to indicate steps that haven't been implemented.
* Let's create step definitions for each one of our steps.
    * Our first step looks like this: `Given the number 5`.
    * Cucumber suggests the following skeleton implementation: `Given("^the number (\\d+)$", (Integer arg1) -> {});`.
    * Cucumber will call this closure and pass it the number 5 as the parameter `arg1`.
    * `Given` steps are for initializing state so the main thing we need to do to implement this step is to store the parameter for use by later steps.
    * So we create an instance variable called `inputNumber` and save the value of arg1 to it.
        * We'll also convert the Integer class to an `int` primitive to make it easier to work with.
    * Our second step looks like this: `When I double the number`.
    * Cucumber suggests the following skeleton: `When("^I double the number$", () -> {});`.
    * `When` steps should actually call the code being tested -- in our case our `MathHelper` class.
    * In true TDD fashion, we haven't actually created our `MathHelper` class so we'll take a moment to do so now.
        * `MathHelper` has a single method called `doubleValue` that takes in a integer and returns is double.
    * Now that we've got a method to test, we call it passing in the `inputValue` that we stored earlier and save the result to a new instance variable called `result`.
    * Our final step is: `Then I should get 10`.
    * This produces the skeleton: `Then("^I should get (\\d+)$", (Integer arg1) -> {});`.
    * In a `Then` step, we need to check our output against its expected value.
        * Cucumber will pass in the expected value (which is 10 in this case) as `arg1`.
        * We need to compare it against the `result` instance variable we stored in the previous step.
        * Cucumber allows you to choose any way to do this as long as it throws an exception if the values don't match.
        * One way to do this is using jUnit asserts which is what we will do.
        * This requires downloading the jUnit JAR (and its sole dependency hamcrest) and adding them to our project's `jar` folder.
        * Then we can use jUnit's `assertEquals` method to check our result like this: `assertEquals(arg1.intValue(), result);`.
* In the closure-less versions of Java before Java8, the syntax for step definitions looked completely different and made use of Java annotations to association a regular expression with a method.
    * In Java 7, our `When` step might look like this: 
    
    @Given("^the number (\\d+)$")
    public void theNumber(Integer arg1) throws Throwable {}
    
* Now that we have step definitions defined, our test should pass if we run it.
* However, in order to run it we need to update our CLI command so Cucumber can find our step definition class as well as our `MathHelper` class.
    * It took a lot of fiddling in order to get this right.
    * I'm using Eclipse to compile the project's Java files so the source is located in a directory called `src` and Eclipse puts the compiled `.class` files in a directory called `bin`.
    * First, we need to add the `bin` directory to our classpath listing.
    * Then we use the `--glue` or `-g` argument to tell Cucumber the name of the package that our step definitions our located in: `step-definitions`.
    * Our final CLI command looks like this: `java -cp "jars/*;bin" cucumber.api.cli.Main -p pretty features --monochrome  -g step_definitions`.
* And it works! Our test runs and passes!
    * If we change the 10 in the last step of our scenario to 11, the test fails.
    * Even though it was a contrived example it contains a large amount of what you would need to use Cucumber on a real project.
    
## Acceptance Testing of Web Applications

* Since much of my career has been spent working on web applications, I am particularly interested in how Cucumber could be used to test them.
* In my opinion, integration testing is particularly important for web applications because they are tough to adequately test with unit tests.
    * Many screens I have worked on use a database framework to run a SQL query against a database and convert the results into objects which are then passed to a rendering engine to generate HTML or JSON.
    * In between the database and the rendering engine, there is often very little logic to test and trying to unit test the straight-forward logic that there is requires creating lots of mock objects.
    * The real logic is all embedded in the database query and a template file with client-side JavaScript code.
        * Both are difficult or impossible to thoroughly test with isolated unit tests.
        * But automated integration tests can provide a lot of benefits.
* To run automated tests against a web application, you need a way to drive a web browser.
* Probably the most popular tool for doing this is Selenium which is available from [here](http://www.seleniumhq.org/).
    * Selenium comes with drivers for all of most popular web browsers -- including Chrome, Internet Explorer, FireFox and Safari.
    * The Selenium API for driving browsers is available in many programming languages including Java, C#, Python and Ruby.
    * The Selenium API contains methods for finding HTML elements like text boxes and buttons and performing interactions like entering text and pressing buttons.
        * This allows you to do most of what you would do in a manual test of the web application.
* One powerful aspect of Cucumber is that the step definitions that implement your tests are simply Java code (or whatever language you are using) so you can use any libraries you need to carry out your tests.
    * In the example we worked through earlier, we saw how easy it is to use jUnit with Cucumber.
    * We can just as easily incorporate calls to the Selenium API to drive web applications.
    * No special set-up is required other than adding Selenium's required JAR files to the project and becoming familiar with is API.
    
## Advice from the Experts

* The authors of *The Cucumber for Java Book* have a lot of experience using Cucumber in the real world.
    * One of the authors created Cucumber while the other two also have been using it for years.
* They offer several tips for using Cucumber successfully that I found interesting.
* One downside of integration tests when compared to unit tests is they take longer to run.
    * In order to keep the test suite runnable in a reasonable amount of time, the authors recommend not creating too many scenarios.
        * Instead of testing every possible situation, focus on the most important or representative ones.
        * Consider if unit testing may be a better option for some scenarios such as those involving complex business logic with many possible code branches.
        * It's also possible to use tags to choose some tests to run frequently and others that only need to run once in a while.
* Another problem that can hurt the success of your testing process is brittle tests that break often and need to be fixed constantly.
    * The authors argue that brittle tests are often caused by interdependencies between scenarios.
        * An example of this would be if one scenario loads data in the database that the next scenario expect to still be there when it runs.
        * These type of interdependencies are tempting because they can make tests run faster and reduce the amount of code needed in the step definitions, but they can quickly become a maintenance nightmare.
        * The authors emphasize the importance of making sure each scenario is completely dependent.
    * Brittle tests can also be caused by testing in a shared environment like a database where other users and processes can interfere with your test.
        * Its best if the tests run in their own independent environment so failed tests can't be caused by other users.
* Cucumber features are supposed to written collaboratively by the developers and users, but this doesn't work if the users are disinterested.
    * One thing that cause users to lose interest is not having access to the features because they are located only on a developer's computer or in a version control system.
        * The authors suggest using the Relish tool to publish the features on a server where all the team members can easily access them.
    * Even if they can access the features, users may not want to read them if they are too long and tedious.
        * This can be avoided by keeping the features short and as close to natural English as possible.
        * Technical details and long lists of specific steps for executing the tests should be avoided.
            * These details can go into the step definitions instead leaving the features focused more on what the system should do instead of how it should be done.
                
## Final Thoughts

* Cucumber is interesting because it is both a tool and a way of organizing software development.
* My projects could definitely benefit from automated integration testing, but you don't really need Cucumber just for that.
* After learning about Cucumber, the thing that intrigues me most is the idea of turning the application's requirements or user stories into executable tests.
    * This could save a lot of the effort of creating a separate set of test cases.
* I am looking forward to trying Cucumber on my future projects.