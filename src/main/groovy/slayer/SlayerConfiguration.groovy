package slayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar


/**
 * Created by sith on 1/31/16.
 */
@SpringBootApplication
class SlayerConfiguration {

    static Logger log = LoggerFactory.getLogger(SlayerConfiguration.class)

    static void main(String[] args) {
        SpringApplication.run(SlayerConfiguration.class, args)
        log.info("Chaos AD.")
    }

}

@EnableScheduling
@Configuration
class Scheduler implements SchedulingConfigurer{

    @Bean
    ThreadPoolTaskScheduler warMachinePool(){
        return new ThreadPoolTaskScheduler()
    }

    @Override
    void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(warMachinePool())
    }
}
