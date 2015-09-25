///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.bubble;
//
//import java.util.Random;
//import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.bubble.bubbleMods.BubbleSlowdown;
//
///**
// *
// * @author faris
// */
//public enum EnumBubbleMods {
//
//    BUBBLE_SLOW_DOWN(new BubbleSlowdown());
//
//    private final AbstractBubbleModifierDecorator mod;
//
//    private EnumBubbleMods(AbstractBubbleModifierDecorator dec) {
//        mod = dec;
//    }
//
//    public AbstractBubbleModifierDecorator getMod() {
//        return mod;
//    }
//
//    public static EnumBubbleMods getRandomPlayerMod() {
//        EnumBubbleMods[] thisarr = EnumBubbleMods.values();
//        Random rand = new Random();
//        return thisarr[rand.nextInt(thisarr.length)];
//    }
//}
