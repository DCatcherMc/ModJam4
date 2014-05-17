package net.dcatcher.enderius.common.util;

import net.minecraft.entity.Entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: DCatcher
 */
public class EnderiusConfiguration {

    public static List<String> checkBlacklist(File location) throws Exception {
        File blacklistFile = new File(location + "/Enderius/blacklist.txt");

        if(!blacklistFile.exists())
            blacklistFile.createNewFile();

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
}
