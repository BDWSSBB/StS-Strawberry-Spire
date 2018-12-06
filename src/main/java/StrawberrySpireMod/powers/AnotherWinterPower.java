package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.*;

public class AnotherWinterPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:AnotherWinter";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public AnotherWinterPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("int");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        flash();
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Frost()));
        }
    }
}
