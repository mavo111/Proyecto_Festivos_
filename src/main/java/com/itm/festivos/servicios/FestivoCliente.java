package com.itm.festivos.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FestivoCliente {

    private final String API_NODE = "http://3.88.127.16:8080/api/festivos/verificar";

    public boolean esFestivo(int anio, int mes, int dia){

        RestTemplate restTemplate = new RestTemplate();

        String url = API_NODE + "/" + anio + "/" + mes + "/" + dia;

        Boolean respuesta = restTemplate.getForObject(url, Boolean.class);

        return respuesta != null && respuesta;
    }
}