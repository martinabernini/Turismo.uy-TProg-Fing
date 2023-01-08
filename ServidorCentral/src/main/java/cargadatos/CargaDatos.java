package cargadatos;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.joda.time.*;

import logica.datatypes.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IControladorPaqueteActividades;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IControladorUsuario;


public class CargaDatos implements ICargaDatos {

    private Iterable<CSVRecord> recordsActividades;
    private Iterable<CSVRecord> recordsActividadesPaquetes;
    private Iterable<CSVRecord> recordsDepartamentos;
    private Iterable<CSVRecord> recordsInscripciones;
    private Iterable<CSVRecord> recordsPaquetes;
    private Iterable<CSVRecord> recordsSalidas;
    private Iterable<CSVRecord> recordsUsuarios;
    private Iterable<CSVRecord> recordsTuristas;
    private Iterable<CSVRecord> recordsProveedores;
    private Iterable<CSVRecord> recordsCategorias;
    private Iterable<CSVRecord> recordsCategoriasActividades;
    private Iterable<CSVRecord> recordsCategoriasPaquetes;
    private Iterable<CSVRecord> recordsCompraPaquetes;
    private Iterable<CSVRecord> recordsUsuariosSeguidores;


    private Map<String, Map<String, String>> mapRecordsActividades = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsActividadesPaquetes = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsDepartamentos = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsInscripciones = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsPaquetes = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsSalidas = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsUsuarios = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsTuristas = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsProveedores = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsCategorias = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsCategoriasActividades = new HashMap<>();
    private Map<String, List<String>> mapRecordsCategoriasPaquetes = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsCompraPaquetes = new HashMap<>();
    private Map<String, Map<String, String>> mapRecordsUsuariosSeguidores = new HashMap<>();


    IControladorActividadTuristica controladorActividadTuristica;
    IControladorDepartamento controladorDepartamento;
    IControladorPaqueteActividades controladorPaqueteActividades;
    IControladorSalidaTuristica controladorSalidaTuristica;
    IControladorUsuario controladorUsuario;

    private static Boolean seCargoDatosAntes = false;

    public CargaDatos(IControladorActividadTuristica controladorActividadTuristica, IControladorDepartamento controladorDepartamento, IControladorPaqueteActividades controladorPaqueteActividades, IControladorSalidaTuristica controladorSalidaTuristica, IControladorUsuario controladorUsuario) {

        this.controladorActividadTuristica = controladorActividadTuristica;
        this.controladorDepartamento = controladorDepartamento;
        this.controladorPaqueteActividades = controladorPaqueteActividades;
        this.controladorSalidaTuristica = controladorSalidaTuristica;
        this.controladorUsuario = controladorUsuario;
    }

    public void cargar() {

        if (seCargoDatosAntes) {
            return;
        }
        try {

            CSVFormat formatActividades = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "NombreActiv", "Descripcion", "Duracion", "Costo", "Ciudad", "Departamento", "FechaAlta", "Ref.Proveedor", "Estado", "Imagen", "Video").setSkipHeaderRecord(true).build();

            CSVFormat formatActividadesPaquetes = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("ref", "RefPaq", "RefActiv").setSkipHeaderRecord(true).build();

            CSVFormat formatDepartamento = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "Nombre", "Descripción", "Web").setSkipHeaderRecord(true).build();

            CSVFormat formatInscripciones = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "RefSal", "RefTur", "CantTur", "Costo", "FechaInscrip").setSkipHeaderRecord(true).build();

            CSVFormat formatPaquetes = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "Nombre", "Validez", "Descuento", "FechaAlta", "Descripción", "Imagen").setSkipHeaderRecord(true).build();

            CSVFormat formatSalidas = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "RefActiv", "Nombre", "Fecha", "Hora", "TuristaMax", "Lugar", "FechaAlta", "Imagen").setSkipHeaderRecord(true).build();

            CSVFormat formatUsuarios = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "Tipo", "Nickname", "Nombre", "Apellido", "EMail", "FechaNac", "Imagen", "Password").setSkipHeaderRecord(true).build();

            CSVFormat formatTuristas = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "Nacionalidad").setSkipHeaderRecord(true).build();

            CSVFormat formatProveedores = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "Descripción", "Web").setSkipHeaderRecord(true).build();

            CSVFormat formatCategorias = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("RefCat", "Nombre").setSkipHeaderRecord(true).build();

            CSVFormat formatCategoriasActividades = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "RefCat", "RefActiv").setSkipHeaderRecord(true).build();

            CSVFormat formatCompraPaquetes = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "RefUsuario", "RefPaquete", "CantTuristas", "FechaCompra", "Vencimiento", "Costo").setSkipHeaderRecord(true).build();

            CSVFormat formatCategoriasPaquetes = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("RefPaquete", "RefCategoria").setSkipHeaderRecord(true).build();

            CSVFormat formatUsuariosSeguidores = CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader("Ref", "RefSeguidor", "RefSeguido").setSkipHeaderRecord(true).build();

            recordsActividades = formatActividades.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.ACTIVIDADES)));
            recordsActividadesPaquetes = formatActividadesPaquetes.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.ACTIVIDADES_PAQUETES)));
            recordsDepartamentos = formatDepartamento.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.DEPARTAMENTOS)));
            recordsInscripciones = formatInscripciones.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.INSCRIPCIONES)));
            recordsPaquetes = formatPaquetes.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.PAQUETES)));
            recordsSalidas = formatSalidas.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.SALIDAS)));
            recordsUsuarios = formatUsuarios.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.USUARIOS)));
            recordsTuristas = formatTuristas.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.USUARIOS_TURISTAS)));
            recordsProveedores = formatProveedores.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.USUARIOS_PROVEEDORES)));
            recordsCategorias = formatCategorias.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.CATEGORIAS)));
            recordsCategoriasActividades = formatCategoriasActividades.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.CATEGORIAS_ACTIVIDADES)));
            recordsCategoriasPaquetes = formatCategoriasPaquetes.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.CATEGORIAS_PAQUETES)));
            recordsCompraPaquetes = formatCompraPaquetes.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.COMPRAS_PAQUETES)));
            recordsUsuariosSeguidores = formatUsuariosSeguidores.parse(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DatosPaths.USUARIOS_SEGUIDORES)));

            llenarMapRecordsActividades();
            llenarMapRecordsActividadesPaquetes();
            llenarMapRecordsDepartamentos();
            llenarMapRecordsInscripciones();
            llenarMapRecordsPaquetes();
            llenarMapRecordsSalidas();
            llenarMapRecordsUsuarios();
            llenarMapRecordsTuristas();
            llenarMapRecordsProveedores();
            llenarMapRecordsCategorias();
            llenarMapRecordsCategoriasActividades();
            llenarMapRecordsCategoriasPaquetes();
            llenarMapRecordsCompraPaquetes();
            llenarMapRecordsUsuariosSeguidores();

            cargarUsuarios();
            cargarDepartamentos();
            cargarCatergorias();
            cargarActividades();
            cargarSalidas();
            cargarInscripciones();
            cargarPaquetes();

            System.out.println("");
            System.out.println("- Finaliza carga      -------------------------------------------------");
            System.out.println("");

            seCargoDatosAntes = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cargarCatergorias() {
        System.out.println("- Cargando categorias -------------------------------------------------");

        try {
            for (Map<String, String> col : mapRecordsCategorias.values()) {
                controladorActividadTuristica.darDeAltaCategoria(col.get("Nombre").strip());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarActividades() {
        System.out.println("");
        System.out.println("- Cargando actividades      -------------------------------------------------");
        try {

            for (Map<String, String> col : mapRecordsActividades.values()) {

                String nombreActividad = col.get("NombreActiv");

                ArrayList<String> setDeCategorias = new ArrayList<>();


                for (Map<String, String> entry : mapRecordsCategoriasActividades.values()) {

                    String categoriaFK = entry.get("RefCat").strip().toLowerCase(); // foreign key
                    String nombreRefActividad = entry.get("RefActiv").strip().toLowerCase(); // foreign key
                    String nombreActividad2 = mapRecordsActividades.get(nombreRefActividad).get("NombreActiv");

                    if (nombreActividad2.equals(nombreActividad)) {
                        setDeCategorias.add(mapRecordsCategorias.get(categoriaFK).get("Nombre").strip());
                    }
                }

                DtActividadTuristica nuevaActividad = new DtActividadTuristica();

                nuevaActividad.setCategorias(setDeCategorias);
                nuevaActividad.setNombre(nombreActividad);
                nuevaActividad.setDescripcion(col.get("Descripcion"));
                nuevaActividad.setDuracionHrs(Integer.parseInt(col.get("Duracion").strip()));
                nuevaActividad.setCostoPorPersona(Float.parseFloat(col.get("Costo").strip()));

                nuevaActividad.setCiudad(col.get("Ciudad"));
                nuevaActividad.setDepartamento(col.get("Departamento").strip());

                nuevaActividad.setUrlVideo(col.get("Video"));

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fecha = java.time.LocalDate.parse(col.get("FechaAlta"), formato);

                nuevaActividad.setFechaAlta(fecha.toString());
                String refProveedor = col.get("Ref.Proveedor").strip();
                String nickNameProveedor = mapRecordsUsuarios.get(refProveedor).get("Nickname");
                nuevaActividad.setProovedor(nickNameProveedor);
                nuevaActividad.setImagen(col.get("Imagen"));
                EnumEstadoActividad estado = EnumEstadoActividad.valueOf(col.get("Estado").toUpperCase());
                nuevaActividad.setEstado(estado);

                try {
                    controladorActividadTuristica.darDeAltaActividadTuristica(nuevaActividad);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cargarDepartamentos() {
        System.out.println("");
        System.out.println("- Cargando departamentos      -------------------------------------------------");

        try {
            for (Map<String, String> record : mapRecordsDepartamentos.values()) {
                String nombre = record.get("Nombre").strip();
                String descripcion = record.get("Descripción");
                String web = record.get("Web");

                System.out.println("---------- agregando " + nombre);
                controladorDepartamento.darDeAltaDepartamento(nombre, descripcion, web);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cargarInscripciones() {
        System.out.println("");
        System.out.println("- Cargando inscripciones      -------------------------------------------------");

        for (Map<String, String> col : mapRecordsInscripciones.values()) {
            DtInscripcionSalida nuevaInscripcion = new DtInscripcionSalida();

            try {
                nuevaInscripcion.setNombreSalidaTuristica(mapRecordsSalidas.get(col.get("RefSal").strip()).get("Nombre"));
                nuevaInscripcion.setNickname(mapRecordsUsuarios.get(col.get("RefTur").strip()).get("Nickname"));
                nuevaInscripcion.setCantidadTuristas(Integer.parseInt(col.get("CantTur").strip()));


                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fechaAux = java.time.LocalDate.parse(col.get("FechaInscrip").strip(), formato);

                //  Date fechaAux = new SimpleDateFormat("dd/MM/yyyy").parse(col.get("FechaInscrip").strip());


                nuevaInscripcion.setFechaInscripcion(fechaAux.toString());

                controladorSalidaTuristica.inscribirTuristaASalidaTuristica(nuevaInscripcion);
            } catch (Exception e) {
                System.out.println(nuevaInscripcion.toString());
                for (Map<String, String> cas : mapRecordsSalidas.values()) {

                    String nombreSalida = cas.get("Nombre");
                    System.out.println("Actividades: " + "'" + nombreSalida + "'");
                }
                e.printStackTrace();

            }
            System.out.println(nuevaInscripcion.toString());
        }
    }

    private void cargarPaquetes() {
        System.out.println("");
        System.out.println("- Cargando paquetes de actividades      --------------------------------------------");
        /*
         * Estrategia:
         * - Crear los paquetes vacios
         * - Luego, recorrer las relaciones paquetes actividades y agregarlas
         * - Luego, recorrer las relaciones paquetes categorias y agregarlas
         * - Luego, recorrer las relaciones compras paquetes y agregarlas
         */

        // Crear los paquetes vacios
        for (Map<String, String> col : mapRecordsPaquetes.values()) {
            try {

                DtPaqueteActividades nuevoPaquete = new DtPaqueteActividades();
                nuevoPaquete.setNombre(col.get("Nombre"));
                nuevoPaquete.setDescripcion(col.get("Descripción"));


                // Date fechaAux = new SimpleDateFormat("dd/MM/yyyy").parse(col.get("FechaAlta"));
                // LocalDate fecha = LocalDate.fromDateFields(fechaAux);


                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fecha = java.time.LocalDate.parse(col.get("FechaAlta"), formato);

                nuevoPaquete.setFechaAlta(fecha.toString());

                nuevoPaquete.setValidezEnDias(Integer.parseInt(col.get("Validez")));
                try {
                    nuevoPaquete.setDescuento(Float.parseFloat(col.get("Descuento")) / 100);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                nuevoPaquete.setImagen(col.get("Imagen"));
                controladorPaqueteActividades.darDeAltaPaquete(nuevoPaquete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Agregar las actividades a los paquetes
        for (Map<String, String> fila : mapRecordsActividadesPaquetes.values()) {

            String ref = fila.get("ref").strip().substring(2, 3);
            String refPaq = fila.get("RefPaq").strip();
            String refAct = fila.get("RefActiv").strip();

            String nombrePaquete = mapRecordsPaquetes.get(refPaq).get("Nombre");
            String nombreActividad = mapRecordsActividades.get(refAct).get("NombreActiv");

            try {
                controladorPaqueteActividades.ingresarActividadTuristicaAPaquete(nombreActividad, nombrePaquete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Agregar las categorias de paquetes
        for (String paquetePK : mapRecordsCategoriasPaquetes.keySet()) {

            String nombrePaquete = mapRecordsPaquetes.get(paquetePK).get("Nombre").strip();
            List<String> refCategorias = mapRecordsCategoriasPaquetes.get(paquetePK);

            for (String refCategoria : refCategorias) {
                String categoria = mapRecordsCategorias.get(refCategoria).get("Nombre");
                try {
                    controladorPaqueteActividades.ingresarCategoriaAPaquete(categoria, nombrePaquete);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        // Agregar las compras de paquetes
        for (Map<String, String> fila : mapRecordsCompraPaquetes.values()) {
            String ref = fila.get("Ref").strip().substring(2, 3);
            String refUsuario = fila.get("RefUsuario").strip().toLowerCase();
            String refPaquete = fila.get("RefPaquete").strip().toLowerCase();

            String nickname = mapRecordsUsuarios.get(refUsuario).get("Nickname");
            String nombrePaquete = mapRecordsPaquetes.get(refPaquete).get("Nombre");

            Integer cantidadTuristas = Integer.parseInt(fila.get("CantTuristas").strip());

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate fechaCompraPaquete = java.time.LocalDate.parse(fila.get("FechaCompra").strip(), formato);

            java.time.LocalDate fechaVencimiento = java.time.LocalDate.parse(fila.get("Vencimiento").strip(), formato);


            DtCompraPaquete compra = new DtCompraPaquete();
            compra.setFechaCompra(fechaCompraPaquete.toString());
            compra.setValidoHasta(fechaVencimiento.toString());
            compra.setCantidadTuristas(cantidadTuristas);
            compra.setNombreTurista(nickname);
            compra.setNombrePaquete(nombrePaquete);

            try {
                controladorPaqueteActividades.comprarPaquete(compra);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @SuppressWarnings("deprecation")
    private void cargarSalidas() {
        System.out.println("");
        System.out.println("- Cargando salidas      -------------------------------------------------");

        // List<DtSalidaTuristica> nuevasSalidas = new ArrayList<DtSalidaTuristica>();

        for (Map<String, String> col : mapRecordsSalidas.values()) {

            DtSalidaTuristica nuevaSalida = new DtSalidaTuristica();
            try {
                int horaSalidaInt = Integer.parseInt(col.get("Hora").split("hs")[0].strip());

                nuevaSalida.setNombreSalida(col.get("Nombre"));


                // "Ref", "RefActiv", "Nombre", "Fecha", "Hora", "TuristaMax", "Lugar", "FechaAlta", "Imagen"


                //FECHA ALTA

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fechaAlta = java.time.LocalDate.parse(col.get("FechaAlta"), formato);

                nuevaSalida.setFechaAlta(fechaAlta.toString());

                //FECHA SALIDA


                DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fechaSalidaAuxiliar = java.time.LocalDate.parse(col.get("Fecha"), formato2);
                java.time.LocalDateTime fechaSalida = java.time.LocalDateTime.of(fechaSalidaAuxiliar, java.time.LocalTime.MIDNIGHT);

                fechaSalida = fechaSalida.withHour(horaSalidaInt);

                nuevaSalida.setFechaSalida(fechaSalida.toString());


                nuevaSalida.setCantidadMaximaTuristas(Integer.parseInt(col.get("TuristaMax").strip()));
                nuevaSalida.setLugarSalida(col.get("Lugar"));

                nuevaSalida.setNombreActividad(mapRecordsActividades.get(col.get("RefActiv").strip()).get("NombreActiv"));
                nuevaSalida.setImagen(col.get("Imagen").strip());
                controladorSalidaTuristica.darDeAltaSalidaTuristica(nuevaSalida);
                System.out.println("------------" + nuevaSalida.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // -----------------------------------------------------------------------------------------
    private void cargarUsuarios() {
        System.out.println("");
        System.out.println("- Cargando usuarios      -------------------------------------------------");

        List<DtUsuario> nuevosUsuarios = new ArrayList<DtUsuario>();

        for (Map<String, String> col : mapRecordsUsuarios.values()) {

            if (col.get("Tipo").contains("T")) {
                DtTurista nuevoTurista = new DtTurista();

                nuevoTurista.setNickname(col.get("Nickname"));
                nuevoTurista.setNombre(col.get("Nombre"));
                nuevoTurista.setApellido(col.get("Apellido"));
                nuevoTurista.setEmail(col.get("EMail"));

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fecha = java.time.LocalDate.parse(col.get("FechaNac"), formato);

                nuevoTurista.setFechaNacimiento(fecha.toString());
                nuevoTurista.setNacionalidad(mapRecordsTuristas.get(col.get("Ref")).get("Nacionalidad"));
                nuevoTurista.setImagen(col.get("Imagen"));
                nuevoTurista.setPassword(col.get("Password"));

                nuevosUsuarios.add(nuevoTurista);
            } else {
                DtProveedor nuevoProveedor = new DtProveedor();

                nuevoProveedor.setNickname(col.get("Nickname"));
                nuevoProveedor.setNombre(col.get("Nombre"));
                nuevoProveedor.setApellido(col.get("Apellido"));
                nuevoProveedor.setEmail(col.get("EMail"));

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate fecha = java.time.LocalDate.parse(col.get("FechaNac"), formato);

                nuevoProveedor.setFechaNacimiento(fecha.toString());


                nuevoProveedor.setImagen(col.get("Imagen"));
                nuevoProveedor.setPassword(col.get("Password"));

                nuevoProveedor.setDescripcion(mapRecordsProveedores.get(col.get("Ref")).get("Descripción"));
                nuevoProveedor.setUrlSitioWeb(mapRecordsProveedores.get(col.get("Ref")).get("Web"));

                nuevosUsuarios.add(nuevoProveedor);
            }
        }

        try {
            for (DtUsuario nuevoUsuario : nuevosUsuarios) {
                controladorUsuario.darDeAltaUsuario(nuevoUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // seguir usuarios

        for (Map<String, String> col : mapRecordsUsuariosSeguidores.values()) {

            String refSeguidor = col.get("RefSeguidor");
            String refSeguido = col.get("RefSeguido");

            String nicknameSeguidor = mapRecordsUsuarios.get(refSeguidor.toLowerCase().strip()).get("Nickname");
            String nicknameSeguido = mapRecordsUsuarios.get(refSeguido.toLowerCase().strip()).get("Nickname");

            try {
                controladorUsuario.relacionSeguirUsuario(nicknameSeguidor, nicknameSeguido);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    // ------------------------------------------------------------------------------------------
    private void llenarMapRecordsUsuariosSeguidores() {
        for (CSVRecord record : recordsUsuariosSeguidores) {
            Map<String, String> columnas = new HashMap<>();

            columnas.put("Ref", record.get("Ref"));
            columnas.put("RefSeguidor", record.get("RefSeguidor"));
            columnas.put("RefSeguido", record.get("RefSeguido"));

            mapRecordsUsuariosSeguidores.put(record.get("Ref"), columnas);
        }
    }

    private void llenarMapRecordsCompraPaquetes() {
        for (CSVRecord record : recordsCompraPaquetes) {
            Map<String, String> columnas = new HashMap<>();
            columnas.put("Ref", record.get("Ref"));
            columnas.put("RefUsuario", record.get("RefUsuario"));
            columnas.put("RefPaquete", record.get("RefPaquete"));
            columnas.put("CantTuristas", record.get("CantTuristas"));
            columnas.put("FechaCompra", record.get("FechaCompra"));
            columnas.put("Vencimiento", record.get("Vencimiento"));
            columnas.put("Costo", record.get("Costo"));

            String ref = record.get("Ref").strip();
            mapRecordsCompraPaquetes.put(ref, record.toMap());
        }
    }

    private void llenarMapRecordsCategorias() {
        for (CSVRecord record : recordsCategorias) {
            Map<String, String> columnas = new HashMap<>();
            columnas.put("RefCat", record.get("RefCat"));
            columnas.put("Nombre", record.get("Nombre"));
            mapRecordsCategorias.put(record.get("RefCat").strip().toLowerCase(), columnas);
        }
    }

    private void llenarMapRecordsCategoriasActividades() {
        for (CSVRecord record : recordsCategoriasActividades) {
            Map<String, String> columnas = new HashMap<>();

            columnas.put("Ref", record.get("Ref"));
            columnas.put("RefCat", record.get("RefCat"));
            columnas.put("RefActiv", record.get("RefActiv"));
            mapRecordsCategoriasActividades.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    private void llenarMapRecordsCategoriasPaquetes() {
        for (CSVRecord record : recordsCategoriasPaquetes) {
            List<String> actuales = mapRecordsCategoriasPaquetes.get(record.get("RefPaquete").strip().toLowerCase());
            if (actuales != null) {
                actuales.add(record.get("RefCategoria").strip().toLowerCase());
                mapRecordsCategoriasPaquetes.put(record.get("RefPaquete").strip().toLowerCase(), actuales);
            } else {
                List<String> nuevas = new ArrayList<>();
                nuevas.add(record.get("RefCategoria").strip().toLowerCase());
                mapRecordsCategoriasPaquetes.put(record.get("RefPaquete").strip().toLowerCase(), nuevas);
            }
        }
    }


    private void llenarMapRecordsActividades() {
        // Record es como una fila
        for (CSVRecord record : recordsActividades) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("NombreActiv", record.get("NombreActiv").strip());
            columnas.put("Descripcion", record.get("Descripcion").strip());
            columnas.put("Duracion", record.get("Duracion").strip());
            columnas.put("Costo", record.get("Costo").strip());
            columnas.put("Ciudad", record.get("Ciudad").strip());
            columnas.put("Departamento", record.get("Departamento").strip());
            columnas.put("FechaAlta", record.get("FechaAlta").strip());
            columnas.put("Ref.Proveedor", record.get("Ref.Proveedor").strip().toLowerCase());
            columnas.put("Estado", record.get("Estado").strip().toUpperCase());
            columnas.put("Imagen", record.get("Imagen").strip());
            columnas.put("Video", record.get("Video").strip());


            mapRecordsActividades.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsActividadesPaquetes() {
        for (CSVRecord record : recordsActividadesPaquetes) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("ref", record.get("ref").strip().toLowerCase());
            columnas.put("RefPaq", record.get("RefPaq").strip().toLowerCase());
            columnas.put("RefActiv", record.get("RefActiv").strip().toLowerCase());

            mapRecordsActividadesPaquetes.put(record.get("ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsDepartamentos() {
        for (CSVRecord record : recordsDepartamentos) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("Nombre", record.get("Nombre").strip());
            columnas.put("Descripción", record.get("Descripción").strip());
            columnas.put("Web", record.get("Web").strip());

            mapRecordsDepartamentos.put(record.get("Ref").strip().toLowerCase(), columnas);

        }
    }

    ;

    private void llenarMapRecordsInscripciones() {
        for (CSVRecord record : recordsInscripciones) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("RefSal", record.get("RefSal").strip().toLowerCase());
            columnas.put("RefTur", record.get("RefTur").strip().toLowerCase());
            columnas.put("CantTur", record.get("CantTur").strip());
            columnas.put("Costo", record.get("Costo").strip());
            columnas.put("FechaInscrip", record.get("FechaInscrip").strip());

            mapRecordsInscripciones.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsPaquetes() {
        for (CSVRecord record : recordsPaquetes) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("Nombre", record.get("Nombre").strip().toLowerCase());
            columnas.put("Validez", record.get("Validez").strip());
            columnas.put("Descuento", record.get("Descuento").strip());
            columnas.put("FechaAlta", record.get("FechaAlta").strip());
            columnas.put("Descripción", record.get("Descripción").strip());
            columnas.put("Imagen", record.get("Imagen").strip());

            mapRecordsPaquetes.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsSalidas() {
        for (CSVRecord record : recordsSalidas) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("RefActiv", record.get("RefActiv").strip().toLowerCase());
            columnas.put("Nombre", record.get("Nombre").strip());
            columnas.put("Fecha", record.get("Fecha").strip());
            columnas.put("Hora", record.get("Hora").strip());
            columnas.put("TuristaMax", record.get("TuristaMax").strip());
            columnas.put("Hora", record.get("Hora").strip());
            columnas.put("Lugar", record.get("Lugar").strip());
            columnas.put("FechaAlta", record.get("FechaAlta").strip());
            columnas.put("Imagen", record.get("Imagen").strip());

            mapRecordsSalidas.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsUsuarios() {
        for (CSVRecord record : recordsUsuarios) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("Tipo", record.get("Tipo").strip());
            columnas.put("Nickname", record.get("Nickname").strip().toLowerCase());
            columnas.put("Nombre", record.get("Nombre").strip());
            columnas.put("Apellido", record.get("Apellido").strip());
            columnas.put("EMail", record.get("EMail").strip().toLowerCase());
            columnas.put("FechaNac", record.get("FechaNac").strip());
            columnas.put("Imagen", record.get("Imagen").strip());
            columnas.put("Password", record.get("Password").strip());

            mapRecordsUsuarios.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsTuristas() {
        for (CSVRecord record : recordsTuristas) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("Nacionalidad", record.get("Nacionalidad").strip());

            mapRecordsTuristas.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

    private void llenarMapRecordsProveedores() {
        for (CSVRecord record : recordsProveedores) {
            Map<String, String> columnas = new HashMap<String, String>();

            columnas.put("Ref", record.get("Ref").strip().toLowerCase());
            columnas.put("Descripción", record.get("Descripción").strip());
            columnas.put("Web", record.get("Web").strip());

            mapRecordsProveedores.put(record.get("Ref").strip().toLowerCase(), columnas);
        }
    }

    ;

}
