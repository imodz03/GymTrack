package com.elliotb.Resources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.DAO.StatsDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.Set;
import com.elliotb.Entity.Stat;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.ExerciseService;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

        return Response.ok(stats).build();
    }

}
