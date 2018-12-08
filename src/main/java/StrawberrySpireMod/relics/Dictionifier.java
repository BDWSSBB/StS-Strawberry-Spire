package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

import java.util.*;

public class Dictionifier extends CustomRelic implements CustomSavable<ArrayList<Integer>> { // Originally I wanted to keep the costs 0, but I couldn't find a way

    public static final String ID = "strawberrySpire:Dictionifier";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private boolean cardSelected = false;
    private AbstractCard cardToReduce = null;
    private ArrayList<AbstractCard> reducedCardsList = new ArrayList<>();

    public Dictionifier() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.cardSelected = false;
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        ArrayList<String> cardsAdded = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!cardsAdded.contains(c.cardID) && c.makeCopy().cost >= 0) {
                cardsAdded.add(c.cardID);
                temp.addToTop(c.makeCopy());
            }
        }
        if (temp.isEmpty()) {
            this.cardSelected = true;
            return;
        }
        else if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.gridSelectScreen.open(temp, 1, this.DESCRIPTIONS[1] + this.name + this.DESCRIPTIONS[2], false, false, false, false);
    }

    public void onUnequip() {
        for (AbstractCard c : reducedCardsList) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(c);
            if (cardInDeck != null) {
                AbstractCard copy = c.makeCopy();
                c.updateCost(-c.cost + copy.cost);
                c.isCostModified = false;
            }
        }
    }

    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            this.cardToReduce = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.cardID.equals(this.cardToReduce.cardID) && c.cost != 0) {
                    this.reducedCardsList.add(c);
                    c.updateCost(-c.cost);
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }

    public ArrayList<Integer> onSave() {
        ArrayList<Integer> cardIndices = new ArrayList<>();
        for (AbstractCard c1 : AbstractDungeon.player.masterDeck.group) {
            for (AbstractCard c2: this.reducedCardsList) {
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
                this.reducedCardsList.add(card);
                card.updateCost(-card.cost);
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new Dictionifier();
    }
}
