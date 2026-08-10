
package ppp.ppp.ppp.servlet.helper;

import java.io.File;
import java.io.FileOutputStream;
import jakarta.enterprise.context.RequestScoped;

/**
 * Lllll
 *
 * Utility helper for creating directories and files used by the camera / picture
 * storage functionality.
 * <p>
 * Provides simple methods to ensure a target directory exists and to create
 * a writable file at a given path.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class CameraHelper {

  private String androidDir="/home/conkan/bin/wildfly-9.0.2.Final/picture";
  

  public String makeDir(String dir, String subDir) {
    
    File fileDir = new File(androidDir);

    if (!fileDir.exists() && !fileDir.mkdirs()) {
      return null;
    }
    File pictureFileDir = new File(fileDir, subDir);

    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
      return null;
    }
    return pictureFileDir.getPath();
  }


  public File makeFile(String fileStr) {

    File photoFile = null;
    try {
      photoFile = new File(fileStr);
      photoFile.createNewFile();
      photoFile.setWritable(true, false);
    } catch (Exception error) {
      return null;
    }
    return photoFile;
  }
}
