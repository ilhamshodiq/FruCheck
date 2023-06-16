package com.the12smb.capstonefrucheck.data.remote.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(

	@field:SerializedName("Accuracy")
	val accuracy: Any? = null,

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Predicted Class")
	val predictedClass: String? = null,

	@field:SerializedName("Freshness")
	val freshness: String? = null,


//
	@field:SerializedName("Error")
	val error: Boolean? = null
)
