package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

public class TinySteamboat extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:TinySteamboat";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireMod/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireMod/relics/outline/placeholder.png");
    private static final int CARD_AMOUNT = 2;

    public TinySteamboat() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.SOLID);
        this.counter = -1;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        if (this.counter == -2) {
            this.counter = -1;
            this.pulse = false;
            this.stopPulse();
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new SeekAction(CARD_AMOUNT));
        }
    }

    public void onEnterRestRoom() {
        flash();
        this.counter = -2;
        this.pulse = true;
        beginPulse();
    }

    public AbstractRelic makeCopy() {
        return new TinySteamboat();
    }
}
