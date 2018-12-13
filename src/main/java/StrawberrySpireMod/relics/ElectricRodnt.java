package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class ElectricRodnt extends AbstractStrawberrySpireRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:ElectricRodnt";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int CHANNEL_AMOUNT = 5;

    public ElectricRodnt() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHANNEL_AMOUNT + DESCRIPTIONS[1];
    }

    public void onChannel(AbstractOrb orb) {
        if (orb.ID.equals(Lightning.ORB_ID)) {
            this.counter++;
        }
        if (this.counter % CHANNEL_AMOUNT == 0) {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToTop(new ChannelAction(new Lightning()));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public AbstractRelic makeCopy() {
        return new ElectricRodnt();
    }
}
