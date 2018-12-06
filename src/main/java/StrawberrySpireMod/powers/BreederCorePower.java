package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.*;

public class BreederCorePower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:BreederCore";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public BreederCorePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("cExplosion");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        flash();
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(AbstractOrb.getRandomOrb(true)));
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}
