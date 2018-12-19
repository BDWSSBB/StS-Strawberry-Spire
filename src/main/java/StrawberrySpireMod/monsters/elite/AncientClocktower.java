package StrawberrySpireMod.monsters.elite;

import com.badlogic.gdx.math.*;
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
import com.megacrit.cardcrawl.vfx.combat.*;

import StrawberrySpireMod.powers.*;

public class AncientClocktower extends AbstractMonster {

    public static final String ID = "strawberrySpire:AncientClocktower";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = 8.0F;
    private static final float HB_Y = 136.0F;
    private static final float HB_W = 320.0F;
    private static final float HB_H = 240.0F;
    private static final int HP_MIN = 164;
    private static final int HP_MAX = 168;
    private static final int ASC_HP_MIN = 170;
    private static final int ASC_HP_MAX = 174;
    private static final byte TOLL = 1;
    private static final byte EARRITATE = 2;
    private static final byte MAINTENANCE = 3;
    private static final String TOLL_NAME = MOVES[0];
    private static final String EARRITATE_NAME = MOVES[1];
    private static final String MAINTENANCE_NAME = MOVES[2];
    private static final int INNATE_ARTIFACT_AMOUNT = 2;
    private static final int INNATE_MINUTE_OF_SILENCE_STRENGTH_DOWN_AMOUNT = 60;
    private static final int INNATE_MINUTE_OF_SILENCE_CARD_AMOUNT = 12;
    private static final int INNATE_HOUR_OF_TOLLING_STRENGTH_GAIN_AMOUNT = 2;
    private static final int ASC_INNATE_HOUR_OF_TOLLING_STRENGTH_GAIN_AMOUNT = 3;
    private static final int INNATE_HOUR_OF_TOLLING_TURN_AMOUNT = 3;
    private static final int TOLL_DAMAGE = 5;
    private static final int TOLL_HIT_AMOUNT = 3;
    private static final int ASC_TOLL_DAMAGE = 6;
    private static final int EARRITATE_DAMAGE = 12;
    private static final int EARRITATE_WEAK_AMOUNT = 1;
    private static final int ASC_EARRITATE_DAMAGE = 15;
    private static final int ASC2_EARRITATE_WEAK_AMOUNT = 2;
    private static final int MAINTENANCE_HEAL_AMOUNT = 40;
    private static final int MAINTENANCE_ARTIFACT_AMOUNT = 1;
    private static final int ASC2_MAINTENANCE_HEAL_AMOUNT = 50;
    private int innateArtifactAmount;
    private int innateMinuteOfSilenceStrengthDownAmount;
    private int innateMinuteOfSilenceCardAmount;
    private int innateHourOfTollingStrengthGainAmount;
    private int innateHourOfTollingTurnAmount;
    private int tollDamage;
    private int tollHitAmount;
    private int earritateDamage;
    private int earritateWeakAmount;
    private int maintenanceHealAmount;
    private int maintenanceArtifactAmount;

    public AncientClocktower(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/bigPlaceholder2.png", x, y);
        this.type = EnemyType.ELITE;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(ASC_HP_MIN, ASC_HP_MAX);
        }
        else {
            setHp(HP_MIN, HP_MAX);
        }
        if (AbstractDungeon.ascensionLevel >= 18) {
            this.innateArtifactAmount = INNATE_ARTIFACT_AMOUNT;
            this.innateMinuteOfSilenceStrengthDownAmount = INNATE_MINUTE_OF_SILENCE_STRENGTH_DOWN_AMOUNT;
            this.innateMinuteOfSilenceCardAmount = INNATE_MINUTE_OF_SILENCE_CARD_AMOUNT;
            this.innateHourOfTollingStrengthGainAmount = ASC_INNATE_HOUR_OF_TOLLING_STRENGTH_GAIN_AMOUNT;
            this.innateHourOfTollingTurnAmount = INNATE_HOUR_OF_TOLLING_TURN_AMOUNT;
            this.tollDamage = ASC_TOLL_DAMAGE;
            this.tollHitAmount = TOLL_HIT_AMOUNT;
            this.earritateDamage = ASC_EARRITATE_DAMAGE;
            this.earritateWeakAmount = ASC2_EARRITATE_WEAK_AMOUNT;
            this.maintenanceHealAmount = ASC2_MAINTENANCE_HEAL_AMOUNT;
            this.maintenanceArtifactAmount = MAINTENANCE_ARTIFACT_AMOUNT;
        }
        else if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateArtifactAmount = INNATE_ARTIFACT_AMOUNT;
            this.innateMinuteOfSilenceStrengthDownAmount = INNATE_MINUTE_OF_SILENCE_STRENGTH_DOWN_AMOUNT;
            this.innateMinuteOfSilenceCardAmount = INNATE_MINUTE_OF_SILENCE_CARD_AMOUNT;
            this.innateHourOfTollingStrengthGainAmount = INNATE_HOUR_OF_TOLLING_STRENGTH_GAIN_AMOUNT;
            this.innateHourOfTollingTurnAmount = INNATE_HOUR_OF_TOLLING_TURN_AMOUNT;
            this.tollDamage = ASC_TOLL_DAMAGE;
            this.tollHitAmount = TOLL_HIT_AMOUNT;
            this.earritateDamage = ASC_EARRITATE_DAMAGE;
            this.earritateWeakAmount = EARRITATE_WEAK_AMOUNT;
            this.maintenanceHealAmount = MAINTENANCE_HEAL_AMOUNT;
            this.maintenanceArtifactAmount = MAINTENANCE_ARTIFACT_AMOUNT;
        }
        else {
            this.innateArtifactAmount = INNATE_ARTIFACT_AMOUNT;
            this.innateMinuteOfSilenceStrengthDownAmount = INNATE_MINUTE_OF_SILENCE_STRENGTH_DOWN_AMOUNT;
            this.innateMinuteOfSilenceCardAmount = INNATE_MINUTE_OF_SILENCE_CARD_AMOUNT;
            this.innateHourOfTollingStrengthGainAmount = INNATE_HOUR_OF_TOLLING_STRENGTH_GAIN_AMOUNT;
            this.innateHourOfTollingTurnAmount = INNATE_HOUR_OF_TOLLING_TURN_AMOUNT;
            this.tollDamage = TOLL_DAMAGE;
            this.tollHitAmount = TOLL_HIT_AMOUNT;
            this.earritateDamage = EARRITATE_DAMAGE;
            this.earritateWeakAmount = EARRITATE_WEAK_AMOUNT;
            this.maintenanceHealAmount = MAINTENANCE_HEAL_AMOUNT;
            this.maintenanceArtifactAmount = MAINTENANCE_ARTIFACT_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, tollDamage));
        this.damage.add(new DamageInfo(this, earritateDamage));
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, this.innateArtifactAmount), this.innateArtifactAmount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new MinuteOfSilencePower(this, this.innateMinuteOfSilenceCardAmount, this.innateMinuteOfSilenceStrengthDownAmount), 0));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new HourOfTollingPower(this, this.innateHourOfTollingStrengthGainAmount, this.innateHourOfTollingTurnAmount), 1));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case TOLL: {
                for (int i = 0; i < this.tollHitAmount; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.LIGHT_YELLOW_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75f));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
                }
                break;
            }
            case EARRITATE: {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("BELL", MathUtils.random(-0.2f, -0.3f)));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, this.earritateWeakAmount, true), this.earritateWeakAmount));
                break;
            }
            case MAINTENANCE: {
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maintenanceHealAmount));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, this.maintenanceArtifactAmount), this.maintenanceArtifactAmount));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        if (this.maxHealth - this.currentHealth >= this.maintenanceHealAmount && !lastMove(MAINTENANCE) && !lastMoveBefore(MAINTENANCE)) {
            this.setMove(MAINTENANCE_NAME, MAINTENANCE, Intent.BUFF);
        }
        else if (num < 70 && !lastTwoMoves(TOLL)) {
            this.setMove(TOLL_NAME, TOLL, Intent.ATTACK, this.damage.get(0).base, this.tollHitAmount, true);
        }
        else if (!lastMove(EARRITATE)) {
            this.setMove(EARRITATE_NAME, EARRITATE, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
        }
        else {
            this.getMove(AbstractDungeon.aiRng.random(0, 99));
        }
    }
}
