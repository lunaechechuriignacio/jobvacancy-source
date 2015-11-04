Feature: Como candidato quiero poder adjuntar el link a mi CV al postularme.

Scenario: El usuario necesita adjuntar el link a su CV en la oferta seleccionada.
Given El usuario esta logueado
And El usuario aplica en una oferta
And El usuario ingresa nombre e email valido
When El usuario ingresa un link a su CV valido https://www.dropbox.com/s/5cmg2gr84g4fmhz/cv.pdf?dl=0
Then Se habilita la aplicación final a la oferta

Scenario: El usuario necesita adjuntar el link a su CV en la oferta seleccionada e ingresa un link inválido
Given El usuario esta logueado
And El usuario aplica en una oferta
And El usuario ingresa nombre e email válido
When El usuario ingresa un link a su CV inválido htls:\\//))dfadsf))){{{{{{{{.dropbox.com/cv.pdf
Then Se deshabilita la aplicación final a la oferta