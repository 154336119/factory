package com.slb.factory.command.mymil;

import com.github.pires.obd.commands.control.DistanceSinceCCCommand;
import com.slb.factory.Base;
import com.slb.factory.R;

/**
 * Created by juan on 2018/12/3.
 */

public class MyDistanceSinceCCCommand extends DistanceSinceCCCommand {
    @Override
    public String getName() {
        return Base.getContext().getString(R.string.Distance_Since_Trouble_Codes_Cleared);
    }
    public String getResultUnit() {
        return this.useImperialUnits?"miles":"km";
    }
}
