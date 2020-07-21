package org.mos.tools.rlp;

import java.io.Serializable;

public interface RLPElement extends Serializable {

    byte[] getRLPData();
}
