package it.lanzini.simulationapp.service.impl;

import com.lanzini.exception.MqttPublisherException;
import com.lanzini.simulatorexecutor.SimulatorFlowBuilder;
import com.lanzini.template.configurationexecutor.ConfigurationExecutorTemplate;
import com.lanzini.template.connect.PublisherTemplateConnectionFactory;
import com.lanzini.template.publisher.MqttPublisherTemplate;
import it.lanzini.simulationapp.service.MqttSimulationService;
import org.springframework.stereotype.Service;

@Service
public class MqttSimulationServiceImpl implements MqttSimulationService {

    @Override
    public void simulateWithMessage(int threads, int messages, long delay, String host, int port, String username, String password, String topic, String message) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                .message(() -> message)
                .connect(() -> PublisherTemplateConnectionFactory.mqtt(host,port,username,password))
                .publish(message1 -> {
                    try {
                        MqttPublisherTemplate.send(message1,topic,0);
                    } catch (MqttPublisherException e) {
                        e.printStackTrace();
                    }
                })
                .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithMessages(threads,
                        messages,delay,messageFactory,publisher))
                .build()
                .simulate();
        new Thread(runnable).start();
    }

    @Override
    public void simulateWithMinutes(int threads, int minutes, long delay, String host, int port, String username, String password, String topic, String message) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                        .message(() -> message)
                        .connect(() -> PublisherTemplateConnectionFactory.mqtt(host,port,username,password))
                        .publish(message1 -> {
                            try {
                                MqttPublisherTemplate.send(message1,topic,0);
                            } catch (MqttPublisherException e) {
                                e.printStackTrace();
                            }
                        })
                        .configure((messageFactory, publisher) ->
                                ConfigurationExecutorTemplate.executeWithTimeRangeMinutes(threads,minutes,delay,
                                        messageFactory,publisher))
                        .build()
                        .simulate();
        new Thread(runnable).start();
    }
}
