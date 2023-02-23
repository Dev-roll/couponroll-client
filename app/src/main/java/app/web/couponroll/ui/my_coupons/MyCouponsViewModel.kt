package app.web.couponroll.ui.my_coupons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.couponroll.model.Task
import app.web.couponroll.model.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyCouponsViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    val myCouponsUiState: StateFlow<MyCouponsUiState> =
        tasksRepository.getAllTasksStream().map { MyCouponsUiState(it) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = MyCouponsUiState()
        )

    fun completeTask(task: Task, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            tasksRepository.completeTask(task)
        } else {
            tasksRepository.activateTask(task)
        }
    }

    fun starTask(task: Task, starred: Boolean) = viewModelScope.launch {
        if (starred) {
            tasksRepository.starTask(task)
        } else {
            tasksRepository.unStarTask(task)
        }
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MyCouponsUiState(val itemList: List<Task> = listOf())
