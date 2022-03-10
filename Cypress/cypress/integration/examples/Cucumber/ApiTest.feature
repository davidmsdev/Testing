Feature: Primer conjunto de casos de prueba de paginas de prueba
    Scenario: Implementando casos de backend testing
        Given El usuario se encuentra en la página de ejemplos de Cypress
        Then verificamos el request de GET Comment
        Then verificamos el request de POST Comment
        Then verificamos cambios de respuesta de BackEnd