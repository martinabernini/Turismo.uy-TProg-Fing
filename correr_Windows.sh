#!/bin/env bash
main() {
  # while true read an option
  while true; do
    echo "----------------------------------------------"
    echo "-                turismoUy                   -"
    echo "----------------------------------------------"
    echo "- 1. Correr Servidor Central                 -"
    echo "- 2. Correr Servidor Web                     -"
    echo "- 3. Correr Dispositivo Movil                -"
    echo "- 4. Salir                                   -"
    echo "----------------------------------------------"
    read -n 1 -p "Ingrese opcion: " mainmenuinput
    echo ""
    case $mainmenuinput in
      1) correrCentral;;
      2) correrWeb;;
      3) correrMovil;;
      4) exit;;
      *) clear; mainmenu;;
    esac
    echo ""
    echo ""
    # wait for user input to exit
    read -n 1 -s -r -p "Presione cualquier tecla para continuar..."
  done
}

correrCentral() {
  echo "Corriendo Servidor Central..."
  cd ServidorCentral
  mvn clean package
  cd ..
  java -jar ServidorCentral/target/servidor-central-1.0-SNAPSHOT.jar
}

correrWeb() {
  echo "Corriendo Servidor Web..."
  cd ServidorWeb
  mvn clean package
  mvn org.codehaus.cargo:cargo-maven2-plugin:run
  cd ..
}

correrMovil() {
  echo "Corriendo Dispositivo Movil..."
  cd DispositivoMovil
  mvn clean package
  mvn org.codehaus.cargo:cargo-maven2-plugin:run
  cd ..
}

main
