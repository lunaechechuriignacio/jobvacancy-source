Feature: Como candidato quiero asegurarme que al postularme debo poner un email valido.

Scenario: El usuario ingresa un email válido
Given El usuario ingresa su nombre
When El usuario ingresa mail martin@test.com.ar
And Se habilita la aplicación a la oferta
And El usuario aplica a la oferta
Then Se registra la aplicación del usuario correctamente 
And muestra pantalla de ofertas

Scenario: El usuario ingresa un email inválido
Given El usuario ingresa su nombre
When El usuario ingresa mail martin%&%@a
Then Se deshabilita la aplicación a la oferta 
And El sistema muestra el mensaje “Invalid email”
