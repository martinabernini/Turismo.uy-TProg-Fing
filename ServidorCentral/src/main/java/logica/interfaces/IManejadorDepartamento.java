package logica.interfaces;

import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;

public interface IManejadorDepartamento {

	void add(Departamento departamento);

	void update(Departamento departamento);

	Departamento[] getAll();

	Boolean contains(String nombre);

	ActividadTuristica[] getAllActividadesAsociadasADepartamento(String nombreDepto);

	ActividadTuristica[] getAllActividadesAsociadasADepartamentoNoEnPaquete(String nombreDepto, String nombrePaquete);

	Departamento find(String nombre);

	void removeAll();

}
