package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class HandKnitScarf extends CustomRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:HandKnitScarf";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int BLOCK_AMOUNT = 1;

    public HandKnitScarf() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMOUNT + DESCRIPTIONS[1];
    }

    public void onChannel(AbstractOrb orb) {
        flash();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMOUNT));
    }

    public AbstractRelic makeCopy() {
        return new HandKnitScarf();
    }
}
