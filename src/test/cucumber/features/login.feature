Feature: Login

  Scenario: Select the login dialog
    Given I am on the home page
    When I click on login
    Then I should see the login dialog

#
#  Scenario: Successful login as a user
#    Given I am on the home page
#    And I am on the login dialog
#    When I enter username "foo" and password "bar"
#    Then I am logged in
