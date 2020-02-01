package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Exceptions.FileContentsException;
import pr2.game.Interfaces.IExecuteRandomActions;
import pr2.game.util.FileContentsVerifier;

public class SmartBomb extends GameObject {

	private static final String SERIAL = "S";
	private static final int LIFE = 1;
	private static final int DAMAGE = 1;
	
	public SmartBomb(Game game, int y, int x, int live) {
		super(game, y, x, live);
	}

	public SmartBomb() {
		super();
	}

	@Override
	public void computerAction() {
	}

	@Override
	public void onDelete() {
	}

	@Override
	public void move() {
		//Si sale se recoloca, solo se mueve hacia abajo, en linea recta o en diagonal 1 casilla
		//La posicion que elige tiene que ser aleatoria 33% cada una
		//izquierda	0-33% 
		//recto		33%-66%
		//derecha	66%-100%;
		if (IExecuteRandomActions.canDescendSmartBomb(game)) {
			String moveTo[]=IExecuteRandomActions.chooseSmartBombNextMove(game, fila, columna).split(",");
			int nuevaFila=Integer.parseInt(moveTo[0]);
			int nuevaColumna=Integer.parseInt(moveTo[1]);
			
			if(!game.isOnBoard(nuevaFila,nuevaColumna)) {
				fila=0;
			}else {
				fila=nuevaFila;
				columna=nuevaColumna;
			}
				
		}
	}

	public boolean performAttack(GameObject other) {
		if (other.receiveSmartBombAttack(DAMAGE)) {
			live = 0;
		}
		return true;
	}
	
	public boolean receiveMissileAttack(int damage) {
		live -= damage;
		return true;
	}
	
	public boolean receiveSmartBombAttack(int damage) {
		live -= damage;
		return true;
	}
	
	@Override
	public String toString() {
		return "S[" + live + "]";
	}

	@Override
	public String stringify() {
		String info = "";
		info = SERIAL+";" + fila + "," + columna+";"+live;
		return info;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier)
			throws FileContentsException {
		/*
		 * EJEMPLO S;0,4;1
		 */
		try {
			String[] smartBomb = stringFromFile.split(";");

			if (smartBomb[0].equals(SERIAL) && verifier.verifySmartBombString(stringFromFile, game)) {
				int x = Integer.parseInt(smartBomb[1].substring(0, 1));
				int y = Integer.parseInt(smartBomb[1].substring(2, 3));

				SmartBomb smart= new SmartBomb(game, x, y, LIFE);
				return new GameObject[] { smart };
			}
		} catch (StringIndexOutOfBoundsException ex) {
			return null;
		}

		return null;
	}

	public static int getLife() {
		return LIFE;
	}

}
