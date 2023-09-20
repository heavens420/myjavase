package com.zlx.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JudgeFileType {


    private static boolean isPdfFile(byte[] magicNumber) {
        return magicNumber[0] == 0x25 && magicNumber[1] == 0x50 && magicNumber[2] == 0x44 && magicNumber[3] == 0x46;
    }

    private static boolean isTextFile(byte[] magicNumber) {
        return magicNumber[0] == 0x48 && magicNumber[1] == 0x65 && magicNumber[2] == 0x6C && magicNumber[3] == 0x6C;
    }

    private static boolean isXlsFile(byte[] magicNumber) {
        if (magicNumber[0] == (byte) 0xD0 && magicNumber[1] == (byte) 0xCF && magicNumber[2] == (byte) 0x11 &&
                magicNumber[3] == (byte) 0xE0 && magicNumber[4] == (byte) 0xA1 && magicNumber[5] == (byte) 0xB1 &&
                magicNumber[6] == (byte) 0x1A && magicNumber[7] == (byte) 0xE1) {
            return true;
        }
        return false;
    }

    private static boolean isXlsxFile(byte[] magicNumber) {
        if (magicNumber[0] == (byte) 0x50 && magicNumber[1] == (byte) 0x4B && magicNumber[2] == (byte) 0x03 &&
                magicNumber[3] == (byte) 0x04 && magicNumber[4] == (byte) 0x14 && magicNumber[5] == (byte) 0x00 &&
                magicNumber[6] == (byte) 0x06 && magicNumber[7] == (byte) 0x00) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "";
        byte[] magicNumber = new byte[8];
        FileInputStream fileInputStream = new FileInputStream(filePath);

        int read = fileInputStream.read(magicNumber);
        if (read >= 8) {
            boolean xlsFile = isXlsFile(magicNumber);
            boolean xlsxFile = isXlsxFile(magicNumber);
            System.out.println("=================xlsFile:"+xlsFile);
            System.out.println("=================xlsxFile:"+xlsxFile);
            return;
        }
        System.out.println("===============file type error============");
    }


}
