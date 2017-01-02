package org.ghty826.boot.password;

import java.util.Arrays;
import java.util.Random;

import org.ghty826.boot.util.Point;

public class Password {
	final int size;
	final String[] codes;
	final int codeLength;
	final int width;
	final String widthSpace = "   ";
	final int length;
	final String lengthSpace = " ";
	final String border;
	final String[][] first;
	final String[][] second;

	public Password(String codes) {
		this(3, codes);
	}

	public Password(int size, String codes) {
		this(size, (int) Math.sqrt(Math.pow(codes.length(), 2) / size), codes);
	}

	public Password(int size, int width, String codes) {
		this.size = size;
		this.codes = codes.split("");
		codeLength = this.codes.length;
		this.width = width;
		length = size * width;
		first = new String[width][length];
		second = new String[width][length];
		int borderSize = (length - size) * (lengthSpace.length() + 1) + (size + 1) * widthSpace.length() + size;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < borderSize; ++i) {
			sb.append("~");
		}
		border = sb.toString();
	}

	String[][] generateFirst() {
		int[] rules = new int[codeLength];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < width; ++i) {
			String[] row = first[i];
			for (int j = 0; j < length; ++j) {
				int codesIdx;
				while (codeLength <= rules[codesIdx = random.nextInt(codeLength)])
					;
				++rules[codesIdx];
				row[j] = codes[codesIdx];
			}
		}
		return first;
	}

	String[][] generateSecond() {
		boolean[][] rules = new boolean[codeLength][codeLength];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < width; ++i) {
			String[] row = second[i];
			for (int j = 0; j < length; ++j) {
				int codesIdx;
				int rulesIdx = Arrays.binarySearch(codes, first[i][j]);
				while (rules[rulesIdx][codesIdx = random.nextInt(codeLength)])
					;
				rules[rulesIdx][codesIdx] = true;
				row[j] = codes[codesIdx];
			}
		}
		return second;
	}

	Point[] reg(String s0, String s1) {
		String[] a = s0.split("");
		String[] b = s1.split("");
		int len = a.length;
		Point[] points = new Point[len];
		int lenCount = 0;
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < length; ++j) {
				for (int k = 0; k < len; ++k) {
					if (first[i][j].equals(a[k]) && second[i][j].equals(b[k])) {
						points[k] = new Point(i, j);
						++lenCount;
						break;
					}
				}
			}
		}
		if (lenCount < len) {
			return null;
		}
		return points;
	}

	boolean login(String s, Point[] points) {
		String[] pwd = s.split("");
		int len = points.length;
		for (int k = 0; k < len; ++k) {
			Point point = points[k];
			if (!pwd[k].equals(first[point.x][point.y])) {
				return false;
			}
		}
		return true;
	}

	void show(String[][] code) {
		System.out.println(border);
		for (int i = 0; i < width; ++i) {
			String[] js = code[i];
			for (int j = 0; j < length; ++j) {
				String c = js[j];
				System.out.print(((j + 1) % width == 1 ? widthSpace : lengthSpace) + c);
			}
			System.out.println();
		}
		System.out.println(border);
	}

}
