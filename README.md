# API-Cocumber-Automation-Project

## Client Testing CRUD Project

<img width="1012" alt="imagen" src="https://github.com/dmolina24/API-Cocumber-Automation-Project/assets/159375151/c36f7d3e-700f-4cba-af88-3f4ba09c271c">
<img width="883" alt="imagen" src="https://github.com/dmolina24/API-Cocumber-Automation-Project/assets/159375151/fe595602-47f1-4bd3-8ff2-4f97d924265a">

This project utilizes Cucumber and Java to perform CRUD operations on client resources. It includes scenarios for retrieving client and resource lists, creating new clients, and updating existing resources.

### Features

- **Client Testing CRUD**

### Scenarios

#### 1. Get the list of all clients
- **Given** there are registered clients in the system
- **When** I send a GET request to view all the clients
- **Then** the response should have a status code of 200
- **And** validates the response with client list JSON schema

#### 2. Get the list of all resources
- **Given** there are registered resources in the system
- **When** I send a GET request to view all the resources
- **Then** the response should have a status code of 200
- **And** validates the response with the resource list JSON schema

#### 3. Create a new client
- **Given** I have a client with the following details:
  | Name   | LastName   | Country     | City   | Email             | Phone          |
  | Maria  | Picapiedra | Nunca Jamas | Narnia | pedro@gmail.com   | 123-456-7890   |
- **When** I send a POST request to create a client
- **Then** the response should have a status code of 201
- **And** the response should include the details of the created client
- **And** validates the response with client JSON schema

#### 4. Update the last resource
- **Given** there are registered resources in the system
- **And** I retrieve the details of the latest resource
- **When** I send a PUT request to update the latest resource
- **Then** the response should have a status code of 200
- **And** the resource response should have the following details:
  | name    | trademark    | stock | price | description | tags   | active |
  | NewName | NewTradeMark | 1000  | 99.99 | description | NewTag | True   |
- **And** validates the response with the resource JSON schema

### Tags
- **@smoke**: Denotes scenarios that are part of smoke testing.

Happy testing! 🚀




 
