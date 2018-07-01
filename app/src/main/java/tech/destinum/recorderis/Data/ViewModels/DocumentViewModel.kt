package tech.destinum.recorderis.Data.ViewModels

import io.reactivex.Completable
import tech.destinum.recorderis.Data.Entities.Document
import tech.destinum.recorderis.Data.RecorderisDB
import javax.inject.Inject

class DocumentViewModel @Inject constructor(private val dB: RecorderisDB){

    fun createDocument(document: Document): Completable{
        return Completable.fromAction{ dB.getDocumentDao().create(document)}
    }

}