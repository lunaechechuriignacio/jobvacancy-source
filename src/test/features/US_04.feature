Feature: Permitir republicar una oferta ya vencida a los oferentes
Scenario: El oferente republica una oferta  vencida modificando la fecha de validez
Given la oferta x finalizo su periodo de validez
When el oferente republica la oferta con una nueva fecha de validez
Then la oferta X  se encuentra disponible nuevamente

Scenario: El oferente republica una oferta vencida modificanco la fecha de validez y su contenido
Given la oferta x finalizo su periodo de validez
When el oferente republica la oferta con una nueva fecha de validez 
And modificando los datos de la publicacion
Then la oferta X  se encuentra disponible nuevamente

Scenario: El oferente no puede republicar una oferta  no vencida
Given la oferta x no finalizo su periodo de validez
When el oferente quiere republica la oferta con una nueva fecha de validez
Then la oferta X  no puede ser republicada

