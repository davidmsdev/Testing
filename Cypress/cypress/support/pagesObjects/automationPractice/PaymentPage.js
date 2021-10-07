class PaymentPage {

    getPayBankWireOptionButton() {
        return cy.get('.bankwire')
    }

    getConfirmMyOrderButton() {
        return cy.get('.cart_navigation > .button')
    }

    getDescritpionTitleText() {
        return cy.get('.cheque-indent > .dark')
    }
}
export default PaymentPage