package services;

import java.io.InputStream;
import java.util.function.Function;

public interface ReportService {

    Function<InputStream, byte[]> buildReportFile();

    Function<InputStream, byte[]> buildZipReportFile();
}
