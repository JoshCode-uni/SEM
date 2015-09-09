package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

	private BitSet keyboard = new BitSet();

	private static final KeyCode leftKey = KeyCode.LEFT;
	private static final KeyCode rightKey = KeyCode.RIGHT;
	private static final KeyCode shoot = KeyCode.SPACE;
	Scene scene;

	public Keyboard(Scene scene) {
		this.scene = scene;
	}

	public void addListeners() {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	public void removeListeners() {
		scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			keyboard.set(event.getCode().ordinal(), true);
		}
	};
	private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			keyboard.set(event.getCode().ordinal(), false);
		}
	};

	public boolean isMoveLeft() {
		return keyboard.get(leftKey.ordinal()) && !keyboard.get(rightKey.ordinal());
	}

	public boolean isMoveRight() {
		return keyboard.get(rightKey.ordinal()) && !keyboard.get(leftKey.ordinal());
	}

	public boolean isShoot() {
		return keyboard.get(shoot.ordinal());
	}
}
