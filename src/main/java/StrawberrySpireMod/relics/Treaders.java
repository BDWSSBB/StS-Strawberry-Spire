package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class Treaders extends CustomRelic {

    public static final String ID = "strawberrySpire:Treaders";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int DRAW_AMOUNT = 1;
    private boolean hasDrawnCardThisCombat = false;

    public Treaders() {
        super(ID, IMAGE_PATH, RelicTier.COMMON, LandingSound.FLAT);
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1];
    }

    public void atPreBattle() {
        this.hasDrawnCardThisCombat = false;
        this.pulse = true;
        beginPulse();
    }

    public void onCardDraw(AbstractCard card) {
        if (!this.hasDrawnCardThisCombat && (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE)) {
            this.hasDrawnCardThisCombat = true;
            this.pulse = false;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, DRAW_AMOUNT));
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new Treaders();
    }
}
