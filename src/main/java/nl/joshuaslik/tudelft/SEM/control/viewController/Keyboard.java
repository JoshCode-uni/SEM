package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;

/**
 * Listen to keyboard events. Inspired by:
 * http://stackoverflow.com/questions/29057870/in-javafx-how-do-i-move-a-sprite-across-the-screen
 *
 * @author faris
 */
public class Keyboard implements IKeyboard {

    private BitSet keyboard = new BitSet();

    private static final KeyCode spLeftKey = KeyCode.LEFT;
    private static final KeyCode spRightKey = KeyCode.RIGHT;
    private static final KeyCode spShoot = KeyCode.SPACE;
    private static final KeyCode mpLeftKey = KeyCode.A;
    private static final KeyCode mpRightKey = KeyCode.D;
    private static final KeyCode mp2Shoot = KeyCode.W;
    private static final KeyCode mpShoot = KeyCode.UP;

    private Scene scene;

    /**
     * Create a keyboard which listens to keyboard events performed in the given scene.
     *
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
    private final EventHandler<KeyEvent> keyPressedEventHandler = (KeyEvent event) -> {
        keyboard.set(event.getCode().ordinal(), true);
    };

    /**
     * Handle the "key released" event.
     */
    private final EventHandler<KeyEvent> keyReleasedEventHandler = (KeyEvent event) -> {
        keyboard.set(event.getCode().ordinal(), false);
    };

    /**
     * Check if left arrow is pressed.
     *
     * @return true if left arrow is pressed, otherwise false.
     */
    @Override
    public boolean isMoveLeft(boolean p2) {
        if (!p2) {
            return keyboard.get(spLeftKey.ordinal()) && !keyboard.get(spRightKey.ordinal());
        } else if ((GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP) || GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) && p2) {
            return keyboard.get(mpLeftKey.ordinal()) && !keyboard.get(mpRightKey.ordinal());
        }
        return false;
    }

    /**
     * Check if right arrow is pressed.
     *
     * @param p2 if this is called by player 2.
     * @return true if right arrow is pressed, otherwise false.
     */
    @Override
    public boolean isMoveRight(boolean p2) {
        if (!p2) {
            return keyboard.get(spRightKey.ordinal()) && !keyboard.get(spLeftKey.ordinal());
        } else if ((GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP) || GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) && p2) {
            return keyboard.get(mpRightKey.ordinal()) && !keyboard.get(mpLeftKey.ordinal());
        }
        return false;
    }

    /**
     * Check if spacebar is pressed.
     *
     * @param p2 if this is called by player 2.
     * @return true if spacebar is pressed, otherwise false.
     */
    @Override
    public boolean isShoot(boolean p2) {
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.SINGLE_PLAYER) && !p2) {
            return keyboard.get(spShoot.ordinal());
        } else if (!p2) {
            return keyboard.get(mpShoot.ordinal());
        } else if (p2) {
            return keyboard.get(mp2Shoot.ordinal());
        }
        return false;
    }

    Scene getScene() {
        return scene;
    }

    void setBitSet(BitSet kb) {
        keyboard = kb;
    }
}
