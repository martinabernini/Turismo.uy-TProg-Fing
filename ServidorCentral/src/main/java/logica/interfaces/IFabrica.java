package logica.interfaces;


public interface IFabrica {

	public IControladorActividadTuristica getIControladorActividadTuristica();

    public IControladorDepartamento getIControladorDepartamento();

    public IControladorPaqueteActividades getIControladorPaqueteActividades();

    public IControladorSalidaTuristica getIControladorSalidaTuristica();

    public IControladorUsuario getIControladorUsuario();
    
    public ICargaDatos getICargaDatos();
}
