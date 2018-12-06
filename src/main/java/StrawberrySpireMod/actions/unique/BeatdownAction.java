package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.ui.panels.*;

public class BeatdownAction extends AbstractGameAction {

    private AbstractCreature target;
    private AbstractPlayer player;
    private DamageInfo info;
    private boolean freeToPlayOnce;
    private int energyOnUse;

    public BeatdownAction(AbstractCreature target, DamageInfo info, boolean freeToPlayOnce, int energyOnUse) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.target = target;
        this.player = AbstractDungeon.player;
        this.info = info;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (this.player.hasRelic(ChemicalX.ID)) {
            effect += 2;
            this.player.getRelic(ChemicalX.ID).flash();
        }
        if (effect > 0) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            for (int i = 0; i < 2 * effect - 1; i++) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            if (!this.freeToPlayOnce) {
                this.player.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
