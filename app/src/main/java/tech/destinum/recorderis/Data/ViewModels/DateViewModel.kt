package tech.destinum.recorderis.Data.ViewModels

import io.reactivex.Completable
import io.reactivex.Flowable
import tech.destinum.recorderis.Data.Entities.Date
import tech.destinum.recorderis.Data.RecorderisDB
import javax.inject.Inject

class DateViewModel @Inject constructor(private val dB: RecorderisDB){

    fun createDate(date: Date): Completable {
        return Completable.fromAction{ dB.getDateDao().newDate(date)}
    }

    fun getAllDates(): Flowable<List<Date>> = dB.getDateDao().getAll()
}