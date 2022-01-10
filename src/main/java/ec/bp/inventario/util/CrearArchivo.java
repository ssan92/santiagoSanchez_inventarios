package ec.bp.inventario.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import ec.bp.inventario.service.dto.ReporteClienteDTO;

@Component
public class CrearArchivo {

	
	public File crearArchivo(List<ReporteClienteDTO> reportes,String ruta) {
		FileWriter fw =null;
		File file=null;
		try {
            
            file = File.createTempFile(ruta, null);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("identificacion,nombreCliente,fechaTransaccion,nombreTienda,nombreProducto,cantidad,precioUnitario,total"+"\n");
            for (ReporteClienteDTO rpt: reportes) {
            	bw.write(rpt.getIdentificacion()+","+rpt.getNombreCliente()+","+rpt.getFechaTransaccion()+","+rpt.getNombreTienda()+","+rpt.getNombreProducto()+","+rpt.getCantidad()+","+rpt.getPrecioUnitario()+","+rpt.getTotal()+"\n");
			}
            
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
