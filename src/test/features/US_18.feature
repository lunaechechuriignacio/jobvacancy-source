Feature: Como usuario del sitio quiero saber la cantidad de ofertas vigentes.

Scenario: El usuario del sitio necesita ver la cantidad de ofertas vigentes.
Given El usuario se ha logueado en el sistema
When El usuario accede a las seccion de ofertas
Then El sistema despliega en pantalla la cantidad y las ofertas vigentes.

Scenario: El visitante del sitio necesita ver la cantidad de ofertas vigentes.
Given El visitante se ha logueado en el sistema
When El visitante accede a las seccion de ofertas
Then El visitante despliega en pantalla la cantidad y las ofertas vigentes.
