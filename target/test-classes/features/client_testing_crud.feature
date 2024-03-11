@active
Feature: Client testing CRUD

    @smoke:
    Scenario: Get the list of all clients
        Given there are registered clients in the system
        When I send a GET request to view all the clients
        Then the response should have a status code of 200
        And validates the response with client list JSON schema

    @smoke
    Scenario: Get the list of all resources
        Given there are registered resources in the system
        When I send a GET request to view all the resources
        Then the response should have a status code of 200
        And validates the response with the resource list JSON schema

    @smoke
    Scenario: Create a new client
        Given I have a client with the following details:
            | Name   | LastName   | Country     | City   | Email             | Phone          |
            | Maria  | Picapiedra | Nunca Jamas | Narnia | pedro@gmail.com   | 123-456-7890   |
        When I send a POST request to create a client
        Then the response should have a status code of 201
        And the response should include the details of the created client
        And validates the response with client JSON schema

    @smoke
    Scenario: Update the last resource
        Given there are registered resources in the system
        And I retrieve the details of the latest resource
        When I send a PUT request to update the latest resource
        """
        {
            "name": "NewName",
            "trademark": "NewTradeMark",
            "stock": 1000,
            "price": 99.99,
            "description": "description",
            "tags": "NewTag",
            "active": True
        }
        """
        Then the response should have a status code of 200
        And the resource response should have the following details:
            | name    | trademark    | stock | price | description | tags   | active |
            | NewName | NewTradeMark | 1000  | 99.99 | description | NewTag | True   |
        And validates the response with the resource JSON schema

