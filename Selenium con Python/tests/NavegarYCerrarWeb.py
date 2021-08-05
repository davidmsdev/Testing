"""
    Test:
        1 - Abrir navegador
        2 - Ir a Wikipedia
        3 - Extraer el título y mostrarlo por consola
        4 - Cerrar el navegador
"""
from selenium import webdriver

# Declaramos un navegador
driver = webdriver.Chrome("drivers\chromedriver.exe")

# Con get navegamos a una web
driver.get("https://www.wikipedia.org")

# Extraemos el título de la página y lo mostramos por consola
title = driver.title
print(title)

# Cerramos el navegador
driver.quit()

