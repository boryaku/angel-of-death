package slayer.domain

import org.springframework.data.annotation.Id

/**
 * Created by sith on 2/11/16.
 */
class War {

    @Id
    private String id

    private Date runTime

    private String test

    private String result

    War(Date runTime, String test, String result) {
        this.runTime = runTime
        this.test = test
        this.result = result
    }

    String getId() {
        return id
    }

    Date getRunTime() {
        return runTime
    }

    String getTest() {
        return test
    }

    String getResult() {
        return result
    }


    @Override
    public String toString() {
        return "War{" +
                "id='" + id + '\'' +
                ", runTime=" + runTime +
                ", test='" + test + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
