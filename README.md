# api-test-automation
This is simple api-test-automation project created to test Airalo Partner API and validate the responses, using Rest Assured Framework (Java, Rest Assured, TestNG)

# Setup Instructions
- Clone the project https://github.com/divyakr05/api-test-automation
- Install Java on your system
- Open the project in any IDE of your choice, then open POM.xml and run the maven build that will download and install the required dependencies
- Run following command in the IDE terminal: mvn clean test
- Results will be available in terminal after the build is finished
  
# Folder Structure
![Screenshot 2024-10-21 120802](https://github.com/user-attachments/assets/79c7bcf7-9f96-4dcd-aac6-447c635c1251)

StatusCode.java --> file to store status codes and messages
FileReader.py --> file in the utilities directory. This file is to read the test data json files
ordersTestData.json --> file contains the payload for post operation
eSIMs_test.java --> file contains the tests for eSIMs API Endpoints
orders_test.java --> file contains the tests for orders API Endpoints

# Approach to implement the testcase:
- Created two packages (for eSIMs and orders) and each package contains a java class with different tests for corresponding API Endpoints
- Orders_test class has two tests, one for post an order and one for get orders
- eSIMs_test class have a test to get eSIMs list
- each test case verify status codes and responses

Note : The mentioned endpoint in the requirement document seems wrong (It points to eSIMs). So I created separate test cases for below scenarios:
1. Submit Order
2. Get Order List
3. Get eSIMs List
