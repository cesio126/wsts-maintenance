package br.com.rio.maintenance.usefull;

import java.util.UUID;

public class PassFactory {
	public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
