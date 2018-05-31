import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/refactor")
public class RefactorController {
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public String Detect(String code) {

        return code;
    }
}