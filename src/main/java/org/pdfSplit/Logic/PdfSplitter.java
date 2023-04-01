package org.pdfSplit.Logic;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PdfSplitter {


    public static void main(String[] args) {
        try {

            // Specify the folder path and file name
            String localDirectory = System.getProperty("user.dir");
            String folderPath = "/PDFs/";
            String fileName = "A17_FlightPlan.pdf";
            String inputFullPath = localDirectory + folderPath + fileName;
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(inputFullPath));

            // Create a PDF splitter object
            Splitter splitter = new Splitter();

            // Set the split range to 10 pages per document
            splitter.setSplitAtPage(10);

            // Split the PDF document into separate documents
            java.util.List<PDDocument> pages = splitter.split(document);

            // Save the separate PDF documents
            String outputFolderPath = localDirectory + "/PDFs/outPut/";
            for (int i = 0; i < pages.size(); i++) {
                String outputFileName = outputFolderPath + "output_" + (i+1) + ".pdf";
                pages.get(i).save(outputFileName);
                System.out.println(outputFileName);
            }

            // Close the PDF documents
            document.close();
            for (PDDocument page : pages) {
                page.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
