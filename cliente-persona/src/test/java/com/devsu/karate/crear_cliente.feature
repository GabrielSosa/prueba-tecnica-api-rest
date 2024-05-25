Feature: Crear un nuevo cliente

  Scenario: Crear un cliente con datos válidos
    Given url 'http://localhost:8080/cliente'
    And request
      """
      {
        "clienteId": 1234,
        "contrasena": "securePassword",
        "estado": true,
        "nombre": "John Doe",
        "genero": "Masculino",
        "edad": 35,
        "identificacion": "1234567890",
        "direccion": "123 Main Street",
        "telefono": "555-1212"
      }
      """
    When method post
    Then status 200
    And match response.clienteId == '#notnull'
