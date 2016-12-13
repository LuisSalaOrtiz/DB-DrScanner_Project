package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alber on 11/20/2016.
 */

public class Patients implements Serializable {
    private int pid;
    private String ssn, pfname, plname;
    private PersonalInfo info;
    private ArrayList<Visits> visits;
    private String qrcode;
    private static final long serialVersionUID = -7060210544600464481L;

    /**
     * @param qrcode
     * @param pid
     * @param pfname
     * @param plname
     * @param ssn
     * @param info
     * @param visits
     */
    public Patients(String qrcode, int pid, String pfname, String plname, String ssn, PersonalInfo info, ArrayList<Visits> visits)
    {
        this.pid = pid;
        this.pfname = pfname;
        this.plname = plname;
        this.ssn = ssn;
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
    public String getPfname() {
        return pfname;
    }

    /**
     * @param pfname
     */
    public void setPfname(String pfname) {
        this.pfname = pfname;
    }

    /**
     * @return
     */
    public String getPlname() {
        return plname;
    }

    /**
     * @param plname
     */
    public void setPlname(String plname) {
        this.plname = plname;
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

    /**
     * @param visit
     */
    public void addVisits(Visits visit) {
        this.visits.add(visit);
    }
}
