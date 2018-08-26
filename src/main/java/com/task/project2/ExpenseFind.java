package com.task.project2;

import java.sql.Date;

public class ExpenseFind {
    private Date dateFrom;
    private Date dateTo;
    private double amountAbove;

    public ExpenseFind() {
    }

    public ExpenseFind(Date dateFrom, Date dateTo, double amountAbove) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.amountAbove = amountAbove;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getAmountAbove() {
        return amountAbove;
    }

    public void setAmountAbove(double amountAbove) {
        this.amountAbove = amountAbove;
    }
}
