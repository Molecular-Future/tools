package org.mos.tools.queue;

import java.nio.ByteBuffer;

import com.google.protobuf.ByteString;

public interface IStorable {

	void toBytes(ByteBuffer buff);

	void fromBytes(ByteBuffer buff);
	
	ByteString getStorableKey();
	
	long calcSize();

}