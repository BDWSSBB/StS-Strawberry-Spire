package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class Fadefruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Fadefruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int CARD_AMOUNT = 1;

    public Fadefruit() {
        super(ID, IMAGE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new SeekAction(CARD_AMOUNT));
    }

    public AbstractRelic makeCopy() {
        return new Fadefruit();
    }
}
