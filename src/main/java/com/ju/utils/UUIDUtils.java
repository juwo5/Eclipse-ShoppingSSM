package com.ju.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}
	public static final String IMGS_HOST = "/upload";
}
