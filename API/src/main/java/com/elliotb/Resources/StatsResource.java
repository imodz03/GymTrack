package com.elliotb.Resources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.DAO.StatsDAO;
import com.elliotb.Entity.Stat;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.ExerciseService;
import com.google.inject.Inject;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/API/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatsResource {

    @Inject
    private tokenDecrypter td;

    @Inject
    private StatsDAO dao;

    @Inject
    private ExerciseService exerciseService;

    @GET
    @AuthRequired
    @Path("/{exID}")
    public Response getStats(@PathParam("exID")String exid, @Context HttpHeaders httpHeaders){

        List<Stat> stats = dao.getStats(exid, td.getId(httpHeaders));
        List<Stat> bestStats = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        String lastDate = stats.get(0).getDate();
        dates.add(lastDate);

        // get distinct dates
        for (Stat stat : stats) {
            if (!stat.getDate().equalsIgnoreCase(lastDate)){
                lastDate = stat.getDate();
                dates.add(lastDate);
            }else{
                lastDate = stat.getDate();
            }
        }

        // getting the best stat (weights only) for date
        for (String date : dates) {
            int bestStat = 0;
            Stat temp = null;
            for (Stat stat : stats) {
                if (stat.getDate().equalsIgnoreCase(date)){
                    if (stat.getWeight() > bestStat){
                        bestStat = (int)stat.getWeight();
                        temp = stat;
                    }
                }
            }
            if (temp != null){
                bestStats.add(temp);
            }


        }
        return Response.ok(bestStats).build();
    }

}
