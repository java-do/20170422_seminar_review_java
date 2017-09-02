package com.example;

import com.example.values.CurryManner;
import com.example.values.DateOfBirth;
import com.example.values.FullName;
import com.example.values.Gender;

import java.util.Objects;

public class CurryEater {

	private static final String FORMATTER = "----\n氏名：%s\n性別：%s\n年齢：%d（%s生まれ）\nカレーの食べ方：%s";

	private FullName fullName;
	private Gender gender;
	private DateOfBirth dateOfBirth;
	private CurryManner curryManner;

	public CurryEater(String[] args) {
		Objects.requireNonNull(args, "argsがnull.");
		if (args.length != 6) {
			throw new IllegalArgumentException("argsのサイズは必ず6.");
		}
		this.fullName = new FullName(args[0]);
		this.gender = new Gender(Integer.valueOf(args[1]));
		this.dateOfBirth = new DateOfBirth(Integer.valueOf(args[2]),
			Integer.valueOf(args[3]),
			Integer.valueOf(args[4]));
		this.curryManner = new CurryManner(args[5]);
	}

	@Override
	public String toString() {
		return String.format(FORMATTER,
			fullName.toString(),
			gender.toString(),
			dateOfBirth.howOld(),
			dateOfBirth.toString(),
			curryManner.toString());
	}

	public boolean isMinor() {
		return dateOfBirth.isMinor();
	}


}
