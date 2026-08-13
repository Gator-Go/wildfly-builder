
package ppp.ppp.ppp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.logging.Logger;
import java.util.Set;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * Lllll
 *
 * This is a file download servlet (DownloadFileServlet).
 * It provides a single method doFileDownload that:
 * - Reads FilePath and FileName from the request parameters.
 * - Opens the file at the given path.
 * - Sets the response headers for a file download (Content-Type: application/download
 *   and Content-Disposition: attachment).
 * - Streams the file contents to the client in 4 KB chunks.
 * It is called by the central ControllerServlet when the operation is FileDownload.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class DownloadFileServlet extends HttpServlet
{
    @Inject
    private Logger log;

    public void doFileDownload(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
    {

        String filePath = request.getParameter("FilePath");
        String fileName = request.getParameter("FileName");
        log.info("*** In doFileDownload filePath: " + filePath);

        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        response.setContentType( "application/download" );
        response.setContentLength((int) downloadFile.length());
        response.setHeader( "Content-Disposition", "attachment; filename=" + fileName);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();

    }
}
