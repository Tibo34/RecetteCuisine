package com.ConfigPoste.RecetteCuisine.RecetteCuisine.PDF;




import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFGenerator {

    public PDFGenerator(){}

    public void createPDF(String str){
        Document doc= new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("iTextHelloWorld.pdf"));
            doc.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk(str, font);

            doc.add(chunk);
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
