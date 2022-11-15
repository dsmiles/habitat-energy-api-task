Feature: Prices Service Health check

Scenario: Check the Prices API is live
  When I get service health
  Then I receive 200 status code in response
#  And Response returns status "UP"

