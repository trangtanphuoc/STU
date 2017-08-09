package com.tanphuoc.luanvan.huongdanduongdi.Moudle;

import java.util.List;

/**
 * Created by Phuoc Gia on 13/04/2017.
 */

public interface TimDuong {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
