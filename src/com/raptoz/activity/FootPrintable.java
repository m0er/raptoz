package com.raptoz.activity;

import java.util.Date;

import com.raptoz.activity.Activity.Type;
import com.raptoz.user.User;

/**
 * FootPrintable 인터페이스를 해당 도메인에 구현한다는 것은 사용자의 활동 내역을 저장하겠다는 의미와 같다.
 * 즉, 사용자의 최근 활동 내역에 표시되어야 한다면 해당 도메인에 이 인터페이스를 상속한다.
 * 
 * @author mOer
 *
 */
public interface FootPrintable {
	
	/**
	 * 해당 컨텐츠가 생성된 시각
	 * @return
	 */
	Date getCreated();
	
	/**
	 * 해당 컨텐츠를 생성한 사용자.
	 * @return
	 */
	User getOwner();
	
	/**
	 * 해당 도메인이 어떤 타입인지 나타낸다.
	 * @return
	 */
	Type getType();
	
	/**
	 * Activity 도메인을 저장할 때 불필요하거나 중복되는 정보가 있다면 null로 세팅한다.
	 */
	void clear();
	
	/**
	 * 뷰에서 보여줄 컨텐츠. Post라면 타이틀, Reply는 컨텐츠 등
	 * @return
	 */
	String getContentString();
	
}
