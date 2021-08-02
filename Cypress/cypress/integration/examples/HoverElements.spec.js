/// <reference types="cypress" />

describe('Probando el efecto HOVER en elementos de la web', () => {
    
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    it('Verificar que el dropdown de women, tenga los elementos necesarios', () => {

        // Cambiamos el style del elemento que se muestra cuando hacemos hover
        cy.get('#block_top_menu > ul > li:nth-child(1) > ul').invoke('attr', 'style', 'display: block');

        // Comprobamos que este submenu contiene los títulos correspondientes
        cy.get('a[title="Tops"]').should('be.visible');
        cy.get('a[title="T-shirts"]').should('be.visible');
        cy.get('a[title="Blouses"]').should('be.visible');
        cy.get('a[title="Dresses"]').should('be.visible');

        // Buscamos títulos que empiezan con la palabra usando ^
        cy.get('a[title^="Casual"]').should('be.visible');
        cy.get('a[title^="Evening"]').should('be.visible');
        cy.get('a[title^="Summer"]').should('be.visible');
    })
})