package com.quliantrip.qulian.service;

//把私有方法暴露出去
public interface Iservice {

	public void callPlayMusic();
	
	public void callPauseMusic();
	
	public void callRePlayMusic();
	
	public void callSetSeekPosition(int position);

	public void callPlayMusic(String url);

}
