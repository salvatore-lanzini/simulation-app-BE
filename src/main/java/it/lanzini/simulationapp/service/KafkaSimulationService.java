package it.lanzini.simulationapp.service;

public interface KafkaSimulationService {
    void simulateWithMessages(int threads, int messages, long delay, String brokers, String topic, String message);
    void simulateWithMinutes(int threads, int minutes, long delay, String brokers, String topic, String message);
}
