package com.example.tugasmodule9.data.response

import com.google.gson.annotations.SerializedName

data class PopulerResponse(
	val page: Int,
	val results: List<MovieResponse>,
	@SerializedName("total_page")
	val totalPage: Int,
	@SerializedName("total_result")
	val totalResult: Int,
)