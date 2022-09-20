package services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    public void test_build_report_file_success() throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("files/Subtitles.ru.ass")).getFile());
        InputStream inputStream = Files.newInputStream(file.toPath());
        byte[] bytes = reportService.buildReportFile().apply(inputStream);
        assertNotNull(bytes);
        assertEquals(47275, bytes.length);
    }

    @Test
    public void test_build_zip_report_file_success() throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("files/Subtitles.ru.ass")).getFile());
        InputStream inputStream = Files.newInputStream(file.toPath());
        byte[] bytes = reportService.buildZipReportFile().apply(inputStream);
        assertNotNull(bytes);
        assertEquals(11998, bytes.length);
    }
}
