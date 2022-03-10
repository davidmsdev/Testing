import { Given, When, Then, And } from "cypress-cucumber-preprocessor/steps";
// import AddressPage from '../../support/pageObjects/automationPractice/AddressPage'
// import AuthenticationPage from '../../../support/pageObjects/automationPractice/AuthenticationPage'
// import HomePage from '../../../support/pageObjects/automationPractice/HomePage'
// import PaymentPage from '../../../support/pageObjects/automationPractice/PaymentPage'
// import ShippingPage from '../../../support/pageObjects/automationPractice/ShippingPage'
// import ShoppingSummaryCartPage from '../../../support/pageObjects/automationPractice/ShoppingCartSummaryPage'
import HomePage from '../../../../support/pagesObjects/automationPractice/HomePage'
import AddressPage from '../../../../support/pagesObjects/automationPractice/AddressPage'
import AuthenticationPage from '../../../../support/pagesObjects/automationPractice/AuthenticationPage'
import PaymentPage from '../../../../support/pagesObjects/automationPractice/PaymentPage'
import ShippingPage from '../../../../support/pagesObjects/automationPractice/ShippingPage'
import ShoppingCartSummaryPage from '../../../../support/pagesObjects/automationPractice/ShoppingCartSummaryPage'
//import ShoppingSummaryCartPage from '../../../../support/pagesObjects/automationPractice/ShoppingSummaryCartPage'
 
const homePage = new HomePage()
const shoppingSummaryCartPage = new ShoppingCartSummaryPage()
const authenticationPage = new AuthenticationPage()
const addressPage = new AddressPage()
const shippingPage = new ShippingPage()
const paymentPage = new PaymentPage()
 
Given('el usuario se encuentra en la pagina de compra', () => {
    // ingresamos a la pagina	 
    cy.visit("http://automationpractice.com/index.php")
})
 
And('busca un articulo llamado blusa', () => {
    homePage.getSearchBoxInput().type('Blouse')
    homePage.getSearchBoxButton().click()
})
 
And('agrega una blusa al carrito', () => {
    homePage.getAddToCardElementButton("Blouse").click()
    homePage.getProceedToCheckoutButton().click()
})
 
Then('el valor del articulo es de 27.00 dolares', () => {
    shoppingSummaryCartPage.getProductNameText().should('contain.text', 'Blouse')
    shoppingSummaryCartPage.getProductPriceText().should('contain.text', '27.00')
})

When('finaliza la compra de los articulos', () => {
    shoppingSummaryCartPage.getProceedToCheckoutButton().click()
 
    authenticationPage.getEmailAddressInput().type('cypress@ateneaconocimientos.net')
    authenticationPage.getPasswordInput().type('Atenea')
    authenticationPage.getSignInButton().click()
 
    addressPage.getProceedToCheckoutButton().click()
 
    shippingPage.getTermsOfServiceCheckbox().check().should('be.checked')
    shippingPage.getProceedToCheckoutButton().click()
 
    paymentPage.getPayByBankWireOptionButton().click()
    paymentPage.getIConfirmMyOrderButton().click()
})