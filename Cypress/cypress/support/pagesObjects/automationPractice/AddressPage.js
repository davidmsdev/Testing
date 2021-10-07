class AddressPage {

    getPreceedToCheckoutButton() {
        return cy.get('.cart_navigation > .button')
    }
}

export default AddressPage