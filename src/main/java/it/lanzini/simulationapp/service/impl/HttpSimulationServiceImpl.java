package it.lanzini.simulationapp.service.impl;

import com.lanzini.exception.HttpPublisherException;
import com.lanzini.simulatorexecutor.SimulatorFlowBuilder;
import com.lanzini.template.configurationexecutor.ConfigurationExecutorTemplate;
import com.lanzini.template.publisher.HttpPublisherTemplate;
import it.lanzini.simulationapp.enums.HttpMethodEnum;
import it.lanzini.simulationapp.service.HttpSimulationService;
import org.springframework.stereotype.Service;

@Service
public class HttpSimulationServiceImpl implements HttpSimulationService {

    @Override
    public void simulateGetOrDeleteWithMessages(int threads, int messages, long delay, String url, HttpMethodEnum httpMethodEnum) {
        Runnable runnable = () ->
            SimulatorFlowBuilder.flow()
                    .message(() -> null)
                    .publish(message -> {
                        try {
                            if (httpMethodEnum.equals(HttpMethodEnum.GET)) HttpPublisherTemplate.get(url);
                            else HttpPublisherTemplate.delete(url);
                        } catch (HttpPublisherException e) {
                            e.printStackTrace();
                        }
                    })
                    .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithMessages(threads,messages,delay,messageFactory,publisher))
                    .build()
                    .simulate();
        new Thread(runnable).start();
    }

    @Override
    public void simulateGetOrDeleteWithMinutes(int threads, int minutes, long delay, String url, HttpMethodEnum httpMethodEnum) {
       Runnable runnable = () ->
            SimulatorFlowBuilder.flow()
                    .message(() -> null)
                    .publish(message -> {
                        try {
                            if (httpMethodEnum.equals(HttpMethodEnum.GET)) HttpPublisherTemplate.get(url);
                            else HttpPublisherTemplate.delete(url);
                        } catch (HttpPublisherException e) {
                            e.printStackTrace();
                        }
                    })
                    .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithTimeRangeMinutes(
                            threads,minutes,delay,messageFactory,publisher))
                    .build()
                    .simulate();
        new Thread(runnable).start();
    }

    @Override
    public void simulatePostOrPutWithMessages(int threads, int messages, long delay, String url, HttpMethodEnum httpMethodEnum, String body) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                .message(() -> null)
                .publish(message -> {
                    try {
                        if (httpMethodEnum.equals(HttpMethodEnum.POST)) HttpPublisherTemplate.post(url,body);
                        else HttpPublisherTemplate.put(url,body);
                    } catch (HttpPublisherException e) {
                        e.printStackTrace();
                    }
                })
                .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithMessages(threads,messages,delay,messageFactory,publisher))
                .build()
                .simulate();
        new Thread(runnable).start();
    }

    @Override
    public void simulatePostOrPutWithMinutes(int threads, int minutes, long delay, String url, HttpMethodEnum httpMethodEnum, String body) {
        Runnable runnable = () ->
                SimulatorFlowBuilder.flow()
                .message(() -> null)
                .publish(message -> {
                    try {
                        if (httpMethodEnum.equals(HttpMethodEnum.POST)) HttpPublisherTemplate.post(url,body);
                        else HttpPublisherTemplate.put(url,body);
                    } catch (HttpPublisherException e) {
                        e.printStackTrace();
                    }
                })
                .configure((messageFactory, publisher) -> ConfigurationExecutorTemplate.executeWithTimeRangeMinutes(
                        threads,minutes,delay,messageFactory,publisher))
                .build()
                .simulate();
        new Thread(runnable).start();
    }
}
