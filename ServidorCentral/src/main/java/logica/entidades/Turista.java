package logica.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import logica.datatypes.*;

public class Turista extends Usuario {

    private String nacionalidad;
    private Map<Integer, InscripcionSalida> inscripciones = new HashMap<>();
    private Map<String, CompraPaquete> comprasPaquetes = new HashMap<>();
    private Map<String, ActividadTuristica> actividadesFavoritas = new HashMap<>();

    public Turista() {
        super();
    }


    // ----------------------------------------------------------------
    // Getters y setters
    // ----------------------------------------------------------------

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Map<Integer, InscripcionSalida> getInscripciones() {
        return inscripciones;
    }

    public Map<String, CompraPaquete> getComprasPaquetes() {
        return comprasPaquetes;
    }

    public Map<String, ActividadTuristica> getActividadesFavoritas() {
        return actividadesFavoritas;
    }

    public void agregarCompraPaquete(CompraPaquete compra) {
        comprasPaquetes.put(compra.getIdentificador(), compra);
    }

    public void agregarActividadFavorita(ActividadTuristica favorita) {
        actividadesFavoritas.put(favorita.getNombre(), favorita);
    }

    public boolean yaComproPaquete(String nombrePaquete) {
        return comprasPaquetes.containsKey(nombrePaquete);
    }

    // -----------------------------------------------------------------

    public void agregarInscripcionASalida(InscripcionSalida inscripcion) {
        this.inscripciones.put(inscripcion.getIdentificador(), inscripcion);
    }

    public DtUsuario newDataType() {

        DtTurista dtTurista = new DtTurista();

        dtTurista.setNickname(this.getNickname());
        dtTurista.setNombre(this.getNombre());
        dtTurista.setApellido(this.getApellido());
        dtTurista.setEmail(this.getEmail());
        dtTurista.setFechaNacimiento(this.getFechaNacimiento().toString());
        dtTurista.setNacionalidad(this.getNacionalidad());

        ArrayList<InscripcionSalida> inscripciones = new ArrayList<>(this.getInscripciones().values());
        ArrayList<Object> nombreSalidas = new ArrayList<>();
        for (InscripcionSalida inscripcion : inscripciones) {
            nombreSalidas.add(inscripcion.getSalida().getNombre());
        }

        WrapperArrayList wrapperSalidas = new WrapperArrayList(nombreSalidas);
        dtTurista.setNombreSalidasALasQueEstaInscripto(wrapperSalidas);

        ArrayList<DtInscripcionSalida> dtInscripciones = new ArrayList<>();
        for (InscripcionSalida inscripcion : inscripciones) {
            dtInscripciones.add(inscripcion.newDataType());
        }
        WrapperDtInscripcion wrapperInscripciones = new WrapperDtInscripcion(dtInscripciones);
        dtTurista.setDtInscripcionesASalidas(wrapperInscripciones);

        dtTurista.setImagen(this.getImagen());
        dtTurista.setPassword(this.getPassword());


        Map<String, DtCompraPaquete> dtComprasPaquetes = new HashMap<>();
        for (CompraPaquete compra : this.getComprasPaquetes().values()) {
            dtComprasPaquetes.put(compra.getIdentificador(), compra.newDataType());
        }
        WrapperMapCompraPaquete resPaquetes = new WrapperMapCompraPaquete(dtComprasPaquetes);
        dtTurista.setComprasPaquetes(resPaquetes);

        List<String> resSeguidos = new ArrayList<>();
        for (String seguido : this.getSeguidos().keySet()) {
            resSeguidos.add(seguido);
        }
        dtTurista.setSeguidos(resSeguidos);

        List<String> resSeguidores = new ArrayList<>();
        for (String seguido : this.getSeguidores().keySet()) {
            resSeguidores.add(seguido);
        }

        dtTurista.setSeguidores(resSeguidores);

        return dtTurista;

    }

    @Override
    public String toString() {
        return "Turista [nacionalidad=" + nacionalidad + ", inscripciones=" + inscripciones + ", paquetes=" + comprasPaquetes
                + ", nickname=" + this.getNickname() + ", nombre=" + this.getNombre() + ", apellido=" + this.getApellido() + ", email=" + this.getEmail()
                + ", fechaNacimiento=" + this.getFechaNacimiento() + ", imagen=" + this.getImagen() + ", password=" + this.getPassword() + "]";
    }


    // -----------------------------------------------------------------

}
