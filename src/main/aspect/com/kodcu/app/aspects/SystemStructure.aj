package com.kodcu.app.aspects;

import com.kodcu.app.devices.Device;
import com.kodcu.app.devices.Mobile;
import com.kodcu.app.phones.*;
/**
 * 
 * @author HAKAN
 *
 */
public aspect SystemStructure {

	declare parents : com.kodcu.app.devices..* extends Device;

	declare parents : Phone extends Mobile;
	declare parents : SmartPhone extends Phone;

}
