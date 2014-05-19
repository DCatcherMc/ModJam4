package net.dcatcher.enderius.common.util;

import net.minecraft.entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: DCatcher
 */
public class EnderiusConfiguration {

    public List<String> generateBlacklist(File location) throws Exception {
        File blacklistFile = new File(location + "/Enderius/blacklist.txt");
        createAndPopulateBlacklist(location + "/Enderius/blacklist.txt");

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


    public void createAndPopulateBlacklist(String path) throws IOException {
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
}
