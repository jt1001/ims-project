CREATE database if not exists ims;
Use ims;

CREATE TABLE if not exists Customers(
Customer_ID INT NOT NULL AUTO_INCREMENT,
First_Name VARCHAR(255) NOT NULL,
Last_Name VARCHAR (255) NOT NULL,
Contact_Number VARCHAR (100) NOT NULL,
Email_Address VARCHAR(255) NOT NULL UNIQUE,
Address_Line_1 VARCHAR(300) NOT NULL,
Address_Line_2 VARCHAR(300) NOT NULL,
Postcode VARCHAR (50) NOT NULL,
City VARCHAR (255) NOT NULL,
Country VARCHAR(255) NOT NULL,
Primary Key(Customer_ID)
);

CREATE TABLE if not exists Products(
Product_ID INT NOT NULL AUTO_INCREMENT,
Product_Name VARCHAR(300) NOT NULL,
Description VARCHAR(300),
Product_price DECIMAL(6,2) NOT NULL,
Primary Key(Product_ID)
);

CREATE TABLE if not exists Orders(
Order_ID INT NOT NULL AUTO_INCREMENT,
Order_Date DATE NOT NULL,
Order_Status VARCHAR(255) NOT NULL,
Customer_ID INT NOT NULL,
Primary Key(Order_ID),
Foreign Key(Customer_ID) REFERENCES Customers(Customer_ID)
);

CREATE TABLE if not exists Ordered_Items(
Ordered_Item_ID INT NOT NULL AUTO_INCREMENT,
Product_ID INT NOT NULL,
Order_ID INT NOT NULL,
Primary Key(Ordered_Item_ID),
Foreign Key(Product_ID) REFERENCES Products(Product_ID),
Foreign key(Order_ID) REFERENCES Orders(Order_ID)
);
