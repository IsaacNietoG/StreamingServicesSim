package servicios;

import servicios.planes.Plan;

import java.util.List;

public interface Servicio {

    void enviarRecomendacion();
    void cobrarClientes();
    void cobrarCliente(Cliente c);
    List<List> darPlanes();


    public static void main(String[] args) {

    }



}
