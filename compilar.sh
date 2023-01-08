#!/bin/env bash
main() {
  echo "----------------------------------------------"
  echo "-                turismoUy                   -"
  echo "----------------------------------------------"
  echo "- 1. Compilar todo                           -"
  echo "- 2. Compilar Servidor Central               -"
  echo "- 3. Compilar Servidor Web                   -"
  echo "- 4. Compilar Dispositivo Movil              -"
  echo "- 5. Salir                                   -"
  echo "----------------------------------------------"
  read -n 1 -p "Ingrese opcion: " mainmenuinput

  echo ""

  if [ "$mainmenuinput" = "1" ]; then
    compilarTodo
  elif [ "$mainmenuinput" = "2" ]; then
    compilarServidorCentral
  elif [ "$mainmenuinput" = "3" ]; then
    compilarServidorWeb
  elif [ "$mainmenuinput" = "4" ]; then
    compilarDispositivoMovil
  elif [ "$mainmenuinput" = "5" ]; then
    exit
  else
    clear
    mainmenu
  fi

  echo ""
  echo ""
  # wait for user input to exit
  read -n 1 -s -r -p "Presione cualquier tecla para continuar..."
}

compilarServidorCentral() {
  echo "Compilando Servidor Central..."
  cd ServidorCentral
  mvn clean package
  cd ..
  cp ServidorCentral/target/servidor-central-1.0-SNAPSHOT.jar ./servidor.jar
  cd ServidorCentral
  mvn clean
  cd ..
}

compilarServidorWeb() {
  echo "Compilando Servidor Web..."
  cd ServidorWeb
  mvn clean package
  cd ..
  cp ServidorWeb/target/ServidorWeb-1.0-SNAPSHOT.war ./web.war
  cd ServidorWeb
  mvn clean
  cd ..
}

compilarDispositivoMovil() {
  echo "Compilando Movil..."
  cd DispositivoMovil
  mvn clean package
  cd ..
  cp DispositivoMovil/target/DispositivoMovil-1.0-SNAPSHOT.war ./movil.war
  cd DispositivoMovil
  mvn clean
  cd ..
}

compilarTodo() {
  compilarServidorCentral
  compilarServidorWeb
  compilarDispositivoMovil
}

main
