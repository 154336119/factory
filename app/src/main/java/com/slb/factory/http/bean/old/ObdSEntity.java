package com.slb.factory.http.bean.old;

import java.util.List;

public class ObdSEntity {
    public List<ObdEntity> getObds() {
        return obds;
    }

    public void setObds(List<ObdEntity> obds) {
        this.obds = obds;
    }

    private List<ObdEntity> obds;
}
