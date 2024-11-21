package email;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.TabExpander;


public class GeneratePdf {
    
    
    // Metodo para Obtener el Nombre de la empresa
    static Cell getNameOrganaziation(String text){
        return new Cell().add(text).setFontSize(25f).setBorder(Border.NO_BORDER).setBold();
    }

    //Metodo para obtener el concepto que acompañara al dato
    static Cell getHeaderTextCell(String text){
        return new Cell().add(text).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    //Metodo para obtener valor del dato 
    static Cell getHeaderTextCellValue(String text){
        return new Cell().add(text).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    
    //Metodo para obtener las cabeceras de las columas de datos intermedios
    static Cell getBilllingasndShippingCell(String text){
        return new Cell().add(text).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    //Metodo para agregar individualmente los datos de las partes involucradas
    static Cell getCell10Left(String text, Boolean isBold){
        Cell myCell = new Cell().add(text).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        if(isBold){
            myCell = new Cell().add(text).setFontSize(10f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        }
        return myCell;
    }

    //public static byte[] generatePDF2(Boleta boleta) throws  IOException  {
    public static byte[] generatePDF2() throws  IOException  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String path="invoice.pdf";
        //PdfWriter pdfWriter = new PdfWriter(path);
        
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(new PdfDocument(new PdfWriter(baos)));
        
        float threecol = 190f;
        float twocol = 285f;
        float twocol150= twocol+ 150f;
        float twocolumnWidth[]= {twocol150, twocol };
        float threecolumnWidth[]= {70f, 250f,70f,70f,75f };
        float fullwith[]={threecol*3};
        Table table = new Table(twocolumnWidth);        
        table.addCell(getNameOrganaziation("LAU CHUN"));        
        Table nestedtabe = new Table(new float[]{twocol/2, twocol/2});
        
        
        //AGREGAR LOS VALORES CON LOS METODOS       
        nestedtabe.addCell(getHeaderTextCell("Documento No.").setFontColor(Color.WHITE));
        //nestedtabe.addCell(getHeaderTextCellValue(boleta.getNumeroBoleta()).setFontColor(Color.WHITE));
        nestedtabe.addCell(getHeaderTextCellValue("B001").setFontColor(Color.WHITE));
        nestedtabe.addCell(getHeaderTextCell("Fecha de venta:").setFontColor(Color.WHITE));
        //nestedtabe.addCell(getHeaderTextCellValue(boleta.getFecha()).setFontColor(Color.WHITE));
        nestedtabe.addCell(getHeaderTextCellValue("14/11/2024").setFontColor(Color.WHITE));
        //table.addCell(nestedtabe.setBorder(Border.NO_BORDER).setBackgroundColor(Color.RED, 0.7f).setBorder(Border.NO_BORDER)); //agregamos  los datos de la parte derecha 
        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER).setBold().setBackgroundColor(Color.RED, 0.7f));
        Paragraph espaciado = new Paragraph("\n");

        Border gb = new SolidBorder(Color.GRAY,4f/4f);
        Table divider = new Table(fullwith);
        divider.setBorder(gb); // le paso las propiedades que tendrá mi linea divisora
        
        document.add(table); // agregamos la tabla que contiene el nombre de la empresa
        document.add(espaciado);// agregamos el espaciado entre el divisor y el resto de texto 
        document.add(divider);// agregamos el divisor al pdf        

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBilllingasndShippingCell("Datos del cliente"));
        twoColTable.addCell(getBilllingasndShippingCell("Datos de la empresa"));
        document.add(twoColTable.setMarginLeft(12f).setMarginTop(12f));

        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10Left("Nombre:", true));
        twoColTable2.addCell(getCell10Left("Razon Social:", true));
        //twoColTable2.addCell(getCell10Left(boleta.getNombreCliente(), false));
        twoColTable2.addCell(getCell10Left("Harvey Guerrero Laban", false));
        twoColTable2.addCell(getCell10Left("Lau Chun S.A",  false));
        twoColTable2.addCell(getCell10Left("Dirección:", true));
        twoColTable2.addCell(getCell10Left("Dirección:", true));
        //twoColTable2.addCell(getCell10Left(boleta.getDireccion(), false));
        twoColTable2.addCell(getCell10Left("Los olivos", false));
        twoColTable2.addCell(getCell10Left("San Martín de Porres",  false));
        twoColTable2.addCell(getCell10Left("DNI:", true));
        twoColTable2.addCell(getCell10Left("RUC:", true));
        //twoColTable2.addCell(new Cell().add(boleta.getDni()+"").setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        twoColTable2.addCell(new Cell().add("75578039").setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        twoColTable2.addCell(getCell10Left("20457987456",  false));
        twoColTable2.addCell(getCell10Left("Email:", true));
        twoColTable2.addCell(getCell10Left("Email:", true));
        //twoColTable2.addCell(getCell10Left(boleta.getEmail(), false));
        twoColTable2.addCell(getCell10Left("guerrero964759246@gmail.com", false));
        twoColTable2.addCell(getCell10Left("lauchun.sm@gmail.com",  false));
        twoColTable2.addCell(getCell10Left("", true));
        twoColTable2.addCell(getCell10Left("Teléfono:", true));
        twoColTable2.addCell(getCell10Left("", false));
        twoColTable2.addCell(getCell10Left("964759246",  false));        
        
        document.add(twoColTable2.setMarginLeft(12f));

        
        Table tabledivider2 = new Table(fullwith);
        Border dgb = new DashedBorder(Color.GRAY, 0.5f);
        document.add(tabledivider2.setBorder(dgb));
        
        float oneCoumnwith[]= {twocol150};

        Table oneColTable = new Table(oneCoumnwith);
        oneColTable.addCell(getCell10Left("Productos", true));
               
        document.add(oneColTable.setMargin(12f));
        
        Table cabeceratabladet = new Table(threecolumnWidth);
        cabeceratabladet.setBackgroundColor(Color.RED, 0.7f).setTextAlignment(TextAlignment.CENTER);
        
        cabeceratabladet.addCell(new Cell().add("Item").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        cabeceratabladet.addCell(new Cell().add("Descripción").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        cabeceratabladet.addCell(new Cell().add("Cantidad").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        cabeceratabladet.addCell(new Cell().add("Precio").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        cabeceratabladet.addCell(new Cell().add("Monto").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));        
        document.add(cabeceratabladet);
        
        Table detallesventa = new Table(threecolumnWidth);
        detallesventa.setTextAlignment(TextAlignment.CENTER);
        
        /*
        int items = 0;
        //for(DetalleVentaEntity detalle : boleta.getDetalles()){
        for(int i = 1; i < 5; i++ ){
        	
            items++;
            
            //detallesventa.addCell(new Cell().add(""+items).setBorder(Border.NO_BORDER));
            //detallesventa.addCell(new Cell().add(detalle.getIdproducto().getNombproducto()).setBorder(Border.NO_BORDER));
            //detallesventa.addCell(new Cell().add(""+detalle.getCantidad()).setBorder(Border.NO_BORDER));
            //detallesventa.addCell(new Cell().add(""+detalle.getPrecioventa()).setBorder(Border.NO_BORDER));
            //detallesventa.addCell(new Cell().add(""+detalle.getCantidad()*detalle.getPrecioventa()).setBorder(Border.NO_BORDER));  
            
            
        }  
        */
        detallesventa.addCell(new Cell().add("Reserva de cancha 1").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("Alquiler de la cancha numero 1").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("1").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("120").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("120").setBorder(Border.NO_BORDER)); 
        
        document.add(detallesventa);

        float onetwo[] = {threecol*2+115f,threecol+ 120f };
        Table segmentototal = new Table(onetwo);
        segmentototal.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        segmentototal.addCell(tabledivider2).setBorder(Border.NO_BORDER);
        document.add(segmentototal);
        
        
        Table total = new Table(threecolumnWidth).setBold();
        total.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        total.addCell(new Cell().add("").setBorder(Border.NO_BORDER));        
        total.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        total.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        //total.addCell(new Cell().add(""+boleta.getTotal()).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
        total.addCell(new Cell().add("200").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
        document.add(total);
        document.add(tabledivider2);
        document.add(espaciado);
        document.add(divider);        
           
        
        
        float esloganwidth[]= {500f};
        Table piedepagina = new Table(esloganwidth);
        piedepagina.addCell(getCell10Left("Gracias por confiar en nosotros.", true).setTextAlignment(TextAlignment.CENTER));
        piedepagina.addCell(getCell10Left("Lau Chun, un mundo de conocimiento a tu alcance.", false).setTextAlignment(TextAlignment.CENTER));

        document.add(piedepagina);
        
        document.close();        
        byte[] pdfBytes = baos.toByteArray();
        
        return pdfBytes;
    }

}
