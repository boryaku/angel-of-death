package slayer.domain

import org.springframework.data.annotation.Id

/**
 * Created by sith on 2/11/16.
 */
class Configuration {

    @Id
    private String id

    private String cronSchedule

    private Integer maxRunsPerDay

    Configuration(String cronSchedule, Integer maxRunsPerDay) {
        this.cronSchedule = cronSchedule
        this.maxRunsPerDay = maxRunsPerDay
    }

    String getId() {
        return id
    }

    String getCronSchedule() {
        return cronSchedule
    }

    Integer getMaxRunsPerDay() {
        return maxRunsPerDay
    }
}
