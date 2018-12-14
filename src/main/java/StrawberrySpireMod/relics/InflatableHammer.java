package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

public class InflatableHammer extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:InflatableHammer";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireModResources/relics/outline/placeholder.png");
    private static final int STRENGTH_AMOUNT = 3;
    private static final int STRENGTH_LOSS_AMOUNT = 1;

    public InflatableHammer() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SHOP, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STRENGTH_AMOUNT + DESCRIPTIONS[1] + STRENGTH_LOSS_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STRENGTH_AMOUNT), STRENGTH_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -STRENGTH_LOSS_AMOUNT), -STRENGTH_LOSS_AMOUNT));
            if (!AbstractDungeon.player.hasPower(ArtifactPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GainStrengthPower(AbstractDungeon.player, STRENGTH_LOSS_AMOUNT), STRENGTH_LOSS_AMOUNT));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new InflatableHammer();
    }
}
