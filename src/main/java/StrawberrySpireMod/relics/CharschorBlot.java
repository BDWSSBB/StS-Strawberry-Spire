package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

public class CharschorBlot extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:CharschorBlot";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/charschorBlot.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/charschorBlotOutline.png");
    private static final int CARD_AMOUNT = 25;

    public CharschorBlot() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() { // Note to self: There was some bug about the select screens vanishing and softlocking the player if you went to see other screens, I don't know the exact causes, but something's up.
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;

        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 1, DESCRIPTIONS[2], false, false, false, true);
    }

    public void update() {
        super.update();
        if (this.counter == -1 && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.counter = -2;
            if (AbstractDungeon.isScreenUp) {
                AbstractDungeon.dynamicBanner.hide();
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.screen;
            }
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(card);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            CardGroup availableCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (int i = 0; i < CARD_AMOUNT; i++) {
                AbstractCard temp = AbstractDungeon.getCard(AbstractDungeon.rollRarity());
                if (!availableCards.contains(temp)) {
                    temp.upgrade();
                    availableCards.addToBottom(temp);
                    UnlockTracker.markCardAsSeen(temp.cardID);
                }
                else {
                    i--;
                }
            }
            AbstractDungeon.gridSelectScreen.open(availableCards, 1, DESCRIPTIONS[3], false);
        }
        else if (this.counter == -2 && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.counter = -3;
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }

    public AbstractRelic makeCopy() {
        return new CharschorBlot();
    }
}
