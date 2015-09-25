/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.mods.PlayerSpeedUp;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.mods.ProjectileSpeedUp;

/**
 *
 * @author faris
 */
public enum PowerupTypes {

    // player mods:
    /**
     *
     */
    PLAYER_SPEED_UP(new PlayerSpeedUp(), "/data/gui/img/speedup.png", true, false),
    PROJECTILE_SPEED_UP(new ProjectileSpeedUp(), "/data/gui/img/projectilespeedup.png", true, false),
    PROJECTILE_DELAY(new ProjectileSpeedUp(), "/data/gui/img/spike.png", true, false);

    private IModifier mod;
    private IDecorator decor;
    private final InputStream imageStream;
    private boolean playerPickup, bubblePickup;
    private final static double imageHeight = 100;
    private final static double imageWidth = 100;

    private PowerupTypes(IModifier mod, String imageName, boolean playerPickup, boolean bubblePickup) {
        this.mod = mod;
        
        InputStream is = null;
        try {
            is = getClass().getResource(imageName).openStream();
        }
        catch (IOException ex) {
            Logger.getLogger(PowerupTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageStream = is;
        
        this.decor = (IDecorator) mod;
        this.playerPickup = playerPickup;
        this.bubblePickup = bubblePickup;
    }

    public static PowerupTypes getRandomPowerup() {
        PowerupTypes[] mods = PowerupTypes.values();
        int AMOUNT_OF_MOD_TYPES = mods.length;
        int selectedType = (new Random()).nextInt(AMOUNT_OF_MOD_TYPES);
        
        return mods[selectedType];
    }
    
    public IModifier getMod() {
        return mod;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    public boolean isPlayerPickup() {
        return playerPickup;
    }

    public boolean isBubblePickup() {
        return bubblePickup;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public IDecorator getDecor() {
        return decor;
    }
}
