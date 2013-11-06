package com.epam.lab.controller.web.jsptags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SizeFormatterTag extends SimpleTagSupport {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SizeFormatterTag() {
	}

	@Override
	public void doTag() throws JspException, IOException {
		try {
			Long bytes = null;
			try {
				Double tmp = Double.parseDouble(value);
				bytes = tmp.longValue();
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}

			String[] units = { "Bytes", "KB", "MB", "GB", "TB" };

			String result = null;
			if (bytes == null) {
				result = "n/a";
			} else {
				double resSize = 0;
				int unitN = 0;
				if (bytes != 0) {
					double floor = Math.floor(Math.log(bytes));
					double log = Math.log(1024);
					unitN = (int) (floor / log);
					resSize = bytes / Math.pow(1024, unitN);
				}
				result = String.format("%.2f %s", resSize, units[unitN]);
			}
			getJspContext().getOut().write(result);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SkipPageException("Exception in formatting " + value);
		}
	}
}
