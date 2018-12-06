package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class Dreamfruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Dreamfruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");

    public Dreamfruit() {
        super(ID, IMAGE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUsePotion() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(AbstractDungeon.player));
    }

    public AbstractRelic makeCopy() {
        return new Dreamfruit();
    }
}
