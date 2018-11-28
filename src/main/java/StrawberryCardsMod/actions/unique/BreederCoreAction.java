package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.ui.panels.*;

import StrawberryCardsMod.powers.*;

public class BreederCoreAction extends AbstractGameAction {

    private AbstractPlayer player;
    private boolean upgraded;
    private boolean freeToPlayOnce;
    private int energyOnUse;

    public BreederCoreAction(boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.player = AbstractDungeon.player;
        this.upgraded = upgraded;
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
        if (this.upgraded) {
            effect++;
        }
        if (effect > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new BreederCorePower(this.player, 2 * effect), 2 * effect));
            if (!this.freeToPlayOnce) {
                this.player.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
