package by.htp.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class stored clients data
 */

public class ClientData implements Serializable {
	private static final long serialVersionUID = 1L;

	private final int id;
	private final String name;
	private final String surname;
	private final String email;
	private final String phone;
	private final String login;
	private final String password;
	private final String status;
	private List<Integer> cart = new ArrayList<>();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getStatus() {
		return status;
	}

	public int getCartValue(int position) {
		return cart.get(position);
	}

	public void setCartValue(int value) {
		Integer v = value;
		cart.add(v);
	}

	public List<Integer> getCart() {
		return cart;
	}

	public static class Builder {
		private int id;
		private String name;
		private String surname;
		private String email;
		private String phone;
		private String login;
		private String password;
		private String status;
		private List<Integer> cart = new ArrayList<>();

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder surname(String surname) {
			this.surname = surname;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder login(String login) {
			this.login = login;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder cart(List<Integer> cart) {
			this.cart = cart;
			return this;
		}

		public ClientData build() {
			return new ClientData(this);
		}
	}

	private ClientData(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.surname = builder.surname;
		this.email = builder.email;
		this.phone = builder.phone;
		this.login = builder.login;
		this.password = builder.password;
		this.status = builder.status;
		this.cart = builder.cart;
	}
}
