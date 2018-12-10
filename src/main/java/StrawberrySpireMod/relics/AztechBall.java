package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.*;
import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class AztechBall extends CustomRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:AztechBall";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int CHANNEL_AMOUNT = 2;

    public AztechBall() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SHOP, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHANNEL_AMOUNT + DESCRIPTIONS[1];
    }

    public void onChannel(AbstractOrb orb) {
        if (AbstractDungeon.player.orbs.size() > 2) {
            for (int i = 0; i < AbstractDungeon.player.orbs.size() - 2; i++) {
                if (AbstractDungeon.player.orbs.get(i).ID != null &&
                        AbstractDungeon.player.orbs.get(i).ID == AbstractDungeon.player.orbs.get(i + 1).ID &&
                        AbstractDungeon.player.orbs.get(i).ID == AbstractDungeon.player.orbs.get(i + 2).ID) {
                    flash();
                    for (int j = 0; j < CHANNEL_AMOUNT; j++) { // The actions are listed in reverse so they are added into the queue properly.
                        AbstractDungeon.actionManager.addToTop(new ChannelAction(AbstractOrb.getRandomOrb(true)));
                    }
                    for (int j = 0; j < 3; j++) {
                        AbstractDungeon.actionManager.addToTop(new EvokeSpecificOrbAction(AbstractDungeon.player.orbs.get(i + j)));
                    }
                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.15F));
                    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                }
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new AztechBall();
    }
}
