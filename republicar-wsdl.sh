cd ServidorWeb/src/main/java

## clean folder webservices, for linux and windows
#rm -rf webservices

C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/ControladorActividad?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/ControladorSalida?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices  http://localhost:9128/webservices/ControladorPaquete?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/ControladorDepartamento?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/ControladorUsuario?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/CargaDatos?wsdl
C:/src/jaxws-ri/bin/wsimport.bat -keep -p webservices http://localhost:9128/webservices/ControladorImagenes?wsdl

cd webservices
rm -rf *.class




## remove al .class files in
#find . -name "*.class" -type f -delete

# wait for keypress
read -p "Press any key to continue... " -n1 -s