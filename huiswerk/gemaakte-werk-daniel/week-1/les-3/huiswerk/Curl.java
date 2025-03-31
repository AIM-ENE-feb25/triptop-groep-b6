import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Curl {

    public static void main(String[] args) throws IOException {
        
        String curlCommand = "curl -X POST https://triptop-identity.wiremockapi.cloud/login -H \"Content-Type: application/json\" -d \"{\\\"username\\\":\\\"edevries\\\", \\\"password\\\":\\\"3g2Rw9sT1x\\\"}\"";
        Process process = Runtime.getRuntime().exec(curlCommand);

        //leest response
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder response = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");
        }

        System.out.println("response: " + response.toString().trim());

    }
}

