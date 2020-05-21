package it.lanzini.simulationapp.service.impl;

import com.lanzini.exception.KafkaPublisherException;
import com.lanzini.simulatorexecutor.SimulatorFlowBuilder;
import com.lanzini.template.configurationexecutor.ConfigurationExecutorTemplate;
import com.lanzini.template.connect.PublisherTemplateConnectionFactory;
import com.lanzini.template.publisher.KafkaPublisherTemplate;
import it.lanzini.simulationapp.service.KafkaSimulationService;
import org.springframework.stereotype.Service;

@Service
public class KafkaSimulationServiceImpl implements KafkaSimulationService {

    @Override
    public void simulateWithMessages(int threads, int messages, long delay, String brokers, String topic, String message) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                .message(() -> message)
                .connect(() -> PublisherTemplateConnectionFactory.kafka(brokers))
                .publish(message1 -> {
                    try {
                        KafkaPublisherTemplate.send(topic,message1);
                    } catch (KafkaPublisherException e) {
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
    public void simulateWithMinutes(int threads, int minutes, long delay, String brokers, String topic, String message) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                        .message(() -> message)
                        .connect(() -> PublisherTemplateConnectionFactory.kafka(brokers))
                        .publish(message1 -> {
                            try {
                                KafkaPublisherTemplate.send(topic,message1);
                            } catch (KafkaPublisherException e) {
                                e.printStackTrace();
                            }
                        })
                        .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithTimeRangeMinutes(threads,
                                minutes,delay,messageFactory,publisher))
                        .build()
                        .simulate();
        new Thread(runnable).start();
    }
}
