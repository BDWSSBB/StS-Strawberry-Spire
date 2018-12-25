package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

public class BrokenPrinter extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:BrokenPrinter";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");
    private static final int CARD_AMOUNT = 5;

    public BrokenPrinter() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SHOP, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        flash();
        if (AbstractDungeon.isScreenUp)
        {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, DESCRIPTIONS[2], false, false, false, false);
    }

    public void update() {
        super.update();
        if ((this.counter == -1) && (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1)) {
            this.counter = -2;
            for (int i = 0; i < CARD_AMOUNT; i++) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeStatEquivalentCopy();
                c.inBottleFlame = false;
                c.inBottleLightning = false;
                c.inBottleTornado = false;
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH * (0.2F + i * 0.15F), Settings.HEIGHT / 2.0F));
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public AbstractRelic makeCopy() {
        return new BrokenPrinter();
    }
}
