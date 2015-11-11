Feature: Permitir definir una fecha tope de validez de cada oferta a los oferentes
Scenario: El oferente necesita definir una publicacion con fecha tope de validez
Given El oferente esta logueado en la aplicacion
When El oferente crea una oferta con fecha tope de validez a futuro
Then La oferta estara visible hasta esa fecha