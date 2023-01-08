package logica.interfaces;

import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.PaqueteActividades;

public interface IManejadorCategoria {

    Categoria find(String nombreCategoria);

    void add(Categoria categoria);

    void update(Categoria categoria);

    Categoria[] getAll();

    Boolean contains(String nombreCategoria);

    ActividadTuristica[] getAllActividadesAsociadasACategoria(String nombreCategoria);

    PaqueteActividades[] getAllPaquetesAsociadosACategoria(String nombreCategoria);

    void removeAll();

}

