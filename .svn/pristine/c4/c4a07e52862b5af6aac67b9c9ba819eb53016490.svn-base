
package ppp.ppp.ppp.servlet;

import org.apache.commons.fileupload2.core.FileItemInput;
import org.apache.commons.fileupload2.core.FileItemInputIterator;
import org.apache.commons.fileupload2.core.FileUploadException;

import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;

import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

@RequestScoped
public class CommonLib
{

    @Inject
    private Logger log;

    private int thumbHeight = 150;
    private int maxPicSize = 1024;

    public InputStream loadFileUpload(HttpServletRequest req) {

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {

                if (!item.isFormField()) {
                    log.info("setAttribute name filePath value = " + item.getName());
                    req.setAttribute("filePath", item.getName());
                    return item.getInputStream();
                }
            }

        } catch (Exception e) {
            log.warning("loadFileUpload Exception detected. " + e.getMessage());
        }
        return null;
    }

    /**
     * Load the temp file and req with key-values for processing of a file upload.
     *
     * @param req is the HttpServletRequest containing the parameters.
     */
    public void doFileUpload(HttpServletRequest req) {
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    log.info("setAttribute name = " + name + " value = " + value);
                    req.setAttribute(name, value);
                } else {
                    log.info("setAttribute name = filePath  value = " + item.getName());
                    req.setAttribute("filePath", item.getName());
                    String tempDir = System.getProperty("com.sw-builder.sync.app.temp.dir");
                    File uploadedFile = new File(tempDir + item.getName());
                    log.info("file to upload = " + uploadedFile.getAbsolutePath());
                    try (InputStream uploadedStream = item.getInputStream();
                         FileOutputStream outStream = new FileOutputStream(uploadedFile)) {
                        byte[] buffer = new byte[8 * 1024];
                        int bytesRead;
                        while ((bytesRead = uploadedStream.read(buffer)) != -1) {
                            outStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.warning("doFileUpload Exception detected. " + e.getMessage());
        }
    }

    /**
     * Load the temp file and req with key-values for processing of a file upload.
     *
     * @param req is the HttpServletRequest containing the parameters.
     */
    public void doFileUploadSync(HttpServletRequest req, File upFile)
    {
      File uploadedFile = upFile;

      try {

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);

        for (FileItem item : items) {

          if (item.isFormField()) {
              String name = item.getFieldName();
              String value = item.getString();
              log.info("setAttributeSync name = " + name + " value = " + value);
              req.setAttribute(name, value);
          } else {
              String tempDir = System.getProperty("com.sw-builder.sync.app.temp.dir");
              uploadedFile = new File(tempDir + item.getName());
              log.info("setAttributeSync name = filePath  value = " + item.getName());
              req.setAttribute("filePath", item.getName());

              try (InputStream uploadedStream = item.getInputStream();
                   FileOutputStream outStream = new FileOutputStream(uploadedFile)) {
                  byte[] buffer = new byte[8 * 1024];
                  int bytesRead;
                  while ((bytesRead = uploadedStream.read(buffer)) != -1) {
                      outStream.write(buffer, 0, bytesRead);
                  }
              }

          }

        }
      } catch (Exception e) {
          log.warning("doFileUploadSync Exception detected. " + e.getMessage());
      }

      if ( uploadedFile != upFile ) {
        try {
            // Source and destination paths
            Path source = Paths.get(uploadedFile.getPath());
            Path destination = Paths.get(upFile.getPath());

            // Move the file
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            log.warning("doFileUploadSync Move Exception detected. " + e.getMessage());
        }
      }

    }


    /**
     * Load the temp file and req with key-values for processing of a file upload.
     *
     * @param req is the HttpServletRequest containing the parameters.
     */
    public void doFileUploadThumbnail(HttpServletRequest req)
    {
      File uploadedFile = null;
      String tempDir = System.getProperty("com.sw-builder.sync.app.temp.dir");
      String filePath = "";

      try {

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);

        for (FileItem item : items) {

          if (item.isFormField()) {
              String name = item.getFieldName();
              String value = item.getString();
              log.info("setAttribute name = " + name + " value = " + value);
              req.setAttribute(name, value);
          } else {
              log.info("setAttribute name = filePath  value = " + item.getName());
              req.setAttribute("filePath", item.getName());
              filePath = tempDir + item.getName();
              uploadedFile = new File(filePath);
              log.info("file to upload = " + uploadedFile.getAbsolutePath());
              try (InputStream uploadedStream = item.getInputStream();
                   FileOutputStream outStream = new FileOutputStream(uploadedFile)) {
                  byte[] buffer = new byte[8 * 1024];
                  int bytesRead;
                  while ((bytesRead = uploadedStream.read(buffer)) != -1) {
                      outStream.write(buffer, 0, bytesRead);
                  }
              }
          }

        }

      } catch (Exception e) {
          log.warning("doFileUploadThumbnail Exception detected. " + e.getMessage());
      }

      if (uploadedFile != null ) {
        try {
          BufferedImage bimg = ImageIO.read(uploadedFile);
          int originalWidth = bimg.getWidth();
          int originalHeight = bimg.getHeight();
          double originalWidthToHeightRatio =  1.0 * originalWidth / originalHeight;
          int width = (int) (thumbHeight * originalWidthToHeightRatio);

          BufferedImage img = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_INT_RGB);
          img.createGraphics().drawImage(ImageIO.read(uploadedFile).getScaledInstance(width, thumbHeight, Image.SCALE_SMOOTH),0,0,null);
          ImageIO.write(img, "jpg", new File(filePath));
        } catch (IOException e) {
          Throwable t = e;
          log.warning("FileUploadException detected. " + t.getMessage());
        }
      }

    }


    /**
     * Load the temp file and req with key-values for processing of a file upload.
     *
     * @param req is the HttpServletRequest containing the parameters.
     */
    public void doFileUploadMP4(HttpServletRequest req)
    {
      File uploadedFile = null;
      File frameFile = null;
      String tempDir = System.getProperty("com.sw-builder.sync.app.temp.dir");
      String upName = "";

      try {

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);

        for (FileItem item : items) {

          if (item.isFormField()) {
              String name = item.getFieldName();
              String value = item.getString();
              log.info("setAttribute name = " + name + " value = " + value);
              req.setAttribute(name, value);
          } else {
              log.info("setAttribute name = filePath  value = " + item.getName());
              upName = item.getName();
              if (upName.length() > 4)
                 upName = upName.substring(0, upName.length() - 4);
              req.setAttribute("filePath", upName);
              String filePath = tempDir + upName + ".mp4";
              uploadedFile = new File(filePath);
              log.info("file to upload = " + uploadedFile.getAbsolutePath());
              try (InputStream uploadedStream = item.getInputStream();
                   FileOutputStream outStream = new FileOutputStream(uploadedFile)) {
                  byte[] buffer = new byte[8 * 1024];
                  int bytesRead;
                  while ((bytesRead = uploadedStream.read(buffer)) != -1) {
                      outStream.write(buffer, 0, bytesRead);
                  }
              }
          }

        }

      } catch (Exception e) {
          log.warning("doFileUploadMP4 Exception detected. " + e.getMessage());
      }

      if (uploadedFile != null) {
	  try {
            String framePath = tempDir + upName + ".jpg";
            frameFile = new File(framePath);

	    // Configure video attributes
            VideoAttributes video = new VideoAttributes();
            video.setCodec("mjpeg");
            video.setSize(new VideoSize(640, 480));

	    // Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOutputFormat("mjpeg");
            attrs.setVideoAttributes(video);
            attrs.setOffset(1.0f); // First frame

	    // Perform encoding
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(uploadedFile), frameFile, attrs);

          } catch (ws.schild.jave.EncoderException e) {
            log.severe("First Frame Capture EncoderException: " + e.getMessage() + ", Cause: " + e.getCause());
            //e.printStackTrace(); // Temporary for debugging
          } catch (Exception e) {
            log.severe("First Frame Capture Unexpected Exception: " + e.getMessage() + ", Cause: " + e.getCause());
            //e.printStackTrace(); // Temporary for debugging
          }
	    
      }

      if (frameFile != null ) {
        try {
          BufferedImage bimg = ImageIO.read(frameFile);
          int originalWidth = bimg.getWidth();
          int originalHeight = bimg.getHeight();
          double originalWidthToHeightRatio =  1.0 * originalWidth / originalHeight;
          int width = (int) (thumbHeight * originalWidthToHeightRatio);

          BufferedImage img = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_INT_RGB);
          img.createGraphics().drawImage(ImageIO.read(frameFile).getScaledInstance(width, thumbHeight, Image.SCALE_SMOOTH),0,0,null);
          ImageIO.write(img, "jpg", new File(tempDir + upName + "VidThumb.jpg"));
          frameFile.delete();
        } catch (Exception e) {
          log.warning("FileUploadException detected. " + e.getMessage());
        }
      }

    }



    /**
     * Load the temp file and req with key-values for processing of a file upload.
     *
     * @param req is the HttpServletRequest containing the parameters.
     */
    public void doFileUploadPic(HttpServletRequest req)
    {
      File uploadedFile = null;
      String tempDir = System.getProperty("com.sw-builder.sync.app.temp.dir");
      String upName = "";

      try {

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);

        for (FileItem item : items) {

          if (item.isFormField()) {
              String name = item.getFieldName();
              String value = item.getString();
              log.info("setAttribute name = " + name + " value = " + value);
              req.setAttribute(name, value);
          } else {
              log.info("setAttribute name = filePath  value = " + item.getName());
              upName = item.getName();
              if (upName.length() > 4)
                 upName = upName.substring(0, upName.length() - 4);
              req.setAttribute("filePath", upName);
              String filePath = tempDir + upName + "-temp.jpg";
              uploadedFile = new File(filePath);
              log.info("file to upload = " + uploadedFile.getAbsolutePath());
              try (InputStream uploadedStream = item.getInputStream();
                   FileOutputStream outStream = new FileOutputStream(uploadedFile)) {
                  byte[] buffer = new byte[8 * 1024];
                  int bytesRead;
                  while ((bytesRead = uploadedStream.read(buffer)) != -1) {
                      outStream.write(buffer, 0, bytesRead);
                  }
              }
          }

        }

      } catch (Exception e) {
          log.warning("doFileUploadPic Exception detected. " + e.getMessage());
      }

      if (uploadedFile != null ) {
        try {
          BufferedImage bimg = ImageIO.read(uploadedFile);
          int originalWidth = bimg.getWidth();
          int originalHeight = bimg.getHeight();
          double originalWidthToHeightRatio =  1.0 * originalWidth / originalHeight;
          int width = (int) (thumbHeight * originalWidthToHeightRatio);

          BufferedImage img = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_INT_RGB);
          img.createGraphics().drawImage(ImageIO.read(uploadedFile).getScaledInstance(width, thumbHeight, Image.SCALE_SMOOTH),0,0,null);
          ImageIO.write(img, "jpg", new File(tempDir + upName + "PicThumb.jpg"));
        } catch (Exception e) {
          log.warning("FileUploadException detected. " + e.getMessage());
        }
      }

      if (uploadedFile != null ) {
        try {
          BufferedImage bimg = ImageIO.read(uploadedFile);
          int originalWidth = bimg.getWidth();
          int originalHeight = bimg.getHeight();
          int width;
          int height;
          String picPath = tempDir + upName + ".jpg";

          if (originalWidth <= maxPicSize && originalHeight <= maxPicSize) {

            File picFile = new File(picPath);
            uploadedFile.renameTo(picFile);

          } else {

            if (originalWidth > originalHeight) {
              double originalHeightToWidthRatio =  1.0 * originalHeight / originalWidth;
              height = (int) (maxPicSize * originalHeightToWidthRatio);
              width = maxPicSize;
            } else {
              double originalWidthToHeightRatio =  1.0 * originalWidth / originalHeight;
              width = (int) (maxPicSize * originalWidthToHeightRatio);
              height = maxPicSize;
            }

            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            img.createGraphics().drawImage(ImageIO.read(uploadedFile).getScaledInstance(width, height, Image.SCALE_SMOOTH),0,0,null);
            ImageIO.write(img, "jpg", new File(picPath));

            uploadedFile.delete();
          }
        } catch (Exception e) {
          log.warning("FileUploadException detected. " + e.getMessage());
        }
      }

    }
}
