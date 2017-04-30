package com.hastane.myenum;

public enum Cinsiyet {

	ERKEK, KADIN, DIGER;

	public static Cinsiyet getCinsiyet(String scins) {

		for (Cinsiyet cins : Cinsiyet.values()) {
			if (cins.name().equals(scins)) {
				return cins;
			}
			if ("E".equals(scins)) {
				return Cinsiyet.ERKEK;
			} else if ("K".equals(scins)) {
				return Cinsiyet.KADIN;
			} else {
				return DIGER;
			}
		}

		return DIGER;

	}

}
