package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.entidades.PaqueteActividades;
import logica.interfaces.IManejadorPaqueteActividades;

public class ManejadorPaqueteActividades implements IManejadorPaqueteActividades {

    private Map<String, PaqueteActividades> paquetes;

    private static ManejadorPaqueteActividades instancia = null;

    private ManejadorPaqueteActividades() {
        paquetes = new HashMap<>();
    }

    public static ManejadorPaqueteActividades getInstance() {
        if (instancia == null)
            instancia = new ManejadorPaqueteActividades();

        return instancia;
    }

    // ---------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------

    @Override
    public void add(PaqueteActividades paquete) {
        paquetes.put(paquete.getNombre(), paquete);
    }

    @Override
    public PaqueteActividades[] getAll() {

        if (paquetes.isEmpty()) {
            return null;
        }

        return paquetes.values().toArray(new PaqueteActividades[0]);
    }
    @Override
    public PaqueteActividades find(String nombre) {
        return paquetes.get(nombre);
    }

    @Override
    public void update(PaqueteActividades paquete) {
        paquetes.put(paquete.getNombre(), paquete);
    }

    @Override
    public Boolean contains(String nombre) {
        return paquetes.containsKey(nombre);
    }

    @Override
    public void removeAll() {
        paquetes.clear();
    }
}
