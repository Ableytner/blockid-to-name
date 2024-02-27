package com.ableytner.bidtoname.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.json.simple.JSONObject;

import com.ableytner.bidtoname.BlockIdtoName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class ListGenerator {

    private static boolean hasGenerated;

    public static void setup() {
        hasGenerated = false;
    }

    public static void generateList() {
        generateList(false);
    }

    @SuppressWarnings("unchecked")
    public static void generateList(boolean force) {
        if (hasGenerated && !force) {
            return;
        }

        if (FMLCommonHandler.instance()
            .getEffectiveSide()
            .isServer()) {
            BlockIdtoName.LOG.error("generateList is not supported on server side!");
            return;
        }

        JSONObject blocks = new JSONObject();
        int maxBlockId = 4096;
        if (Loader.isModLoaded("neid")) {
            maxBlockId = 65535;
        }

        for (int blockId = 0; blockId < maxBlockId; blockId++) {
            Block b = Block.getBlockById(blockId);
            if (b != null && !(b instanceof BlockAir)) {
                List<ItemStack> subBlocks = new ArrayList<ItemStack>();
                b.getSubBlocks(Item.getItemFromBlock(b), CreativeTabs.tabAllSearch, subBlocks);
                for (int subBlockId = 0; subBlockId < subBlocks.size(); subBlockId++) {
                    ItemStack i = subBlocks.get(subBlockId);
                    Item item = i.getItem();
                    if (item != null) {
                        String name = Integer.toString(blockId) + ":" + Integer.toString(subBlockId);
                        blocks.put(name, item.getItemStackDisplayName(i));
                    }
                }
            }
        }

        try {
            Writer fw = new OutputStreamWriter(new FileOutputStream("blockid_to_name.json"), StandardCharsets.UTF_16);
            fw.write((blocks.toJSONString()));
            fw.close();
            hasGenerated = true;
            BlockIdtoName.LOG.info("!!! saving finished !!!");
        } catch (IOException e) {
            BlockIdtoName.LOG.info("!!! saving failed !!!");
        }
    }
}
