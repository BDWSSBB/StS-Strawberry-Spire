package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.*;

import basemod.abstracts.*;

public class Sunfruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Sunfruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int HEAL_AMOUNT = 3;

    public Sunfruit() {
        super(ID, IMAGE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEAL_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEnterRoom(AbstractRoom room) {
        flash();
        AbstractDungeon.player.heal(HEAL_AMOUNT);
    }

    public AbstractRelic makeCopy() {
        return new Sunfruit();
    }
}
