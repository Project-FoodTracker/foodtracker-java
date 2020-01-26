package at.ac.fernfh.foodtracker.model;

import java.util.Date;

public class OpeningHours {

    private Date from;
    private Date to;

    public OpeningHours(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "OpeningHours{" + "from=" + from.getTime() + ", to=" + to.getTime() + '}';
    }
}
