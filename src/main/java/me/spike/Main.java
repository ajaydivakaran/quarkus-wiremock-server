package me.spike;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@QuarkusMain
public class Main implements QuarkusApplication {

    @ConfigProperty(name = "wiremock.port")
    int wiremockPort;

    @Override
    public int run(String... args) {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig()
                .port(wiremockPort)
                .mappingSource(new JsonFileMappingsSource(new ClasspathFileSource("mappings")))
                .disableRequestJournal()
        );
        wireMockServer.start();
        return 0;
    }
}
