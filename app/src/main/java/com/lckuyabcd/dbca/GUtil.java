package com.lckuyabcd.dbca;

import java.util.ArrayList;
import java.util.List;


public class GUtil {

    public static List<ItemBean> mItemBeans = new ArrayList<ItemBean>();

    public static ItemBean mBlankItemBean = new ItemBean();


    public static boolean isMoveable(int position) {
        int type = PicMain.TYPE;

        int blankId = GUtil.mBlankItemBean.getItemId() - 1;

        if (Math.abs(blankId - position) == type) {
            return true;
        }

        if ((blankId / type == position / type) &&
                Math.abs(blankId - position) == 1) {
            return true;
        }
        return false;
    }


    public static void swapItems(ItemBean from, ItemBean blank) {
        ItemBean tempItemBean = new ItemBean();

        tempItemBean.setBitmapId(from.getBitmapId());
        from.setBitmapId(blank.getBitmapId());
        blank.setBitmapId(tempItemBean.getBitmapId());

        tempItemBean.setBitmap(from.getBitmap());
        from.setBitmap(blank.getBitmap());
        blank.setBitmap(tempItemBean.getBitmap());

        GUtil.mBlankItemBean = from;
    }


    public static void getpicGenerator() {
        int index = 0;

        for (int i = 0; i < mItemBeans.size(); i++) {
            index = (int) (Math.random() *
                    PicMain.TYPE * PicMain.TYPE);
            swapItems(mItemBeans.get(index), GUtil.mBlankItemBean);
        }
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < mItemBeans.size(); i++) {
            data.add(mItemBeans.get(i).getBitmapId());
        }

        if (canSolve(data)) {
            return;
        } else {
            getpicGenerator();
        }
    }


    public static boolean isSuccess() {
        for (ItemBean tempBean : GUtil.mItemBeans) {
            if (tempBean.getBitmapId() != 0 &&
                    (tempBean.getItemId()) == tempBean.getBitmapId()) {
                continue;
            } else if (tempBean.getBitmapId() == 0 &&
                    tempBean.getItemId() == PicMain.TYPE * PicMain.TYPE) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public static boolean canSolve(List<Integer> data) {

        int blankId = GUtil.mBlankItemBean.getItemId();

        if (data.size() % 2 == 1) {
            return getInversions(data) % 2 == 0;
        } else {

            if (((blankId - 1) / PicMain.TYPE) % 2 == 1) {
                return getInversions(data) % 2 == 0;
            } else {

                return getInversions(data) % 2 == 1;
            }
        }
    }


    public static int getInversions(List<Integer> data) {
        int inversions = 0;
        int inversionCount = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int index = data.get(i);
                if (data.get(j) != 0 && data.get(j) < index) {
                    inversionCount++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }
}
