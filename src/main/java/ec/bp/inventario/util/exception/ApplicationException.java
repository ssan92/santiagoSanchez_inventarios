package ec.bp.inventario.util.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import ec.bp.inventario.util.exception.Error.TipoErrorEnum;
/**
 * 
 * @implNote CLASE REUTILIZABLE PARA LANZAR EXCEPCIONES DE NEGOCIO
 *           PERSONALIZADAS
 * 
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final TipoErrorEnum error;
	final String codigoError;
	final String encabezadoMensaje;

	/**
	 * ADJUNTAMOS UN DETALLE Y UN TIPO DE DE ERROR
	 * 
	 * @param detalle
	 * @param error
	 */
	public ApplicationException(String detalle, TipoErrorEnum error) {
		super(detalle);
		this.error = error;
		this.codigoError = null;
		this.encabezadoMensaje = null;
	}

	/**
	 * ADJUNTUNTAMOS UN MENSAJE DE RESUMEN, UNA DESCRIPCION AMPLIADA COMO DETALLE,
	 * EL TIPO DE ERROR Y LA CUASA Ó EXCEPCION ORIGINAL EN CASO DE QUE SE ESTE
	 * LANZANDO ESTE <b>ApplicationException</b> DENTRO DEL BLOQUE ORIGINAL
	 * 
	 * @param detalle
	 * @param encabezadoMensaje
	 * @param error
	 * @param causa
	 */
	public ApplicationException(String detalle, String encabezadoMensaje, TipoErrorEnum error, Throwable causa) {
		super(detalle, causa);
		this.error = error;
		this.codigoError = null;
		this.encabezadoMensaje = encabezadoMensaje;
	}

	/**
	 * ADJUNTUNTAMOS UN DETALLE, EL TIPO DE ERROR Y LA CUASA Ó EXCEPCION ORIGINAL EN
	 * CASO DE QUE SE ESTE LANZANDO ESTE <b>ApplicationException</b> DENTRO DEL
	 * BLOQUE ORIGINAL
	 * 
	 * @param detalle
	 * @param error
	 * @param causa
	 */
	public ApplicationException(String detalle, TipoErrorEnum error, Throwable causa) {
		super(detalle, causa);
		this.error = error;
		this.codigoError = null;
		this.encabezadoMensaje = null;
	}

	/**
	 * ADJUNTUNTAMOS UN DETALLE, EL TIPO DE ERROR Y UN CODIGO DE ERROR PERSONALIZADO
	 * 
	 * @param detalle
	 * @param error
	 * @param codigoError
	 */
	public ApplicationException(String detalle, TipoErrorEnum error, String codigoError) {
		super(detalle);
		this.error = error;
		this.codigoError = codigoError;
		this.encabezadoMensaje = null;
	}

	/**
	 * 
	 * ADJUNTUNTAMOS UN MENSAJE DE RESUMEN, UNA DESCRIPCION AMPLIADA COMO DETALLE,
	 * EL TIPO DE ERROR
	 * 
	 * @param detalle
	 * @param encabezadoMensaje
	 * @param error
	 */
	public ApplicationException(String detalle, String encabezadoMensaje, TipoErrorEnum error) {
		super(detalle);
		this.error = error;
		this.codigoError = null;
		this.encabezadoMensaje = encabezadoMensaje;
	}

	/**
	 * POR DEFECTO ESTA DEFINIDO COMO TIPO DE ERROR: ERROR_INESPERADO
	 * 
	 * @param detalle
	 */
	public ApplicationException(String detalle) {
		super(detalle);
		this.error = TipoErrorEnum.ERROR_INESPERADO;
		this.codigoError = null;
		this.encabezadoMensaje = null;
	}

	/**
	 * @return the error
	 */
	public TipoErrorEnum getError() {
		return error;
	}

	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @return the mensaje
	 */
	public String getEncabezadoMensaje() {
		return encabezadoMensaje;
	}

	/**
	 * VALIDAMOS EL CONTENIDO DE UNA EXCEPCION DE NEGOCION "ApplicationException"
	 * PARA DEFINIR EL CODIGO HTTP CORRESPONDIENTE
	 * 
	 * @param ex
	 * @return el dato de repuesta con el codigo Http correspondiente
	 */
	public static ResponseEntity<List<Error>> validarResultado(ApplicationException ex) {
		ResponseEntity<List<Error>> respuesta;

		switch (ex.getError()) {

		case NO_AUTORIZADO:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0007"), HttpStatus.UNAUTHORIZED);

			break;

		case SOLICITUD_INVALIDA:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0006"), HttpStatus.BAD_REQUEST);
			break;

		case SERVICIO_INACCESIBLE:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0005"), HttpStatus.INTERNAL_SERVER_ERROR);

			break;

		case LOGICA_NEGOCIO:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0004"), HttpStatus.BAD_REQUEST);
			break;

		case FUENTE_DATOS:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0003"), HttpStatus.BAD_REQUEST);

			break;

		case SERVIDOR_INALCANZABLE:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0002"), HttpStatus.INTERNAL_SERVER_ERROR);

			break;

		case ERROR_INESPERADO:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0001"), HttpStatus.INTERNAL_SERVER_ERROR);
			break;

		default:
			respuesta = new ResponseEntity<>(bodyErrorsBuilder(ex, "0001"), HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}

		return respuesta;
	}

	/**
	 * 
	 * @param ex
	 * @param codigoDefault
	 * @return
	 */
	private static final List<Error> bodyErrorsBuilder(ApplicationException ex, String codigoDefault) {
		List<Error> listError = new ArrayList<>();
		String codigo = ex.getCodigoError() == null ? codigoDefault : ex.getCodigoError();
		String mensaje = ex.getEncabezadoMensaje() == null ? ex.getError().getValue().replace("_", " ")
				: ex.getEncabezadoMensaje();

		listError.add(new Error().codigo(codigo).mensaje(mensaje).detalle(ex.getMessage())
				.tipoError(ex.getError() == null ? TipoErrorEnum.ERROR_INESPERADO : ex.getError()));

		return listError;
	}

	/**
	 * 
	 * @param ex
	 * @param exceptionLog
	 * @return
	 */
	public static ResponseEntity<List<Error>> validarResultado(Exception ex,TipoErrorEnum tipoError) {

		List<Error> errores = new ArrayList<>();

		errores.add(new Error().codigo("0001").mensaje("ERROR INTERNO").detalle(ex.getMessage()).tipoError(tipoError));

		return new ResponseEntity<>(errores, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
