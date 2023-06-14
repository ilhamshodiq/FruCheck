package com.the12smb.capstonefrucheck.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailBuahResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class Data(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("penyimpanan")
	val penyimpanan: List<String?>? = null,

	@field:SerializedName("manfaat")
	val manfaat: List<String?>? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nutrisi")
	val nutrisi: List<String?>? = null,

	@field:SerializedName("olahan")
	val olahan: List<String?>? = null,

	@field:SerializedName("id")
	val id: String? = null
)
