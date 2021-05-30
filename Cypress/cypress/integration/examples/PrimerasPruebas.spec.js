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

    // Caso 3
});