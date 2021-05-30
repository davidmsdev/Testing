/// <reference types="cypress" />

// Suite de pruebas
describe('Primer conjunto de casos de prueba', () => {

    // Caso 1
    it('Contar la cantidad de elementos en la sección "Popular" de la home', () => {

        cy.visit('http://automationpractice.com/index.php');

        // Obtener elemento #homefeatured .product-container como un parámetro
        cy.get('#homefeatured .product-container').as('PopularProducts');

        // Verificamos que hayan 7 elementos en la lista de populares usando nuestro parámetro
        cy.get('@PopularProducts').should('have.length', 7);
    });

    // Caso 2
    it('Agregar el elemento de tipo "blouse" al carrito desde la home', () => {

        cy.visit('http://automationpractice.com/index.php');

        // Obtener los elementos #homefeatured .product-container como un parámetro
        cy.get('#homefeatured .product-container').as('PopularProducts');

        // Iteramos en cada elemento de la lista para encontrar el producto
        cy.get('@PopularProducts')
        .find('.product-name')
        .each(($el, index, $list) => {
            // Cada elemento tiene un atributo title que contiene el nombre del producto
            if($el.attr('title') === 'Blouse') {
                
                // Una vez lo encontramos mostramos un mensaje por consola
                cy.log('Se ha encontrado el elemento buscado');

                // Comprobamos que en el elemento en el que estamos tenga el botón 'Add to cart'
                // si lo tiene clicamos sobre el
                cy.get('@PopularProducts').eq(index).contains('Add to cart').click();
            }
        });
    });

    // Caso 3
});