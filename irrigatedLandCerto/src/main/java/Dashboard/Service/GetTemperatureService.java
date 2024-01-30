package Dashboard.Service;


import Dashboard.AirData;
import Dashboard.Sensors.AirMeasurements;
import Dashboard.Sensors.TempAmbient;
import Dashboard.Sensors.TempGround;
import EndPoints.EndPoint;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GetTemperatureService implements GetTemperature{
    @EJB
    private EndPoint endPoint;

//    @Override
//    public String getTempAmbient() throws IOException {
//
//        String url = endPoint.getTempAmbient();
//
//        try {
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//            // Configurações da requisição
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json"); // Define o tipo de conteúdo do corpo da requisição
//            con.setDoOutput(true);
//
//            // Envio do corpo da requisição vazio
//            OutputStream os = con.getOutputStream();
//            os.write("".getBytes());
//            os.flush();
//            os.close();
//
//            // Captura da resposta
//            int responseCode = con.getResponseCode();
//            System.out.println("Código de resposta: " + responseCode);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            String stringResponse = response.toString();
//            System.out.println("temperatura ambiente obtida do sensor: " + stringResponse);
//            Gson gson = new Gson();
//            TempAmbient temp = gson.fromJson(stringResponse, TempAmbient.class);
//
//            return String.format("%.1f",temp.getTemperature());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    @Override
    public String getTempGround() throws IOException {

        String url = endPoint.getTempGround();

        try {
            String stringResponse = getStringResponse(url);
            System.out.println("temperatura do solo obtida do sensor: " + stringResponse);
            Gson gson = new Gson();
            TempGround temp = gson.fromJson(stringResponse, TempGround.class);

            return String.format("%.1f",temp.getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public AirData getAirTemp() throws IOException {
        String url = endPoint.getAirTemp();

        try {
            String stringResponse = getStringResponse(url);

            Gson gson = new Gson();
            AirMeasurements temp = gson.fromJson(stringResponse, AirMeasurements.class);

            AirData data = new AirData();
            double temperature = temp.getTemperature();
            double humid = temp.getAirHumidity();
            data.setTemperature(String.format("%.1f", temperature));
            data.setHumidity(String.format("%.1f",humid));
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

        String stringResponse = response.toString();
        return stringResponse;
    }
}
