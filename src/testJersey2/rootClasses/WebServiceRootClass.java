package testJersey2.rootClasses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/foo")
public class WebServiceRootClass {
    
    static {
        System.out.println("**** Loading WebServiceRootClass ****"); //$NON-NLS-1$
    }
    
    @GET @Path("/bar")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createDummyText() {
        String response = "foo bar content...";
        return Response.ok(response).type(MediaType.TEXT_PLAIN).build();
    }

}
