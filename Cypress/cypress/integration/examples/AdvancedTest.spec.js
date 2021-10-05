/// <reference types="cypress" />

describe('Pruebas más avanzadas', () => {

    before(() => {
        cy.fixture('cart').then(function(data) {

            // Para tener los datos en ámbito global
            this.data = data 
        })
    })

    beforeEach( () => {
        // Accedemos a la página de compras
        cy.visit('https://demo.opencart.com/index.php')
    })

    it('Realizar compra de móviles basadas en su título', function() {

        // Móviles
        let products = this.data.mobiles
    
        cy.get('#menu ul a:contains(Phones & PDAs)')
            .click()

        // Obtenemos todos los elementos de la parrilla usando nuestro comando personalizado
        products.forEach( (product) => {
            cy.log('Agregando ' + product + ' al carrito...')
            cy.addProduct(product)
        })   
        
        cy.get('.btn-inverse')
            .click()

        // Verificamos que los productos estan correctamente en el carrito
        products.forEach( (product) => {
            cy.log('Verificando que ' + product + ' está en el carrito')
            cy.verifyProductInCart(product) 
        }) 
    })
})