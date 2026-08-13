
package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.entity.Yyyyy;

import java.util.logging.Logger;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.annotation.security.RolesAllowed;

/**
 * Lllll
 *
 * This is a JAX-RS REST service (YyyyyDeletedRestService) that returns the IDs
 * of deleted records.
 * It exposes a single secured endpoint:
 * - GET /yyyyysDeleted/{param} (roles ADMIN or USER)
 *   Expects a date string in the format dd-MM-yyyy as the path parameter (lastSyncDateStr).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Path("/yyyyysDeleted")
@RequestScoped
public class YyyyyDeletedRestService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Path("/{param}")
    @Produces("application/json")
    public List<String> listDeletedYyyyys(@PathParam("param") String lastSyncDateStr) {
        @SuppressWarnings("unchecked")

        List<String> results = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date lastSyncDate = null;
        try {
            lastSyncDate = dateFormat.parse(lastSyncDateStr);
        } catch (ParseException e) {
            log.info("Bad lastSyncDate = " + lastSyncDateStr);
            return results;
        }

        final List<Yyyyy> yyyyys = em.createQuery("select m from Yyyyy m where m.lastUpdate >= :syncDate AND m.deleteFlag = TRUE order by m.id")
            .setParameter("syncDate", lastSyncDate, TemporalType.DATE)
            .getResultList();

        for (Yyyyy yyyyy : yyyyys) {

          String id = yyyyy.getCloudId();
          results.add(id);
        }

        return results;
    }

}
