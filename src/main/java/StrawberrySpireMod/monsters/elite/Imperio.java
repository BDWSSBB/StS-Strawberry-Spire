package StrawberrySpireMod.monsters.elite;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.powers.*;

public class Imperio extends AbstractMonster {

    public static final String ID = "strawberrySpire:Imperio";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = 4.0F;
    private static final float HB_Y = 68.0F;
    private static final float HB_W = 160.0F;
    private static final float HB_H = 120.0F;
    private static final int HP_MIN = 51;
    private static final int HP_MAX = 53;
    private static final int ASC_HP_MIN = 57;
    private static final int ASC_HP_MAX = 59;
    private static final byte BOUNCE = 1;
    private static final String BOUNCE_NAME = MOVES[0];
    private static final int INNATE_REALLOCATE_HEAL_AMOUNT = 10;
    private static final int INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 2;
    private static final int ASC2_INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 3;
    private static final int BOUNCE_DAMAGE = 1;
    private static final int BOUNCE_HIT_AMOUNT = 3;
    private static final int ASC_BOUNCE_HIT_AMOUNT = 4;
    private int innateReallocateHealAmount;
    private int innateReallocateStrengthGainAmount;
    private int bounceDamage;
    private int bounceHitAmount;

    public Imperio(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder2.png", x, y);
        this.type = EnemyType.ELITE;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(ASC_HP_MIN, ASC_HP_MAX);
        }
        else {
            setHp(HP_MIN, HP_MAX);
        }
        if (AbstractDungeon.ascensionLevel >= 18) {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = ASC2_INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.bounceDamage = BOUNCE_DAMAGE;
            this.bounceHitAmount = ASC_BOUNCE_HIT_AMOUNT;
        }
        else if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.bounceDamage = BOUNCE_DAMAGE;
            this.bounceHitAmount = ASC_BOUNCE_HIT_AMOUNT;
        }
        else {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.bounceDamage = BOUNCE_DAMAGE;
            this.bounceHitAmount = BOUNCE_HIT_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, bounceDamage));
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ReallocatePower(this, innateReallocateHealAmount, innateReallocateStrengthGainAmount), innateReallocateStrengthGainAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case BOUNCE: {
                for (int i = 0; i < this.bounceHitAmount; i++) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                    if (i == this.bounceHitAmount - 1) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                    }
                    else {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                    }
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        this.setMove(BOUNCE_NAME, BOUNCE, Intent.ATTACK, this.damage.get(0).base, this.bounceHitAmount, true);
    }
}
