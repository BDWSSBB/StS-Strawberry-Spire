package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

import java.util.ArrayList;

public class Dreamfruit extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:Dreamfruit";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireModResources/relics/outline/placeholder.png");
    private ArrayList<String> powersToRemove = new ArrayList<>();

    public Dreamfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        boolean hasFlashed = false;
        for (String s : this.powersToRemove) {
            if (AbstractDungeon.player.hasPower(s)) {
                if (!hasFlashed) {
                    hasFlashed = true;
                    flash();
                    AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                }
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, s));
            }
        }
        this.powersToRemove.clear();
    }

    public void onPlayerEndTurn() {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF && !this.powersToRemove.contains(p.ID)) {
                this.powersToRemove.add(p.ID);
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new Dreamfruit();
    }
}
