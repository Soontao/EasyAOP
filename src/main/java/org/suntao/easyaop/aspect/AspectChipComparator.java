package org.suntao.easyaop.aspect;

import java.util.Comparator;

public class AspectChipComparator implements Comparator<AspectChip> {

	@Override
	public int compare(AspectChip o1, AspectChip o2) {
		int result = 0;
		AspectChip chip1 = o1;
		AspectChip chip2 = o2;
		if (chip1.order < chip2.order)
			result = -1;
		else if (chip1.order == chip2.order)
			result = 1;
		else
			result = 0;
		return result;
	}

}
