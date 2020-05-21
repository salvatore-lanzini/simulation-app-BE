package it.lanzini.simulationapp.controller;

import it.lanzini.simulationapp.model.ResponseDto;
import it.lanzini.simulationapp.service.KafkaSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaSimulationController {

    @Autowired
    KafkaSimulationService kafkaSimulationService;

    @PostMapping("produceWithMessages")
    public ResponseEntity<ResponseDto> produceWithMessages(@RequestParam int threads, @RequestParam int messages,
                                                           @RequestParam long delay, @RequestParam String brokers,
                                                           @RequestParam String topic, @RequestBody String message){
        kafkaSimulationService.simulateWithMessages(threads, messages, delay, brokers, topic, message);
        return ResponseEntity.ok(ResponseDto.ok());
    }

    @PostMapping("produceWithMinutes")
    public ResponseEntity<ResponseDto> produceWithMinutes(@RequestParam int threads, @RequestParam int minutes,
                                                          @RequestParam long delay, @RequestParam String brokers,
                                                          @RequestParam String topic, @RequestBody String message){
        kafkaSimulationService.simulateWithMinutes(threads, minutes, delay, brokers, topic, message);
        return ResponseEntity.ok(ResponseDto.ok());
    }
}
