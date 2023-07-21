Project Name : Identify Courses
 
Associate Name : Soham Mukherjee
Associate ID : 2271264

URL used: https://www.coursera.org
Operating System used: Windows 11

Versions of Browsers :-

>Chrome Browser- Version 111.0.5563.111 (Official Build) (64-bit)

>Edge Browser- Version 114.0.1823.43 (Official build) (64-bit)

Dependencies used :-

>selenium-java - Version 4.9.0
>commons-io -Version 2.11.0
>extentreports - Version 5.0.9
>dom - Version 2.3.0-jaxb-1.0.6
>poi-ooxml - Version 5.2.3
>webdrivermanager - Version 5.3.3

Case Study : Identify Courses

Suggested Site : https://www.coursera.org

Detailed Description :

1. Search for web development courses for Beginners level & English Language and extract the course names, total learning hours & rating for first 2 courses
2. Look for Language Learning; Extract all the languages and different levels with its total count & display them
3. In Home page, go to "For Enterprise"; Look into Courses for Campus under Product; Fill the  "Ready to transform" form with any one input invalid (example: email); Capture the error message & display

Key Automation Scope :

1. Handling different browser windows, search option
2. Extract multiple drop down list items & store in collections
3. Navigating back to home page
4. Filling form (in different objects in web page)
5. Capture warning message
6. Scrolling down in web page

Technology/Automation Tools Used: 

1. Selenium Webdriver and it's concepts
2. WebDriverManager and it's concepts
3. Maven
4. TestNG framework and it's concepts
5. Data Driven approach
6. Page Object Model
7. Extent Report
8. Excel, XML and Property file concepts
9. Multiple Browser testing concepts
10. Java Concepts

Folder Structure :

IdentifyCourse
├───.settings
├───Screenshots
├───src
│   ├───main
│   │   ├───java
│   │   │   ├───coursera
│   │   │   ├───driverUtils
│   │   │   └───utils
│   │   └───resources
│   └───test
│       ├───java
│       │   └───test
│       └───resources
├───target
│   ├───classes
│   │   ├───coursera
│   │   ├───driverUtils
│   │   ├───META-INF
│   │   │   └───maven
│   │   │       └───org.coursera
│   │   │           └───IdentifyCourse
│   │   └───utils
│   └───test-classes
│       └───test
├───test-output
│   ├───Coursera
│   ├───Default suite
│   ├───Failed suite [Default suite]
│   ├───IdentifyCourse by groups
│   ├───junitreports
│   └───old
│       ├───Coursera
│       ├───Default suite
│       ├───Failed suite [Default suite]
│       └───IdentifyCourse by groups
└───TestResult

Data Driven Concepts :

1) Properties File (Reading Data)

   > (config.properties)- This properties file is present in src/main/resources 
    
   > This file consists of Browser name and URL value.

2) Excel File (Reading Data/Writing Data)
    
   > (FormInput.xlsx)- This Excel file is present in src/main/resources  
 
   > This file consists of values to be entered in 'Contact Sales' form. These include values of:

     > First Name 
     > Last Name
     > Work Email
     > Company Name
     > Job Title
     > Job Function
     > Phone Number
     > No. Of Employee
     > No. Of Trainees
     > Country

   > The error message after entering an invalid data in a mandatory field in the form and submitting it is written to this file.

   > (Courses.xlsx)- This Excel file is present in src/test/resources
   
   > This file contains details of the retrieved courses

3) XML File (Reading data)

   > (input.xml)- This XML file is present in src/main/resources   

   > This file contains the input to be entered into the Search bar. 

Steps To Execute :

> Go to testng.xml or smoke.xml or regression.xml file in the src/test/reources folder according to requirement and right click on it.
> Click on 'Run As' and select 'TestNG Suite' to execute the automation script. 

***Note :

> All the dependencies are present in pom.xml, a stable internet connection is required to run the maven project.
> TestNG Library is required.
> For multiple browsers (Chrome and Edge), the browser name is read from 'config.properties' file
	> If we want to use chrome browser, go to 'config.properties' file and set browser name as 'chrome'.
	> If we want to use edge browser, go to 'config.properties' file and set browser name as 'edge'.
> All the necessary screenshots are present in the Screenshots folder.
> All the retrieved languages and levels are present in the 'TestFlowOutputs...' in the src/test/resources folder.
> The automation test report is present in the TestResult folder.