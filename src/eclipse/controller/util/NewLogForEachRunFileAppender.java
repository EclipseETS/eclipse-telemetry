package eclipse.controller.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;

/**
* This is a customized log4j appender, which will create a new file for every
* run of the application.
*
* @author veera | http://veerasundar.com
*
*/
public class NewLogForEachRunFileAppender extends FileAppender {

public NewLogForEachRunFileAppender() {
}

public NewLogForEachRunFileAppender(Layout layout, String filename,
        boolean append, boolean bufferedIO, int bufferSize)
        throws IOException {
    super(layout, filename, append, bufferedIO, bufferSize);
}

public NewLogForEachRunFileAppender(Layout layout, String filename,
        boolean append) throws IOException {
    super(layout, filename, append);
}

public NewLogForEachRunFileAppender(Layout layout, String filename)
        throws IOException {
    super(layout, filename);
}

public void activateOptions() {
if (fileName != null) {
    try {
    	Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy_MM_dd_hh_mm_ss");

    	fileName = "log/Telemetry_"+ft.format(dNow)+".txt";
        setFile(fileName, fileAppend, bufferedIO, bufferSize);
    } catch (Exception e) {
        errorHandler.error("Error while activating log options", e,
                ErrorCode.FILE_OPEN_FAILURE);
    }
}
}

}