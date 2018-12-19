package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.actions.unique.*;

public class HarvestPower extends AbstractStrawberrySpirePower {

    public static final String POWER_ID = "strawberrySpire:Harvest";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public HarvestPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("sadistic");
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(new ReducePowerAction(this.owner, this.owner, this.ID, 1), 1));
        }
    }

    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            if (AbstractDungeon.player.hasRelic(Boot.ID) && damageAmount < 5) { // Is there a better way to do this? Doesn't seem so.
                this.owner.heal(5 * 3 / 5);
            }
            else if (damageAmount > target.currentHealth) {
                this.owner.heal(target.currentHealth * 3 / 5);
            }
            else {
                this.owner.heal(damageAmount * 3 / 5);
            }
        }
    }
}
