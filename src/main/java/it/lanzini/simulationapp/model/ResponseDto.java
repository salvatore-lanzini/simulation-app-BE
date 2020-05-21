package it.lanzini.simulationapp.model;

public class ResponseDto {
    private String simulazione;

    public String getSimulazione() {
        return simulazione;
    }

    public void setSimulazione(String simulazione) {
        this.simulazione = simulazione;
    }

    public static ResponseDto ok(){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSimulazione("avviata");
        return responseDto;
    }
}
