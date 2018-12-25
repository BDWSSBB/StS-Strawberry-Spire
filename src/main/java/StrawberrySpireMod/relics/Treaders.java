package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

public class Treaders extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:Treaders";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");
    private static final int DRAW_AMOUNT = 1;
    private boolean hasActivated = false;

    public Treaders() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.COMMON, LandingSound.FLAT);
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1];
    }

    public void atPreBattle() {
        this.hasActivated = false;
        this.pulse = true;
        beginPulse();
    }

    public void onCardDraw(AbstractCard card) {
        if (!this.hasActivated && (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE)) {
            this.hasActivated = true;
            this.pulse = false;
            flash();
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, DRAW_AMOUNT));
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new Treaders();
    }
}
