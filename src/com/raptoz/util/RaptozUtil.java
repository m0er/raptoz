package com.raptoz.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.raptoz.tag.Tag;

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
	
	public static String getCsvTagValues(List<Tag> tags) {
		String result = "";
		for (Tag tag : tags)
			result += tag.getValue() + ",";
		return result.equals("") ? "" : result.substring(0, result.length() - 1);
	}
	
}