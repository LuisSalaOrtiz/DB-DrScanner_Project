package com.example.gilbertojimenezorench.myapp;

import java.util.ArrayList;

/**
 * Created by alber on 11/20/2016.
 */

public class Patients {
    private int pid;
    private String ssn, pname;
    private Diagnostics conditions;
    private PersonalInfo info;
    private ArrayList<Visits> visits;
    private String qrcode;

    /**
     * @param qrcode
     * @param pid
     * @param pname
     * @param ssn
     * @param conditions
     * @param info
     * @param visits
     */
    public Patients(String qrcode, int pid, String pname, String ssn, Diagnostics conditions, PersonalInfo info, ArrayList<Visits> visits)
    {
        this.pid = pid;
        this.pname = pname;
        this.ssn = ssn;
        this.conditions = conditions;
        this.visits = visits;
        this.qrcode = qrcode;
        this.info = info;

    }

    /**
     * @return
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * @param qrcode
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return
     */
    public Diagnostics getConditions() {
        return conditions;
    }

    /**
     * @param conditions
     */
    public void setConditions( Diagnostics conditions) {
        this.conditions = conditions;
    }

    /**
     * @return
     */
    public PersonalInfo getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(PersonalInfo info) {
        this.info = info;
    }

    /**
     * @return
     */
    public ArrayList<Visits> getVisits() {
        return visits;
    }

    /**
     * @param visits
     */
    public void setVisits(ArrayList<Visits> visits) {
        this.visits = visits;
    }
}
