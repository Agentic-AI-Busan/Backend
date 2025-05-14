package hyu.erica.capstone.utils;

import java.util.Map;

public class CategoryImageMapper {

	private static final Map<String, String> imageUrlMap = Map.ofEntries(
			Map.entry("다방", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/dabang.png"),
			Map.entry("한식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/han.png"),
			Map.entry("호프/통닭", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/chicken.jpg"),
			Map.entry("뷔페식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/b.png"),
			Map.entry("회집", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/hui.png"),
			Map.entry("제과점영업", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/cake.png"),
			Map.entry("중국식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/jungsik.png"),
			Map.entry("분식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/bun.png"),
			Map.entry("김밥(도시락)", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/gimbab.jpg"),
			Map.entry("식육(숯불구이)", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/sut.png"),
			Map.entry("커피숍", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/cafe.png"),
			Map.entry("패스트푸드", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/fastfood.jpg"),
			Map.entry("전통찻집", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/cha.png"),
			Map.entry("냉면집", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/nangmen.jpg"),
			Map.entry("일식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/ill.png"),
			Map.entry("양식", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/yang.png"),
			Map.entry("기타", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/default.png"),
			Map.entry("", "https://cdn.jsdelivr.net/gh/msk226/Busan-Image@main/default.png")
	);

	public static String getImageUrl(String categoryName) {
		if (categoryName == null || categoryName.isBlank()) {
			return imageUrlMap.get("기타");
		}
		return imageUrlMap.getOrDefault(categoryName.trim(), imageUrlMap.get("기타"));
	}
}
