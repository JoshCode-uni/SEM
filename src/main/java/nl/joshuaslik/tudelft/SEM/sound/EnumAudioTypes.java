/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;


/**
 * An enum which stores the paths to the files containing the sound effects.
 * @author Faris
 */
public enum EnumAudioTypes {
    
    UT_DOUBLE_KILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/Double_Kill.wav").toExternalForm()), false),
    UT_MULTI_KILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/MultiKill.wav").toExternalForm()), false),
    UT_MEGA_KILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/MegaKill.wav").toExternalForm()), false),
    UT_MONSTER_KILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/MonsterKill.wav").toExternalForm()), true),
    UT_KILLING_SPREE(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/Killing_Spree.wav").toExternalForm()), true),
    UT_FIRST_BLOOD(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/first_blood.wav").toExternalForm()), false),
    UT_HUMILIATING_DEFEAT(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/Humiliating_defeat.wav").toExternalForm()), false),
    GLADOS_DOMINATING(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-dominating.wav").toExternalForm()), false),
    GLADOS_DOUBLEKILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-doublekill.wav").toExternalForm()), false),
    GLADOS_FIRSTBLOOD(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-firstblood.wav").toExternalForm()), false),
    GLADOS_GODLIKE(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-godlike.wav").toExternalForm()), false),
    GLADOS_MEGAKILL_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-megakil-1.wav").toExternalForm()), false),
    GLADOS_MEGAKILL_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-megakill-2.wav").toExternalForm()), false),
    GLADOS_MONSTERKILL_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-monsterkill-1.wav").toExternalForm()), false),
    GLADOS_MONSTERKILL_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-monsterkill-2.wav").toExternalForm()), false),
    GLADOS_MULTIKILL_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-multikill-1.wav").toExternalForm()), false),
    GLADOS_MULTIKILL_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-multikill-3.wav").toExternalForm()), false),
    GLADOS_MULTIKILL_3(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-multikill-3.wav").toExternalForm()), false),
    GLADOS_QUADRAKILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-quadrakill.wav").toExternalForm()), false),
    GLADOS_RAMPAGE(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-rampage.wav").toExternalForm()), false),
    GLADOS_TRIPLEKILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-triplekill.wav").toExternalForm()), false),
    GLADOS_ULTRAKILL(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-ultrakill.wav").toExternalForm()), false),
    GLADOS_UNCATEGORIZED(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-uncatagorized-1.wav").toExternalForm()), false),
    GLADOS_UNSTROPPABLE(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-unstoppable.wav").toExternalForm()), false),
    GLADOS_WIN_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-win-1.wav").toExternalForm()), false),
    GLADOS_WIN_MULTI(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/announcer/glados-win-multiplayer.wav").toExternalForm()), false),
    LIFE_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/1-up-1.wav").toExternalForm()), false),
    LIFE_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/1-up-2.wav").toExternalForm()), false),
    LIFE_3(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/1-up-3.wav").toExternalForm()), false),
    COLLIDE_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/collide-1.wav").toExternalForm()), false),
    COLLIDE_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/collide-2.wav").toExternalForm()), false),
    POP_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/pop-1.wav").toExternalForm()), false),
    POP_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/pop-2.wav").toExternalForm()), false),
    POP_3(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/pop-3.wav").toExternalForm()), false),
    SHOOT_1(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/shoot-1.wav").toExternalForm()), false),
    SHOOT_2(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/shoot-2.wav").toExternalForm()), false),
    WALK_LOOP(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/walk-loop.wav").toExternalForm()), false),
    COIN(new SoundEffect(EnumAudioTypes.class.
            getResource("/data/sound/effects/coin.wav").toExternalForm()), false);
    
    private final SoundEffect sound;
    
    /**
     * Private enum contructor.
     * @param sound SoundEffect object with the path to the sound file set.
     * @param echo if an echo should be played.
     */
    private EnumAudioTypes(final SoundEffect sound, final boolean echo) {
        this.sound = sound;
        sound.setEcho(echo);
    }
    
    /**
     * Get the sound effect of this enum instance.
     * @return a SoundEffect object.
     */
    protected SoundEffect getSound() {
        return sound;
    }
}
