package model;

public class Lector extends Rol{
	
	public void leerResultados(Matriz<?> matriz, int fila) throws InterruptedException{
		RowIterator<?> it = this.crearIterador(matriz, fila);
		
		while(it.hasCurrent()){
			it.getCeldaActual().leer();
			it.next();
		}
	}
	
	

	@Override
	protected boolean puedoReservarFila(Matriz<?> matriz, int nroFilaAModificar) {
		return true;
	}


}
