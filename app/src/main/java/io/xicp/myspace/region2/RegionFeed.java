package io.xicp.myspace.region2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class RegionFeed {
//    private static RegionFeed regionFeed;
    private Integer itemcount = 0;
    private List<Region> regionList = new ArrayList<Region>();
//    private RegionFeed(){
//    }
//    public static RegionFeed get(){
//        if(regionFeed == null){
//            regionFeed = new RegionFeed();
//        }
//        return regionFeed;
//    }
    public Integer addItem(Region region){
        regionList.add(region);
        itemcount ++;
        return itemcount;

    }
    public Region getRegionItem(int index){
        return regionList.get(index);

    }

    public List<Region> getAllRegionList(){
        return regionList;
    }

    public Integer getItemcount() {
        return itemcount;
    }

    public void setItemcount(Integer itemcount) {
        this.itemcount = itemcount;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }
}
