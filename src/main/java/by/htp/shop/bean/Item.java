package by.htp.shop.bean;

import java.io.Serializable;

/**
 * Class stored items data Used builder constructor
 */

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private final int id;
	private final String name;
	private final String type;
	private final String description;
	private final String manufacturer;
	private final int price;
	private final String img;
	private final int owner;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getPrice() {
		return price;
	}

	public String getImg() {
		return img;
	}

	public int getOwner() {
		return owner;
	}

	public static class Builder {
		private int id;
		private String name;
		private String type;
		private String description;
		private String manufacturer;
		private int price;
		private String img;
		private int owner;

		public Builder id(int id){
			this.id = id;
			return this;
		}
			
		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder type(String type){
			this.type = type;
			return this;
		}
		
		public Builder description(String description){
			this.description = description;
			return this;
		}
		
		public Builder manufacturer(String manufacturer){
			this.manufacturer = manufacturer;
			return this;
		}
		
		public Builder price(int price){
			this.price = price;
			return this;
		}
		
		public Builder img(String img){
			this.img = img;
			return this;
		}
		
		public Builder owner(int owner){
			this.owner = owner;
			return this;
		}
		
		public Item build() {
			return new Item(this);
		}
	}

	private Item(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.type = builder.type;
		this.description = builder.description;
		this.manufacturer = builder.manufacturer;
		this.price = builder.price;
		this.img = builder.img;
		this.owner = builder.owner;
	}

}
