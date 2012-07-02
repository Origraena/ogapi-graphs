package ori.ogapi.util;

/**
 * Implementation of OperatorPlus for <code>long</code> type.
 */
public class LongOperatorPlus implements OperatorPlus1T<Long> {

	public Long exec(Long b, Long c) {
		return exec(b.longValue(),c.longValue());
	}

	public Long exec(long b, long c) {
		return new Long(b+c);
	}

};

