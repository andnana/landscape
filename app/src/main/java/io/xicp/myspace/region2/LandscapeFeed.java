package io.xicp.myspace.region2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LandscapeFeed {
    private Integer itemcount = 0;
    private List<Landscape> landscapeList = new ArrayList<Landscape>();

    public Integer addItem(Landscape landscape){
        landscapeList.add(landscape);
        itemcount ++;
        return itemcount;

    }
    public Landscape getLandscapeItem(int index){
        return landscapeList.get(index);

    }

    public List<Landscape> getAlllandscapeList(){
        return landscapeList;
    }

    public Integer getItemcount() {
        return itemcount;
    }

    public void setItemcount(Integer itemcount) {
        this.itemcount = itemcount;
    }

    public List<Landscape> getLandscapeList() {
        return landscapeList;
    }

    public void setRegionList(List<Landscape> landscapeList) {
        this.landscapeList = landscapeList;
    }
}
