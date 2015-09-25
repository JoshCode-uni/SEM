/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.util.Random;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.EnumPlayerMods;

/**
 *
 * @author faris
 */
public class RandomMod {
    
    public static IModifier getRandomModifier() {
        int AMOUNT_OF_MOD_TYPES = 1;
        int selectedType = (new Random()).nextInt(AMOUNT_OF_MOD_TYPES);
        
        switch (selectedType) {
            case 0:
                return getRandomPlayerModifier();
            default:
                throw new AssertionError();
        }
    }
    
    private static IModifier getRandomPlayerModifier() {
        return EnumPlayerMods.getRandomPlayerMod().getMod();
    }
}
