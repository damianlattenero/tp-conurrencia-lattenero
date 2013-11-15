package battleship;

public class SelectScanAndShoot implements Estrategia {

	@Override
	public void estategia(Jugador jugador) {
		//encontrar bien numero aleatorio
		int i = (int) Math.round(Math.random() * (jugador.getServidor().getMatriz().getCantColumnas()-1));
		int j = (int) Math.round(Math.random() * (jugador.getServidor().getMatriz().getCantFilas()-1));
		if(jugador.scan(i, j)){
			jugador.shoot(i, j);
		}
	}

}
