/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.plikiUpload;

import java.io.File;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class Ocr {
    Tesseract instance;
    
    public Ocr(){
        ImageIO.scanForPlugins();
        //System.err.println("tttttttttteeeeeeeeeeeeeeeocr"+imageFile.exists());
        instance = new Tesseract(); // JNA Direct Mapping
        String tessdata=Tesseract.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        tessdata=tessdata.replace("tess4j-3.0.jar", "");
        tessdata=tessdata.replaceFirst("/","");
        //System.err.println(tessdata);
        instance.setDatapath(tessdata);
        instance.setLanguage("pol");
        instance.setPageSegMode(3);
        instance.setHocr(true);
        //Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping
        //

        /*try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println("eeeeeeeeeeeeeeeocr"+e.getMessage());
        }*/
    }
    
    public String oceeruj(File file){
        String result="brak OCR";
        try {
            result = instance.doOCR(file);
            //System.out.println(result);
        } catch (TesseractException e) {
            System.err.println("error_ocr"+e.getMessage());
        } catch (Exception ex1){
            System.err.println("error_ocr_inny_blad"+ex1.getMessage());
        }
        return result;
    }
    
}
