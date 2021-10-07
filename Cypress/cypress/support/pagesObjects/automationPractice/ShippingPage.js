class ShippingPage {

    getTermsOfServiceCheckbox() {
        return cy.get('#cgv')
    }

    getPreceedToCheckoutButton() {
        return cy.get('.cart_navigation > .button')
    }
}
export default ShippingPage