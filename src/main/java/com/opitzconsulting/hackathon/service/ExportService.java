package com.opitzconsulting.hackathon.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class ExportService {

    private final ChargingSessionRepositoy chargingSessionRepositoy;

    public Document createReport() {

        List<ChargingSession> chargingSessions = chargingSessionRepositoy.findAll();
        Document document = new Document();
//        PdfWriter.getInstance(document, );
        return null;
    }


}
