package StrawberrySpireMod.monsters.elite;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.combat.*;

import StrawberrySpireMod.powers.*;

public class ElectricalPylon extends AbstractMonster {

    public static final String ID = "strawberrySpire:ElectricalPylon";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = 4.0F;
    private static final float HB_Y = 68.0F;
    private static final float HB_W = 160.0F;
    private static final float HB_H = 120.0F;
    private static final int HP_MIN = 38;
    private static final int HP_MAX = 42;
    private static final int ASC_HP_MIN = 41;
    private static final int ASC_HP_MAX = 45;
    private static final byte DISCHARGE = 1;
    private static final byte DISINTEGRATE = 2;
    private static final byte CHARGE = 3;
    private static final String DISCHARGE_NAME = MOVES[0];
    private static final String DISINTEGRATE_NAME = MOVES[1];
    private static final String CHARGE_NAME = MOVES[2];
    private static final int INNATE_CONDUCTION_AMOUNT = 3;
    private static final int DISCHARGE_DAMAGE = 4;
    private static final int DISCHARGE_STRENGTH_GAIN_AMOUNT = 1;
    private static final int ASC2_DISCHARGE_STRENGTH_GAIN_AMOUNT = 2;
    private static final int ASC_DISCHARGE_DAMAGE = 5;
    private static final int DISINTEGRATE_DAMAGE = 8;
    private static final int ASC_DISINTEGRATE_DAMAGE = 9;
    private static final int CHARGE_STRENGTH_GAIN_AMOUNT = 1;
    private int innateConductionAmount;
    private int dischargeDamage;
    private int dischargeStrengthGainAmount;
    private int disintegrateDamage;
    private int chargeStrengthAmount;

    public ElectricalPylon(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder3.png", x, y);
        this.type = EnemyType.ELITE;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(ASC_HP_MIN, ASC_HP_MAX);
        }
        else {
            setHp(HP_MIN, HP_MAX);
        }
        if (AbstractDungeon.ascensionLevel >= 18) {
            this.innateConductionAmount = INNATE_CONDUCTION_AMOUNT;
            this.dischargeDamage = ASC_DISCHARGE_DAMAGE;
            this.dischargeStrengthGainAmount = ASC2_DISCHARGE_STRENGTH_GAIN_AMOUNT;
            this.disintegrateDamage = ASC_DISINTEGRATE_DAMAGE;
            this.chargeStrengthAmount = CHARGE_STRENGTH_GAIN_AMOUNT;
        }
        else if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateConductionAmount = INNATE_CONDUCTION_AMOUNT;
            this.dischargeDamage = ASC_DISCHARGE_DAMAGE;
            this.dischargeStrengthGainAmount = DISCHARGE_STRENGTH_GAIN_AMOUNT;
            this.disintegrateDamage = ASC_DISINTEGRATE_DAMAGE;
            this.chargeStrengthAmount = CHARGE_STRENGTH_GAIN_AMOUNT;
        }
        else {
            this.innateConductionAmount = INNATE_CONDUCTION_AMOUNT;
            this.dischargeDamage = DISCHARGE_DAMAGE;
            this.dischargeStrengthGainAmount = DISCHARGE_STRENGTH_GAIN_AMOUNT;
            this.disintegrateDamage = DISINTEGRATE_DAMAGE;
            this.chargeStrengthAmount = CHARGE_STRENGTH_GAIN_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, dischargeDamage));
        this.damage.add(new DamageInfo(this, disintegrateDamage));
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ConductionPower(this, innateConductionAmount), innateConductionAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case DISCHARGE: {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05f));
                if (Settings.FAST_MODE) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.1F));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.3F));
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0)));
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, this.dischargeStrengthGainAmount), this.dischargeStrengthGainAmount));
                }
                break;
            }
            case DISINTEGRATE: {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5f)); // lol this is the sentry's code
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));
                if (Settings.FAST_MODE) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.1F));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.NONE, Settings.FAST_MODE));
                break;
            }
            case CHARGE: {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, chargeStrengthAmount), chargeStrengthAmount));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        if (lastMove(DISCHARGE)) {
            this.setMove(DISINTEGRATE_NAME, DISINTEGRATE, Intent.ATTACK, this.damage.get(1).base);
        }
        else if (lastMove(DISINTEGRATE)) {
            this.setMove(CHARGE_NAME, CHARGE, Intent.BUFF);
        }
        else {
            this.setMove(DISCHARGE_NAME, DISCHARGE, Intent.ATTACK_BUFF, this.damage.get(0).base);
        }
    }
}
