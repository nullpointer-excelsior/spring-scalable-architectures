# Gateway implementation

## Problemas 

configuracion de telemetria
- bad request 409 al enviar telemetria solucion: configurar BraveOtlpConfig con bean de encoder
- span y trace id no se enviaban: solucion agregar `spring.reactor.context-propagation=AUTO`