Feature: Mostrar solo las ofertas no vencidas a los candidatos
Scenario: Los candidatos necesitan ver las ofertas aun vigentes
Given Los candidatos acceden a la seccion de ofertas
When Los candidados analian las ofertas presentadas por el sistema
Then Los candiados observan solo aquellas ofertas no vencidas