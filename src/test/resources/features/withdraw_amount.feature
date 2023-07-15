Feature: Withdraw Fixed Amount
  The "Withdraw Cash" menu contains several fixed amounts to speed up transactions for users.

  Scenario Outline: Withdraw fixed amount
    Given I choose to withdraw cash from an account by phone number "<phone>"
    And I have <balance> in my account
    When I choose to withdraw the fixed amount of <withdrawal>
    Then I should receive <received> cash
    And the balance of my account should be <remaining>

    Examples:
     | phone     | balance | withdrawal | received | remaining |
     | 770000000 | 500.0   | 100.0      | 100.0    | 400.0     |
     | 771111111 | 600.0   | 300.0      | 300.0    | 300.0     |
     | 772222222 | 700.0   | 200.0      | 200.0    | 500.0     |
