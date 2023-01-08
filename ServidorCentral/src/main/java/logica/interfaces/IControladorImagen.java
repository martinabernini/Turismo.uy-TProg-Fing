package logica.interfaces;

public interface IControladorImagen {

    String almacenarImagenUsuario(byte[] imagen, String nick, String extension);

    String almacenarImagenActividad(byte[] imagen, String nombre, String extension);

    String almacenarImagenSalida(byte[] imagen, String nombre, String extension);

    String almacenarImagenPaquete(byte[] imagen, String nombre, String extension);

    byte[] obtenerImagen(String identificador);
    
}
