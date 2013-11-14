package battleship;

public class SelectScanAndShoot implements Estrategia {

	@Override
	public void estategia(Jugador jugador) {
		int i = (int) Math.round(Math.random() * 3);
		int j = (int) Math.round(Math.random() * 3);
		if(jugador.scan(i, j)){
			jugador.shoot(i, j);
		}
	}

}
