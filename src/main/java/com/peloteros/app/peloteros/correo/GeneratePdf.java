package com.peloteros.app.peloteros.correo;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    
    
    

    
    public static byte[] generatePDF2(Boleta boleta, Long lastId) throws  IOException  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //String path="invoice.pdf";
        //PdfWriter pdfWriter = new PdfWriter(path);
        
     // Ruta de la imagen desde resources
        InputStream logoStream = GeneratePdf.class.getResourceAsStream("/logotipo.png");
        if (logoStream == null) {
            throw new FileNotFoundException("Logo no encontrado en /logotipo.png");
        }

        // Crear ImageData y la imagen
        ImageData imageData = ImageDataFactory.create(logoStream.readAllBytes());
        Image logo = new Image(imageData);
        logo.setWidth(50); // Ajusta el tamaño según lo necesario
        logo.setHeight(40);
        
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(new PdfDocument(new PdfWriter(baos)));
        
        float threecol = 190f;
        float twocol = 285f;
        float twocol150= twocol+ 150f;
        float twocolumnWidth[]= {twocol150, twocol };
        float threecolumnWidth[]= {70f, 250f,70f,70f,75f };
        float threecolumnhead[]= {70f, 330f,280,100f};
        float fullwith[]={threecol*3};
        Table table = new Table(threecolumnhead);
     // Celda con el logo
        Cell logoCell = new Cell().add(logo).setBorder(Border.NO_BORDER);
        table.addCell(logoCell);
        table.addCell(getNameOrganaziation("PELOTEROS")).setVerticalAlignment(VerticalAlignment.MIDDLE);        
        Table nestedtabe = new Table(new float[]{twocol/2, twocol/2});
        
        if(boleta.getTipodoc().equals("Boleta")) {
        	//AGREGAR LOS VALORES CON LOS METODOS       
            nestedtabe.addCell(getHeaderTextCell("Boleta N°:").setFontColor(Color.BLACK));
            nestedtabe.addCell(getHeaderTextCellValue("B000"+lastId).setFontColor(Color.BLACK));
            nestedtabe.addCell(getHeaderTextCell("Fecha:").setFontColor(Color.BLACK));
            nestedtabe.addCell(getHeaderTextCellValue(extraerfecha()).setFontColor(Color.BLACK));
            //table.addCell(nestedtabe.setBorder(Border.NO_BORDER).setBackgroundColor(Color.RED, 0.7f).setBorder(Border.NO_BORDER)); //agregamos  los datos de la parte derecha 
            table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER).setBold().setBackgroundColor(Color.WHITE, 1f));
            
        }else if(boleta.getTipodoc().equals("Factura")) {
        	//AGREGAR LOS VALORES CON LOS METODOS       
            nestedtabe.addCell(getHeaderTextCell("Factura N°:").setFontColor(Color.BLACK));
            //nestedtabe.addCell(getHeaderTextCellValue(boleta.getNumeroBoleta()).setFontColor(Color.WHITE));
            nestedtabe.addCell(getHeaderTextCellValue("F000-00"+lastId).setFontColor(Color.BLACK));
            nestedtabe.addCell(getHeaderTextCell("Fecha:").setFontColor(Color.BLACK));
            nestedtabe.addCell(getHeaderTextCellValue(extraerfecha()).setFontColor(Color.BLACK));
            //table.addCell(nestedtabe.setBorder(Border.NO_BORDER).setBackgroundColor(Color.RED, 0.7f).setBorder(Border.NO_BORDER)); //agregamos  los datos de la parte derecha 
            table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER).setBold().setBackgroundColor(Color.WHITE, 1f));
        }
        
        //Paragraph espaciado = new Paragraph("\n");

        Border gb = new SolidBorder(Color.GRAY,4f/4f);
        Table divider = new Table(fullwith);
        divider.setBorder(gb); // le paso las propiedades que tendrá mi linea divisora
        
        document.add(table); // agregamos la tabla que contiene el nombre de la empresa
        //document.add(espaciado);// agregamos el espaciado entre el divisor y el resto de texto 
        document.add(divider);// agregamos el divisor al pdf        

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBilllingasndShippingCell("Datos del cliente"));
        twoColTable.addCell(getBilllingasndShippingCell("Datos de la empresa"));
        document.add(twoColTable.setMarginLeft(12f).setMarginTop(12f));

        Table twoColTable2 = new Table(twocolumnWidth);
        if(boleta.getTipodoc().equals("Boleta")) {
        	twoColTable2.addCell(getCell10Left("Nombre:", true));
            twoColTable2.addCell(getCell10Left("Razon Social:", true));
            twoColTable2.addCell(getCell10Left(boleta.getNombreCliente(), false));
            twoColTable2.addCell(getCell10Left("Peloteros S.A.C",  false));
            twoColTable2.addCell(getCell10Left("Dirección:", true));
            twoColTable2.addCell(getCell10Left("Dirección:", true));
            twoColTable2.addCell(getCell10Left(boleta.getDireccion(), false));
            twoColTable2.addCell(getCell10Left("Trapiche 12-B, Comas, Lima",  false));
            twoColTable2.addCell(getCell10Left("DNI:", true));
            twoColTable2.addCell(getCell10Left("RUC:", true));
            twoColTable2.addCell(new Cell().add(boleta.getDni()+"").setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));            
            twoColTable2.addCell(getCell10Left("20608648748",  false));
            twoColTable2.addCell(getCell10Left("Email:", true));
            twoColTable2.addCell(getCell10Left("Email:", true));
            twoColTable2.addCell(getCell10Left(boleta.getEmail(), false));
            twoColTable2.addCell(getCell10Left("peloteros.ch@gmail.com",  false));
            twoColTable2.addCell(getCell10Left("", true));
            twoColTable2.addCell(getCell10Left("Teléfono:", true));
            twoColTable2.addCell(getCell10Left("", false));
            twoColTable2.addCell(getCell10Left("994048444",  false));
            
        }else if(boleta.getTipodoc().equals("Factura")) {
        	twoColTable2.addCell(getCell10Left("Razon Social:", true));
            twoColTable2.addCell(getCell10Left("Razon Social:", true));            
            twoColTable2.addCell(getCell10Left(boleta.getNombreCliente(), false));
            twoColTable2.addCell(getCell10Left("Peloteros S.A.C",  false));
            twoColTable2.addCell(getCell10Left("Dirección:", true));
            twoColTable2.addCell(getCell10Left("Dirección:", true));
            twoColTable2.addCell(getCell10Left(boleta.getDireccion(), false));
            twoColTable2.addCell(getCell10Left("Trapiche 12-B, Comas, Lima",  false));
            twoColTable2.addCell(getCell10Left("RUC:", true));
            twoColTable2.addCell(getCell10Left("RUC:", true));
            twoColTable2.addCell(new Cell().add(boleta.getDni()+"").setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));            
            twoColTable2.addCell(getCell10Left("20608648748",  false));
            twoColTable2.addCell(getCell10Left("Email:", true));
            twoColTable2.addCell(getCell10Left("Email:", true));
            twoColTable2.addCell(getCell10Left(boleta.getEmail(), false));
            twoColTable2.addCell(getCell10Left("peloteros.ch@gmail.com",  false));
            twoColTable2.addCell(getCell10Left("", true));
            twoColTable2.addCell(getCell10Left("Teléfono:", true));
            twoColTable2.addCell(getCell10Left("", false));
            twoColTable2.addCell(getCell10Left("994048444",  false));  
        }      
              
        
        document.add(twoColTable2.setMarginLeft(12f));

        
        Table tabledivider2 = new Table(fullwith);
        Border dgb = new DashedBorder(Color.GRAY, 0.5f);
        document.add(tabledivider2.setBorder(dgb));
        
        float oneCoumnwith[]= {twocol150};

        Table oneColTable = new Table(oneCoumnwith);
        oneColTable.addCell(getCell10Left("Productos", true));
               
        document.add(oneColTable.setMargin(12f));
        
        Table cabeceratabladet = new Table(threecolumnWidth);
        cabeceratabladet.setBackgroundColor(Color.GRAY, 1f).setTextAlignment(TextAlignment.CENTER);
        
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
        
        
        
        
        
        detallesventa.addCell(new Cell().add("1").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("Alquiler de la cancha numero: "+boleta.getIdcancha()+"\n Fecha: " + boleta.getFecha() + "  Hora: " + boleta.getHora()).setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add("1").setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add(""+boleta.getPrecio()).setBorder(Border.NO_BORDER));
        detallesventa.addCell(new Cell().add(""+boleta.getPrecio()).setBorder(Border.NO_BORDER));
        
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
        total.addCell(new Cell().add(boleta.getPrecio()).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
        document.add(total);
        document.add(tabledivider2);
        //document.add(espaciado);
        document.add(divider);        
           
        
        
        float esloganwidth[]= {500f};
        Table piedepagina = new Table(esloganwidth);
        piedepagina.addCell(getCell10Left("Gracias por confiar en nosotros.", true).setTextAlignment(TextAlignment.CENTER));
        piedepagina.addCell(getCell10Left("Peloteros, la seguridad de una buena cancha.", false).setTextAlignment(TextAlignment.CENTER));

        document.add(piedepagina);
        
        document.close();        
        byte[] pdfBytes = baos.toByteArray();
        
        return pdfBytes;
    }
    
    public static String extraerfecha() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Formatear la fecha como una cadena
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);
        // Imprimir la fecha formateada
        System.out.println("Fecha actual: " + fechaFormateada);
        return fechaFormateada;
    }

}
