# Spring Boot Project Expense Tracker.

- Frameworks and Language used :

    ![SpringBoot](https://img.shields.io/badge/Framework-SpringBoot-green) ![Java](https://img.shields.io/badge/Language-Java-yellow)
    
    ![SwaggerUi](https://img.shields.io/badge/SwaggerUi-http%3A%2F%2F13.49.183.202%3A8080%2Fswagger--ui%2Findex.html%23-brightgreen)
    
- Data Flow :
  1. Controller
      <br/>
      > User
      - signup : This method is used to call the signup method of the service class to save/register the user data into the database.
      - signin : This method is used to call the signin method of the service class which authenticate user based on their email and password.
      - updateUserById : This method is used to call the updateUserById method of the service class to update user data into the database.
      - getUserById : This method is used to call the getUserById method of the service class to get user data from the database.
      - deleteUserById : This method is used to call the deleteUserById method of the service class to delete user data from the database.
       
       <br/>
	
      > Product
      - saveProduct : This method is used to call the saveProduct method of the service class to save product data into the database.
      - updateProductById : This method is used to call the updateProductById method of the service class to update product data into the database.
      - getProductById : This method is used to call the getProductById method of the service class to get product data from the database.
      - deleteProductById : This method is used to call the deleteProductById method of the service class to delete product data from the database.
      - getTotalExpenseByMonth : This method is used to call the getTotalExpenseByMonth method of the service class to get total expense based on selected month..
      - getAllProductsByDate : This method is used to call the getAllProductsByDate method of the service class to get the list of product data from the database based on selected date.
      - generateReportByMonth : This method is used to call the generateReportByMonth method of the service class to generate report of expense from the database based on selected month.
       
  2. Services
      <br/>
      > Patient
      - signup : This method is used to call the save method of the repository class to save the user data into the database.
      - signin :  This method is also used to call the getToken method of the authentication service class to authenticate user based on their email and password.
      - updateUserById :  This method is used to call the save method of the repository class to update user data into the database.
      - getUserById :  This method is used to call the findById method of the repository class to get user data from the database.
      - deleteUserById :  This method is used to call the deleteById method of the repository class to delete user data from the database.
     
       <br/>
       
      > Product
      - saveProduct : This method is used to call the save method of the repository class to save the user data into the database.
      - updateProductById :  This method is used to call the save method of the repository class to update product data into the database.
      - getProductById :  This method is used to call the findById method of the repository class to get product data from the database.
      - deleteProductById :  This method is used to call the deleteById method of the repository class to delete product data from the database.
      - getTotalExpenseByMonth :  This method is used to call the findAllProductsByMonth method of the repository class to get list of product data from the database then sum the price of each product and return the value.
      - getAllProductsByDate :  This method is used to call the findAllByDate method of the repository class to get the list of product data from the database based on selected date.
      - generateReportByMonth :  This method is used to call the findAllProductsByMonth method of the repository class to generate the expense report based on the data available on the database.
      
       <br/>
       
      > Authentication
      - saveToken : This method is used to call the save method of the repository class to save the authentication data into the database.
      - getToken :  This method is also used to call the findByUser method of the repository class to get the token data from the database based on signin user.
      - authenticate : This method is used to call the findFirstByToken method of the repository class to get the token data from the database based on selected token to authenticate user.
      - getUserByToken : This method is used to call the findFirstByToken method of the repository class to get the user data from the database based on selected token.
      
  3. Repository
      - Used `Predefined` JpaRepository methods such as findById , save , deleteById for basic CRUD operations.
      
      <br/>
      
      > User
      - findFirstByEmail `Userdefined` : This method is used to get the user data from the database based on user email.
       
      <br/>
      
      > Product
      - findAllProductsByMonth `Userdefined` : This method is used to get the list of product data from the database based on selected month.
      - findAllByDate `Userdefined` : This method is used to get the list of product data from the database based on selected date.
         
      <br/>
      
      > Authentication
      - findByUser `Userdefined` : This method is used to get the authentication data from the database based on user.
      - findFirstByToken `Userdefined` : This method is used to get the token data from the database based on token.
      
  4. Database Design
  
      ![MySql](https://img.shields.io/badge/DBMS-MYSQL-lightblue)
      ```
	 	table user (
       	 id bigint not null auto_increment,
	        email varchar(255) not null,
	        name varchar(255) not null,
	        password varchar(255) not null,
	        primary key (id)
    	)
      
      	table product (
       	id bigint not null auto_increment,
	        date date not null,
	        description varchar(255) not null,
	        price float(53) not null,
	        time time not null,
	        title varchar(255) not null,
	        user_id bigint,
	        primary key (id)
    	)
      
       	table authentication (
       	id bigint not null auto_increment,
        	created_date datetime(6) not null,
        	name varchar(255) not null,
        	user_id bigint not null,
        	primary key (id)
    	)
      ```
   
- Data Structure used in project :
  - List
  - Map

- Project Summary :
```
  This is a Spring Boot project of Expense Tracker App. User can register themselves by filling the required information.
  Upon registration basic validation applied to the filled data if all the validation passes then and only then a data is registered into the system.
  Once registered, User can able to signin into the app, can able fetch their saved information. User can create a list of products by filling the required
  information to track their expense. User can fetch the expense list based on date and month , User can also be able to generate a monthly report of expense.
  At last user can also unregister themselves or update the save data from/to the App if they want to.
```
