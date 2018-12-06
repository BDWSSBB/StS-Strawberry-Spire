package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class Swiftfruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Swiftfruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int DEXTERITY_AMOUNT = 2;

    public Swiftfruit() {
        super(ID, IMAGE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DEXTERITY_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, DEXTERITY_AMOUNT), DEXTERITY_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public AbstractRelic makeCopy() {
        return new Swiftfruit();
    }
}
