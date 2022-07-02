package com.payloads;

import com.pojo.AddAddress_Input_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;

public class PayLoad {
	public AddAddress_Input_Pojo createAddress(String first_name, String last_name, String mobile, String apartment,
			int state, int city, int country, String zipcode, String address, String address_type) {
		AddAddress_Input_Pojo addAddress_Input_pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state, city, country, zipcode, address, address_type);
		return addAddress_Input_pojo;

	}

	public UpdateAddress_Input_Pojo updateAddress(String address_id, String first_name, String last_name,
			String mobile, String apartment, int state, int city, int country, String zipcode, String address,
			String address_type) {

		UpdateAddress_Input_Pojo updateAddress_input_pojo = new UpdateAddress_Input_Pojo(address_id, first_name,
				last_name, mobile, apartment, state, city, country, zipcode, address, address_type);

		return updateAddress_input_pojo;
	}

	public DeleteAddress_Input_Pojo deleteAddress(String address_id) {

		DeleteAddress_Input_Pojo deleteAddress_Input_pojo = new DeleteAddress_Input_Pojo(address_id);

		return deleteAddress_Input_pojo;
	}
}

