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
    
        UT_DOUBLE_KILL(new SoundEffect("/data/sound/announcer/Double_Kill.wav"), false),
    UT_MULTI_KILL(new SoundEffect("/data/sound/announcer/MultiKill.wav"), false),
    UT_MEGA_KILL(new SoundEffect("/data/sound/announcer/MegaKill.wav"), false),
    UT_MONSTER_KILL(new SoundEffect("/data/sound/announcer/MonsterKill.wav"), true),
    UT_KILLING_SPREE(new SoundEffect("/data/sound/announcer/Killing_Spree.wav"), true),
    UT_FIRST_BLOOD(new SoundEffect("/data/sound/announcer/first_blood.wav"), false),
    UT_HUMILIATING_DEFEAT(new SoundEffect("/data/sound/announcer/Humiliating_defeat.wav"), false),
    GLADOS_DOMINATING(new SoundEffect("/data/sound/announcer/glados-dominating.wav"), false),
    GLADOS_DOUBLEKILL(new SoundEffect("/data/sound/announcer/glados-doublekill.wav"), false),
    GLADOS_FIRSTBLOOD(new SoundEffect("/data/sound/announcer/glados-firstblood.wav"), false),
    GLADOS_GODLIKE(new SoundEffect("/data/sound/announcer/glados-godlike.wav"), false),
    GLADOS_MEGAKILL_1(new SoundEffect("/data/sound/announcer/glados-megakil-1.wav"), false),
    GLADOS_MEGAKILL_2(new SoundEffect("/data/sound/announcer/glados-megakill-2.wav"), false),
    GLADOS_MONSTERKILL_1(new SoundEffect("/data/sound/announcer/glados-monsterkill-1.wav"), false),
    GLADOS_MONSTERKILL_2(new SoundEffect("/data/sound/announcer/glados-monsterkill-2.wav"), false),
    GLADOS_MULTIKILL_1(new SoundEffect("/data/sound/announcer/glados-multikill-1.wav"), false),
    GLADOS_MULTIKILL_2(new SoundEffect("/data/sound/announcer/glados-multikill-3.wav"), false),
    GLADOS_MULTIKILL_3(new SoundEffect("/data/sound/announcer/glados-multikill-3.wav"), false),
    GLADOS_QUADRAKILL(new SoundEffect("/data/sound/announcer/glados-quadrakill.wav"), false),
    GLADOS_RAMPAGE(new SoundEffect("/data/sound/announcer/glados-rampage.wav"), false),
    GLADOS_TRIPLEKILL(new SoundEffect("/data/sound/announcer/glados-triplekill.wav"), false),
    GLADOS_ULTRAKILL(new SoundEffect("/data/sound/announcer/glados-ultrakill.wav"), false),
    GLADOS_UNCATEGORIZED(new SoundEffect("/data/sound/announcer/glados-uncatagorized-1.wav"), false),
    GLADOS_UNSTROPPABLE(new SoundEffect("/data/sound/announcer/glados-unstoppable.wav"), false),
    GLADOS_WIN_1(new SoundEffect("/data/sound/announcer/glados-win-1.wav"), false),
    GLADOS_WIN_MULTI(new SoundEffect("/data/sound/announcer/glados-win-multiplayer.wav"), false),
    LIFE_1(new SoundEffect("/data/sound/effects/1-up-1.wav"), false),
    LIFE_2(new SoundEffect("/data/sound/effects/1-up-2.wav"), false),
    LIFE_3(new SoundEffect("/data/sound/effects/1-up-3.wav"), false),
    COLLIDE_1(new SoundEffect("/data/sound/effects/collide-1.wav"), false),
    COLLIDE_2(new SoundEffect("/data/sound/effects/collide-2.wav"), false),
    POP_1(new SoundEffect("/data/sound/effects/pop-1.wav"), false),
    POP_2(new SoundEffect("/data/sound/effects/pop-2.wav"), false),
    POP_3(new SoundEffect("/data/sound/effects/pop-3.wav"), false),
    SHOOT_1(new SoundEffect("/data/sound/effects/shoot-1.wav"), false),
    SHOOT_2(new SoundEffect("/data/sound/effects/shoot-2.wav"), false),
    WALK_LOOP(new SoundEffect("/data/sound/effects/walk-loop.wav"), false),
    COIN(new SoundEffect("/data/sound/effects/coin.wav"), false);
    
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
