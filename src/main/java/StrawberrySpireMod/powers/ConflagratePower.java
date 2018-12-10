package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.*;

public class ConflagratePower extends AbstractPower implements OnCardDrawPower{

    public static final String POWER_ID = "strawberrySpire:Conflagrate";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public ConflagratePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("flameBarrier");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        for (int i = 0; i < this.amount; i++) {
            this.description += DESCRIPTIONS[1];
        }
        this.description += DESCRIPTIONS[2];
    }

    public void onCardDraw(AbstractCard card) {
        if (card instanceof Burn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
        }
    }
}
