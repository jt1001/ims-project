CREATE database if not exists ims;
Use ims;

CREATE TABLE if not exists Customers(
Customer_ID INT NOT NULL AUTO_INCREMENT,
First_Name VARCHAR(255) NOT NULL,
Last_Name VARCHAR (255) NOT NULL,
Contact_Number VARCHAR (100) NOT NULL,
Primary Key(Customer_ID)
);

CREATE TABLE if not exists Items(
Item_ID INT NOT NULL AUTO_INCREMENT,
Item_Name VARCHAR(300) NOT NULL,
Item_price DECIMAL(6,2) NOT NULL,
Primary Key(Item_ID)
);

CREATE TABLE if not exists Orders(
Order_ID INT NOT NULL AUTO_INCREMENT,
Customer_ID INT NOT NULL,
Primary Key(Order_ID),
Foreign Key(Customer_ID) REFERENCES Customers(Customer_ID)
);

CREATE TABLE if not exists Ordered_Items(
Ordered_Item_ID INT NOT NULL AUTO_INCREMENT,
Order_ID INT NOT NULL,
Item_ID INT NOT NULL,
Primary Key(Ordered_Item_ID),
Foreign Key(Item_ID) REFERENCES Items(Item_ID),
Foreign key(Order_ID) REFERENCES Orders(Order_ID)
);
