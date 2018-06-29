package tech.destinum.recorderis.Data.ViewModels

import io.reactivex.Completable
import io.reactivex.Flowable
import tech.destinum.recorderis.Data.Entities.Date
import tech.destinum.recorderis.Data.RecorderisDB
import javax.inject.Inject

class DateViewModel @Inject constructor(val dB: RecorderisDB){

    fun createDate(name: String, symbol: String, date: String): Completable {
        return Completable.fromAction{ dB.getDateDao().newDate(Date(name, symbol, date))}
    }

}