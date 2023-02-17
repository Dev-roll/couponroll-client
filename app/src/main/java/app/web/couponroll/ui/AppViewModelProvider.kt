package app.web.couponroll.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.web.couponroll.CouponRollApplication
import app.web.couponroll.ui.entry.TaskEntryViewModel
import app.web.couponroll.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { TaskEntryViewModel(couponrollApplication().container.tasksRepository) }

        initializer { HomeViewModel(couponrollApplication().container.tasksRepository) }
    }

}

fun CreationExtras.couponrollApplication(): CouponRollApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CouponRollApplication)