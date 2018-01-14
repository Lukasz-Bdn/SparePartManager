# SparePartManager
Simple software to help with managing spare parts for data processing system in remote locations.

System can be very complex (thousands of disks, CPUs etc.). They can be placed in very remote locations (Arctic, Desert, Ocean etc.) with delivery time ranging from weeks to months. 

Goal of this project is to build a software that helps in managing spare parts in a remote location.

Main functionalities and features:

1. User and access management with Spring Security.
2. Two types of locations: global IT Hubs and remote Locations.
3. Two types of users: administrators with overall access rights and users attached to particular remote location.
4. Catalog of available manufacturers.
5. Catalog of available spare parts (with name, manufacturers and part number).
6. List of existing spare parts (with name, manufacturer and part number selected from catalog and unique serial number and location).
7. Functionality to ship part from global IT hub to remote location (admin only).
8. Functionality to receive part at remote location (admin and users attached to this location).
9. Functionality to insert part into system (admin and users attached to this location).
10. Functionality to remove part from the system and either return it to local storage or ship back to global IT Hub (admin and users attached to this location).
11. Functionalty to mark the arrival of part that was shipped to global IT Hub (admin only).
12. Shipment history with all active and archived shipments.

Technologies used:
1. Java EE.
2. Maven.
3. Git.
4. Trello.
5. Spring Core.
6. Spring Data.
7. Spring Security.
8. Hibernate.
9. JSP with JSTL and Spring Security Tag Library.
10. HTML, CSS, Java Script, Bootstrap.
