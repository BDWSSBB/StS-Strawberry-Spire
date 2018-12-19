package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.actions.unique.*;

public class ReallocatePower extends AbstractStrawberrySpirePower {

    public static final String POWER_ID = "strawberrySpire:Reallocate";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private int healAmount;
    private int strengthAmount;

    public ReallocatePower(AbstractCreature owner, int healAmount, int strengthAmount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.healAmount = healAmount;
        this.strengthAmount = this.amount = strengthAmount;
        updateDescription();
        loadRegion("ai");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.healAmount + DESCRIPTIONS[1] + this.strengthAmount + DESCRIPTIONS[2];
    }

    public void onDeath() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) { // The order of monster buffs will be backwards but whatever
            if ((!m.isDying) && (!m.isEscaping)) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, null, new StrengthPower(m, this.strengthAmount), this.strengthAmount));
                AbstractDungeon.actionManager.addToTop(new FasterHealAction(m, null, this.healAmount));
            }
        }
    }
}
