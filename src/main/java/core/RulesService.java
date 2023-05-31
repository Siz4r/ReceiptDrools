package core;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class RulesService {
    static {
        try {
            readKnowledgeBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static KieSession ksession;
    private static KieBase kieBase;

    private RulesService() {
    }


    public static KieSession getKsession() {
        return ksession;
    }

    private static void readKnowledgeBase() throws IOException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        File resourcesDir = new File("src/main/resources");
        if (!resourcesDir.exists() || !resourcesDir.isDirectory()) {
            throw new RuntimeException("Couldn't find dir with rules!");
        }

        for (File rule :
                Objects.requireNonNull(resourcesDir.listFiles())) {
            kfs.write( "src/main/resources/" + rule.getName(),
                    kieServices.getResources().newInputStreamResource(Files.newInputStream(rule.toPath())));
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
        Results results = kieBuilder.getResults();

        if( results.hasMessages(  Message.Level.ERROR ) ){
            System.out.println( results.getMessages() );
            throw new IllegalStateException( "### errors ###" );
        }

        initializeEngine(kieServices);

    }

    public static void initializeEngine(KieServices kieServices) {
        KieContainer kieContainer =
                kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
        kieBase = kieContainer.getKieBase();
        ksession = kieContainer.newKieSession();
    }
}