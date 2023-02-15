package dev.aashishtathod.noteit.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

abstract class BaseViewModel<STATE : State>(initialState: STATE) : ViewModel() {
	
    /**
     * Mutable State of this ViewModel
     */
    private val _state = MutableStateFlow(initialState)
	
    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<STATE> = _state.asStateFlow()
	
    /**
     * Retrieves the current UI state
     */
    val currentState: STATE get() = state.value
	
    /**
     * Updates the state of this ViewModel and returns the new state
     *
     * @param update Lambda callback with old state to calculate a new state
     * @return The updated state
     */
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)
}
