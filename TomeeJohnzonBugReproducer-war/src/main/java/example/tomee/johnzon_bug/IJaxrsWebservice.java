package example.tomee.johnzon_bug;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("services/JaxrsWebservice")
@Produces("application/json")
@Consumes("application/json")
public interface IJaxrsWebservice {
  
  @POST
  @Path("/trigger")
  public ModelObject trigger(ModelObject param);
  
}
