/// <reference types="cypress" />

// Suite de pruebas
describe('Primer conjunto de casos de prueba', () => {

    // Caso 1
    it('Visitar pagina principal de Automation Practice', () => {

        cy.visit('http://automationpractice.com/index.php');
        cy.get('.login').click();
    });

    // Caso 2

    // Caso 3
});