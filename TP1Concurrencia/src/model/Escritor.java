package model;


public class Escritor extends Rol{
	
	private  void escribirResultadosSobreFila(int fila) throws InterruptedException{
		RowIterator it = this.crearIterador(fila);
		it.getMatriz().reservarFila(it.getNroFila());
		
		while(it.hasCurrent()){
			it.getCeldaActual().escribir();
			it.next();
		}
		iteradorExcMutua.signal();
	}
	
	private boolean hayLectores(int x,int nroFilaAModificar){
		return this.getMatriz().getEstructura()[nroFilaAModificar][x].getNroLectores() > 0;
	}
	
	public boolean puedoReservarFila(int nroFilaAModificar){
		boolean puedo = true;
		for(int x = 0; x < this.getMatriz().getCantColumnas();x++){
			puedo = puedo && (this.hayEscritores(x,nroFilaAModificar) || this.hayLectores(x,nroFilaAModificar));
		}
		return puedo;
	}
}
