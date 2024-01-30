package Dashboard.Service;

import CommunicationMQTT.TopicRemoteService;
import Dashboard.Sensors.WaterFlow;
import EndPoints.EndPoint;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Stateless(mappedName = "GetWaterFlowServiceBean")
@EJB(name = "Dashboard.Service.GetWaterFlowRemoteService", beanInterface = GetWaterFlowRemoteService.class)
public class GetWaterFlowServiceBean implements GetWaterFlowService, GetWaterFlowRemoteService {

    @EJB
    private EndPoint endPoint;

    @Override
    public double getWaterFlow() {
        String url = endPoint.getWaterFlow();

        try {
            String stringResponse = getStringResponse(url);
            Gson gson = new Gson();

            WaterFlow waterFlow = gson.fromJson(stringResponse, WaterFlow.class);
            System.out.println("Vazão de Água do Pivô: "
                    + String.format("%.2f", waterFlow.getWaterFlowMeansurement()) + " L/min");
            return waterFlow.getWaterFlowMeansurement();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

    private static String getStringResponse(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Configurações da requisição
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json"); // Define o tipo de conteúdo do corpo da requisição
        con.setDoOutput(true);

        // Envio do corpo da requisição vazio
        OutputStream os = con.getOutputStream();
        os.write("".getBytes());
        os.flush();
        os.close();

        // Captura da resposta
        int responseCode = con.getResponseCode();
//        System.out.println("Código de resposta: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
