package com.helpers;

import com.model.EstadoSesion;
import webservices.DtProveedor;
import webservices.DtTurista;
import webservices.DtUsuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EstadoSesionHelper {

	/**
	 * inicializa la sesión si no estaba creada
	 * 
	 * @param request
	 */
	public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute(SessionKeys.ESTADO_SESION) == null) {
			session.setAttribute(SessionKeys.ESTADO_SESION, EstadoSesion.NO_LOGIN);
		}
	}

	public static EstadoSesion getEstado(HttpServletRequest request) {
		return (EstadoSesion) request.getSession().getAttribute(SessionKeys.ESTADO_SESION);
	}

	public static void setEstado(HttpServletRequest request, EstadoSesion estado) {
		request.getSession().setAttribute(SessionKeys.ESTADO_SESION, estado);
	}

	public static boolean hayUsuarioLogueado(HttpServletRequest request) {
		return getEstado(request) == EstadoSesion.LOGIN_CORRECTO;
	}

	public static boolean hayProveedorLogueado(HttpServletRequest request) {
		if (!hayUsuarioLogueado(request)) {
			return false;
		}
		return getUsuarioLogueado(request) instanceof DtProveedor;
	}

	public static boolean hayTuristaLogueado(HttpServletRequest request) {
		if (!hayUsuarioLogueado(request)) {
			return false;
		}
		return getUsuarioLogueado(request) instanceof DtTurista;
	}

	public static DtUsuario getUsuarioLogueado(HttpServletRequest request) {
		return (DtUsuario) request.getSession().getAttribute(SessionKeys.USUARIO_LOGUEADO);
	}

	public static void setUsuarioLogueado(HttpServletRequest request, DtUsuario usuario) {
		request.getSession().setAttribute(SessionKeys.USUARIO_LOGUEADO, usuario);
	}
}
