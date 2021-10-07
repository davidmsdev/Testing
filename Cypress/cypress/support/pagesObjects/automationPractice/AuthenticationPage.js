class AuthenticationPage {

    getEmailAdressInput() {
        return cy.get('#email')
    }

    getPasswordInput() {
        return cy.get('#passwd')
    }

    getSubmitButton() {
        return cy.get('#SubmitLogin')
    }
}

export default AuthenticationPage