package it.lanzini.simulationapp.controller;

import it.lanzini.simulationapp.enums.HttpMethodEnum;
import it.lanzini.simulationapp.model.ResponseDto;
import it.lanzini.simulationapp.service.HttpSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("http")
public class HttpSimulationController {

    @Autowired
    HttpSimulationService httpSimulationService;

    @GetMapping("getOrDeleteWithMessages")
    public ResponseEntity<ResponseDto> getOrDeleteWithMessages(@RequestParam int threads, @RequestParam int messages,
                                                               @RequestParam long delay, @RequestParam String url,
                                                               @RequestParam HttpMethodEnum httpMethodEnum) {
        httpSimulationService.simulateGetOrDeleteWithMessages(threads, messages, delay, url, httpMethodEnum);
        return ResponseEntity.ok(ResponseDto.ok());
    }

    @GetMapping("getOrDeleteWithMinutes")
    public ResponseEntity<ResponseDto> getOrDeleteWithMinutes(@RequestParam int threads, @RequestParam int minutes,
                                                              @RequestParam long delay, @RequestParam String url,
                                                              @RequestParam HttpMethodEnum httpMethodEnum) {
        httpSimulationService.simulateGetOrDeleteWithMinutes(threads, minutes, delay, url, httpMethodEnum);
        return ResponseEntity.ok(ResponseDto.ok());
    }

    @PostMapping("postOrPutWithMessages")
    public ResponseEntity<ResponseDto> postOrPutWithMessages(@RequestParam int threads, @RequestParam int messages,
                                                             @RequestParam long delay, @RequestParam String url,
                                                             @RequestParam HttpMethodEnum httpMethodEnum,
                                                             @RequestBody String body) {
        httpSimulationService.simulatePostOrPutWithMessages(threads, messages, delay, url, httpMethodEnum, body);
        return ResponseEntity.ok(ResponseDto.ok());
    }

    @PostMapping("postOrPutWithMinutes")
    public ResponseEntity<ResponseDto> postOrPutWithMinutes(@RequestParam int threads, @RequestParam int minutes,
                                                            @RequestParam long delay, @RequestParam String url,
                                                            @RequestParam HttpMethodEnum httpMethodEnum,
                                                            @RequestBody String body) {
        httpSimulationService.simulatePostOrPutWithMinutes(threads, minutes, delay, url, httpMethodEnum, body);
        return ResponseEntity.ok(ResponseDto.ok());
    }

}
