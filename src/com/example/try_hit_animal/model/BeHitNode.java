package com.example.try_hit_animal.model;

import android.graphics.Bitmap;

import com.example.try_gameengine.framework.Sprite;

public class BeHitNode extends Sprite {
	public enum Type {
		COIN, CAT
	}

	private Type type;
	private int money;
	private boolean isHited;
	private Bitmap beHitedTextture;
	private Bitmap[] beHitedTextturesArray;
	
	public Type getType() {
		return type;
	}
	public int getMoney() {
		return money;
	}
	public boolean isHited() {
		return isHited;
	}
	public Bitmap getBeHitedTextture() {
		return beHitedTextture;
	}
	public Bitmap[] getBeHitedTextturesArray() {
		return beHitedTextturesArray;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void setHited(boolean isHited) {
		this.isHited = isHited;
	}
	public void setBeHitedTextture(Bitmap beHitedTextture) {
		this.beHitedTextture = beHitedTextture;
	}
	public void setBeHitedTextturesArray(Bitmap[] beHitedTextturesArray) {
		this.beHitedTextturesArray = beHitedTextturesArray;
	}
}
