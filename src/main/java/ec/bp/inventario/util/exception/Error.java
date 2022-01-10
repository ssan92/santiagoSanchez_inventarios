package ec.bp.inventario.util.exception;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;


public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("detalle")
	private String detalle;

	@JsonProperty("mensaje")
	private String mensaje;

	/**
	 * Gets or Sets tipoError
	 */
	public enum TipoErrorEnum {
		FUENTE_DATOS("FUENTE_DATOS"),

		LOGICA_NEGOCIO("LOGICA_NEGOCIO"),

		SOLICITUD_INVALIDA("SOLICITUD_INVALIDA"),

		ERROR_INESPERADO("ERROR_INESPERADO"),

		SERVIDOR_INALCANZABLE("SERVIDOR_INALCANZABLE"),

		SERVICIO_INACCESIBLE("SERVICIO_INACCESIBLE"),
		
		NO_AUTORIZADO("NO_AUTORIZADO");

		private String value;

		TipoErrorEnum(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TipoErrorEnum fromValue(String value) {
			for (TipoErrorEnum b : TipoErrorEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	@JsonProperty("tipoError")
	private TipoErrorEnum tipoError;

	public Error codigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	/**
	 * Get codigo
	 * 
	 * @return codigo
	 */

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Error detalle(String detalle) {
		this.detalle = detalle;
		return this;
	}

	/**
	 * Get detalle
	 * 
	 * @return detalle
	 */

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Error mensaje(String mensaje) {
		this.mensaje = mensaje;
		return this;
	}

	/**
	 * Get mensaje
	 * 
	 * @return mensaje
	 */

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Error tipoError(TipoErrorEnum tipoError) {
		this.tipoError = tipoError;
		return this;
	}

	/**
	 * Get tipoError
	 * 
	 * @return tipoError
	 */

	public TipoErrorEnum getTipoError() {
		return tipoError;
	}

	public void setTipoError(TipoErrorEnum tipoError) {
		this.tipoError = tipoError;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Error error = (Error) o;
		return Objects.equals(this.codigo, error.codigo) && Objects.equals(this.detalle, error.detalle)
				&& Objects.equals(this.mensaje, error.mensaje) && Objects.equals(this.tipoError, error.tipoError);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, detalle, mensaje, tipoError);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
		sb.append("    detalle: ").append(toIndentedString(detalle)).append("\n");
		sb.append("    mensaje: ").append(toIndentedString(mensaje)).append("\n");
		sb.append("    tipoError: ").append(toIndentedString(tipoError)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
