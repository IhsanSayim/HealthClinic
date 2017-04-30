package com.hastane.myenum;

public enum KanGrubu {
	A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE, DIGER;

	public static KanGrubu getKanGrubu(String skan) {

		for (KanGrubu kan : KanGrubu.values()) {
			if (kan.name().equals(skan)) {
				return kan;
			}
			if ("A(+)".equals(skan)) {
				return KanGrubu.A_POSITIVE;
			} else if ("A(-)".equals(skan)) {
				return KanGrubu.A_NEGATIVE;
			} else if ("B(+)".equals(skan)) {
				return KanGrubu.B_POSITIVE;
			} else if ("B(-)".equals(skan)) {
				return KanGrubu.B_NEGATIVE;
			} else if ("AB(+)".equals(skan)) {
				return KanGrubu.AB_POSITIVE;
			} else if ("AB(-)".equals(skan)) {
				return KanGrubu.AB_NEGATIVE;
			} else if ("0(+)".equals(skan)) {
				return KanGrubu.O_POSITIVE;
			} else if ("0(-)".equals(skan)) {
				return KanGrubu.O_NEGATIVE;
			} else {
				return DIGER;
			}
		}

		return DIGER;

	}

}
