package Job;

import CommunicationMQTT.TopicRemoteService;
import Dashboard.Sensors.WaterFlow;
import Dashboard.Service.GetUmidityRemoteService;
import Dashboard.Service.GetWaterFlowRemoteService;
import Dashboard.Service.GetWaterFlowService;
import Strategy.Interfaces.StrategyRemoteService;
import Strategy.Model.Strategy;
import Util.InterfaceMessage;
import Util.InterfaceMessageRemote;
import Util.MessageToDevice;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Random;


@DisallowConcurrentExecution
public class JobQuartz implements Job {

    private MqttClient client;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object topicObject = null;
        Object stratObject = null;
        Object umidObject = null;
        Object wF = null;
        Object messagesGrowl = null;
        try {

            Context ctx = new InitialContext();
            topicObject = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/TopicServiceBean!CommunicationMQTT.TopicRemoteService");
            stratObject = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/StrategyServiceBean!Strategy.Interfaces.StrategyRemoteService");
            umidObject = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/GetUmidityService!Dashboard.Service.GetUmidityRemoteService");
            wF = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/GetWaterFlowServiceBean!Dashboard.Service.GetWaterFlowRemoteService");
            messagesGrowl = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/EJBMessages!Util.InterfaceMessageRemote");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        TopicRemoteService topic = (TopicRemoteService) topicObject;
        StrategyRemoteService strat = (StrategyRemoteService) stratObject;
        GetUmidityRemoteService umid = (GetUmidityRemoteService) umidObject;
        GetWaterFlowRemoteService waterFlow = (GetWaterFlowRemoteService) wF;
        InterfaceMessageRemote msg = (InterfaceMessageRemote) messagesGrowl;
//        MessageToDevice messageD = new MessageToDevice();
        double value = randomValue();

        System.out.println("Executando...");
        // Substitua com o endereço do seu broker MQTT
        String broker = "tcp://ec2-54-92-135-4.compute-1.amazonaws.com:1883";
        MQTTManager.initialize(broker);
        // Substitua pelo tópico desejado
        String topic1 = topic.getTopicStartStop();
        String mobileTopic = topic.getTopicMessageMobile();
        // Mensagem que você deseja publicar
//        String message = "1";
        String message = "";
        //ligar o led é 0, desligar é 1;
        int codigo = 0;
        String messageToDevice = "";

        Strategy strategyObj = strat.getStrat().get(0);
        if (strategyObj.getFarm().getMoisture() > value) {

            System.out.println("vazao de agua: " + waterFlow.getWaterFlow() + "L/min");
            message = "0";
            codigo = 1;
            messageToDevice = "Pivô Ligado";
            boolean checkWaterFlow = checkWaterFlow(waterFlow.getWaterFlow());
            if (checkWaterFlow) {
                message += ";1;";
                messageToDevice += " com Vazão Normal";
            } else {
                message += ";17;";
                codigo = 17;
                messageToDevice += " com Vazão Baixa";
            }

        } else {
            message = "1;0;";
            messageToDevice = "Pivô Desligado";
        }
        System.out.println("RandomValue" + value + "\nMessage: " + message);
        System.out.println("Message Device: " + messageToDevice);

        MQTTManager.publish(topic1, message);
        MQTTManager.publish(mobileTopic, messageToDevice);
    }

    private boolean checkWaterFlow(double waterFlowMeansurement) {
        return waterFlowMeansurement > 500.00 && waterFlowMeansurement < 1000.00;
    }

    private double randomValue() {
        Random rand = new Random();
        int min = 30;
        int max = 35;
        int val = rand.nextInt(max - min + 1) + min;
        return Double.parseDouble(String.valueOf(val));
    }

    private static MqttMessage getMqttMessage(String message) {
        return new MqttMessage(message.getBytes());
    }



}
