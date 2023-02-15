package dev.aashishtathod.noteit.core.utils.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.presentation.State

@Composable
fun <S : State, VM : BaseViewModel<S>> VM.collectState() = state.collectAsState()
