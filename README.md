Coverage: 68.5%
# Inventory Management System

An Command Line app that allows a user to Create, Read, Update and Delete from Customers, Items and Orders in the attached database, schema can be found in [src\main\resources](src\main\resources) folder.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
1.  Firstly, clone down the repository by clicking `Code` then copy the link for your preferred method of cloning: ![](Documentation\Screenshots\GitClone.png)
2. You will need java installed to allow you to run `.jar` files, see [How do I Install Java](https://www.java.com/en/download/help/download_options.html)

3. After the `fatjar` is downloaded, you will need to download and install [MySQL Workbench](https://www.mysql.com/products/workbench/)
4. After MySQL Workbench is installed, you will need to add it to your Environment Variables if you are on Windows:
    - In Windows search for:
    
     ![Windows Search Environment Variables](Documentation\Screenshots\EnvironmentVariables.png)
    - Then click on `Environment Variables...` ![](Documentation\Screenshots\EnvironmentVariablesPart2.png)
    - Next Create a `New` System Variable: ![](Documentation\Screenshots\EnvironmentVariablesPart3.png)
    - Now add the `MYSQL_HOME` System Variable to your `Path` by selecting path then clicking `Edit`: ![](Documentation\Screenshots\EnvironmentVariablesPart4.png)
    - Close all dialog boxes by clicking `OK` recursively.


### Installing
1. Now the setup is done, open up MySQL and sign in using the password you would have created earlier.
2. Navigate to the [src\main\resources](src\main\resources) folder and open the [sql-schema](src\main\resources\sql-schema.sql) schema file inside MySQL Workbench then Execute the query.
3. Your database is now all set up to be used with the jar file. Now open your preffered terminal and navigate to the folder where you cloned the repository.
4. Once there, execute the command 
```
java -jar ims-0.0.1-jar-with-dependencies
```
5. You should now be met with:
```
Welcome to the Inventory Management System!
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

## Demo
Let's Create a new Customer into the database.

- First, let's select `customer` and check it is empty:
```
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
customer
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
read

What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```
- Now we are going to create a customer called *Foo Bar*:
```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
create
Please enter a first name
Foo
Please enter a surname
Bar
Customer created

Customer ID: 1 First Name: Foo Surname: Bar
```
- We have now added a new Customer to the database.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

Git was used for Version Control.
GitHub used as the online repository hosting service.

## Authors

* **Jordan Benbelaid** - *Start Point* - [jordanbenbelaid](https://github.com/jordanbenbelaid/IMS-22EnableMay2)
* **Cameron Black** - *Continued Development* - [cam-black](https://github.com/cam-black/ims)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 