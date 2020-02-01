package pr2.game.Interfaces;

import pr2.game.Exceptions.MoveException;
import pr2.game.Exceptions.NoShockwaveException;
import pr2.game.Exceptions.MissileInFlightException;
import pr2.game.Exceptions.NoSuperMissileException;

public interface IPlayerController {
	
	// PLAYER ACTIONS	
	public boolean move (int numCells) throws MoveException;
	public boolean shootLaser() throws MissileInFlightException;
	public boolean shootSuperMissile() throws MissileInFlightException,NoSuperMissileException;
	public boolean shockWave() throws NoShockwaveException;
	
	// CALLBACKS
	public void receivePoints(int points);
	public void disableShockWave();
	public void enableShockWave();
	public void enableMissile();
	public void disableMissile();
	
}
