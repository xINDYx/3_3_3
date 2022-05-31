package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("c:\\Games\\savegames\\zip.zip", "c:\\5\\");

        GameProgress gameProgress;

        gameProgress = openProgress("c:\\5\\save1.dat");

        System.out.println(gameProgress);
    }

    public static void openZip(String zipFile, String pathToUnZip){
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile)))
        {
            ZipEntry entry;
            String name;
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathToUnZip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String pathToSaveDat){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToSaveDat)))
        {
            return (GameProgress)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
