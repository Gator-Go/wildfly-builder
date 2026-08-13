package ppp.ppp.ppp.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.logging.Logger;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.annotation.security.RolesAllowed;

/**
 * Lllll
 *
 * This is a JAX-RS REST service (NewDeviceRestService) that generates the next device ID.
 * It exposes a single secured endpoint:
 * - GET /newDevice (roles ADMIN or USER)
 * Returns the next sequential integer ID.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */
@Path("/newDevice")
@RequestScoped
public class NewDeviceRestService {

    @Inject
    private Logger log;

    @RolesAllowed({"ADMIN", "USER"})
    @GET
    @Produces("application/json")
    public Integer getDeviceID() {
        Integer seq = -1;
        String httpRoot = System.getProperty("com.sw-builder.sync.app.data.dir");

        if (httpRoot == null || httpRoot.isBlank()) {
            log.warning("System property 'com.sw-builder.sync.app.data.dir' is not set");
            return seq;
        }

        String seqGeneratorFile = httpRoot + "/DeviceGenerator.dat";
        RandomAccessFile seqFile = null;
        FileLock lock = null;

        try {
            seqFile = new RandomAccessFile(seqGeneratorFile, "rw");
            FileChannel fc = seqFile.getChannel();
            lock = fc.lock();

            try {
                seq = seqFile.readInt();
            } catch (IOException ex) {
                log.warning("IOException reading seqFile, resetting sequence number to 1");
                seq = 1;
            }

            seq++;
            seqFile.seek(0);
            seqFile.writeInt(seq);

        } catch (FileNotFoundException ex) {
            log.warning("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            log.warning("IOException: " + ex.getMessage());
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                } catch (IOException ex) {
                    log.warning("IOException releasing lock: " + ex.getMessage());
                }
            }
            if (seqFile != null) {
                try {
                    seqFile.close();
                } catch (IOException ex) {
                    log.warning("IOException closing file: " + ex.getMessage());
                }
            }
        }

        return seq;
    }
}