"""
    Identificar elementos web:
        Links
        Botones
        Cajas de texto (Textbox)
        Menús desplegables (Combobox)
        Checkbox y Radiobutton
        Cajas de calendario (Datepicker)
"""
from selenium import webdriver

# Declaramos un navegador
driver = webdriver.Chrome("drivers\chromedriver.exe")

# Con get navegamos a una web
driver.get("https://es.wikipedia.org/")

# Extraemos el título de la página y lo mostramos por consola
title = driver.title
print(title)

# Buscar un elemento por su etiqueta
tag = driver.find_element_by_tag_name("title")
print(tag)

# Extraer texto de un elemento, sólo se pueden extraer aquellos textos que están renderizados
# Por ejemplo el texto de la etiqueta "title" no se puede extraer con .text
text = driver.find_element_by_id("Batalla_del_Puente_Milvio").text
print(text)

# Extraer el contenido de un etiqueta, con esto se puede acceder al texto de la etiqueta title
titleText = driver.find_element_by_tag_name("title").get_attribute("textContent")
print(titleText)

# Cerramos el navegador
driver.quit()
