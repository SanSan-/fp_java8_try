package services;

import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@NoArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Override
    public Function<InputStream, byte[]> buildReportFile() {
        return inputStream -> {
            try {
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public Function<InputStream, byte[]> buildZipReportFile() {
        return inputStream -> {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                zipOutputStream.putNextEntry(new ZipEntry(""));

                int count;
                byte data[] = new byte[2048];
                BufferedInputStream entryStream = new BufferedInputStream(inputStream, 2048);
                while ((count = entryStream.read(data, 0, 2048)) != -1) {
                    zipOutputStream.write(data, 0, count);
                }
                entryStream.close();

                zipOutputStream.closeEntry();
                zipOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
