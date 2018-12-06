package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class HellbenderPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:Hellbender";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public HellbenderPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("attackBurn");
        this.priority = 25;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }
        else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[4];
        }
    }

    public void atStartOfTurnPostDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(this.owner, this.owner, this.amount, false));
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
        }
    }
}
