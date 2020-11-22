# Bank
A demo web application for bank admin builtt using spring boot and MySQL.

##Possible functions by admin:
1. Create account savings or current for a customer.
2. Search account.
3. List all accounts.
4. Deposit amount.
5. Withdraw amount.
6. Transfer funds to other bank accounts.


#Tech Stack used:
1. Spring Boot
2. Spring Security
3. HTML 5
4. CSS 3
5. MySQL

Java version - 1.8

#Requirements:
1. Spring boot tomcat server
2. MySQL local server.

#Database setup steps:

1. Start MySQL server
2. run create database bankDb;
3. use bankDb;
Optional: If DB connection fails please enter local SQL password in application.properties file

#Build apps:
mvn clean install

##Start the jar: java -jar bank-0.0.1-SNAPSHOT.jar from the target folder.

#Running the application:

Browser visit localhost:8080

##Username: admin
##Password: secret.
