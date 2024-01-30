package Util;

import Dashboard.Sensors.MoistureGround;
import EndPoints.EndPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Stateless(mappedName = "EJBMessages")
@EJB(name = "Util.InterfaceMessageRemote", beanInterface = InterfaceMessageRemote.class)
public class EJBMessages implements InterfaceMessage, InterfaceMessageRemote, Serializable {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();;
    @EJB
    private EndPoint endPoint;
    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @Override
    public void sendMessageToMobile(MessageToDevice message) {

        try {
            String endPointDevice = endPoint.getPostMessageDevice();
            List<String> ipDevices = getIpDevices();

            for (String ipDevice : ipDevices) {
                String url = "http://";
                url += ipDevice + endPointDevice;
                StringBuffer response = sedMessageToDevice(message, url);
                System.out.println("response: " + response.toString());
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private StringBuffer sedMessageToDevice(MessageToDevice message, String url) throws IOException {

        HttpURLConnection connection = openConnection(url);
        String json = gson.toJson(message);

        // Envio do corpo da requisição
        writeBodyRequest(connection, json);

        // Captura da resposta
        getResponseCode(connection);

        return writeResponseMessage(connection);
    }

    private StringBuffer writeResponseMessage(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    private void getResponseCode(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
//        System.out.println("Código de resposta: " + responseCode);
    }

    private void writeBodyRequest(HttpURLConnection connection, String json) throws IOException {
        OutputStream os = connection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
    }

    private static HttpURLConnection openConnection(String url) throws IOException {
        URL link = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        // Configurações da requisição
        connection.setRequestMethod("POST");
        // Define o tipo de conteúdo do corpo da requisição
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    public List<String> getIpDevices(){
        String hql = "SELECT dev.ip " +
                "FROM DeviceMobile dev ";
        return manager.createQuery(hql, String.class)
                .getResultList();
    }



}
