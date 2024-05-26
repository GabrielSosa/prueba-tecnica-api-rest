Feature: User registration

  Background:
    * url api.baseUrl
    * def responseSuccessful = read("successful-response-get.json")
    * def responseError      = read("fail-response-get.json")

  Scenario: Register a new user successfully
    * def randomId = (Math.floor(Math.random() * 100000)).toString()
    Given path '/cliente'
    And request
      """
      {
        "contrasena": "jjhhh",
        "estado": true,
        "nombre": "omar Doe",
        "genero": "Masculino",
        "edad": 35,
        "identificacion": "#(randomId)",
        "direccion": "123 Main Street",
        "telefono": "555-1212"
      }
      """
    When method post
    Then status 200
    And match $ == responseSuccessful

  Scenario: Register a new user with missing fields
    * def randomId = (Math.floor(Math.random() * 100000)).toString()
    Given path '/cliente'
    And request
      """
      {
        "contrasena": "jjhhh",
        "estado": true,
        "nombre": "omar Doe"
      }
      """
    When method post
    Then status 500
    And match $ == responseError
