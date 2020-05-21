package it.lanzini.simulationapp.controller;

import it.lanzini.simulationapp.model.ResponseDto;
import it.lanzini.simulationapp.service.MqttSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mqtt")
public class MqttSimulationController {

    @Autowired
    MqttSimulationService mqttSimulationService;

    @PostMapping("publishWithMessages")
    public ResponseEntity<ResponseDto> publishWithMessages(@RequestParam int threads, @RequestParam int messages,
                                                           @RequestParam long delay, @RequestParam String host,
                                                           @RequestParam int port, @RequestParam String username,
                                                           @RequestParam String password, @RequestParam String topic,
                                                           @RequestBody String message){
        mqttSimulationService.simulateWithMessage(threads, messages, delay, host, port, username, password, topic,
                message);
        return ResponseEntity.ok(ResponseDto.ok());
    }

    @PostMapping("publishWithMinutes")
    public ResponseEntity<ResponseDto> publishWithMinutes(@RequestParam int threads, @RequestParam int minutes,
                                                           @RequestParam long delay, @RequestParam String host,
                                                           @RequestParam int port, @RequestParam String username,
                                                           @RequestParam String password, @RequestParam String topic,
                                                           @RequestBody String message){
        mqttSimulationService.simulateWithMinutes(threads, minutes, delay, host, port, username, password, topic,
                message);
        return ResponseEntity.ok(ResponseDto.ok());
    }
}
