# Programming-Exercise

## Short Demo
https://youtu.be/C0asz23C-3k (Time 1.14 minutes) 

## Task 1: Data Processing
Merge the two xml files into a single file and load the consolidated file into MySQL DB  
Task1.java has two methods: mergeXMLFiles() and loadXMLintoDB().

### Steps
1. Download the folder 'Task1'
2. Import into Eclipse IDE  
File -> Import -> General -> Projects from Folder or Archive
3. Add /lib/mysql-connector-java-5.1.45.jar in the build path
4. To run:  
Right-click Task1.java -> Run as -> Java Application 
5. Now a table named persondata will be created in MySQL DB and the data from persondata.xml(merged xml file) will be inserted into the table.

### Files
Input files: First.xml, Second.xml  
Output file: persondata.xml

### Technologies Used
Java DOM Parser, Transformer, JDBC

## Task 2: Web Application
Web application that accepts some keywords from the user for each db field and a corresponding action servlet that searches the persondata db table, and displays the matching record on to the user screen.

### Steps
1. Download the folder 'Task2'
2. Import into Eclipse IDE  
File -> Import -> General -> Projects from Folder or Archive
3. Add the following two jar files in the build path:  
(i) apache-tomcat/lib/servlet-api.jar  
(ii) /WebContent/WEB-INF/lib/mysql-connector-java-5.1.45.jar  
4. To run:  
Right-click project -> Run as -> Run on server -> Apache Tomcat 9.0

### Technologies Used  
MySQL, Servlet, SQL, AJAX, JQuery, JSP
