/**
 * 
 */
package ec.bp.inventario.service.command.ifc;

/**
 * @author Santiago
 *
 */
public interface ICommand {
	
	Object execute(IParam parametro) throws Exception;

}
