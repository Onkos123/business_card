package br.keneitec.dio.cartodevisitas

import android.app.Application
import br.keneitec.dio.cartodevisitas.data.AppDatabase
import br.keneitec.dio.cartodevisitas.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}