// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

Cypress.Commands.add('addProduct', (productName) => {

    cy.get('div[class="product-thumb"]').as('productsContainer')
    cy.get('@productsContainer')
        .each( ($el, index) => {

            // Para cada elemento buscamos el nombre que queremos
            cy.get(':has(.caption) h4 a').eq(index).then( $product => {
                let product = $product.text()
                
                if (product.includes(productName)) {
                    cy.log('Se ha encontrado el elemento HTC Touch HD')

                    // Buscamos el botón para añadir del elemento en el que estamos
                    cy.get('@productsContainer').eq(index).find('button[onclick^="cart.add"]')
                        .click()
                }
            })
        })
})
