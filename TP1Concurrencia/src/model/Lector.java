package model;

public class Lector extends Rol{
	
	public void leerResultados(int fila) throws InterruptedException{
		RowIterator it = this.crearIterador(fila);
		
		while(it.hasCurrent()){
			it.getCeldaActual().leer();
			it.next();
		}
	}
	
	public boolean puedoReservarFila(int nroFilaAModificar){
		boolean puedo = true;
		for(int x = 0; x < this.getMatriz().getCantColumnas();x++){
			puedo = puedo && (!this.hayEscritores(x,nroFilaAModificar));
		}
		return puedo;
	}


}
