/// <reference types="cypress" />

import AddressPage from '../../support/pagesObjects/automationPractice/AddressPage'
import AuthenticationPage from '../../support/pagesObjects/automationPractice/AuthenticationPage'
import HomePage from '../../support/pagesObjects/automationPractice/HomePage'
import PaymentPage from '../../support/pagesObjects/automationPractice/PaymentPage'
import ShippingPage from '../../support/pagesObjects/automationPractice/ShippingPage'
import ShoppingCartSummaryPage from '../../support/pagesObjects/automationPractice/ShoppingCartSummaryPage'

describe('Comprando un producto', () => {

    // Instanciamos las clases de pageObjects
    const addressPage = new AddressPage()
    const authenticationPage = new AuthenticationPage()
    const homePage = new HomePage()
    const paymentPage = new PaymentPage()
    const shippingPage = new ShippingPage()
    const shoppingCartSummaryPage = new ShoppingCartSummaryPage()

    before(() => {
        cy.fixture('buyProduct').then(function(data) {

            // Para tener los datos en ámbito global
            this.data = data 
        })
    })
    
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    Cypress.config('defaultCommandTimeout', 15000)
    
    it('Realizar todos los pasos para poder comprar un producto', function() {

        let productName = this.data.product.name
        let productPrice = this.data.product.price
        let userEmail = this.data.user.email
        let userPass = this.data.user.pass

        // Buscamos un producto en concreto
        homePage.getSearchBoxInput()
            .type(productName)
        homePage.getSearchBoxButton()
            .click()

        // Añadimos el prodcuto al carrito
        homePage.getAddToCartElementButton(productName)
            .click()

        // Comprobamos que se nos ha abierto el popup, ponemos un timeout dado que la página carga muy lenta
        // y Cypress no da tiempo a que el popup sea visible
        cy.get('#layer_cart', { timeout: 10000 })
            .should('be.visible')

        // Continuamos al checkout clicando el botón en el popup que se nos abre
        homePage.getPreceedToCheckoutButton()
            .click()

        // Verificamos que el producto se ha añadido correctamente al carrito y que tiene el precio esperado
        shoppingCartSummaryPage.getProductNameText()
            .should('contain.text', productName)

        shoppingCartSummaryPage.getProdcutPriceText()
            .should('contain.text', productPrice)

        // Una vez esta todo ok damos click
        shoppingCartSummaryPage.getPreceedToCheckoutButton()
            .click() 
            
        // Apartado 02. Sign In
        authenticationPage.getEmailAdressInput()
            .type(userEmail)

        authenticationPage.getPasswordInput()
            .type(userPass)

        authenticationPage.getSubmitButton().click()

        // Apartado 03. Address
        addressPage.getPreceedToCheckoutButton()
            .click() 

        // Apartado 04. Shipping
        shippingPage.getTermsOfServiceCheckbox()
            .check()
            .should('be.checked')

        shippingPage.getPreceedToCheckoutButton()
            .click() 

        // Apartado 05. Payment
        paymentPage.getPayBankWireOptionButton()
            .click()

        paymentPage.getConfirmMyOrderButton()
            .click() 

        // Verificamos que se ha ejectuado la orden
        paymentPage.getDescritpionTitleText()
            .should('contain.text', 'Your order on My Store is complete.')
    })
})