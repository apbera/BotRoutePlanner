#Bot Route Planner

An Application that finds the fastest route for a bot from the given starting point to the product and 
from the product to the receiving station.

#Alghorithm description

The solution to finding the fastest route in this application is using the Dijkstra algorithm with an adjacency matrix.

1. Application receives paths to grid and job files as arguments.
2. Two given files are parsed and all data are stored by Parser Object.
3. Adjacency matrix is made from parsed data.
4. We are running the Dijkstra algorithm for every path from bot to product and from product to receiving station. The fastest route is saved and returned as the Result object. The fastest route is a sum of a route from bot to product, extraction of product, and route from product to receiving station.
5. Result is printed on standard output.

#Time complexity

We are using Dijkstra with an adjacency matrix therefore time complexity of Dijkstra is O(V^2), where V = XY.
This application has a time complexity of O(2*p*V^2) = O(2*p*(XY)^2), where p is the number of products with the same name.