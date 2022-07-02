Feature: Verify OMR Branch Address

  Scenario: Get User Logtoken From Login Endpoint
    Given User Add Header
    And User Add Basic Authentication For Login
    And User send "POST" Request for Login Endpoint
    Then User Verify the Status Code is 200
    Then User Verify the Response Body message of login "Login successfully"

  Scenario Outline: User should Create Address
    Given User should Add Headers of create address
    And User Should Give AddressDetails "<firstName>","<lastName>","<mobileNumber>","<apartment>","<state>","<city>","<country>","<zipcode>","<Address>"and"<AdressType>"
    And User Send "POST" for ADDADDRESS Endpoint
    Then User Verify the Status Code is 200
    Then User Verify the Response Body as message of create address "Address added successfully" and get address_id saved

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | chandan    | Kumar     | 8778517884 | akhsya    |    34 | 3378 |     102 |  804453 | Suitha  | Home         |

  Scenario Outline: User should Update Address
    Given User should Add Headers of update address
    And User Should Give "<addressID>","<firstName>","<lastName>","<mobileNumber>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<adressType>" and User Should Add Body
    And User Send "PUT" for UPDATEADDRESS Endpoint
    Then User Verify the Status Code is 200
    Then User Verify the Response Body message of update address as "Address updated successfully"

    Examples: 
      | addressID | firstName | lastName | mobileNumber | apartment | state | city | country | zipcode | address      | adressType |
      |      3881 | chandan   | Kumar    |   8978694555 | Sahasra   |    33 | 3378 |     101 |  533003 | Thorraipakum | Home       |

  Scenario: User should Get Address
    Given User Add Headers of get address
    And User Send "GET" for GETADDRESS Endpoint
    Then User Verify the Status Code is 200
    Then User Verify the Response Body message of get address as "OK"

  Scenario: User should Delete Address
    Given User Add Headers of delete address
    And User Should Add Body of delete address
    And User Send "DELETE" for DELETEADDRESS Endpoint
    Then User Verify the Status Code is 200
    Then User Verify the Response Body message of Delete Address as "Address deleted successfully"
