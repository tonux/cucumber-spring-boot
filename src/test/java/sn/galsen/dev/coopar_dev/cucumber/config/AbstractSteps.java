package sn.galsen.dev.coopar_dev.cucumber.config;

import org.springframework.boot.test.web.server.LocalServerPort;

public abstract class AbstractSteps {

    @LocalServerPort
    private int port;

    public String baseUrl() {
        return "http://localhost:" + port;
    }
}
