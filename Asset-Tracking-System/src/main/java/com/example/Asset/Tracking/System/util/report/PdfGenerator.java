package com.example.Asset.Tracking.System.util.report;

import com.example.Asset.Tracking.System.dto.HistoryDto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class PdfGenerator {

    public static ByteArrayInputStream generateHistoryPdf(List<HistoryDto> histories) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Asset Assignment History Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Space

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 3, 3, 4, 4});

            Stream.of("Asset", "User", "Notes", "Start Date", "End Date", "Status")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (HistoryDto dto : histories) {
                table.addCell(dto.getAssetName() != null ? dto.getAssetName() : "");
                table.addCell(dto.getUserName() != null ? dto.getUserName() : "");
                table.addCell(dto.getNotes() != null ? dto.getNotes() : "");
                table.addCell(dto.getStartDate() != null ? dto.getStartDate().format(formatter) : "");
                table.addCell(dto.getEndDate() != null ? dto.getEndDate().format(formatter) : "");
                table.addCell(dto.getEndDate() == null ? "Active" : "Completed");
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
