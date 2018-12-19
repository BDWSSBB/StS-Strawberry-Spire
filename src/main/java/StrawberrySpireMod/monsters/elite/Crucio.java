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

public class Crucio extends AbstractMonster {

    public static final String ID = "strawberrySpire:Crucio";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = 4.0F;
    private static final float HB_Y = 68.0F;
    private static final float HB_W = 160.0F;
    private static final float HB_H = 120.0F;
    private static final int HP_MIN = 45;
    private static final int HP_MAX = 47;
    private static final int ASC_HP_MIN = 49;
    private static final int ASC_HP_MAX = 51;
    private static final byte REBOUND = 1;
    private static final String REBOUND_NAME = MOVES[0];
    private static final int INNATE_REALLOCATE_HEAL_AMOUNT = 10;
    private static final int INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 2;
    private static final int ASC2_INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 3;
    private static final int REBOUND_DAMAGE = 2;
    private static final int REBOUND_HIT_AMOUNT = 2;
    private static final int ASC_REBOUND_DAMAGE = 3;
    private int innateReallocateHealAmount;
    private int innateReallocateStrengthGainAmount;
    private int reboundDamage;
    private int reboundHitAmount;

    public Crucio(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder5.png", x, y);
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
            this.reboundDamage = ASC_REBOUND_DAMAGE;
            this.reboundHitAmount = REBOUND_HIT_AMOUNT;
        }
        else if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.reboundDamage = ASC_REBOUND_DAMAGE;
            this.reboundHitAmount = REBOUND_HIT_AMOUNT;
        }
        else {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.reboundDamage = REBOUND_DAMAGE;
            this.reboundHitAmount = REBOUND_HIT_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, reboundDamage));
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ReallocatePower(this, innateReallocateHealAmount, innateReallocateStrengthGainAmount), innateReallocateStrengthGainAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case REBOUND: {
                for (int i = 0; i < this.reboundHitAmount; i++) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                    if (i == this.reboundHitAmount - 1) {
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
        this.setMove(REBOUND_NAME, REBOUND, Intent.ATTACK, this.damage.get(0).base, this.reboundHitAmount, true);
    }
}
