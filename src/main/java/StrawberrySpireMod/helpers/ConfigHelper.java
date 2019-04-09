package StrawberrySpireMod.helpers;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    public static SpireConfig modConfig = null;
    private static ModPanel settingsPanel = null;
    public static boolean useModCards;
    public static ModLabeledToggleButton useModCardsButton;
    public static boolean useModRelics;
    public static ModLabeledToggleButton useModRelicsButton;
    public static boolean useModEnemies;
    public static ModLabeledToggleButton useModEnemiesButton;
    public static boolean useModEvents;
    public static ModLabeledToggleButton useModEventsButton;
    public static boolean useSideSwipe;
    public static ModLabeledToggleButton useSideSwipeButton;
    public static boolean useSpecialUpgradeNames;
    public static ModLabeledToggleButton upgradeNamesButton;
    private static final float X_START = 350.0F;
    private static final float Y_START = 750.0F;
    private static final float Y_SPACING = -50.0F;
    private static float currentX = X_START;
    private static float currentY = Y_START;

    public static void setupConfig() {
        try {
            Properties defaults = new Properties();
            defaults.put("useModCards", Boolean.toString(true));
            defaults.put("useModRelics", Boolean.toString(true));
            defaults.put("useModEnemies", Boolean.toString(true));
            defaults.put("useModEvents", Boolean.toString(true));
            defaults.put("useSideSwipe", Boolean.toString(true));
            defaults.put("useSpecialUpgradeNames", Boolean.toString(true));
            modConfig = new SpireConfig("StrawberrySpire", "config", defaults);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (modConfig.getBool("useModCards")) {
            useModCards = true;
        }
        else {
            useModCards = false;
        }
        if (modConfig.getBool("useModRelics")) {
            useModRelics = true;
        }
        else {
            useModRelics = false;
        }
        if (modConfig.getBool("useModEnemies")) {
            useModEnemies = true;
        }
        else {
            useModEnemies = false;
        }
        if (modConfig.getBool("useModEvents")) {
            useModEvents = true;
        }
        else {
            useModEvents = false;
        }
        if (modConfig.getBool("useSideSwipe")) {
            useSideSwipe = true;
        }
        else {
            useSideSwipe = false;
        }
        if (modConfig.getBool("upgradeNames")) {
            useSpecialUpgradeNames = true;
        }
        else {
            useSpecialUpgradeNames = false;
        }
    }

    public static void initializeConfig() {
        settingsPanel = new ModPanel();

        // These require a restart for changes to take effect.
        settingsPanel.addUIElement(new ModLabel("Requires restart for changes to take effect:", currentX, currentY, settingsPanel, label -> {}));
        spaceY();

        useModCardsButton = new ModLabeledToggleButton("Use Mod's Cards.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useModCards, settingsPanel, label -> {},
                button -> {
                    useModCards = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("useModCards", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(useModCardsButton);
        spaceY();

        useModRelicsButton = new ModLabeledToggleButton("Use Mod's Relics.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useModRelics, settingsPanel, label -> {},
                button -> {
                    useModRelics = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("useModRelics", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(useModRelicsButton);
        spaceY();

        useModEnemiesButton = new ModLabeledToggleButton("Use Mod's Enemies.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useModEnemies, settingsPanel, label -> {},
                button -> {
                    useModEnemies = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("useModEnemies", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(useModEnemiesButton);
        spaceY();

        useModEventsButton = new ModLabeledToggleButton("Use Mod's Events.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useModEvents, settingsPanel, label -> {},
                button -> {
                    useModEvents = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("useModEvents", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(useModEventsButton);
        spaceY();

        // Replace Prepared with Side Swipe
        useSideSwipeButton = new ModLabeledToggleButton("Replace Prepared with Side Swipe.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useSideSwipe, settingsPanel, label -> {},
                button -> {
                    useSideSwipe = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("useSideSwipe", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(useSideSwipeButton);
        spaceY();

        // End of stuff that requires a restart.
        spaceY();

        // Use special upgrade names on certain Strawberry Spire cards
        upgradeNamesButton = new ModLabeledToggleButton("Use special upgrade names for certain Strawberry Spire cards.",
                currentX, currentY, Settings.CREAM_COLOR, FontHelper.charDescFont,
                useSpecialUpgradeNames, settingsPanel, label -> {},
                button -> {
                    useSpecialUpgradeNames = button.enabled;
                    if (modConfig != null) {
                        modConfig.setBool("upgradeNames", button.enabled);
                        try {
                            modConfig.save();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        settingsPanel.addUIElement(upgradeNamesButton);
        spaceY();

        BaseMod.registerModBadge(ImageMaster.loadImage("StrawberrySpireModResources/placeholderBadge.png"), "Strawberry Spire", "BDWSSBB", "TODO", settingsPanel);
    }

    private static void spaceY() {
        currentY += Y_SPACING;
    }
}
