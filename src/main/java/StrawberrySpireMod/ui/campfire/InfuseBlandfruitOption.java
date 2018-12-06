package StrawberrySpireMod.ui.campfire;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.ui.campfire.*;
import com.megacrit.cardcrawl.core.*;

import StrawberrySpireMod.vfx.campfire.*;

public class InfuseBlandfruitOption extends AbstractCampfireOption
{
    public static final String ID = "strawberrySpire:InfuseBlandfruitOption";
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = UI_STRINGS.TEXT;

    public InfuseBlandfruitOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = new Texture("ui/campfire/infuseBlandfruit.png");
    }

    public void useOption() {
        AbstractDungeon.effectList.add(new CampfireInfuseBlandfruitEffect());
    }
}
