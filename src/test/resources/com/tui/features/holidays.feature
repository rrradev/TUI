Feature: Holidays

  Scenario: The user can see holidays
    When the user navigates to Holidays from the top bar
    Then he can see a holiday listed:
      | name                 | destination                   | rating | type      | price  |
      | Quinta da Bela Vista | in Portugal, Madeira, Funchal | 5      | Frühstück | $2,574 |