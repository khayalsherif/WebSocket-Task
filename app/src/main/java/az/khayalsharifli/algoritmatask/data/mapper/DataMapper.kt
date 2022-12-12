package az.khayalsharifli.algoritmatask.data.mapper

import az.khayalsharifli.algoritmatask.model.LocalDto
import az.khayalsharifli.algoritmatask.model.RemoteDto

object DataMapper {
    fun fromRemoteToLocal(remote: RemoteDto.Result): LocalDto {
        return LocalDto(mainSting = remote.`1`)
    }
}