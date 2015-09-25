///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player;
//
//import java.util.Random;
//import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.playerMods.PlayerSpeedUp;
//import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.playerMods.ProjectileSpeedUp;
//import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.playerMods.ProjectileSpikeDelayUp;
//
///**
// *
// * @author faris
// */
//public enum EnumPlayerMods {
//    
//    PLAYER_SPEED_UP(new PlayerSpeedUp()),
//    PROJECTILE_SPEED_UP(new ProjectileSpeedUp()),
//    PROJECTILE_SPIKE_DELAY_UP(new ProjectileSpikeDelayUp());
//    
//    private final AbstractPlayerModifierDecorator mod;
//    
//    private EnumPlayerMods(AbstractPlayerModifierDecorator dec) {
//        mod = dec;
//    }
//    
//    public AbstractPlayerModifierDecorator getMod() {
//        return mod;
//    }
//    
//    public static EnumPlayerMods getRandomPlayerMod() {
//        EnumPlayerMods[] thisarr = EnumPlayerMods.values();
//        Random rand = new Random();
//        return thisarr[rand.nextInt(thisarr.length)];
//    }
//}
