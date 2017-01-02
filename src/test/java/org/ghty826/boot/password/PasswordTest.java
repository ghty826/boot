package org.ghty826.boot.password;

import java.util.Arrays;
import java.util.Scanner;

import org.ghty826.boot.util.Point;

public class PasswordTest {

	public static void main(String[] args) {
		Password pw = new Password("0123456789");
		Scanner sc = new Scanner(System.in);
		System.out.println("===========First===============================");
		pw.show(pw.generateFirst());
		System.out.println("Please input your password:");
		System.out.println();
		String s0 = sc.next();
		System.out.println("===========Second==============================");
		pw.show(pw.generateSecond());
		System.out.println("Please input your confirm password:");
		System.out.println();
		String s1 = sc.next();
		Point[] points = pw.reg(s0, s1);
		if (null == points) {
			System.out.println("注册失败！");
		} else {
			System.out.println("注册成功！");
			while (true) {
				pw.show(pw.generateFirst());
				System.out.println(Arrays.deepToString(Arrays.asList(points).toArray()));
				System.out.println("请输入密码登录：");
				System.out.println();
				s0 = sc.next();
				if ("b".equalsIgnoreCase(s0) || "exit".equalsIgnoreCase(s0)) {
					break;
				}
				System.out.println(pw.login(s0, points) ? "登录成功！" : "登录失败！");
			}
		}
		sc.close();
		System.out.println("谢谢使用!");
	}
}
