package sample;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {
    @Id
    private long id;
    private String weekDay;
    private String Date;

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
