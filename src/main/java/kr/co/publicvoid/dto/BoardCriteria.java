package kr.co.publicvoid.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BoardCriteria {
	@Builder.Default
	private int category = 1;
	@Builder.Default
	private int pageNum = 1;
	@Builder.Default
	private int amount = 10;
	@Builder.Default
	private String type = "";
	@Builder.Default
	private String keyword = "";

	private final TypeDetails[] typeDetails = {
		new TypeDetails("T", "title", "제목"),
		new TypeDetails("C", "content", "내용"),
		new TypeDetails("I", "id", "작성자"),
	};
	
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	private class TypeDetails{
		String shortName;
		String longName;
		String detail;
	}
	
	public String getQueryString() {
		List<String> list = new ArrayList<String>();
		list.add("category="+category);
		list.add("pageNum="+pageNum);
		list.add("amount="+amount);
		list.add("type="+type);
		list.add("keyword="+keyword);
		
		return String.join("&", list);
	}
	
	public String getQueryString2() {
		List<String> list = new ArrayList<String>();
		list.add("amount="+amount);
		list.add("type="+type);
		list.add("keyword="+keyword);
		
		return String.join("&", list);
	}
}




