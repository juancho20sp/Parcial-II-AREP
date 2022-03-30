package co.edu.escuelaing.parcial2.proxy.services;


public class RoundRobin {
    int[] ports;
    int actualPort = 0;

    public RoundRobin() {
        ports = new int[]{getTargetPortOne(), getTargetPortTwo()};
    }

    public void rotateServer() {
        actualPort = (actualPort + 1) % ports.length;
    }

    public int[] getPorts() {
        return ports;
    }

    public int getTargetPort() {
        int port = ports[actualPort];

        rotateServer();

        return port;
    }

    static int getTargetPortOne() {
        if (System.getenv("SERVER_PORT_ONE") != null) {
            return Integer.parseInt(System.getenv("SERVER_PORT_ONE"));
        }
        return 8087; //returns default port if heroku-port isn't set
    }

    static int getTargetPortTwo() {
        if (System.getenv("SERVER_PORT_TWO") != null) {
            return Integer.parseInt(System.getenv("SERVER_PORT_TWO"));
        }
        return 8088; //returns default port if heroku-port isn't set
    }

}
