package ca.sunlis.greenhouses;

import net.dries007.tfc.api.capability.food.FoodTrait;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ca.sunlis.greenhouses.proxy.CommonProxy;
import ca.sunlis.greenhouses.util.CellarsTab;
import ca.sunlis.greenhouses.util.Reference;
import ca.sunlis.greenhouses.util.handlers.RegistryHandler;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

    public static final CellarsTab creativeTab = new CellarsTab();

    @Mod.Instance
    public static Main INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
        ModConfig.loadConfig(event);
    }
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        System.out.println("Mod Config for Cellars has Cool Mod as "+ModConfig.coolMod);
        System.out.println("Mod Config for Cellars has Icy Mod as "+ModConfig.icyMod);
        System.out.println("Mod Config for Cellars has Frozen Mod as "+ModConfig.icleMod);

        RegistryHandler.initRegistries();

        Reference.COOL = new FoodTrait("sharkCool", ModConfig.coolMod);
        Reference.ICY = new FoodTrait("sharkIcy", ModConfig.icyMod);
        Reference.FREEZING = new FoodTrait("sharkIcle", ModConfig.icleMod);

        Map<String, FoodTrait> tmp = FoodTrait.getTraits();
        for (Map.Entry<String,FoodTrait> entry : tmp.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue() +
                    ", Decay = " + entry.getValue().getDecayModifier());

    }
    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        Reference.initialized = true;
    }

}
