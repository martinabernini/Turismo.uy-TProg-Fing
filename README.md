# Tprog Tarea 3:

---

## Requerimientos antes de ejecutarlo

- Instalar maven.
- En windows, agregar una variable de sistema llamada "JAVA_TOOL_OPTIONS" con el valor "-Dfile.encoding=UTF8".

## Sobre los scripts que hay

- republicar-wsdl.sh -> vuelve a republicar los web services, para usarlo es necesario tener en la carpeta C:/ una carpeta src que tenga la carpeta jaxws-ri (descomprimir el jaxws-ri.zip)
- compilar.sh -> en linux genera los war y jar que se pida. (Lo que se pide en la entrega de la tarea 3)
- correr_Windows.sh -> un menu para correr el programa en windows rapido para poder testearlo y usarlo


## Levantar el Servidor Central

- Desde el IDE, correr el archivo Main.java del ServidorCentral

U opcionalmente 

- Desde la terminal
``` bash
~tpgr22\ServidorCentral>  mvn clean package
~tpgr22\ServidorCentral>  java -jar .\target\servidor-central-1.0-SNAPSHOT.jar
```

o

- Desde el script correr_Windows.sh

Para cerrar el Servidor Central, presionar Ctrl+C en la terminal.


## Levantar el Servidor Web

- Desde la terminal:

``` bash
~tpgr22\ServidorWeb>  mvn clean package
~tpgr22\ServidorWeb>  mvn org.codehaus.cargo:cargo-maven2-plugin:run
```

o

- Desde el script correr_Windows.sh (es necesario mantener corriendo el servidor central)

Y queda levantado la página en http://localhost:8080/ServidorWeb/Home

Para cerrar el servidor web, presionar Ctrl+C en la terminal.

## Levantar el Servidor Web

- Desde el script correr_Windows.sh (es necesario mantener corriendo el servidor central)

Y queda levantado la página en http://localhost:8080/DispositivoMovil/

---

## Comandos wsimport por si se quiere republicar a mano

``` bash
wsimport -keep -p webservices http://localhost:9128/webservices/ControladorActividad?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorSalida?wsdl

wsimport -keep -p webservices  http://localhost:9128/webservices/ControladorPaquete?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorDepartamento?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorUsuario?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/ControladorImagenes?wsdl

wsimport -keep -p webservices http://localhost:9128/webservices/CargaDatos?wsdl


```
