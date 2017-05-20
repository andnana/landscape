package io.xicp.myspace.region2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class Region {
    private String regionName;
    private String enName;
    private int pic;
    private String describe;
    private String provincialCapital;
    private String belongTo;
    private String abbreviation;
    private List<Landscape> landscapeList = new ArrayList<Landscape>();

    public Region(){

    }

    public List<Landscape> getLandscapeList() {
        return landscapeList;
    }

    public void setLandscapeList(List<Landscape> landscapeList) {
        this.landscapeList = landscapeList;
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public Region(String regionName, int pic, String describe, String provincialCapital) {
        this.regionName = regionName;
        this.pic = pic;
        this.describe = describe;
        this.provincialCapital = provincialCapital;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getProvincialCapital() {
        return provincialCapital;
    }

    public void setProvincialCapital(String provincialCapital) {
        this.provincialCapital = provincialCapital;
    }

    @Override
    public String toString() {
        return  regionName;
    }
}
