/// <reference types="cypress" />

describe('Pruebas con números', () => {

    before(() => {
        cy.fixture('cart').then(function(data) {

            // Para tener los datos en ámbito global
            this.data = data 
        })
    })

    beforeEach( () => {
        // Accedemos a la página de compras
        cy.visit(Cypress.env('url') + '/index.php')
    })

    it('Verificación de la suma de los productos del drop down de carrito de compras', function() {
        
        // Móviles
        let products = this.data.mobiles
        let sum = 0
    
        cy.get('#menu ul a:contains(Phones & PDAs)')
            .click()

        products.forEach( (product) => {
            cy.addProduct(product)
        })   
        
        cy.get('.btn-inverse')
            .click()

        products.forEach( (product) => {
            cy.log('Verificando que ' + product + ' está en el carrito')
            cy.verifyProductInCart(product) 
        }) 

        // Obtenemos una lista con todos los elementos del carrito que contienen un texto con $
        cy.get('tr:has(button) td:contains("$")')
            .each( $el => {
                const priceText = $el.text()
                // Quitamos del texto el símbolo del $ para que nos quede solamente el número
                let price = priceText.replace('$', '')
                price = Number(price)
                sum = sum + price
                cy.log('La suma es: ' + sum)
            })

        // Obtenemos el valor total del carrito
        cy.get('.table.table-bordered :nth-child(4) :contains("$")')
            .then( $el => {
                const totalPriceText = $el.text()
                const totalPrice = totalPriceText.replace('$', '')
                // Hacemos la comparación
                expect(Number(totalPrice)).to.equal(sum)
            })
    })
})