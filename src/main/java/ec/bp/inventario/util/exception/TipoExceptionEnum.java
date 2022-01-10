/**
 * 
 */
package ec.bp.inventario.util.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TipoExceptionEnum
 */
public enum TipoExceptionEnum {
  
  FUENTE_DATOS("FUENTE_DATOS"),
  
  LOGICA_NEGOCIO("LOGICA_NEGOCIO"),
  
  SOLICITUD_INVALIDA("SOLICITUD_INVALIDA"),
  
  ERROR_INESPERADO("ERROR_INESPERADO"),
  
  SERVIDOR_INALCANZABLE("SERVIDOR_INALCANZABLE"),
  
  SERVICIO_INACCESIBLE("SERVICIO_INACCESIBLE"),
  
  NO_AUTORIZADO("NO_AUTORIZADO");

  private String value;

  TipoExceptionEnum(String value) {
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
  public static TipoExceptionEnum fromValue(String value) {
    for (TipoExceptionEnum b : TipoExceptionEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

