package slayer.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import slayer.domain.War
import slayer.repos.WarRepository


/**
 * Created by sith on 1/31/16.
 */
@Service
class WarMachine {

    Logger log = LoggerFactory.getLogger(WarMachine.class)

    @Autowired
    private WarRepository warRepository

    @Value('${commandPath}')
    String commandPath

    Random random = new Random()

    War seekAndDestroy(String uuid) {
        log.info("calling host to run browsers :: request=${uuid}")

        //Produce a random integer between 100 and 200 inclusive
        int randomInt = random.nextInt(200-100+1)+100

        String test = randomInt % 2 == 0 ? "chromeTest" : "firefoxTest"

        def process = new ProcessBuilder(addShellPrefix("${commandPath}gradlew ${test}"))
                .directory(new File(commandPath))
                .redirectErrorStream(true)
                .start()
        process.waitFor();

        War warResult = new War(new Date(), test, String.valueOf(process.exitValue()))
        warRepository.save(warResult)

        log.info("war_result=${warResult.toString()} :: request=${uuid}")

        return warResult
    }

    List<War> findAll(){
        return warRepository.findAll()
    }

    private def addShellPrefix(String command) {
        def commandArray = new String[3]
        commandArray[0] = "sh"
        commandArray[1] = "-c"
        commandArray[2] = command
        return commandArray
    }
}
