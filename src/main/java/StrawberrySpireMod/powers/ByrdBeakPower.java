package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.actions.unique.*;

import java.util.ArrayList;

public class ByrdBeakPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:ByrdBeak";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private static final int VULNERABLE_AMOUNT = 1;
    private ArrayList<AbstractCreature> affectedMonsters = new ArrayList<>();

    public ByrdBeakPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        updateDescription();
        loadRegion("doubleTap");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + VULNERABLE_AMOUNT + DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID), 1));
        }
    }

    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
        if (target != this.owner && !affectedMonsters.contains(target) && info.type == DamageInfo.DamageType.NORMAL) {
            this.affectedMonsters.add(target);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new VulnerablePower(target, VULNERABLE_AMOUNT, false), VULNERABLE_AMOUNT));
        }
    }
}