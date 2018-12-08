package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

import java.util.*;

public class DirtyLipstick extends CustomRelic implements CustomSavable<ArrayList<Integer>>{

    public static final String ID = "strawberrySpire:DirtyLipstick";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int STAT_INCREASE_AMOUNT = 2;
    private ArrayList<AbstractCard> boostedCardsList = new ArrayList<>();

    public DirtyLipstick() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STAT_INCREASE_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        flash();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.rarity == AbstractCard.CardRarity.BASIC) {
                boostedCardsList.add(c);
                c.baseDamage += STAT_INCREASE_AMOUNT;
                c.baseBlock += STAT_INCREASE_AMOUNT;
            }
        }
    }

    public void onUnequip() {
        for (AbstractCard c : boostedCardsList) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(c);
            if (cardInDeck != null) {
                c.baseDamage -= STAT_INCREASE_AMOUNT;
                c.baseBlock -= STAT_INCREASE_AMOUNT;
                c.isCostModified = false;
            }
        }
    }

    public ArrayList<Integer> onSave() {
        ArrayList<Integer> cardIndices = new ArrayList<>();
        for (AbstractCard c1 : AbstractDungeon.player.masterDeck.group) {
            for (AbstractCard c2: this.boostedCardsList) {
                if (c1 == c2) {
                    cardIndices.add(AbstractDungeon.player.masterDeck.group.indexOf(c1));
                }
            }
        }
        return cardIndices;
    }

    public void onLoad(ArrayList<Integer> cardIndices) {
        for (Integer i : cardIndices) {
            AbstractCard card = AbstractDungeon.player.masterDeck.group.get(i);
            if (card != null) {
                this.boostedCardsList.add(card);
                card.baseDamage += STAT_INCREASE_AMOUNT;
                card.baseBlock += STAT_INCREASE_AMOUNT;
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new DirtyLipstick();
    }
}
