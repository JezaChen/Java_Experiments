package com.qrcore;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QRCore {
    private static MultiFormatReader multiFormatReader = new MultiFormatReader();

    public static String decode(File file)
            throws NotFoundException, IOException {
        String result = null;
        if (!file.exists())
            return result;

        BufferedImage image = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(image);

        Binarizer binarizer = new HybridBinarizer(source); //选取一个二值法转成二进制
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //增加一个属性

        Result decodeResult = multiFormatReader.decode(binaryBitmap, hints);

        return decodeResult.toString();
    }

    public static void encode(String target, int width, int height, String format) {
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(target, BarcodeFormat.QR_CODE, width, height, hints);
            File outputFile = new File("temp." + format);
            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encodeAndSave(String target, int width, int height, String format, File file) {
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(target, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToFile(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        encode(text, 300, 300, "png");

        File file = new File("temp.png");
        try {
            String ans = decode(file);
            System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        Form form = new Form();
        form.setVisible(true);
    }
}
