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

public class Accio extends AbstractMonster {

    public static final String ID = "strawberrySpire:Accio";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = -8.0F;
    private static final float HB_Y = -10.0F;
    private static final float HB_W = 150.0F;
    private static final float HB_H = 150.0F;
    private static final int HP_MIN = 38;
    private static final int HP_MAX = 40;
    private static final int ASC_HP_MIN = 42;
    private static final int ASC_HP_MAX = 44;
    private static final byte FLARE = 1;
    private static final String FLARE_NAME = MOVES[0];
    private static final int INNATE_REALLOCATE_HEAL_AMOUNT = 10;
    private static final int INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 1;
    private static final int FLARE_DAMAGE = 4;
    private static final int FLARE_BURN_AMOUNT = 1;
    private static final int ASC_FLARE_DAMAGE = 5;
    private int innateReallocateHealAmount;
    private int innateReallocateStrengthGainAmount;
    private int flareDamage;
    private int flareBurnAmount;

    public Accio(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder.png", x, y);
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
            this.flareDamage = ASC_FLARE_DAMAGE;
            this.flareBurnAmount = FLARE_BURN_AMOUNT;
        }
        else {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.flareDamage = FLARE_DAMAGE;
            this.flareBurnAmount = FLARE_BURN_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, flareDamage));

        loadAnimation("StrawberrySpireModResources/monsters/accio/skeleton.atlas", "StrawberrySpireModResources/monsters/accio/skeleton.json", 1f);
        AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ReallocatePower(this, innateReallocateHealAmount, innateReallocateStrengthGainAmount), innateReallocateStrengthGainAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case FLARE: {
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
                if (AbstractDungeon.ascensionLevel >= 18) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), this.flareBurnAmount));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), this.flareBurnAmount));
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        this.setMove(FLARE_NAME, FLARE, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
    }
}
