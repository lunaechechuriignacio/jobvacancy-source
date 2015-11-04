Feature: Como oferente quiero poder generar una nueva oferta a partir de una ya existente.

Scenario: El oferente  requiere que desde una oferta previa se genere una nueva. 
Given El oferente esta logueado en la aplicacion
And El oferente visita su listado de ofertas
When.El oferente elige una de sus ofertas
And El oferente clona una de sus historias
And El oferente actualiza su oferta
And El oferente salva los cambios
Then La oferta se guarda correctamente
