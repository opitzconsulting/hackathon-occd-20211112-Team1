package com.opitzconsulting.hackathon.endpoint;

import com.itextpdf.text.DocumentException;
import com.opitzconsulting.hackathon.service.ExportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;

@Controller("/report/api/v1/consumption")
@RequiredArgsConstructor
public class ExportController {


    private final ExportService exportService;

    @Get(value = "/download", consumes = {MediaType.MULTIPART_FORM_DATA})
    @Operation(summary = "Starts a download of a pdf file.",
            description = "Starts a download of a pdf file containing a list of all charging sessions persisted in the database.")
    @Tag(name = "export")
    public HttpResponse<byte[]> downloadConsumptionReport() throws DocumentException {
        OffsetDateTime now = OffsetDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = String.valueOf(now.getMonth().getValue());
        String day = String.valueOf(now.getDayOfMonth());
        String millis = String.valueOf(now.get(ChronoField.MILLI_OF_SECOND));
        String fileName = year.concat(month).concat(day).concat(millis).concat("_ConsumptionReport.pdf");
        ByteArrayOutputStream report = exportService.createReport();
        byte[] pdfFile = report.toByteArray();
        return HttpResponse.ok(pdfFile)
                .header("Pragma", "public")
                .header("Cache-Control", "must-revalidate, post-check=0, pre-check=0")
                .header("Expires", "0")
                .header("Content-Transfer-Encoding", "binary")
                .header("Content-Type", MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\";")
                .header("Content-Length", String.valueOf(pdfFile.length));
    }

}
