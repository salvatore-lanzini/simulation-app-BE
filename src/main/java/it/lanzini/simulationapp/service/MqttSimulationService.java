package it.lanzini.simulationapp.service;

public interface MqttSimulationService {
    void simulateWithMessage(int threads, int messages, long delay, String host, int port, String username,
                             String password, String topic, String message);
    void simulateWithMinutes(int threads, int minutes, long delay, String host, int port, String username,
                             String password, String topic, String message);
}
