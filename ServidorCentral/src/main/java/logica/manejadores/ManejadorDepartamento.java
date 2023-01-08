package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.interfaces.IManejadorDepartamento;

public class ManejadorDepartamento implements IManejadorDepartamento {

    private Map<String, Departamento> departamentos;

    private static ManejadorDepartamento instancia = null;

    private ManejadorDepartamento() {
        departamentos = new HashMap<String, Departamento>();
    }

    public static ManejadorDepartamento getInstance() {
        if (instancia == null)
            instancia = new ManejadorDepartamento();

        return instancia;
    }

    // ---------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------

    @Override
    public void add(Departamento departamento) {
        departamentos.put(departamento.getNombre(), departamento);
    }

    @Override
    public void update(Departamento departamento) {
        departamentos.put(departamento.getNombre(), departamento);
    }

    @Override
    public Departamento[] getAll() {

        if (departamentos.isEmpty()) {
            return null;
        }

        Departamento[] allDepartamentos = departamentos.values().toArray(new Departamento[0]);

        return allDepartamentos;

    }

    @Override
    public Boolean contains(String nombre) {
        return departamentos.containsKey(nombre);
    }

    @Override
    public Departamento find(String nombre) {
        return departamentos.get(nombre);
    }

    @Override
    public ActividadTuristica[] getAllActividadesAsociadasADepartamento(String nombreDepto) {
        if (!departamentos.containsKey(nombreDepto))
            return null;

        Map<String, ActividadTuristica> actividadesEnDepartamentoMap = departamentos.get(nombreDepto)
                .getActividadesTuristicas();

        if (actividadesEnDepartamentoMap == null || actividadesEnDepartamentoMap.isEmpty())
            return null;

        List<ActividadTuristica> actividadesEnDepartamentoList = new ArrayList<>(actividadesEnDepartamentoMap.values());

        return actividadesEnDepartamentoList.toArray(new ActividadTuristica[0]);
    }

    @Override
    public ActividadTuristica[] getAllActividadesAsociadasADepartamentoNoEnPaquete(String nombreDepto,
                                                                                   String nombrePaquete) {

        if (!departamentos.containsKey(nombreDepto))
            return null;

        Map<String, ActividadTuristica> actividadesEnDepartamentoMap = departamentos.get(nombreDepto)
                .getActividadesTuristicas();

        if (actividadesEnDepartamentoMap.isEmpty())
            return null;

        List<ActividadTuristica> actividadesNoEnPaqueteList = new ArrayList<>();

        List<ActividadTuristica> actividadesEnDepartamentoList = new ArrayList<>(actividadesEnDepartamentoMap.values());

        for (ActividadTuristica actividad : actividadesEnDepartamentoList) {
            if (!actividad.tienePaqueteAsociado(nombrePaquete))
                actividadesNoEnPaqueteList.add(actividad);
        }

        return actividadesNoEnPaqueteList.toArray(new ActividadTuristica[0]);
    }
    
    @Override
    public void removeAll() {
        departamentos.clear();
    }
}
