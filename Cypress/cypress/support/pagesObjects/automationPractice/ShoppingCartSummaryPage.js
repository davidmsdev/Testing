class ShoppingCartSummaryPage {

    // Elementos
    getProductNameText() {
        return cy.get('tr[id^=product]')
                .find('.product-name > a')
    }

    getProdcutPriceText() {
        return cy.get('tr[id^=product]')
                .find('.cart_unit > .price')
    }

    getPreceedToCheckoutButton() {
        return cy.get('.cart_navigation > .button')
    }
}

export default ShoppingCartSummaryPage