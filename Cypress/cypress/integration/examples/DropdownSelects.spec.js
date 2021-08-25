/// <reference types="cypress" />

describe('Probando los DropDwon menus y los Selects', () => {
    
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    it('Verificar que funciona el dropdown de "sort by"', () => {

        // Nos dirigimos a la página de Dresses
        cy.get('.sf-menu > :nth-child(2) > .sf-with-ul')
            .click()

        // Seleccionamos el valor In stock de la lista de selects
        cy.get('#selectProductSort')
            .select('In stock')
            // Y comprobamos que está seleccionado
            .should('have.value', 'quantity:desc')
    })
})