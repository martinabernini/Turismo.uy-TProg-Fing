package logica.validacion;

public final class Constantes {

    private static final int MENOR_CANTIDAD_DE_TURISTAS_INSCRIBLES_A_SALIDA = 0;

    private static final int MENOR_CANTIDAD_MAXIMA_DE_TURISTAS_EN_SALIDA = 0;

    // El siguiente regex es para validar que el nickname no tenga caracteres especiales
    // solo letras, números, puntos, guiones y guiones bajos, y tildes y ñ en español
    private static final String NICKNAME_REGEX = "^[a-zA-Z0-9_\\-\\.\\u00f1\\u00d1\\u00e1\\u00c1\\u00e9\\u00c9\\u00ed" +
            "\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc]+$";


    // El siguiente regex es para validar que el mail no tenga caracteres especiales
    // solo letras, números, puntos, guiones y guiones bajos, y tildes y ñ en español
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+@[A-Za-z0-9._-áéíóúñÁÉÍÓÚÑ]+\\.[A-Za-z0-9" +
            "._-áéíóúñÁÉÍÓÚÑ]+$";


    // solo letras, números, espacio, tildes y ñ en español, sin guiones ni puntos ni guiones bajos
    private static final String SOLO_ALFANUMERICOS_Y_ESPACIOS_SIN_CARACTERES_ESPECIALES_REGEX = "^[a-zA-Z0-9\\u00f1" +
            "\\u00d1" +
            "\\u00e1" +
            "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$";

	// solo letras, números, espacio, tildes y ñ en español, con guiones puntos y guiones bajos
	private static final String ALFANUMERICOS_REGEX = "^[a-zA-Z0-9_\\-\\.\\u00f1" +
	        "\\u00d1" +
	        "\\u00e1" +
	        "\\u00c1\\u00e9\\u00c9\\u00ed\\u00cd\\u00f3\\u00d3\\u00fa\\u00da\\u00fc\\u00dc\\s]+$";

    // ---------------------------------------------------------------------------------------------

    public static int getMenorCantidadMaximaDeTuristasEnSalida() {
        return MENOR_CANTIDAD_DE_TURISTAS_INSCRIBLES_A_SALIDA;
    }

    public static int getMenorCantidadDeTuristasInscribiblesASalida() {
        return MENOR_CANTIDAD_MAXIMA_DE_TURISTAS_EN_SALIDA;
    }

    public static String getNicknameRegex() {
        return NICKNAME_REGEX;
    }

    public static String getEmailRegex() {
        return EMAIL_REGEX;
    }

    public static String getAlfanumericosRegexSinGuiones() {
        return SOLO_ALFANUMERICOS_Y_ESPACIOS_SIN_CARACTERES_ESPECIALES_REGEX;
    }

    public static String getAlfanumericosRegex() {
        return ALFANUMERICOS_REGEX;
    }

}
