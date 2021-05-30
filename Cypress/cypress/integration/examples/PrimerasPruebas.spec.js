/// <reference types="cypress" />

// Suite de pruebas
describe('Primer conjunto de casos de prueba', () => {

    // Caso 1
    it('Contar la cantidad de elementos en la sección "Popular" de la home', () => {

        cy.visit('http://automationpractice.com/index.php');

        // Verificamos la cantidad de elementos visibles son 7
        cy.get('#homefeatured .product-container').should('have.length', 7);
    });

    // Caso 2

    // Caso 3
});