/// <reference types="cypress" />

// const { contains } = require("cypress/types/jquery");

// Suite de pruebas
describe('Primer conjunto de casos de prueba', () => {

    // Antes de cada caso de prueba visitamos la página
    beforeEach(() => {
        cy.visit('http://automationpractice.com/index.php');
    })

    // Caso 1
    it('Contar la cantidad de elementos en la sección "Popular" de la home', () => {

        // Obtener elemento #homefeatured .product-container como un parámetro
        cy.get('#homefeatured .product-container').as('PopularProducts');

        // Verificamos que hayan 7 elementos en la lista de populares usando nuestro parámetro
        cy.get('@PopularProducts').should('have.length', 7);
    });

    // Caso 2
    it('Agregar el elemento de tipo "Printed Dress" al carrito desde la home', () => {

        // Obtener los elementos #homefeatured .product-container como un parámetro
        cy.get('#homefeatured .product-container').as('PopularProducts');

        // Iteramos en cada elemento de la lista para encontrar el producto
        cy.get('@PopularProducts')
        .find('.product-name')
        .each(($el, index, $list) => {

            // Queremos añadir el producto que tenga un precio en concreto
            cy.get('@PopularProducts').eq(index).find('.price').then( $priceElement => {

                // Guardamos el precio 
                let price = $priceElement.text();

                // Mostramos por consola el precio
                cy.log(price);

                // Cada elemento tiene un atributo title que contiene el nombre del producto y el precio estipulado
                if($el.attr('title') === 'Printed Dress' && price.includes('26.00')) {
                
                    // Una vez lo encontramos mostramos un mensaje por consola
                    cy.log('Se ha encontrado el elemento buscado');
                    cy.log('Se ha encontrado el elemento con precio 26.00$');

                    // Comprobamos que en el elemento en el que estamos tenga el botón 'Add to cart'
                    // si lo tiene clicamos sobre el
                    cy.get('@PopularProducts').eq(index).contains('Add to cart').click();
                }
            }); 
        });
        
        // Comprobamos que una vez añadido el producto al carrito aparece el popup del mismo y contiene el texto
        cy.get('h2 >.ajax_cart_product_txt')
                    .should('contain.text', 'There is 1 item in your cart.')
                    .should('be.visible');
    });

    // Caso 3
});