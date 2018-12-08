package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class Stormfruit extends CustomRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:Stormfruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int DRAW_AMOUNT = 1;

    public Stormfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1];
    }

    public void onChannel(AbstractOrb orb) {
        if (orb.ID.equals(Lightning.ORB_ID)) {
            flash();
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, DRAW_AMOUNT));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public AbstractRelic makeCopy() {
        return new Stormfruit();
    }
}
