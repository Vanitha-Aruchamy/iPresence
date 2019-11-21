# iPresence

The project contains link validation, booking form validation test suites using selenium grid and web driver.

Test methods are written in a generic way. Utils package contains all the reusable methods and classes. We can use the same methods and test cases for different scenarios.

## Project Structure
	src/main/java - Contains main methods, classes and utils packages.
	src/main/resources - Contains the driver file
	src/test/java - Contains base test and test java files
	src/test/resources - Contains test suites xml file, input files.
	target - Contains all compiled java classes, test files and resources. This will be used to execute the test suites.
	test-outputs - Generated once the test suites triggered. Contains output files of tests.
	POM - Contains project information, project dependencies, directories, plugin and goals.
	testNG.bat - Batch file to trigger test suites
	testNG.xml - Suite test contains all test suites path and order of execution.
	

## To run test suites in eclipse
### Pre-requisite
	Java
	Eclipse IDE
	[TestNG] (https://testng.org/doc/eclipse.html)
	[Maven] (https://testng.org/doc/maven.html)
	Selenium Grid Chrome
 
### Steps to follow
1. Extract the .jar file
2. Import the project to your workspace
3. Right click on testng.xml file. Run As -> TestNG

## To run test suites using batch file.
### Pre-requisite
	Java
	[Maven] (https://maven.apache.org/install.html)
	Selenium Grid Chrome
	
### Steps to follow
1. Open a batch file in a notepad, which is located inside the project folder.(<Projectfolder>/testng.bat)
2. Change the project location
3. Save it and double click to trigger the test suites.



 
