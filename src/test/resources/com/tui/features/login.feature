@login
Feature: Login

  Scenario: The user can log in successfully
    When the user logs in with credentials:
      | username | password     | dateOfBirth |
      | adam.5   | secret_sauce | 1994-12-31  |
    Then he should see the top navigation bar

  Scenario Outline: The user can see correct errors for required fields
    When the user logs in with credentials:
      | username   | password   | dateOfBirth   |
      | <username> | <password> | <dateOfBirth> |
    Then he should see the <errorFieldNames> error fields with message "Required"

    Examples:
      | username | password     | dateOfBirth | errorFieldNames                   |
      | [empty]  | [empty]      | [empty]     | Username, Password, Date of birth |
      | [empty]  | [empty]      | 2000-10-10  | Username, Password                |
      | [empty]  | secret_sauce | 2000-10-10  | Username                          |
      | jake.5   | [empty]      | 2000-10-10  | Password                          |
#    and so on



