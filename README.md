## IMS PROJECT

 An Inventory management system created using JAVA that is used to interact with a MySQL database.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
This project was built using the following:

MySQL Server - Used to create the DATABASE on the command line.
MySQL Workbench - A unified visual tool used to also create MySQL databases.
Eclipse-IDE - A JAVA integrated development environment, very useful for compiling JAVA code and uses a number of useful Java applications.
Maven - Build automation tool which allows you to download Java libraries and Maven plugins.
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
6. A message will appear on the screen with: "Customer created"
```


## Running the tests

Tests were carried out in Eclipse IDE using Junit and Junit Mockito.

### Unit Tests 


```
A unit test is one of the most important tests as it tests a small amount of code such as a single method to see it the expected output is returned.
The tests carried out were for (CRUD) CREATE, READ, UPDATE, DELETE funtionalities within each Data Access Object(DAO).

 	@Test
	public void testCreate() {
		final Customer created = new Customer(null, "chris", "perrins", "1234678901");
		assertEquals(created, DAO.create(created));
  }
  
   	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(null, "jordan", "harrison", "55372850"));
		assertEquals(expected, DAO.readAll());
	}
 
  @Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins", "1234678901");
		assertEquals(updated, DAO.update(updated));
	}
 
 	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
 
 Above we have the tests used on CRUD functionalities within the Customer DAO Class.


```


## Deployment

To deploy this you must have downloaded the softwares in the prerequisites.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **JT Lana** - https://github.com/jt1001

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

Thank you to the 22JanSoftware cohort team, my code was inspired by different individuals of the team and without them I wouldnt be able to understand alot of these concepts.
