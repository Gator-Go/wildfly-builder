
package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.entity.Yyyyy;
import ppp.ppp.ppp.dto.YyyyyDto;

import java.util.logging.Logger;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.math.BigDecimal;

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
 * This is a JAX-RS REST service (YyyyyRestService) that returns non-deleted
 * Yyyyy records as DTOs for synchronization.
 * It exposes a single secured endpoint:
 * - GET /yyyyys/{param} (roles ADMIN or USER)
 *   Expects a date string in the format dd-MM-yyyy as the path parameter.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Path("/yyyyys")
@RequestScoped
public class YyyyyRestService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Path("/{param}")
    @Produces("application/json")
    public List<YyyyyDto> listAllYyyyys(@PathParam("param") String lastSyncDateStr) {

        List<YyyyyDto> results = new ArrayList<YyyyyDto>();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date lastSyncDate = null;
        try {
            lastSyncDate = dateFormat.parse(lastSyncDateStr);
        } catch (ParseException e) {
            log.info("Bad lastSyncDate = " + lastSyncDateStr);
            return results;
        }

        List<Yyyyy> yyyyys = em.createQuery(
            "select m from Yyyyy m where m.lastUpdate >= :syncDate AND m.deleteFlag = FALSE order by m.id")
           .setParameter("syncDate", lastSyncDate, TemporalType.DATE)
           .getResultList();

        for (Yyyyy yyyyy : yyyyys) {

          YyyyyDto yyyyyDto = new YyyyyDto();
          yyyyyDto.setId(yyyyy.getId().toString());
          yyyyyDto.setDeviceId(yyyyy.getDeviceId().toString());
          yyyyyDto.setYyyyyId(yyyyy.getYyyyyId().toString());
          yyyyyDto.setLastUpdate(Long.toString(yyyyy.getLastUpdate().getTime()));
          yyyyyDto.setDeleteFlag("0");

___SET_DTO_STRING___
___SET_DTO_ENUM___
___SET_DTO_TAG___
___SET_DTO_CLOB___
___SET_DTO_BOOLEAN___
___SET_DTO_INTEGER___
___SET_DTO_LONG___
___SET_DTO_DOUBLE___
___SET_DTO_BIG_DECIMAL___
___SET_DTO_MONEY___
___SET_DTO_LOC___
___SET_DTO_CURRENT_LOC___
___SET_DTO_DATE___
___SET_DTO_DATE_TIME___
___SET_DTO_CAMERA___
___SET_DTO_VIDEO___
___SET_DTO_THUMBNAIL___
___SET_DTO_POST___
___SET_DTO_ONE2MANY_CHILD___
___SET_DTO_ONE2MANY_CHILD_ALERT___

          results.add(yyyyyDto);
        }

        return results;
    }

}
