package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class ElectricRodnt extends CustomRelic {

    public static final String ID = "strawberrySpire:ElectricRodnt";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int CARD_AMOUNT = 7;

    public ElectricRodnt() {
        super(ID, IMAGE_PATH, RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_AMOUNT + DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter++;
        if (this.counter % CARD_AMOUNT == 0) {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
        }
    }

    public AbstractRelic makeCopy() {
        return new ElectricRodnt();
    }
}
