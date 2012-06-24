package com.raptoz.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class RaptozUtil {
	public static byte[] getBytes(MultipartFile profileImage) {
		byte[] bytes = null;
		try {
			bytes = profileImage.getBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return bytes;
	}
}