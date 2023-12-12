package lesson02.controls;

import java.util.HashMap;

public interface Controller {
	String excute(HashMap<String, Object> model) throws Exception;
}
