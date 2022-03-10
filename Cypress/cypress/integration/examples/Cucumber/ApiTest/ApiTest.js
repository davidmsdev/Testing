import { Given, When, Then, And } from "cypress-cucumber-preprocessor/steps";

Given('El usuario se encuentra en la página de ejemplos de Cypress', () => {
    // ingresamos a la pagina	 
    cy.visit("https://example.cypress.io/commands/network-requests")
})

Then('verificamos el request de GET Comment', () => {
    
    // Escuchamos las peticiones GET a comments/1
    const endPoint = 'https://jsonplaceholder.cypress.io/'
    const comment = 'comments/1'
    const endPointComment = endPoint + comment

    // Antes de hacer click le decimos a Cypress que escuche, interceptamos y guardamos la resuesta con el as
    cy.intercept('GET', '**/comments/*').as('getComment')

    // Tenemos codigo que obtiene un comentario cuando se clickea en el botón
    cy.get('.network-btn').click()

    // Usamos un mecanismo de espera para espera que el getComment tenga la respuesta que queremos
    cy.wait('@getComment').its('response.statusCode').should('be.oneOf', [200, 304])

})

Then('verificamos el request de POST Comment', () => {
    
    cy.intercept('POST', '**/comments').as('postComment')

    // Código que publica un comentario cuando clickamos al boton
    cy.get('.network-post').click()

    cy.wait('@postComment').should(({request, response}) => {
        expect(request.body).to.include('email')
        expect(request.headers).to.have.property('content-type')
        expect(response && response.body).to.have.property('name', 'Using POST in cy.intercept()')
    })

})

Then('verificamos cambios de respuesta de BackEnd', () => {

    const message = 'Mensaje propio'
    
    // Cambiar la respuesta de PUT comments
    cy.intercept({
        method: 'PUT',
        url: '**/comments/*'
    },
    {
        statusCode: 404,
        body: {error: message},
        headers: { 'access-control-allow-origin': '*' },
        delayMs: 500,
    }).as('putComment')

    cy.get('.network-put').click()

    cy.wait('@putComment')

    // our 404 statusCode logic in scripts.js executed
    cy.get('.network-put-comment').should('contain', message)
})