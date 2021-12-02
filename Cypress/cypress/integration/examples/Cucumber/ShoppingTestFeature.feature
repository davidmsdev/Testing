Feature: Primer conjunto de casos de prueba de paginas de prueba

    Feature esta siendo adaptado desde un formato POM hacia Cucumber

    Scenario: Crear compra desde cero
        Given el usuario se encuentra en la pagina de compra
        And busca un articulo llamado blusa
        When agrega una blusa al carrito
        Then el valor del articulo es de 27.00 dolares
        When finaliza la compra de los articulos
        Then el mensaje de orden completada deberia aparecer