# jersey2-test-example
We are currently migrating von Jersey 1.x to 2.x and hit now the problem that root resource classes are not found when we create a jar from our source code.

## Description of the bug
As long as we load these classes from the file system the resources are found and working, but as soon as we create a jar from the compiled classes (using the jar program) and run the program the resource classes are not found.

Interesting enough, when we open the jar and move the contents into a newly created zip file and rename this zip file extension to jar, the classes are also found, the same when we use maven to create the package.

Since our build system depends on using jar to create the complete project we need to find out what is causing this.

## How to reproduce
This is a small git project that demonstrates the behaviour.
 * It compiles our test code with javac/jar that does not work
 * a newly created zip archive where we simply moved the compiled classes from the not working jar and that work
 * a pom file that creates a package from the same sources that also works.

This example was compiled with JDK 1.8.232 on Windows 10, but the same problem also occured on Java 11 and Linux.


To reproduce the problem, you can use those bat files on windows (or rum the equivalent commands in the command line on any other operating system):
 * startTestWebservice_javac.bat will start the webservice with the compiled classes from javac (in /runtime/classes_javac), which **doesn't** work
 * startTestWebservice_javac_repackaged.bat start the webservice with the repackaged classes from above (in /runtime/classes_javac_repackaged), which **does** work
 * startTestWebservice_maven.bat start the webservice with the class created by maven (./target/Jersey2-0.0.1.jar), which **does** work.

To test it, you can make a GET request to http://localhost:12345/foo/bar. On success you will get a response OK with text `foo bar content...`

The class `WebserviceRootClass` has a static initializer block, which will println `**** Loading WebServiceRootClass ****` once it is found by the class loader, at the first use of that class. This doesn't appear when I run `startTestWebservice_javac.bat`, but when I run `startTestWebservice_javac_repackaged.bat`. So it seems to be some problem with the class loader used by jersey2, in combination with the `jar` command. 

## What we tried so far...
 * Analyzing and comparing the jar files in `classes_javac` versus `classes_javac_repackaged` should help. something must be wrong with the first. We compared them with various compressing tools, but with no result.
 * We already tried several compressing programs and zip compression algorithms, it is very unlikely to be related to that.
* On our machine, it worked in Java 1.7, but not in Java 1.8 and 11. Maybe something changed in 1.8?
