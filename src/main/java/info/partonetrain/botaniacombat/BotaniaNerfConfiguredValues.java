package info.partonetrain.botaniacombat;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BotaniaNerfConfiguredValues { //have default values loaded so mixins don't classload config
    public static float terraBladeDamage = 7;
    public static int terrasteelBeamCost = 0;
    public static boolean slowTerraArmor = false;
    public static boolean conformSoulscribe = false;
    public static int daggerDamageModifier = -3;
    public static float daggerSpeed =  -1.6f;
    public static float chakramDamage = 12;
    public static int starcallerProjectileCost = 0;
    public static float fallingStarNormalDamage = 5;
    public static float fallingStarHighDamage = 10;
    public static float missileDamage = 7f;
    public static int tiaraCost = 35;
    public static int tiaraOverkillCost = 105;
    public static int tiaraExtraTime = 0;
    public static int tiaraAdditionalRow = 0;
    public static int manaBlasterCost = 120;
    public static float dmgLensDamage = 8;
    public static float odinHealthMod = 20f;
    public static AttributeModifier.Operation odinHealthOperation = AttributeModifier.Operation.ADDITION;
    public static float babylonDamage = 20f;
    public static void init(){
        terraBladeDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.terraBladeDamage;
        terrasteelBeamCost = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.terrasteelBeamCost;
        slowTerraArmor = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.slowTerraArmor;
        conformSoulscribe = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.conformSoulscribe;
        daggerDamageModifier = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().daggerDamageModifier;
        daggerSpeed = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().daggerSpeed;
        chakramDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.chakramDamage;
        starcallerProjectileCost = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.starcallerProjectileCost;
        fallingStarNormalDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.fallingStarNormalDamage;
        fallingStarHighDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.fallingStarHighDamage;
        missileDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.missileDamage;
        manaBlasterCost = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.manaBlasterCost;
        tiaraCost = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.tiaraCost;
        tiaraOverkillCost = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.tiaraOverkillCost;
        tiaraExtraTime = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.tiaraExtraTime;
        tiaraAdditionalRow = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.tiaraAdditionalRow;
        dmgLensDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.dmgLensDamage;
        odinHealthMod = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthMod;
        odinHealthOperation = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthOperation;
        babylonDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.babylonDamage;
    }


    public static void readFromFileDirectly() {
        //this is necessary because Botania may register Soulscribe before config is read and conformSoulscribe is set
        //however, mixin is always applied first; SoulscribeItemMixin calls this method
        if (!conformSoulscribe) { //if it's false then it might not have been set
            BotaniaCombat.LOGGER.info("Reading from config file directly to confirm conformSoulscribe option");
            final String configFileLoc = System.getProperty("user.dir") + "\\config\\botaniacombat.json5";
            Path configFilePath = Paths.get(configFileLoc); //converts to correct path regardless of platform
            try {
                String conformSoulscribeLine = Files.readAllLines(configFilePath).toString();
                if (conformSoulscribeLine.contains("\"conformSoulscribe\": true")) {
                    conformSoulscribe = true;
                }
            } catch (IOException e) {
                BotaniaCombat.LOGGER.error("config error:" + e);
                BotaniaCombat.LOGGER.info("Don't fret! Above error is most likely one-time occurrence from config file not existing yet");
            }
        }
    }
}
