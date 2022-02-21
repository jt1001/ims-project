IMS Project


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
This project was built using the following:

MySQL Server - Used to create the DATABASE on the command line.
MySQL Workbench - A unified visual tool used to also create MySQL databases.
Eclipse-IDE - A JAVA integrated development environment, very useful for compiling JAVA code and uses a number of useful Java applications.
```

### Installing

A step by step series of examples that tell you how to get a development env running

```
1. Clone the repository: https://github.com/jt1001/ims-project

2. Open ims-project within Eclipse IDE

3. Open Runner class within: ims-project\src\main\java\com\qa\ims\Runner.class

4. Right Click -> Click 'Run As' -> Click '1 JAVA Application'
This will then start the application in the Console.

```

Test the functionality by creating a Customer:

```
1. Once the applciation is running by following the steps above enter: Customer
2. To create enter: create
3. You will then be prompted to enter a first name and press enter: e.g JT
4. You will then be prompted to enter a last name and press enter: e.g Lana
5. You will then be prompted to enter a phone number and press enter: e.g 987654321
6. A message will appear on the screen with: "Customer create"
```


## Running the tests

Tests were carried out in Eclipse IDE using Junit and Junit Mockito.

### Unit Tests 


```
A unit test is one of the most important tests as it tests a small amount of code such as a single method to see it the expected output is returned.
Instead of calling methods by methods, the following is used:
Stubs - replacement for method, instead of calling the real method we hard code what we expect the response to be. As long as the method is working correctly then our expected response will be correct.
Drivers - replacement for inputs, they 'drive' data or information into the tested method. This can be very beneficial for when testing methods from an external system.
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Integration testing is usually carried out once unit testing is complete or once the indivual components have are confirmed to be successful.
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
