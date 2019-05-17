package StrawberrySpireMod.monsters.elite;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
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

public class Zivicio extends AbstractMonster {

    public static final String ID = "strawberrySpire:Zivicio";
    private static final MonsterStrings MONSTER_STRINGS = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = MONSTER_STRINGS.NAME;
    public static final String[] MOVES = MONSTER_STRINGS.MOVES;
    private static final float HB_X = -8.0F;
    private static final float HB_Y = -10.0F;
    private static final float HB_W = 150.0F;
    private static final float HB_H = 150.0F;
    private static final int HP_MIN = 58;
    private static final int HP_MAX = 60;
    private static final int ASC_HP_MIN = 65;
    private static final int ASC_HP_MAX = 67;
    private static final byte ENERGIZE = 1;
    private static final String ENERGIZE_NAME = MOVES[0];
    private static final int INNATE_REALLOCATE_HEAL_AMOUNT = 10;
    private static final int INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 3;
    private static final int ASC2_INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT = 4;
    private static final int ENERGIZE_DAMAGE = 4;
    private static final int ENERGIZE_STRENGTH_GAIN_AMOUNT = 1;
    private static final int ASC_ENERGIZE_DAMAGE = 5;
    private int innateReallocateHealAmount;
    private int innateReallocateStrengthGainAmount;
    private int energizeDamage;
    private int energizeStrengthGainAmount;

    public Zivicio(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "StrawberrySpireModResources/monsters/placeholder3.png", x, y);
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
            this.energizeDamage = ASC_ENERGIZE_DAMAGE;
            this.energizeStrengthGainAmount = ENERGIZE_STRENGTH_GAIN_AMOUNT;
        }
        else if (AbstractDungeon.ascensionLevel >= 3) {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.energizeDamage = ASC_ENERGIZE_DAMAGE;
            this.energizeStrengthGainAmount = ENERGIZE_STRENGTH_GAIN_AMOUNT;
        }
        else {
            this.innateReallocateHealAmount = INNATE_REALLOCATE_HEAL_AMOUNT;
            this.innateReallocateStrengthGainAmount = INNATE_REALLOCATE_STRENGTH_GAIN_AMOUNT;
            this.energizeDamage = ENERGIZE_DAMAGE;
            this.energizeStrengthGainAmount = ENERGIZE_STRENGTH_GAIN_AMOUNT;
        }
        this.damage.add(new DamageInfo(this, energizeDamage));

        loadAnimation("StrawberrySpireModResources/monsters/zivicio/skeleton.atlas", "StrawberrySpireModResources/monsters/zivicio/skeleton.json", 1f);
        AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ReallocatePower(this, innateReallocateHealAmount, innateReallocateStrengthGainAmount), innateReallocateStrengthGainAmount));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case ENERGIZE: {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05f));
                if (Settings.FAST_MODE) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.1F));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.3F));
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0)));
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, this.energizeStrengthGainAmount), this.energizeStrengthGainAmount));
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num) {
        this.setMove(ENERGIZE_NAME, ENERGIZE, Intent.ATTACK_BUFF, this.damage.get(0).base);
    }
}
