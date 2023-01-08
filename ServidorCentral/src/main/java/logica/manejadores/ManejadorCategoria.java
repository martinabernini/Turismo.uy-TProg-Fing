package logica.manejadores;

import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.PaqueteActividades;
import logica.interfaces.IManejadorCategoria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ManejadorCategoria implements IManejadorCategoria {
    private static ManejadorCategoria instancia = null;
    private Map<String, Categoria> categorias = new HashMap<>();

    private ManejadorCategoria() {
    }

    public static ManejadorCategoria getInstance() {
        if (instancia == null) {
            instancia = new ManejadorCategoria();
        }
        return instancia;
    }

    @Override
    public Categoria find(String nombreCategoria) {
        if (categorias.containsKey(nombreCategoria)) {
            return categorias.get(nombreCategoria);
        }
        return null;
    }

    @Override
    public void add(Categoria categoria) {
        categorias.put(categoria.getNombre(), categoria);
    }

    @Override
    public void update(Categoria categoria) {
        categorias.put(categoria.getNombre(), categoria);
    }

    @Override
    public Categoria[] getAll() {
        List<Categoria> list = new ArrayList<>(categorias.values());
        if (list.size() > 0) {
            return list.toArray(new Categoria[0]);
        }
        return null;
    }

    @Override
    public Boolean contains(String nombreCategoria) {
        return categorias.containsKey(nombreCategoria);
    }

    @Override
    public ActividadTuristica[] getAllActividadesAsociadasACategoria(String nombreCategoria) {
        Categoria categoria = categorias.get(nombreCategoria);
        Collection<ActividadTuristica> actividadesCollection = categoria.getActividades().values();

        if (actividadesCollection.size() > 0) {
            return actividadesCollection.toArray(new ActividadTuristica[0]);
        }
        return null;
    }

    @Override
    public PaqueteActividades[] getAllPaquetesAsociadosACategoria(String nombreCategoria) {
        Categoria categoria = categorias.get(nombreCategoria);
        Collection<PaqueteActividades> paquetesCollection = categoria.getPaquetes().values();

        if (paquetesCollection.size() > 0) {
            return paquetesCollection.toArray(new PaqueteActividades[0]);
        }
        return null;
    }

    @Override
    public void removeAll() {
        categorias.clear();
    }
}
