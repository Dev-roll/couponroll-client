package app.web.couponroll.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.web.couponroll.CouponRollApplication
import app.web.couponroll.ui.my_coupons.MyCouponsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { MyCouponsViewModel(couponRollApplication().container.tasksRepository) }
    }

}

fun CreationExtras.couponRollApplication(): CouponRollApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CouponRollApplication)