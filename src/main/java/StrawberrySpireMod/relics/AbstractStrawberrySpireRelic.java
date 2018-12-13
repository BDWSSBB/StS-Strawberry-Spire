package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.*;

public abstract class AbstractStrawberrySpireRelic extends CustomRelic {

    public AbstractStrawberrySpireRelic(final String id, final Texture imagePath, final Texture imageOutlinePath, final RelicTier relicTier, final LandingSound landingSound) {
        super(id, imagePath, imageOutlinePath, relicTier, landingSound);
    }

    public AbstractStrawberrySpireRelic(final String id, final Texture imagePath, final RelicTier relicTier, final LandingSound landingSound) {
        super(id, imagePath, relicTier, landingSound);
    }

    public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source) { }
}
