/// <reference types="cypress" />

// Suite de casos de pruebas avanzados
describe('Trabajando con Data Drive Testing y Hooks', () => {

    // Hook para cargar solo una vez los datos del archivo data.json
    before(() => {
        cy.fixture('data').then(function(data) {

            // Para tener los datos en ámbito global
            this.data = data 

            // Cargamos la imagen
            cy.fixture(this.data.image).as('image')
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
        let hobbies = this.data.hobbies
        let image = this.data.image
        let address = this.data.address
        let state = this.data.state
        let city = this.data.city

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

        // Seleecionando los hobbies, en este caso el primero del array
        cy.get('div[class="custom-control custom-checkbox custom-control-inline"]:has(label:contains("'+ hobbies[0] +'")) input')
            .check({force:true})
            .should("be.checked")

        cy.get('div[class="custom-control custom-checkbox custom-control-inline"]:has(label:contains("'+ hobbies[1] +'")) input')
            .check({force:true})
            .should("be.checked")

        // Subir la imagen
        cy.get('#uploadPicture').then( function ($el) {
            // Convertir image de base64 a blob
            const blob = Cypress.Blob.base64StringToBlob(this.image, 'image/png')

            // Aquí seleccionamos la imagen
            const file = new File([blob], image , { type: 'image/png' })
            const list = new DataTransfer()

            // Agregar a la lista nuestros archivos
            list.items.add(file)

            // Nuestra lsita de archivos
            const myFileList = list.files

            $el[0].files = myFileList
            $el[0].dispatchEvent(new Event('change', { bubbles: true }))
        })

        // Añadir dirección
        cy.get('#currentAddress')
            .type(address)

        // Seleccionar estado
        cy.get('#state')
            .click()
            .find('div:contains("' + state + '")[id*="react-select"]')
            .should('be.visible')
            .click()

        // Seleccionar ciudad
        cy.get('#city')
            .click()
            .find('div:contains("' + city + '")[id*="react-select"]')
            .should('be.visible')
            .click()

        // Submiteamos el form
        cy.get('#submit')
            .click()

        // Una vez rellenado todo el formulario aparece una pantalla resumen, vamos a comprobar que los datos son correctos
        cy.get('#example-modal-sizes-title-lg')
            .should('have.text', 'Thanks for submitting the form')

        // Encontramos el label y su td, una vez hecho esto queremos el hermano de este td que contiene nuestros datos
        cy.get('td:contains(Student Name) + td')
            .should('have.text', name + " " + surname)

        cy.get('td:contains(Student Email) + td')
            .should('have.text', email)

        cy.get('td:contains(Gender) + td')
            .should('have.text', gender)

        cy.get('td:contains(Mobile) + td')
            .should('have.text', phone)

        cy.get('td:contains(Date of Birth) + td')
            .should('have.text', day + " " + month + "," + year)

        cy.get('td:contains(Subjects) + td')
            .should('have.text', subject)

        cy.get('td:contains(Hobbies) + td')
            .should('have.text', hobbies[0] + ", " + hobbies[1])

        cy.get('td:contains(Picture) + td')
            .should('have.text', image)

        cy.get('td:contains(Address) + td')
            .should('have.text', address)

        cy.get('td:contains(State and City) + td')
            .should('have.text', state + " " + city) 
    })
    
})