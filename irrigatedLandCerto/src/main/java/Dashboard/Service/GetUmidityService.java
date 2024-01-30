package Dashboard.Service;

import Dashboard.Sensors.MoistureGround;
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

@Stateless(mappedName = "GetUmidityService")
@EJB(name = "Dashboard.Service.GetUmidityRemoteService", beanInterface = GetUmidityRemoteService.class)
public class GetUmidityService implements GetUmidity, GetUmidityRemoteService{

    @EJB
    private EndPoint endPoint;

    @Override
    public String getUmidity() {
        String url = endPoint.getUmidity();

        try {
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
//            System.out.println("Código de resposta: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String stringResponse = response.toString();
            System.out.println("temperatura ambiente obtida do sensor: " + stringResponse);
            Gson gson = new Gson();
            MoistureGround moist = gson.fromJson(stringResponse, MoistureGround.class);

            return String.format("%.1f",moist.getMoisture());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
