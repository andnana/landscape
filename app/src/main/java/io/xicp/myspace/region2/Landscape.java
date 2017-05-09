package io.xicp.myspace.region2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class Landscape {
    private String enName;
    private String describe;
    private String name;
    private String region_name;

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    private List<LandscapeImage> landscapeImagesList = new ArrayList<LandscapeImage>();

    public List<LandscapeImage> getLandscapeImagesList() {
        return landscapeImagesList;
    }
    public void addImage(LandscapeImage landscapeImage){
        landscapeImagesList.add(landscapeImage);
    }
    public void setLandscapeImagesList(List<LandscapeImage> landscapeImagesList) {
        this.landscapeImagesList = landscapeImagesList;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
