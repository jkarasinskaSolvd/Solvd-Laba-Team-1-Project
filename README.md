# Solvd-Laba-Team-1-Projec
Optimising logistics for order fulfilment:

Warehouses: id, address, list of available products, allowed transport.

Products: id, name, price, volume m3.

Transport: id, type( track, train, airplane), max capacity m3, cost per km.

Logistics companies: id, name, list of available transport vehicles.

Order item: id (products), quantity.

Order: id, list of order item, delivery address

Address: id, country, city, street, postal code

Basic Steps:
1. Create classes for warehouses, orders, goods, transport and logistics companies.
2. Implement an algorithm for selecting the optimal transport for order delivery:
   -find warehouses with the appropriate goods;
   -calculate the volume of orders;
   -select the most suitable transport to fulfil the order, taking into account cost and availability

