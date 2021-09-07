/// <reference types="cypress" />

describe('Comprando un producto', () => {
    
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    it('Realizar todos los pasos para poder comprar un producto', () => {

        // Buscamos un producto en concreto
        cy.get('#search_query_top')
            .type('Blouse')
        cy.get('#searchbox > .btn')
            .click()

        // Comprobamos que este producto aparece en la búsqeda y tiene el botón add to cart
        cy.get('.product-container:has(.product-name[title="Blouse"]) .ajax_add_to_cart_button')
            .click()

        // Comprobamos que se nos ha abierto el popup, ponemos un timeout dado que la página carga muy lenta
        // y Cypress no da tiempo a que el popup sea visible
        cy.get('#layer_cart', { timeout: 10000 })
            .should('be.visible')

        // Continuamos al checkout clicando el botón en el popup que se nos abre
        cy.get('.button-medium[title="Proceed to checkout"]')
            .click()

        // Verificamos que el producto se ha añadido correctamente al carrito y que tiene el precio esperado
        // Apartado 01. Summary
        cy.get('tr[id^=product]')
            .find('.product-name > a')
            .should('contain.text', 'Blouse')

        cy.get('tr[id^=product]')
            .find('.cart_unit > .price')
            .should('contain.text', '27.00')

        // Una vez esta todo ok damos click
        cy.get('.cart_navigation > .button')
            .click() 
            
        // Apartado 02. Sign In
        cy.get('#email').type('correo7@correo.com')
        cy.get('#passwd').type('12345')
        cy.get('#SubmitLogin').click()

        // Apartado 03. Address
        cy.get('.cart_navigation > .button')
            .click() 

        // Apartado 04. Shipping
        cy.get('#cgv')
            .check()
            .should('be.checked')

        cy.get('.cart_navigation > .button')
            .click() 

        // Apartado 05. Payment
        cy.get('.bankwire')
            .click()

        cy.get('.cart_navigation > .button')
            .click() 

        // Verificamos que se ha ejectuado la orden
        cy.get('.cheque-indent > .dark').should('contain.text', 'Your order on My Store is complete.')
    })
})