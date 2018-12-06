package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class DirtyLipstick extends CustomRelic {

    public static final String ID = "strawberrySpire:DirtyLipstick";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int STAT_INCREASE_AMOUNT = 2;

    public DirtyLipstick() {
        super(ID, IMAGE_PATH, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STAT_INCREASE_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        flash();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.rarity == AbstractCard.CardRarity.BASIC) {
                c.baseDamage += STAT_INCREASE_AMOUNT;
                c.baseBlock += STAT_INCREASE_AMOUNT;
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new DirtyLipstick();
    }
}
