package app.web.couponroll

import android.app.Application
import app.web.couponroll.model.AppContainer
import app.web.couponroll.model.AppDataContainer

class CouponRollApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}