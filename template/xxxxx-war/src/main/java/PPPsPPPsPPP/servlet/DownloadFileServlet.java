
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
 * Servlet implementation of file download
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
