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

    cy.log('AGREGAR PRODUCTOS EN EL CARRITO')

    cy.get('div[class="product-thumb"]').as('productsContainer')
    cy.get('@productsContainer')
        .each( ($el, index) => {

            // Para cada elemento buscamos el nombre que queremos
            cy.get(':has(.caption) h4 a').eq(index).then( $product => {
                let product = $product.text()
                
                if (product.includes(productName)) {
                
                    // Buscamos el botón para añadir del elemento en el que estamos
                    cy.get('@productsContainer').eq(index).find('button[onclick^="cart.add"]')
                        .click()

                    // Verificamos que se ha añadido al carrito correctamente a partir del mensaje de alerta
                    cy.get('.alert.alert-success.alert-dismissible')
                        .should('contain.text', productName)
                }
            })
        })
})

Cypress.Commands.add('verifyProductInCart', (productName) => {

    cy.log('VERIFICAR PRODUCTOS EN EL CARRITO')
    cy.log('Producto recibido' + productName)

    // Almacenamos la fila de los productos a partir del botón de eliminar
    cy.get('tr:has(button[onclick*="cart.remove"])').as('cartRow')
    cy.get('tr:has(button[onclick*="cart.remove"]) td[class="text-left"] a')
        .each(($el, index, $list) => {
            cy.get('td[class="text-left"] a')
                .eq(index)
                .then(($product) => {
                    let product = $product.text()
                    cy.log('Nombre del producto:' + product)
                    cy.get('@cartRow')
                        .should('contain.text', productName)
                })
        })
})
