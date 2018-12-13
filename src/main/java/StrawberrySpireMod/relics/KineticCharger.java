package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

public class KineticCharger extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:KineticCharger";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int MINIMUM_COST_AMOUNT = 3;

    public KineticCharger() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.COMMON, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.freeToPlayOnce && card.costForTurn >= MINIMUM_COST_AMOUNT || (card.costForTurn == -1 && card.energyOnUse >= MINIMUM_COST_AMOUNT)) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
    }

    public AbstractRelic makeCopy() {
        return new KineticCharger();
    }
}
