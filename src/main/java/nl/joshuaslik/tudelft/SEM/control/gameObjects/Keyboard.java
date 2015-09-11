package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Listen to keyboard events. Inspired by:
 * http://stackoverflow.com/questions/29057870/in-javafx-how-do-i-move-a-sprite-across-the-screen
 * @author faris
 */
public class Keyboard {

	private BitSet keyboard = new BitSet();

	private static final KeyCode leftKey = KeyCode.LEFT;
	private static final KeyCode rightKey = KeyCode.RIGHT;
	private static final KeyCode shoot = KeyCode.SPACE;
	Scene scene;

	/**
	 * Create a keyboard which listens to keyboard events performed in the given
	 * scene.
	 * @param scene the scene to add the listeners to.
	 */
	public Keyboard(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Add listeners to the scene.
	 */
	public void addListeners() {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	/**
	 * Remove listeners from the scene.
	 */
	public void removeListeners() {
		scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	/**
	 * Handle the "key pressed" event.
	 */
	private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			keyboard.set(event.getCode().ordinal(), true);
		}
	};
	
	/**
	 * Handle the "key released" event.
	 */
	private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			keyboard.set(event.getCode().ordinal(), false);
		}
	};

	/**
	 * Check if left arrow is pressed.
	 * @return true if left arrow is pressed, otherwise false.
	 */
	public boolean isMoveLeft() {
		return keyboard.get(leftKey.ordinal()) && !keyboard.get(rightKey.ordinal());
	}

	/**
	 * Check if right arrow is pressed.
	 * @return true if right arraow is pressed, otherwise false.
	 */
	public boolean isMoveRight() {
		return keyboard.get(rightKey.ordinal()) && !keyboard.get(leftKey.ordinal());
	}

	/**
	 * Check if spacebar is pressed.
	 * @return true if spacebar is pressed, otherwise false.
	 */
	public boolean isShoot() {
		return keyboard.get(shoot.ordinal());
	}
}
