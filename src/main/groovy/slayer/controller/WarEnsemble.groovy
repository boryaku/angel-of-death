package slayer.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import slayer.domain.Configuration
import slayer.domain.War
import slayer.repos.ConfigurationRepository
import slayer.service.WarMachine


/**
 * Created by sith on 1/31/16.
 *
 * RESTful API which can be called to start the WarMachine and get details about the War.
 */
@RestController
class WarEnsemble {

    Logger log = LoggerFactory.getLogger(WarEnsemble.class)

    private TaskScheduler taskScheduler

    private ConfigurationRepository configurationRepository

    private WarMachine warMachine

    @Autowired
    WarEnsemble(
            @Qualifier("warMachinePool")TaskScheduler taskScheduler,
            ConfigurationRepository configurationRepository,
            WarMachine warMachine) {
        this.taskScheduler = taskScheduler
        this.configurationRepository = configurationRepository
        this.warMachine = warMachine

        this.begin()
    }

    void begin(){
        log.info("the war begins")

        Runnable warMachineTask = new Runnable() {
            public void run() {

                3.times{
                    UUID correlationId = UUID.randomUUID()
                    War warResult = warMachine.seekAndDestroy(correlationId.toString())
                    if(warResult.result == "1"){
                        log.info("we failed to click")
                        //implement a three in a row and we're done for the day
                    }
                }
            }
        }

        long configCount = configurationRepository.count()

        Configuration configuration

        if(configCount == 0){
            log.info("no configuration found, creating one")
            configuration = new Configuration("* */5 * * * MON-FRI", 20)
            configuration = configurationRepository.save(configuration)
        } else {
            configuration = configurationRepository.findAll().get(0)
            log.info("configuration found, using it")
        }

        //create a runner to change the trigger frequency.

        CronTrigger trigger = new CronTrigger(configuration.getCronSchedule())

        taskScheduler.schedule(warMachineTask, trigger)
        log.info("no configuration found, creating one")
    }


    @RequestMapping(value = "/", produces="application/json")
    String index(){
        UUID requestId = UUID.randomUUID()

        log.info("processing_started  :: request=${requestId.toString()}")

        warMachine.seekAndDestroy(requestId.toString())

        return "{requestId: ${requestId}}"
    }


    @RequestMapping(value = "/details", produces="application/json")
    List<War> details(){

        return warMachine.findAll()
    }
}
