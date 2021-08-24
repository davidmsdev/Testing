/// <reference types="cypress" />

describe('Probando los checkboxes', () => {
    
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    it('Verificar que funcionan los checkboxes', () => {

        // Nos dirigimos a la página de Dresses
        cy.get('.sf-menu > :nth-child(2) > .sf-with-ul')
            .click()

        // Clickamos en el checkbox de Casual Dresses
        cy.get('li[class="nomargin hiddable col-lg-6"]:has(a[href*="categories-casual"]) input')
            .check()
            .should('be.checked')

        // Comporbamos que los otros dos checkboxes no están activos
        cy.get('li[class="nomargin hiddable col-lg-6"]:has(a[href*="categories-evening"]) input')
            .should('not.be.checked')

        cy.get('li[class="nomargin hiddable col-lg-6"]:has(a[href*="categories-summer"]) input')
            .should('not.be.checked')
    })
})