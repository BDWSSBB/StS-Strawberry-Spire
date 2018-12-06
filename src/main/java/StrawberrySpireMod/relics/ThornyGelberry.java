package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class ThornyGelberry extends CustomRelic {

    public static final String ID = "strawberrySpire:ThornyGelberry";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int THORNS_AMOUNT = 1;

    public ThornyGelberry() {
        super(ID, IMAGE_PATH, RelicTier.RARE, LandingSound.FLAT);
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + THORNS_AMOUNT + DESCRIPTIONS[1];
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, THORNS_AMOUNT), THORNS_AMOUNT));
        }
        return damageAmount;
    }

    public AbstractRelic makeCopy() {
        return new ThornyGelberry();
    }
}
