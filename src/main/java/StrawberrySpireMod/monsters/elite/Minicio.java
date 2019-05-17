package StrawberrySpireMod.monsters.elite;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.powers.*;

public class Minicio extends AbstractMonster {

    public static final String ID = "strawberrySpire:Minicio";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = -8.0F;
    private static final float HB_Y = -10.0F;
    private static final float HB_W = 150.0F;
    private static final float HB_H = 150.0F;
    private static final int HP_MIN = 32;
    private static final int HP_MAX = 34;
    private static final int ASC_HP_MIN = 36;
    private static final int ASC_HP_MAX = 38;
    private static final byte PULSE = 1;
    private static final String PULSE_NAME = MOVES[0];
    private static final int INNATE_REALLOCATE_HEAL_AMOUNT = 10;
    private static final int INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 1;
    private static final int PULSE_DAMAGE = 4;
    private static final int PULSE_DAZE_AMOUNT = 1;
    private static final int ASC_PULSE_DAMAGE = 5;
    private int innateReallocateHealAmount;
    private int innateReallocateStrengthGainAmount;
    private int pulseDamage;
    private int pulseDazeAmount;

    public Minicio(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder4.png", x, y);
        this.type = EnemyType.ELITE;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(ASC_HP_MIN, ASC_HP_MAX);
        }
        else {
            setHp(HP_MIN, HP_MAX);
        }
        if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.pulseDamage = ASC_PULSE_DAMAGE;
            this.pulseDazeAmount = PULSE_DAZE_AMOUNT;
        }
        else {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.pulseDamage = PULSE_DAMAGE;
            this.pulseDazeAmount = PULSE_DAZE_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, pulseDamage));

        loadAnimation("StrawberrySpireModResources/monsters/minicio/skeleton.atlas", "StrawberrySpireModResources/monsters/minicio/skeleton.json", 1f);
        AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ReallocatePower(this, innateReallocateHealAmount, innateReallocateStrengthGainAmount), innateReallocateStrengthGainAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case PULSE: {
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if (AbstractDungeon.ascensionLevel >= 18) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Dazed(), this.pulseDazeAmount, true, true));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Dazed(), this.pulseDazeAmount));
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        this.setMove(PULSE_NAME, PULSE, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
    }
}