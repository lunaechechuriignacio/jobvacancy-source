Feature: Permitir al oferente satisfacer una oferta y que no la vean los aplicantes
Scenario: El oferente satisface una oferta publicada
Given la oferta fue satisfecha por el oferente
When el oferente observa su lista de ofertas en MyOffers
Then la oferta X  se encuentra satisfecha
And la oferta X se encuentra visible en MyOffers
And la oferta X tiene la opcion Satisfied bloqueada en MyOffers

Scenario: Como aplicante quiero ver solo las ofertas no satisfechas
Given la oferta fue satisfecha por el oferente
When el aplicante accede al listado de ofertas en Offers
Then la oferta X no se encuentra disponible en el listado de ofertas disponibles