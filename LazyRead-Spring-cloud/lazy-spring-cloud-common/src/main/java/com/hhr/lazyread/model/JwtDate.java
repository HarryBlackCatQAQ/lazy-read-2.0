package com.hhr.lazyread.model;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 自定义设置的JwtDate
 *
 * @Author: Harry
 * @Date: 2019-07-17 00:14
 * @Version 1.0
 */
public class JwtDate {
    private int years;
    private int months;
    private int weeks;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    public JwtDate(){
        this.years = 0;
        this.months = 0;
        this.weeks = 0;
        this.days = 0;
        this.hours = 0;
        this.months = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    /**
     * 获取过期时间
     * @param now
     * @return
     */
    public Date getExp(Date now){
        Date exp = now;
        exp = DateUtils.addYears(exp,this.years);
        exp = DateUtils.addMonths(exp,this.months);
        exp = DateUtils.addWeeks(exp,this.weeks);
        exp = DateUtils.addDays(exp,this.days);
        exp = DateUtils.addHours(exp,this.hours);
        exp = DateUtils.addMinutes(exp,this.minutes);
        exp = DateUtils.addSeconds(exp,this.seconds);

        return exp;
    }


    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "JwtDate{" +
                "years=" + years +
                ", months=" + months +
                ", weeks=" + weeks +
                ", days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}