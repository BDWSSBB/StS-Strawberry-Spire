package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class Sorrowfruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Sorrowfruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int POISON_AMOUNT = 9;

    public Sorrowfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + POISON_AMOUNT + DESCRIPTIONS[1];
    }

    public void onShuffle() {
        flash();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if ((!m.isDead) && (!m.isDying)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PoisonPower(m, AbstractDungeon.player, POISON_AMOUNT), POISON_AMOUNT, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new Sorrowfruit();
    }
}