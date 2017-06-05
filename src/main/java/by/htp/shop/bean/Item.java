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
	private final String owner;

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

	public String getOwner() {
		return owner;
	}

	public static class Builder {
		private final int id;
		private final String name;
		private final String type;
		private final String description;
		private final String manufacturer;
		private final int price;
		private final String img;
		private final String owner;

		public Builder(int id, String name, String type, String description, String manufacturer, int price,
				String img, String owner) {
			this.id = id;
			this.name = name;
			this.type = type;
			this.description = description;
			this.manufacturer = manufacturer;
			this.price = price;
			this.img = img;
			this.owner = owner;
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
