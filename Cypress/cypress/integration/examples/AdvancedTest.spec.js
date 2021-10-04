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
        let htc = this.data.mobiles[0]
        let iPhone = this.data.mobiles[1]
        let palmTreo = this.data.mobiles[2]
        

        cy.log(htc)

        cy.get('#menu ul a:contains(Phones & PDAs)')
            .click()

        // Obtenemos todos los elementos de la parrilla
        cy.get('div[class="product-thumb"]').as('productsContainer')
        cy.get('@productsContainer')
            .each( ($el, index) => {

                // Para cada elemento buscamos el nombre que queremos
                cy.get(':has(.caption) h4 a').eq(index).then( $product => {
                    let product = $product.text()
                    
                    if (product.includes(htc)) {
                        cy.log('Se ha encontrado el elemento HTC Touch HD')

                        // Buscamos el botón para añadir del elemento en el que estamos
                        cy.get('@productsContainer').eq(index).find('button[onclick^="cart.add"]')
                            .click()
                    }
                })

                cy.get(':has(.caption) h4 a').eq(index).then( $product => {
                    let product = $product.text()
                    
                    if (product.includes(iPhone)) {
                        cy.log('Se ha encontrado el elemento HTC Touch HD')

                        // Buscamos el botón para añadir del elemento en el que estamos
                        cy.get('@productsContainer').eq(index).find('button[onclick^="cart.add"]')
                            .click()
                    }
                })

                cy.get(':has(.caption) h4 a').eq(index).then( $product => {
                    let product = $product.text()
                    
                    if (product.includes(palmTreo)) {
                        cy.log('Se ha encontrado el elemento HTC Touch HD')

                        // Buscamos el botón para añadir del elemento en el que estamos
                        cy.get('@productsContainer').eq(index).find('button[onclick^="cart.add"]')
                            .click()
                    }
                })
            })
    })
})