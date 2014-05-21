package net.dcatcher.enderius.common.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: DCatcher
 */
public class EnderiusConfiguration {

    public List<String> generateMobBlacklist(File location) throws Exception {
        File blacklistFile = new File(location + "/Enderius/MobBlacklist.txt");
        createAndPopulateMobBlacklist(location + "/Enderius/MobBlacklist.txt");

        BufferedReader buf = new BufferedReader(new FileReader(blacklistFile));
        String current;
        List<String> blacklist = new ArrayList<String>();

        while((current = buf.readLine()) != null){
            if(current.startsWith("#"))
                continue;
            if(current.startsWith("//"))
                continue;

            blacklist.add(current.replaceAll(" ", ""));
        }
        return blacklist;
    }


    public void createAndPopulateMobBlacklist(String path) throws IOException {
        File f = new File(path);
        if(!f.exists()){
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter print = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            print.write("# This is the Entity Blacklist for the Summoner. To add an entity, type their name as shown below. \n");
            print.write("# Comments are started with either a # or a // \n");
            print.write("# Sheep \n");
            print.write("EnderDragon \n");
            print.write("WitherBoss \n");
            print.write("VillagerGolem \n");
            print.close();
        }
    }

    public List<String> genBannedBlocks(File location) throws IOException {
        File blacklistFile = new File(location + "/Enderius/BlockBlacklist.txt");
        createAndPopulateBlockBlacklist(location + "/Enderius/BlockBlacklist.txt");

        BufferedReader buf = new BufferedReader(new FileReader(blacklistFile));
        String current;
        List<String> blacklist = new ArrayList<String>();

        while((current = buf.readLine()) != null){
            if(current.startsWith("#"))
                continue;
            if(current.startsWith("//"))
                continue;

            blacklist.add(current);
        }
        return blacklist;
    }

    public void createAndPopulateBlockBlacklist(String path) throws IOException {
        File f = new File(path);
        if(!f.exists()){
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter print = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            print.write("# This is the Block Blacklist for the Ender Focus. To add an Block, type their name as shown below. \n");
            print.write("# Comments are started with either a # or a // \n");
            print.write("# dirt \n");
            print.write("bedrock \n");
            print.write("water \n");
            print.write("lava \n");
            print.close();
        }
    }
}
