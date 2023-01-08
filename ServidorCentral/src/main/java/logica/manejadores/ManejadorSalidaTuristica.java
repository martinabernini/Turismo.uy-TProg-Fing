package logica.manejadores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.entidades.ActividadTuristica;
import logica.entidades.SalidaTuristica;
import logica.interfaces.IManejadorActividadTuristica;
import logica.interfaces.IManejadorSalidaTuristica;

public class ManejadorSalidaTuristica implements IManejadorSalidaTuristica {

    private static IManejadorActividadTuristica manejadorActividadTuristica;

    private Map<String, SalidaTuristica> salidas;

    private static ManejadorSalidaTuristica instancia = null;

    private ManejadorSalidaTuristica() {
        salidas = new HashMap<>();
    }

    public static ManejadorSalidaTuristica getInstance(IManejadorActividadTuristica manejadorActividadTuristica) {
        if (instancia == null) instancia = new ManejadorSalidaTuristica();

        ManejadorSalidaTuristica.manejadorActividadTuristica = manejadorActividadTuristica;

        return instancia;
    }

    // ---------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------

    @Override
    public void add(SalidaTuristica salida) {
        salidas.put(salida.getNombre(), salida);
    }

    @Override
    public void update(SalidaTuristica salida) {
        salidas.put(salida.getNombre(), salida);
    }

    @Override
    public SalidaTuristica[] getAllAsociadasAActividadTuristica(String nombreActividad) {
        if (!manejadorActividadTuristica.contains(nombreActividad)) {
            return null;
        }

        ActividadTuristica actividad = manejadorActividadTuristica.find(nombreActividad);

        List<SalidaTuristica> salidasList = new ArrayList<>(actividad.getSalidasAsociadas().values());

        if (salidasList.isEmpty()) {
            return null;
        }

        return salidasList.toArray(new SalidaTuristica[0]);
    }

    @Override
    public SalidaTuristica find(String nombre) {
        return salidas.get(nombre);
    }

    @Override
    public SalidaTuristica[] getAllVigentesAsociadasAActividad(String nombreActividad) {
        if (!manejadorActividadTuristica.contains(nombreActividad)) {
            return null;
        }

        ActividadTuristica actividad = manejadorActividadTuristica.find(nombreActividad);

        List<SalidaTuristica> salidasList = new ArrayList<>(actividad.getSalidasAsociadas().values());

        List<SalidaTuristica> salidasVigentesList = new ArrayList<>();

        for (SalidaTuristica salida : salidasList) {
            if (esVigenteSalida(salida)) salidasVigentesList.add(salida);
        }

        if (salidasVigentesList.isEmpty()) return null;

        return salidasVigentesList.toArray(new SalidaTuristica[0]);
    }

    @Override
    public Boolean contains(String nombreSalida) {
        return salidas.containsKey(nombreSalida);
    }

    @Override
    public void removeAll() {
        salidas.clear();
    }

    // -----------------------------------------------------------------------------------

    private Boolean esVigenteSalida(SalidaTuristica salida) {
        return salida.getFechaSalida().isAfter(LocalDateTime.now());
    }

}
