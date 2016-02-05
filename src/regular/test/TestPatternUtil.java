package regular.test;

import regular.util.PatternUtil;

public class TestPatternUtil {

	public static void main(String[] args) {
		System.out.println(PatternUtil.haveUpperAndLowerCasePattern("gouchao@qq.com", "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"));
	}

}
