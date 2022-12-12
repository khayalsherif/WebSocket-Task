package az.khayalsharifli.algoritmatask.model


data class RemoteDto(
    val result: List<Result>,
    val total: Int
) {
    data class Result(
        val `0`: String,
        val `1`: String,
        val `2`: String,
        val `3`: String,
        val `4`: String,
        val `5`: String,
        val `6`: Int,
        val `7`: String
    )
}


