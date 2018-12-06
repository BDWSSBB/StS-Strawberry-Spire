package StrawberrySpireMod.vfx.campfire;

import com.megacrit.cardcrawl.vfx.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.*;
import com.megacrit.cardcrawl.rewards.*;
import com.megacrit.cardcrawl.rooms.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.core.*;

import StrawberrySpireMod.relics.blandfruitRelics.*;

public class CampfireInfuseBlandfruitEffect extends AbstractGameEffect {
    private static final float DURATION = 2.0f;
    private boolean hasInfused;
    private Color screenColor;

    public CampfireInfuseBlandfruitEffect() {
        this.hasInfused = false;
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = DURATION;
        this.screenColor.a = 0.0f;
        ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.updateBlackScreenColor();
        if (this.duration < 1.0f && !this.hasInfused) {
            this.hasInfused = true;
            CardCrawlGame.sound.play("SPORE_CLOUD_RELEASE");
            AbstractDungeon.getCurrRoom().rewards.clear();
            if (AbstractDungeon.player.hasRelic(Blandfruit.ID)) {
                for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                    if (AbstractDungeon.player.relics.get(i).relicId.equals(Blandfruit.ID)) {
                        AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(((Blandfruit)AbstractDungeon.player.relics.get(i)).chosenFruit));
                    }
                }
            }
            AbstractDungeon.player.loseRelic(Blandfruit.ID);
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.combatRewardScreen.open();
        }
        if (this.duration < 0.0f) {
            this.isDone = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.5f) {
            this.screenColor.a = Interpolation.fade.apply(1.0f, 0.0f, (this.duration - 1.5f) * 2.0f);
        }
        else if (this.duration < 1.0f) {
            this.screenColor.a = Interpolation.fade.apply(0.0f, 1.0f, this.duration);
        }
        else {
            this.screenColor.a = 1.0f;
        }
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }
}
