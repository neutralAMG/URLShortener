package com.NeutralAMG.URLShortener.Features.Url

import com.NeutralAMG.URLShortener.Common.Util.AppResult
import com.NeutralAMG.URLShortener.Common.Util.CodeGenerator
import com.NeutralAMG.URLShortener.Features.Url.Dtos.CreateUrlDto
import com.NeutralAMG.URLShortener.Features.Url.Dtos.UrlDto
import com.NeutralAMG.URLShortener.Features.Url.Helpers.UrlMapper
import com.NeutralAMG.URLShortener.Features.Url.Helpers.UrlValidator
import com.NeutralAMG.URLShortener.Features.Url.Interfaces.UrlRepository
import com.NeutralAMG.URLShortener.Features.Url.Interfaces.UrlService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UrlService(private val repository: UrlRepository) : UrlService {

    override fun getAllEntities(): AppResult<List<UrlDto>> {

        return try {
            val urlsGetted: Iterable<UrlDBModel> = repository.findAll()

            AppResult.Success(urlsGetted.map { u -> UrlMapper.mapToDto(u) }.toList(), "Url got successfully")

        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error getting the urls")
        }
    }

    override fun getEntityById(id: Long): AppResult<UrlDto> {

        return try {
            val urlGetted: Optional<UrlDBModel> = repository.findById(id)

            if (urlGetted.isEmpty) {
               return AppResult.Error("No url was found")
            }

            AppResult.Success(UrlMapper.mapToDto(urlGetted.get()))

        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error getting the url")
        }

    }

    override fun saveEntity(saveDto: CreateUrlDto): AppResult<UrlDto> {
        return try {
            val urlValidationResult: AppResult<Nothing> = UrlValidator.validate(saveDto.url);

            if (urlValidationResult.isError) {
                return urlValidationResult
            }

            var newCode: String

            do {
                newCode = CodeGenerator.generateCode()
            } while (repository.existsByCode(newCode))

            val urlToSave: UrlDBModel = UrlDBModel(newCode, saveDto.url!!)

            val savedUrl: UrlDBModel = repository.save(urlToSave)

            AppResult.Success(UrlMapper.mapToDto(savedUrl), "Url was saved correctLy")

        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error saving the url")
        }
    }

    override fun updateEntity(saveDto: CreateUrlDto): AppResult<UrlDto> {
        return try {
            //Make a validator
            if(saveDto.id == null || saveDto.id <= 0L){
               return AppResult.Error("Url id was null or invalid")
            }

            if (saveDto.url.isNullOrEmpty()){
                return AppResult.Error("Url cant be null")
            }

            val urlGetted: Optional<UrlDBModel> = repository.findById(saveDto.id)

            if (urlGetted.isEmpty){

                return AppResult.Error("Url not found")
            }

            val urlToUpdate:UrlDBModel = urlGetted.get()

             urlToUpdate.longUrl = saveDto.url
             repository.save(urlToUpdate)

            AppResult.Success(UrlMapper.mapToDto(urlToUpdate), "Url updated successfully")

        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error updating the url")
        }

    }

    override fun deleteEntity(id: Long): AppResult<Nothing> {
        return try{
            if(id <= 0L){
                return AppResult.Error("Id was invalid")
            }

            repository.deleteById(id)

            AppResult.SuccessWithoutResult("Url deleted successfully")

        }catch (e: Exception){
            e.printStackTrace()
            AppResult.Error("Error deleting the Url")
        }
    }

    override fun findByCode(code: String?): AppResult<UrlDto> {
        return try {
            if(code.isNullOrEmpty()){
                return AppResult.Error("Code cant be null or empty")
            }

            val urlGetted: Optional<UrlDBModel> = repository.findByCode(code)

            if (urlGetted.isEmpty){
                return AppResult.Error("Url not found")
            }

            AppResult.Success(UrlMapper.mapToDto(urlGetted.get()), "Url found")

        }catch (e: Exception){
            e.printStackTrace()
            AppResult.Error("Error finding the url")
        }
    }
}