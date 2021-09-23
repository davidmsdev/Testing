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
        let month = this.data.birth.month
        let year = this.data.birth.year
        let day = this.data.birth.day
        let subject = this.data.subject

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

        cy.get('#dateOfBirthInput')
            // Abrimos el calendario
            .click()

        // Selecionamos los datos de la fecha de nacimineto
        cy.get('.react-datepicker__month-select')
            .should('be.visible')
            .select(month)

        cy.get('.react-datepicker__year-select')
            .should('be.visible')
            .select(year)

        cy.get('.react-datepicker__day--0' + day)
            .should('be.visible')
            .click()

        // Verificamos que el value del elemento coincide con los datos enviados
        cy.get('#dateOfBirthInput')
            .should('contain.value', month.substring(0,3)) // Comparamos con las 3 primeras letras
            .should('contain.value', year)
            .should('contain.value', day)  
            
        // Seleccionamos la materia que queremos escoger para posteriormente darle click
        cy.get('.subjects-auto-complete__value-container')
            .type(subject)

        // Buscamos un id que contenga react-select para darle click
        cy.get('div[id^="react-select-"]')
            .click()

        // Comprobamos que se ha agregado correctamente
        cy.get('.subjects-auto-complete__multi-value')
            .should('contain.text', subject)

        
    })
    
})