package it.lanzini.simulationapp.service;

import it.lanzini.simulationapp.enums.HttpMethodEnum;

public interface HttpSimulationService {
    void simulateGetOrDeleteWithMessages(int threads, int messages, long delay, String url,
                                         HttpMethodEnum httpMethodEnum);
    void simulateGetOrDeleteWithMinutes(int threads, int minutes, long delay, String url,
                                        HttpMethodEnum httpMethodEnum);
    void simulatePostOrPutWithMessages(int threads, int messages, long delay, String url, HttpMethodEnum httpMethodEnum,
                                         String body);
    void simulatePostOrPutWithMinutes(int threads, int minutes, long delay, String url, HttpMethodEnum httpMethodEnum,
                                        String body);
}
