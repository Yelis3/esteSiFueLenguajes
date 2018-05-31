import javax.ws.rs.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Path("/detect")
public class DetectorController {
    @POST
    @Consumes("application/json")
    public String Detect(String code) throws Exception {
        String[] auxArray = code.split("~");

        Detector detector = new Detector();
        detector.detect(auxArray);

        return "code received successfully from server";
    }
}