/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup;

import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.bubbleMods.BubbleSlowdown;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods.PlayerSpeedUp;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods.ProjectileSpeedUp;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods.ProjectileSpikeDelayUp;

/**
 * Store all kinds of powerups and their images.
 * @author faris
 */
public enum EnumPowerupTypes {

    PLAYER_SPEED_UP(PlayerSpeedUp.class, "/data/gui/img/speedup.png", true, false),
    PROJECTILE_SPEED_UP(ProjectileSpeedUp.class, "/data/gui/img/projectilespeedup.png", true, false),
    PROJECTILE_DELAY(ProjectileSpikeDelayUp.class, "/data/gui/img/spike.png", true, false),
    BUBBLE_SLOW_DOWN(BubbleSlowdown.class, "/data/gui/img/slowdown.png", false, true);

    private final Class mod;
    private final String imageName;
    private final boolean playerPickup, bubblePickup;
    private final static double imageHeight = 50;
    private final static double imageWidth = 50;

    /**
     * Private enum constructor.
     * @param mod the modifier.
     * @param imageName the image path.
     * @param playerPickup if it is a player pickup.
     * @param bubblePickup if it is a bubble pickup.
     */
    private EnumPowerupTypes(Class mod, String imageName, boolean playerPickup, boolean bubblePickup) {
        this.mod = mod;
        this.imageName = imageName;
        this.playerPickup = playerPickup;
        this.bubblePickup = bubblePickup;
    }

    /**
     * Get a random enum powerup.
     * @return an enum powerup.
     */
    public static EnumPowerupTypes getRandomPowerup() {
        EnumPowerupTypes[] mods = EnumPowerupTypes.values();
        int AMOUNT_OF_MOD_TYPES = mods.length;
        int selectedType = (new Random()).nextInt(AMOUNT_OF_MOD_TYPES);

        return mods[selectedType];
    }

    /**
     * Get the stream of the image.
     * @return stream of the image.
     */
    public InputStream getImageStream() {
        return getClass().getResourceAsStream(imageName);
    }

    /**
     * @return if this is a player pickup.
     */
    public boolean isPlayerPickup() {
        return playerPickup;
    }

    /**
     * @return if this is a bubble pickup.
     */
    public boolean isBubblePickup() {
        return bubblePickup;
    }

    /**
     * Get the height of the image.
     * @return the height of the image.
     */
    public double getImageHeight() {
        return imageHeight;
    }

    /**
     * Get the width of the image.
     * @return the width of the image.
     */
    public double getImageWidth() {
        return imageWidth;
    }

    /**
     * Get the decorator.
     * @return the decorator.
     */
    public IDecorator getDecor() {
        try {
            return (IDecorator) mod.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnumPowerupTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
