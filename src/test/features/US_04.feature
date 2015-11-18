Feature: Permitir republicar una oferta ya vencida a los oferentes
Scenario: El oferente desea republicar una oferta ya vencida.
Given El oferente esta logueado en la aplicación
And la oferta a republicar ha vencido.
When El oferente elije republicar la oferta vencida
Then El sistema muestra la oferta 
And permite cambiar solo la nueva fecha tope.