package org.mos.tools.rlp;

import java.util.ArrayList;

import org.mos.tools.bytes.BytesHelper;

public class RLPList extends ArrayList<RLPElement> implements RLPElement {

    byte[] rlpData;

    public void setRLPData(byte[] rlpData) {
        this.rlpData = rlpData;
    }

    public byte[] getRLPData() {
        return rlpData;
    }

    public static void recursivePrint(RLPElement element) {

        if (element == null)
            throw new RuntimeException("RLPElement object can't be null");
        if (element instanceof RLPList) {

            RLPList rlpList = (RLPList) element;
            for (RLPElement singleElement : rlpList)
                recursivePrint(singleElement);
        } else {
            String hex = BytesHelper.toHexString(element.getRLPData());
        }
    }
}
