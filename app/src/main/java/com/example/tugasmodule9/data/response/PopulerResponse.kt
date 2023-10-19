package com.example.tugasmodule9.data.response

import com.google.gson.annotations.SerializedName

data class PopulerResponse(


	val page: Int? = null,
	val results: List<PopulerResponse?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,


)

