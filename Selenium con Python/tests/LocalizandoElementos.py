from selenium import webdriver

# Declaramos un navegador
driver = webdriver.Chrome("drivers\chromedriver.exe")

# Con get navegamos a una web
driver.get("https://es.wikipedia.org/")

# Buscamos parte de un texto de un link, si hay varios devuelve sólo el primero
linkText = driver.find_element_by_partial_link_text("Página").text
print(linkText)

# Almacenamos todos los elementos anteriores en una lista y los mostramos uno por uno
linksText = driver.find_elements_by_partial_link_text("Página")
for element in linksText:
    print(element.text)

# Comprobar cuantas etiquetas <p> hay en la página
pList = driver.find_elements_by_tag_name("p")
print("Numero de etiquetas <p> en la página: " + str(len(pList)))

# Buscar elementos con XPath y clicamos sobre el
xpath = driver.find_element_by_xpath("//div[@id='main-tfa']/descendant::span[text()=' Leer ']").click()

# Buscar por CSS
css = driver.find_element_by_css_selector("div.mw-parser-output>div.main-wrapper>div.main-wrapper-column>div#main-tfa>h2>span").text
print(css)

# Cerramos el navegador
driver.quit()
