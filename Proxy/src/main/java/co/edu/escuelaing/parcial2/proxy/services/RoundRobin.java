package co.edu.escuelaing.parcial2.proxy.services;


public class RoundRobin {
    int totalPorts = 2;
    int actualPort = 0;

    public RoundRobin() {

    }

    public void rotateServer() {
        actualPort = (actualPort + 1) % totalPorts;
    }

    public int getActualPort() {
        rotateServer();

        return actualPort;
    }



}
