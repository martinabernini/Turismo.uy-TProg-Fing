# Tprog Tarea 3:

---


Instalar maven!!!!!


## Levantar el Servidor Central

- Desde el IDE, correr el archivo Main.java del ServidorCentral

U opcionalmente 

- Desde la terminal
``` bash
~tpgr22\ServidorCentral>  mvn clean package
~tpgr22\ServidorCentral>  java -jar .\target\servidor-central-1.0-SNAPSHOT.jar
```
Para cerrar el Servidor Central, presionar Ctrl+C en la terminal.

**¡Cerrar la ventana de swing no es suficiente!**

## Levantar el Servidor Web
- Desde la terminal:

``` bash
~tpgr22\ServidorWeb>  mvn clean package
~tpgr22\ServidorWeb>  mvn org.codehaus.cargo:cargo-maven2-plugin:run
```

Y queda levantado la página en http://localhost:8080/ServidorWeb/Home

Para cerrar el servidor web, presionar Ctrl+C en la terminal.

PARA LEVANTAR LO MOVIL!!

http://localhost:8080/DispositivoMovil/
---


Comandos wsimport:

``` bash
wsimport -keep -p webservices http://localhost:9128/webservices/ControladorActividad?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorSalida?wsdl

wsimport -keep -p webservices  http://localhost:9128/webservices/ControladorPaquete?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorDepartamento?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorUsuario?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorImagenes?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/CargaDatos?wsdl


```
