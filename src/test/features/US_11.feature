Feature: Como administrador quiero saber la cantidad histórica total de ofertas publicadas.

Scenario: El administrador necesita ver la cantidad historica de ofertas y las vigentes.
Given El administrador se ha logueado en el sistema
When El administrador accede a las metricas
Then El sistema despliega en pantalla la cantidad histórica de ofertas y las vigentes.