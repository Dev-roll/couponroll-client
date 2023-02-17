package app.web.couponroll.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.web.couponroll.CttApplication
import app.web.couponroll.ui.entry.TaskEntryViewModel
import app.web.couponroll.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { TaskEntryViewModel(cttApplication().container.tasksRepository) }

        initializer { HomeViewModel(cttApplication().container.tasksRepository) }
    }

}

fun CreationExtras.cttApplication(): CttApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CttApplication)