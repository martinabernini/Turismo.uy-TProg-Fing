package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class LoggingHandler implements InvocationHandler {

	private final Object target;

	public LoggingHandler(Object target) {
		this.target = target;
	}

	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------

	@Override
	public Object invoke(Object proxy, Method metodo, Object[] args) throws Throwable {

		log(metodoInvovado(metodo, args));

		String nombreClaseInvocada = conPrefijoAdecuado(sinPackages(target.getClass().toString()));

		try {
			// Se captura el retorno del metodo llamado
			Object retorno = metodo.invoke(target, args);

			logRetornoDeMetodo(nombreClaseInvocada, retorno);

			return retorno;

		} catch (Exception e) {
			// Si el metodo tira una excepcion
			// Como el proxy la envuelve en su propio tipo de error automaticamente
			// Usamos el metodo getCause para desenvolverla (obtener el error original)
			// Loggeamos y tiramos el error original


			if (e.getCause() != null) {
				String tipoError = e.getCause().getClass().getName();

				log("  <" + nombreClaseInvocada + " tira: '" + e.getCause().getMessage() + "'    " + sinPackages(tipoError));
				throw e.getCause(); // https://stackoverflow.com/questions/5490139/getting-undeclaredthrowableexception-instead-of-my-own-exception
			}
			// Si no tuviera una causa la excepcion tiramos el error del proxy
			throw e;
		}
	}

	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------
	// Metodos que formatean texto,segun la clase que se llame y con que argumentos
	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------

	private void logRetornoDeMetodo(String nombreClase, Object resultado) {
		if (resultado != null) {

			if (resultado.getClass().isArray()) {
				logArrayObjetos(nombreClase, resultado);
			} else {
				log("  <" + nombreClase + " retorna: " + resultado.toString());
			}
		}
		log("");
	}

	private void logArrayObjetos(String nombreClase, Object resultado) {
		log("  <" + nombreClase + " retorna: ");
		for (Object res : (Object[]) resultado) {
			if (res == null) {
				log("     - null");
			} else {
				log("     - " + conComillasSiCorresponde(res));
			}
		}
	}

	private String metodoInvovado(Method metodo, Object[] argumentos) {
		String nombreClase = sinPackages(target.getClass().toString());
		String nombreMetodo = metodo.getName();
		return conPrefijoAdecuado(nombreClase + "." + nombreMetodo) + argsFormateados(argumentos);
	}

	private String argsFormateados(Object[] args) {
		String argsString = "";

		if (args != null) {
			argsString = " << " + conComillasSiCorresponde(args[0]);

			for (int i = 1; i < args.length; i++) {

				argsString = argsString.concat(" _ " + conComillasSiCorresponde(args[i].toString()));
			}

		}
		return argsString;
	}

	private String conPrefijoAdecuado(String nombreLlamado) {
		// Formatea la linea a loggear dependiendo de si es controlador o manejador

		if (nombreLlamado.startsWith("Controlador")) {

			return "- " + nombreLlamado;
		}

		if (nombreLlamado.startsWith("Manejador"))
			return "--- " + nombreLlamado;

		if (nombreLlamado.startsWith("Validador"))
			return "-- " + nombreLlamado;

		if (nombreLlamado.startsWith("MensajeError"))
			return "-- " + nombreLlamado;

		return nombreLlamado;
	}

	private String sinPackages(String claseConPrefijoPaquetes) {
		// Saca los prefijos de los packages
		// Transforma "class logica.controladores.ControladorDepartamento" a
		// "ControladorDepartamento"

		String[] nombreSeparadoPorPuntos = claseConPrefijoPaquetes.split("\\.");
		return nombreSeparadoPorPuntos[nombreSeparadoPorPuntos.length - 1];
	}

	private String conComillasSiCorresponde(Object arg) {
		if (arg == null) {
			return "null";
		}

		if ((arg instanceof String) || (arg instanceof Integer) || (arg instanceof Date)) {
			return "'" + arg.toString() + "'";
		}

		String argStr = arg.toString();
		if (argStr.contains("[") && argStr.contains("]") && argStr.contains("=")) {
			return argStr;
		}

		return "'" + argStr + "'";
	}

	private void log(String message) {
		System.out.println(message);
	}

}
