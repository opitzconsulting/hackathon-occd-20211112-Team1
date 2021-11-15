package com.opitzconsulting.hackathon.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.time.OffsetDateTime;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class ExportService {

    private final ChargingSessionRepositoy chargingSessionRepositoy;

    public ByteArrayOutputStream createReport() throws DocumentException {

        List<ChargingSession> chargingSessions = chargingSessionRepositoy.findAll();
        Document document = new Document();

        ByteArrayOutputStream pdfBytes = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, pdfBytes);

        document.open();
        PdfPTable table = new PdfPTable(7);
        createHeader(table);
        createContent(table, chargingSessions);
        document.add(table);
        document.close();
        return pdfBytes;
    }

    private void createHeader(PdfPTable table) {
        PdfPCell idTagHeader = new PdfPCell(new Phrase("ID-Tag"));
        PdfPCell transactionIdHeader = new PdfPCell(new Phrase("Transaction-ID"));
        PdfPCell startMeterHeader = new PdfPCell(new Phrase("Start Meter"));
        PdfPCell stopMeterHeader = new PdfPCell(new Phrase("Stop Meter"));
        PdfPCell consumptionHeader = new PdfPCell(new Phrase("Consumption"));
        PdfPCell chargingStartHeader = new PdfPCell(new Phrase("Charging Start"));
        PdfPCell chargingEndHeader = new PdfPCell(new Phrase("Charging End"));
        table.addCell(idTagHeader);
        table.addCell(transactionIdHeader);
        table.addCell(startMeterHeader);
        table.addCell(stopMeterHeader);
        table.addCell(consumptionHeader);
        table.addCell(chargingStartHeader);
        table.addCell(chargingEndHeader);
    }

    private void createContent(PdfPTable table, List<ChargingSession> chargingSessions) {
        chargingSessions.forEach( chargingSession -> {
            table.addCell(new Phrase(String.valueOf(chargingSession.getIdTag())));
            table.addCell(new Phrase(String.valueOf(chargingSession.getTransactionId())));
            table.addCell(new Phrase(String.valueOf(chargingSession.getStartMeter())));
            Integer stopMeter = chargingSession.getStopMeter();
            if (stopMeter == null) {
                stopMeter = 0;
            }
            table.addCell(new Phrase(String.valueOf(stopMeter)));
            table.addCell(new Phrase(String.valueOf(stopMeter - chargingSession.getStartMeter())));
            table.addCell(new Phrase(chargingSession.getChargingStart().toString()));
            OffsetDateTime chargingEndDate = chargingSession.getChargingEnd();
            String chargingEnd = "<On Going>";
            if (chargingEndDate != null) {
                chargingEnd = chargingEndDate.toString();
            }
            table.addCell(new Phrase(chargingEnd));
        });
    }
}
