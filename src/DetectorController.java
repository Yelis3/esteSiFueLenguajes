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
    public String Detect(String code) throws IOException {
        String input = "holiiiii.txt";
        java.nio.file.Path path = Paths.get(input);
        List<String> lines = new LinkedList<String>();

        String[] auxArray = code.split("~");

        System.out.println(Arrays.toString(auxArray));

        for(String x:auxArray)
            lines.add(x);

        Files.write(path, lines, StandardCharsets.UTF_8);

        System.out.println(lines);
        return "code recieved successfully from server";
    }
}