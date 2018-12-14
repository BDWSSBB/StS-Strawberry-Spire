package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

import basemod.abstracts.*;

public class Blandfruit extends AbstractStrawberrySpireRelic implements CustomSavable<String>{

    public static final String ID = "strawberrySpire:Blandfruit";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireMod/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireMod/relics/outline/placeholder.png");
    public AbstractRelic chosenFruit;
    private AbstractRelic tempFruit;

    public Blandfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
        this.chosenFruit = null;
        this.tempFruit = null;
    }

    public String getUpdatedDescription() {
        if (this.chosenFruit == null) {
            return DESCRIPTIONS[0];
        }
        else {
            return DESCRIPTIONS[1] + chosenFruit.name + DESCRIPTIONS[2];
        }
    }

    public void update() {
        super.update();
        if (tempFruit != chosenFruit) {
            tempFruit = chosenFruit;
            this.description = DESCRIPTIONS[1] + chosenFruit.name + DESCRIPTIONS[2];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }

    public String onSave() {
        if (this.chosenFruit == null) {
            return null;
        }
        return this.chosenFruit.relicId;
    }

    public void onLoad(String chosenFruitID) {
        if (chosenFruitID == null) {
            return;
        }
        else {
            this.chosenFruit = RelicLibrary.getRelic(chosenFruitID).makeCopy();
        }
    }

    public AbstractRelic makeCopy() {
        return new Blandfruit();
    }
}
