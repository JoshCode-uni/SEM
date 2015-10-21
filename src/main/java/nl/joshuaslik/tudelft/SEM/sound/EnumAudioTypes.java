/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;


/**
 *
 * @author Faris
 */
public enum EnumAudioTypes {
    
    DOUBLE_KILL(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/Double_Kill.wav").toExternalForm()), false),
    MULTI_KILL(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/MultiKill.wav").toExternalForm()), false),
    MEGA_KILL(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/MegaKill.wav").toExternalForm()), false),
    MONSTER_KILL(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/MonsterKill.wav").toExternalForm()), true),
    KILLING_SPREE(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/Killing_Spree.wav").toExternalForm()), true),
    FIRST_BLOOD(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/first_blood.wav").toExternalForm()), false),
    BALL_OUT_OF_PLAY(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/BallOutOfPlay.wav").toExternalForm()), false),
    LAST_MAN_STANDING(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/last_man_standing.wav").toExternalForm()), false),
    HUMILIATING_DEFEAT(new SoundEffect(EnumAudioTypes.class.getResource("/data/sound/effects/Humiliating_defeat.wav").toExternalForm()), false);
    
    private final SoundEffect sound;
    
    private EnumAudioTypes(final SoundEffect sound, final boolean echo) {
        this.sound = sound;
        sound.setEcho(echo);
    }
    
    protected SoundEffect getSound() {
        return sound;
    }
}
