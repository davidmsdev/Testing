/// <reference types="cypress" />

// Suite de casos de pruebas avanzados
describe('Trabajando con Data Drive Testing y Hooks', () => {

    // Hook para cargar solo una vez los datos del archivo data.json
    before(() => {
        cy.fixture('data').then(function(data) {
            // Para tener los datos en ámbito global
            this.data = data
            
        })
    })
    
    // Hook para ejecutarse una vez por cada caso de prueba
    beforeEach(() => {
        cy.visit('https://demoqa.com/automation-practice-form')
    })

    it('Rellenando el formulario a partir de data', function() {
        
        // Datos
        let name = this.data.name
        let surname = this.data.surname
        let email = this.data.email
        let gender = this.data.gender
        let phone = this.data.mobile

        cy.get('#firstName')
            .type(name)

        cy.get('#lastName')
            .type(surname)

        cy.get('#userEmail')
            .type(email)

        cy.get('input[name="gender"][value="'+ gender +'"]')
            // Aplicamos force: true dado que no encontraba el elemento a clickar porque tenia un elemento label por encima
            .check({force: true})
            .should('be.checked')

        cy.get('#userNumber')
            .type(phone)
    })
    
})