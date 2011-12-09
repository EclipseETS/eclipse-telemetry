package util;

import java.util.*;

/*
 * Inspired from "Simplify Command-Line Argument Parsing"
 * http://www.devx.com/tips/Tip/13004
 */

public class ArgumentParser {
	public ArgumentParser(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-") || args[i].startsWith("/")) {
				int loc = args[i].indexOf("=");
				String key = (loc > 0) ? args[i].substring(1, loc) : args[i]
						.substring(1);
				String value = (loc > 0) ? args[i].substring(loc + 1) : "";
				options.put(key.toLowerCase(), value);
			} else {
				params.addElement(args[i]);
			}
		}
	}

	public boolean hasOption(String opt) {
		return options.containsKey(opt.toLowerCase());
	}

	public String getOption(String opt) {
		return (String) options.get(opt.toLowerCase());
	}

	public String nextParam() {
		if (paramIndex < params.size()) {
			return (String) params.elementAt(paramIndex++);
		}
		return null;
	}

	private Vector<String> params = new Vector<String>();
	private Hashtable<String,String> options = new Hashtable<String,String>();
	private int paramIndex = 0;
}
