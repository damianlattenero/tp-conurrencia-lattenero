package model;


public class Escritor extends Rol implements ReadWrite{
	
	private  void escribirResultadosSobreFila(Matriz<?> matriz, int fila) throws InterruptedException{
		
	    RowIterator<?> it = this.crearIterador(matriz,fila);
	    matriz.reservarFila(fila);
		while(it.hasCurrent()){
			it.getCeldaActual().escribir();
			it.next();
		}
		iteradorExcMutua.signal();
	}
	
	
	public boolean puedoReservarFila(Matriz<?> matriz, int nroFilaAModificar){
		return matriz.hayEscritores(nroFilaAModificar) || matriz.hayLectrores(nroFilaAModificar);
	}

	@Override
	public void leer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escribir() {
		// TODO Auto-generated method stub
		
	}
}
