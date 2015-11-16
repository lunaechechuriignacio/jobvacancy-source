Feature: Como oferente quiero saber la cantidad de postulaciones de c/u de mis ofertas

Scenario: El visitante elije una oferta y decide aplicar en ella.
Given Un visitante autenticado en la aplicación revisa la lista de ofertas
When El visitante aplica en una oferta creada
Then Se incrementa en uno la cantidad de postulaciones de dicha oferta.

Scenario: El oferente ve la lista de sus ofertas y quiere ver el detalle de una.
Given Un oferente autenticado en la aplicación.
When El oferente ve el detalle de alguna de sus ofertas        
Then Se observa la cantidad de postulaciones recibidas en esa oferta
