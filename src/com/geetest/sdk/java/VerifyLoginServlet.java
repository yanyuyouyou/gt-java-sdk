package com.geetest.sdk.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 244554953219893949L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO： replace your own Key here after create a Captcha App in
		// 'my.geetest.com'
		String private_key = "24a612ae8c62203f724c81a5a9b4e761";
		GeetestLib geetest = new GeetestLib(private_key);

		String gtResult = "fail";

		if (geetest.resquestIsLegal(request)) {
			gtResult = geetest.enhencedValidateRequest(request);
			System.out.println(gtResult);
		} else {
			// TODO use you own system when geetest-server is down:failback
			gtResult = "fail";

		}

		if (gtResult.equals(GeetestLib.success_res)) {
			// TODO handle the Success result
			PrintWriter out = response.getWriter();
			out.println(GeetestLib.success_res + ":" + geetest.getVersionInfo());

		} else if (gtResult.equals(GeetestLib.forbidden_res)) {
			// TODO handle the Forbidden result
			PrintWriter out = response.getWriter();
			out.println(GeetestLib.forbidden_res + ":"
					+ geetest.getVersionInfo());
		} else {
			// TODO handle the Fail result
			PrintWriter out = response.getWriter();
			out.println(GeetestLib.fail_res + ":" + geetest.getVersionInfo());
		}

	}
}
