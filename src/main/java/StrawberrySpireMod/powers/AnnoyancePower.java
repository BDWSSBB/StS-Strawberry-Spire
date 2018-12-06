package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.badlogic.gdx.math.*;

public class AnnoyancePower extends AbstractPower {

    public static final String POWER_ID = "StrawberrySpire:Annoyance";
    public static final String NAME = "Annoyance";
    public static final String[] DESCRIPTIONS = {
            "You are an annoyance to yourself."
    };
    public static final String[] QUOTES = {
            "~FUSING~ ~DISTORT~ ~AND~ ~OOZE...~",
            "~SLUDGING~ ~HAMMERS...~",
            "~SUSPENDING~ ~SPACETIME...~",
            "~APPLYING~ ~SMOKE~ ~AND~ ~MIRRORS...~",
            "~APPLYING~ ~CHAOS~ ~CONTROL...~",
            "~RESERVING~ ~SHOOTERS...~",
            "~ENERGIZING~ ~DARK~ ~MATTER...~",
            "~LIQUEFYING~ ~PENGUIN~ ~SOULS...~",
            "~SMAAAASHING...~",
            "~GETTING~ ~THE~ ~CAMERAS...~",
            "~RELEASING~ ~THE~ ~KRAKEN...~",
            "~SPLASHING~ ~TEXT...~",
            "~RECODING~ ~PHYSICS...~",
            "~REWIRING~ ~WIRES...~",
            "~DROPPING~ ~BEATS...~",
            "~MINDING~ ~OVER~ ~MATTER...~",
            "~ACCELERATING~ ~USER-SPACE...~",
            "~REFORMING~ ~MOLECULAR~ ~BONDS...~",
            "~CORRECTING~ ~CORRECTORS...~",
            "~NUKING~ ~LIFE...~",
            "~FROSTING~ ~THE~ ~CAKE...~",
            "~PLAYING~ ~GOD...~",
            "~ENFORCING~ ~HAPPY~ ~HAPPYISM...~",
            "~BURNING~ ~COMPACT~ ~DISCS...~",
            "~BREAKING~ ~YOU...~",
            "~APPLYING~ ~ALL~ ~STARS...~",
            "~APPLYING~ ~LAYERS...~",
            "~QUESTIONING~ ~INTRUDERS...~",
            "~LIQUEFYING~ ~DONKEY~ ~SOULS...~",
            "~ACQUIRING~ ~A~ ~TRIPLE...~",
            "~SMASHING~ ~MOUTHS...~",
            "~COMMITTING~ ~SO~ ~MUCH~ ~TO~ ~DO...~",
            "~OBSERVING~ ~SO~ ~MUCH~ ~TO~ ~SEE...~",
            "~DANCING~ ~THE~ ~DUANE...~",
            "~INITIALIZING~ ~SWAMP~ ~MATTER...~",
            "~APPLYING~ ~CHUBBY~ ~CODE:~ ~47...~",
            "~INITIATING~ ~BATTLE~ ~MODE...~",
            "~PRAYING~ ~AT~ ~THE~ ~SINK~ ~ALL~ ~DAY...~",
            "~BREAKING~ ~A~ ~LEG...~",
            "~RECORDING...~",
            "~CONTACTING~ ~DIVINE~ ~SPIRITS...~",
            "~LAUGHING~ ~OUT~ ~LOUD...~",
            "~INCREASING~ ~SPEED~ ~OF~ ~LIGHT...~",
            "~INSTALLING~ ~ANTIVIRUS...~",
            "~FIRING~ ~THE~ ~LASER...~",
            "~BATTLING~ ~TOADS...~",
            "~HURTING~ ~BUTTS...~",
            "~APPLYING~ ~BURN~ ~MEDICATION...~",
            "~INHALING...~",
            "~INITIALIZING~ ~INITIALIZERS...~",
    };

    public AnnoyancePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.owner = owner;
        this.updateDescription();
        loadRegion("amplify");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new TalkAction(true, QUOTES[MathUtils.random(0, QUOTES.length - 1)], 0.0F, 2.0F));
    }
}
