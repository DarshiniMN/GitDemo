Feature: Validating Place APIs
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
    |name           |language |address|    
    |sherlock homes |English  |London |    
#    |friends  home  |French   |France |    
@DeletePlace
Scenario: Verify if DeletePlace functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is successful with status code 200
    And "status" in response body is "OK"       